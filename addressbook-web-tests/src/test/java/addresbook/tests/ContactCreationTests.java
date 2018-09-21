package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.Contacts;
import addresbook.model.GroupData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @DataProvider
    public Iterator<Object[]> validContacts(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new ContactData().setFirstname("Fred1")
                .setMiddlename("Middle1").setLastname("Lushin1").setCompany("Comp1").setMobilePhone("1541").setHomePhone("777").setWorkPhone("457")
                .setEmail("2132131@test.com")});
        list.add(new Object[] {new ContactData().setFirstname("Fred2")
                .setMiddlename("Middle2").setLastname("Lushin2").setCompany("Comp2").setMobilePhone("15412").setHomePhone("777").setWorkPhone("457")
                .setEmail("2132132@test.com")});
        list.add(new Object[] {new ContactData().setFirstname("Fred3")
                .setMiddlename("Middle3").setLastname("Lushin3").setCompany("Comp3").setMobilePhone("15413").setHomePhone("777").setWorkPhone("457")
                .setEmail("2132133@test.com")});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreationTests(ContactData contact ) {
        app.getNavigationHelper().goToHomePage();
        //Set<ContactData> before = app.getContactHelper().getContactAll();
        Contacts before = app.getContactHelper().getContactAll();
        //относительный путь
        File photo = new File("src/test/resources/flowers.jpg");
        //ContactData contact = new ContactData().setFirstname("Fred")
         //      .setMiddlename("Middle").setLastname("Lushin").setCompany("Comp").setMobilePhone("1541").setHomePhone("777").setWorkPhone("457")
         //       .setEmail("213213@test.com").setPhoto(photo);

        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().goToHomePage();
        Contacts after = app.getContactHelper().getContactAll();
        //Assert.assertEquals(after.size(), before.size() + 1);

        //Hamcrest - checker - numbers of objects
        assertThat(after.size(), equalTo(before.size() + 1));

        //contact.setId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()); //max id
        //before.add(contact);
        /*Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);*/

        //Assert.assertEquals(before, after);

        //Hamcrest - checker - objects
        assertThat(after, equalTo(
                before.withAdded(contact.setId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

    }

    /*@Test
    public void testCurrentDir(){
        File currentDir = new File(".");
        File photo = new File("src/test/resources/flowers.jpg");
        System.out.println(currentDir.getAbsolutePath());
        System.out.println(photo.exists());
    }*/
}
