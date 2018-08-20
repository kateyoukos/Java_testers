package addresbook.tests;

import addresbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("First", "Middle",
                    "Last", "Comp", "1541", "777",
                    "457", "213213@test.com"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().editContactButton();
        app.getContactHelper().fillContactForm(new ContactData("FFFF", "Middle", "Last", "Comp", "4546", "4567", "1111", "555@test.com"));
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
    }
}
