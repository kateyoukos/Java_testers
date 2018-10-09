package tests;

import appmanager.ApplicationManager;
import model.Project;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {

    //protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
    //options to run in VMoptions: -ea -Dbrowser=firefox
    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        //app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite
    public void tearDown() throws IOException {
        //app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }


    private boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        System.out.println(app.soap().getIssuesCategories(issueId));
        if (app.soap().getIssuesCategories(issueId).equals("resolved")){
            return true;
        }else{
            return false;
        }
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}