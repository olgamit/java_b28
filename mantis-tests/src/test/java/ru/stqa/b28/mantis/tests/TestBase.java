package ru.stqa.b28.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.b28.mantis.appmanager.ApplicationManager;
import ru.stqa.b28.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",
                                                                                              BrowserType.CHROME));

    boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        Issue issue = app.soap().getIssueById(issueId);
        if (! issue.getStatus().getName().equals("closed")) {
            return true;
        } else {
            return false;
        }
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

}
