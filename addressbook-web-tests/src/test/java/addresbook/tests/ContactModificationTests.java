package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.Contacts;
import addresbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0){
            app.getNavigationHelper().goToHomePage();
            app.getContactHelper().createContact(new ContactData()
                    .setFirstname("FirstF").setMiddlename("Middle").setLastname("Last").setCompany("Comp").setMobilePhone("1541")
                    .setWorkPhone("777").setHomePhone("457").setEmail("213213@test.com"));
        }

        /*app.getNavigationHelper().goToHomePage();
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData()
                    .setFirstname("FirstF").setMiddlename("Middle").setLastname("Last").setCompany("Comp").setMobilePhone("1541")
                    .setWorkPhone("777").setHomePhone("457").setEmail("213213@test.com"));
        }*/
    }

    @Test(enabled = false)
    public void testContactModification(){
        Contacts before = app.getContactHelper().getContactAll();
        ContactData modifiedContact = before.iterator().next();
        //int index = before.size() - 1;
        ContactData contact = new ContactData()
                .setId(modifiedContact.getId()).setFirstname("QDSGASDGDSG").setMiddlename("TestMiddle").setLastname("CC").setCompany("Comp")
                .setMobilePhone("4546").setWorkPhone("4567").setHomePhone("1111").setEmail("test@test.com");
        app.getContactHelper().modifyContact(contact);
        app.getNavigationHelper().goToHomePage();
        Contacts after = app.getContactHelper().getContactAll();
        Assert.assertEquals(after.size(), before.size());
        /*
        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);*/
        /*Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);*/
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

    @Test(enabled = true)
    public void testContactModificationDB(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .setId(modifiedContact.getId()).setFirstname("QDSGA333").setMiddlename("TestMiddle").setLastname("CC").setCompany("Comp")
                .setMobilePhone("4546").setWorkPhone("4567").setHomePhone("1111").setEmail("test@test.com");
        app.getContactHelper().modifyContact(contact);
        app.getNavigationHelper().goToHomePage();
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

        verifyContactListInUI();
    }

}