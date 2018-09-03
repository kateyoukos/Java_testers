package addresbook.tests;

import addresbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Fil", "Middle", "Matue", "Comp", "446", "567", "145111", "55777@test.com"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().editContactButton();
        ContactData contact = new ContactData("PFQQo", "Mdle", "True", "Comp", "4546", "4567", "1111", "555@test.com");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(0);
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
