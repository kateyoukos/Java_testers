package addresbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase {

    @Test
    public void deleteContact(){
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeDialogWindow();
        app.getNavigationHelper().goToHomePage();
    }
}
