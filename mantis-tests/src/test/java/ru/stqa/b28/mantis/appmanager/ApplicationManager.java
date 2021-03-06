package ru.stqa.b28.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private WebDriver wd;
    private String browser;
    private final Properties properties;
    private SessionHelper sessionHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private UserHelper user;
    private SoapHelper soapHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }


    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public UserHelper user() {
        if (user == null) {
            user = new UserHelper(this);
        }
        return user;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public SoapHelper soap() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.CHROME)) {
                //System.setProperty("webdriver.chrome.driver", "C:\\ProgramData\\Webdrivers\\chromedriver.exe");
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
}
