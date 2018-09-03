package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("AAA", "Middle",
                "TFF", "Comp", "1541", "777",
                "457", "213213@test.com");

        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);
        Comparator<? super ContactData> byLastName = (c1, c2)-> c1.getLastname().compareTo(c2.getLastname());
        before.sort(byLastName);
        after.sort(byLastName);
        Comparator<? super ContactData> byFirstName = (c1, c2)-> c1.getFirstname().compareTo(c2.getFirstname());;
        before.sort(byFirstName);
        after.sort(byFirstName);
        Assert.assertEquals(before, after);

    }

}
