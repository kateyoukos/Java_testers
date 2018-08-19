package addresbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() {
        app.goToNewContactPage();
        app.fillContactForm(new ContactData("Firstaa", "Middle", "Last", "Comp", "4546", "4567", "1111", "qqq@test.com"));
        app.submitContactCreationForm();
        app.goToHomePage();
    }

}
