package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.GroupData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() {
        app.getNavigationHelper().goToHomePage();
        Set<ContactData> before = app.getContactHelper().getContactAll();
        ContactData contact = new ContactData().setFirstname("Fred")
                .setMiddlename("Middle").setLastname("Lushin").setCompany("Comp").setMobilePhone("1541").setHomePhone("777").setWorkPhone("457")
                .setEmail("213213@test.com");

        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().goToHomePage();
        Set<ContactData> after = app.getContactHelper().getContactAll();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()); //max id
        before.add(contact);
        /*Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);*/
        Assert.assertEquals(before, after);

    }

}
