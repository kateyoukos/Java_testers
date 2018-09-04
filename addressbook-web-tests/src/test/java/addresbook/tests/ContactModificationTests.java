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
            app.getContactHelper().createContact(new ContactData("SDAGFl", "Middle", "RRRatue", "Comp", "446", "567", "145111", "55777@test.com"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().editContactButton();
        ContactData contact = new ContactData(before.get(0).getId(), "AA", "Middle", "CC", "Comp", "4546", "4567", "1111", "555@test.com");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(0);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        for (int i = 0; i < before.size(); i++){
            System.out.println("end lists: " + before.get(i).getId() + " " + after.get(i).getId());
            System.out.println("end lists: " + before.get(i).getLastname() + " " + after.get(i).getLastname());
            System.out.println("end lists: " + before.get(i).getFirstname() + " " + after.get(i).getFirstname());

            }

        Assert.assertEquals(before, after);


    }
}
