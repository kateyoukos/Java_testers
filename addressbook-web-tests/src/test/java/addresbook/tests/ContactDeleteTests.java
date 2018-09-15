package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.Contacts;
import addresbook.model.GroupData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTests extends TestBase {

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
    public void testDeleteContact(){
        //Set<ContactData> before = app.getContactHelper().getContactAll();
        Contacts before = app.getContactHelper().getContactAll();
        ContactData deletedContact = before.iterator().next();;
        //int index = before.size() - 1;
        app.getContactHelper().deleteContact(deletedContact);
        app.getNavigationHelper().goToHomePage();
        Contacts after = app.getContactHelper().getContactAll();

        Assert.assertEquals(after.size(), before.size()-1);
        //before.remove(deletedContact);
        //Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.without(deletedContact)));

    }




}
