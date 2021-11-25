import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class YandexTest extends WebDriverSettings{

    @Test

    public void test () throws InterruptedException {
        // Открыли Маркет
        driver.get("https://market.yandex.ru/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        TimeUnit.SECONDS.sleep(30); // КОСТЫЛЬ - у меня выскакивает капча - приходится вводить вручную

        // Перешли в Каталог
        driver.findElement(By.xpath("(//*[@data-tid-prop='89fe4462'])[1]")).click();

        // Навели курсор на зоотовары
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@class='_1hPrb cia-cs'])[6]")));
        actions.moveToElement(driver.findElement(By.xpath("(//*[@class='_1hPrb cia-cs'])[6]"))).perform();

        // Переход в раздел лакомства для кошек
        driver.findElement(By.xpath("(//a[text()='Лакомства'])[1]")).click();

        // Фильтр от - до
        WebElement glpricefrom = driver.findElement(By.id("glpricefrom"));
        glpricefrom.sendKeys("50");
        WebElement glpriceto = driver.findElement(By.id("glpriceto"));
        glpriceto.sendKeys("150");
        glpriceto.sendKeys(Keys.ENTER);

        // Фильтр - доставка
        driver.findElement(By.xpath("//span[text()='Доставка курьером']")).click();

        // Фильтр - Деревенские лакомства, Whiskas - нету
        driver.findElement(By.xpath("(//span[text()='Деревенские лакомства'])[1]")).click();

        // Добавляем первый товар списка к сравнению
        actions.moveToElement(driver.findElement(By.xpath("(//img[@data-tid='26287868'])[1]"))).perform();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("(//*[@data-tid='b9e1d6c3'])[2]"))));

        // Сняли галку с Деревенских лакомств
        driver.findElement(By.xpath("(//span[text()='Деревенские лакомства'])[1]")).click();

        // Фильтр - Мнямс
        driver.findElement(By.xpath("(//span[text()='Мнямс'])[1]")).click();

        // Добавляем второй товар списка к сравнению
        actions.moveToElement(driver.findElement(By.xpath("(//img[@data-tid='26287868'])[2]"))).perform();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("(//*[@data-tid='b9e1d6c3'])[3]"))));
        TimeUnit.SECONDS.sleep(10);

        // Перешли в «Сравнение»
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Сравнить']"))).click();

        // Удаление Деревенских лакомств и проверка неотображения
        actions.moveToElement(driver.findElement(By.xpath("(//img[@data-tid='26287868'])[1]"))).perform();
        driver.findElement(By.xpath("(//div[@class='_2bqiO'])[1]")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//a[contains(text(),'Деревенские лакомства')]"))));
        String singleProduct = driver.findElement(By.xpath("//a[@class='_2f75n PzFNv cia-cs']")).getText();
        Assert.assertNotEquals(singleProduct, "Деревенские лакомства");

        // Удалить список
        driver.findElement(By.xpath("//button[@class='_1KpjX _1KU3s']")).click();

        driver.quit();
    }

}
