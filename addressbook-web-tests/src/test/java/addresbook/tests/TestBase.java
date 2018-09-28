package addresbook.tests;

import addresbook.appmanager.ApplicationManager;
import addresbook.model.ContactData;
import addresbook.model.Contacts;
import addresbook.model.GroupData;
import addresbook.model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    //protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
    //options to run in VMoptions: -ea -Dbrowser=firefox
    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    /*@AfterSuite
    public void tearDown() {
        app.stop();
    }*/

    public void verifyGroupListInUI() {
        //in parameters Edit Configuration -DverifyUI
        if(Boolean.getBoolean("verifyUI")){
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.getGroupHelper().getGroupAll();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g)-> new GroupData().setId(g.getId()).setName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        //in parameters Edit Configuration -DverifyUI
        if(Boolean.getBoolean("verifyUI")){
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.getContactHelper().getContactAll();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((g)-> new ContactData().setId(g.getId()).setFirstname(g.getFirstname()).setLastname(g.getLastname()))
                    .collect(Collectors.toSet())));
        }

    }
}