package addresbook.tests;

import addresbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() {
        app.getNavigationHelper().goToNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Firstaa", "Middle", "Last", "Comp", "4546", "4567", "1111", "qqq@test.com"));
        app.getContactHelper().submitContactCreationForm();
        app.getNavigationHelper().goToHomePage();
    }

}
