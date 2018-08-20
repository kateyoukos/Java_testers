package addresbook.tests;

import addresbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase {

    @Test
    public void testDeleteContact(){
        app.getNavigationHelper().goToHomePage();
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("First", "Middle",
                    "Last", "Comp", "1541", "777",
                    "457", "213213@test.com"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeDialogWindow();
        app.getNavigationHelper().goToHomePage();
    }
}
