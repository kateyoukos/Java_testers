package addresbook.tests;

import addresbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().goToHomePage();
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("First", "Middle",
                    "Last", "Comp", "1541", "777",
                    "457", "213213@test.com"));
        }
    }

    @Test
    public void testContactModification(){
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData(before.get(0).getId(), "AA", "Middle", "CC", "Comp", "4546", "4567", "1111", "555@test.com");
        app.getContactHelper().modifyContact(contact);
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(0);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }


}
