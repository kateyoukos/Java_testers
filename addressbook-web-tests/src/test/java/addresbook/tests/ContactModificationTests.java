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
            app.getContactHelper().createContact(new ContactData()
                    .setFirstname("FirstF").setMiddlename("Middle").setLastname("Last").setCompany("Comp").setMobilePhone("1541")
                    .setWorkPhone("777").setHomePhone("457").setEmail("213213@test.com"));
        }
    }

    @Test
    public void testContactModification(){
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData()
                .setId(before.get(0).getId()).setFirstname("AA").setMiddlename("Middle").setLastname("CC").setCompany("Comp")
                .setMobilePhone("4546").setWorkPhone("4567").setHomePhone("1111").setEmail("555@test.com");
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
