package SeleniumCourse.TaskCheckConfig.HomeWorkDay4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductFiltering7 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
        driver.manage().window().maximize();
        WebElement priceFilter = driver.findElement(By.xpath("//input[@name='price'][@value='1-2']"));
        priceFilter.click();

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException var11) {
            var11.printStackTrace();
        }

        WebElement productsContainer = driver.findElement(By.xpath("//div[@id='js-product-list']/div[@class='products row']"));
        int displayedProductsCount = productsContainer.findElements(By.xpath(".//div[@class='product-description']")).size();
        boolean allProductsPassFilter = true;

        for(int i = 1; i <= displayedProductsCount; ++i) {
            WebElement productPrice = driver.findElement(By.xpath("(//div[@class='product-description'])[" + i + "]//span[@class='price']"));
            String priceText = productPrice.getText();
            priceText = priceText.replaceAll("[^\\d.]", "");
            double price = Double.parseDouble(priceText);
            if (price < 1.0 || price > 2.0) {
                allProductsPassFilter = false;
                System.out.println("Produkt " + i + " nie spełnia warunku filtra cenowego.");
            }
        }

        if (allProductsPassFilter) {
            System.out.println("Wszystkie wyświetlone produkty spełniają warunek filtra cenowego.");
        } else {
            System.out.println("Nie wszystkie wyświetlone produkty spełniają warunek filtra cenowego.");
        }

    }
}

