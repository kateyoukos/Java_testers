package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.GroupData;
import org.openqa.selenium.By;
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
        ContactData contact = new ContactData("Leon", "Middle",
                "Lushin", "Comp", "1541", "777",
                "457", "213213@test.com");

        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        for(int i = 0; i<before.size(); i++){
            System.out.println(before.get(i).getLastname());
            System.out.println(before.get(i).getFirstname());
        }

        for(int i = 0; i<after.size(); i++){
            System.out.println(after.get(i).getLastname());
            System.out.println(after.get(i).getFirstname());
        }

        Assert.assertEquals(before, after);

    }

}
