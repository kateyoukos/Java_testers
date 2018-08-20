package addresbook.tests;

import addresbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() {
        app.getContactHelper().createContact(new ContactData("First", "Middle",
                "Last", "Comp", "1541", "777",
                "457", "213213@test.com"));
        app.getNavigationHelper().goToHomePage();
    }

}
