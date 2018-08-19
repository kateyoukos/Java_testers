package addresbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() {
        goToNewContactPage();
        fillContactForm(new ContactData("Firstaa", "Middle", "Last", "Comp", "4546", "4567", "1111", "qqq@test.com"));
        submitContactCreationForm();
        goToHomePage();
    }

}
