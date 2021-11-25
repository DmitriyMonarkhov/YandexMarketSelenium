import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings {
    protected ChromeDriver driver;
    @Before
    public  void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/macbook/Desktop/Работа/ITFB/chromedriver");
        driver = new ChromeDriver();
        //Размер для окна браузера максимальный
        driver.manage().window().maximize();
    }

    }

