package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactDeleteTests extends TestBase {

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
    public void testDeleteContact(){
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().deleteContact(index);
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), index);
        before.remove(index);
        Assert.assertEquals(before, after);

    }




}
