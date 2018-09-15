package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<ContactData> before = app.getContactHelper().getContactAll();
        ContactData deletedContact = before.iterator().next();;
        //int index = before.size() - 1;
        app.getContactHelper().deleteContact(deletedContact);
        app.getNavigationHelper().goToHomePage();
        Set<ContactData> after = app.getContactHelper().getContactAll();

        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(deletedContact);
        Assert.assertEquals(before, after);

    }




}
