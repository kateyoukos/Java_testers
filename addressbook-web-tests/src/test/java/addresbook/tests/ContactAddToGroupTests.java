package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().goToHomePage();
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData()
                    .setFirstname("FirstF").setMiddlename("Middle").setLastname("Last").setCompany("Comp").setMobilePhone("1541")
                    .setWorkPhone("777").setHomePhone("457").setEmail("213213@test.com"));
        }
    }

    @Test(enabled = true)
    public void testContactCreationWithAddToGroupTestsDB(ContactData contact ) {
        app.getNavigationHelper().goToHomePage();
        Contacts before = app.db().contacts();
        // добавить контакт в группу
        //извчлечь группы для контактов ui
        //извчлечь группы для контактов bd
        //сравнить их


        app.getNavigationHelper().goToHomePage();

        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.setId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

        verifyContactListInUI();
    }
}
