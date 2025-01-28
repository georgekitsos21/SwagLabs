package base;

import io.appium.java_client.android.AndroidDriver;
import pages.homepage.productspages.FirstProductsPage;
import pages.homepage.productspages.ThirdProductsPage;
import pages.menupages.GeolocationPage;
import pages.menupages.WebViewPage;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.homepage.HomePage;
import pages.loginpage.LoginPage;
import pages.checkoutpage.CheckOutPage;
import pages.homepage.productspages.ProductsPage;
import pages.homepage.productspages.SecondProductPage;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected static AndroidDriver driver;
    protected static LoginPage loginPage;
    protected static HomePage homePage;
    protected static GeolocationPage geolocationPage;
    protected static WebViewPage webViewPage;
    protected static ProductsPage productsPage;
    protected static FirstProductsPage firstProductsPage;
    protected static SecondProductPage secondProductPage;
    protected static ThirdProductsPage thirdProductsPage;
    protected static CheckOutPage checkOutPage;


    @BeforeClass
    public static void setup() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "15");
        capabilities.setCapability("deviceName", "sdk_gphone64_x86_64");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "com.swaglabsmobileapp");
        capabilities.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        geolocationPage = new GeolocationPage(driver);
        webViewPage = new WebViewPage(driver);
        productsPage = new ProductsPage(driver);
        firstProductsPage = new FirstProductsPage(driver);
        secondProductPage = new SecondProductPage(driver);
        thirdProductsPage = new ThirdProductsPage(driver);
        checkOutPage = new CheckOutPage(driver);
    }
}
