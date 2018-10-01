package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.Contacts;
import addresbook.model.GroupData;
import addresbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.*;

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

    @Test
    public void testContactCreationWithAddToGroupTestsDB() {
        app.getNavigationHelper().goToHomePage();

        Contacts beforeContactList = app.db().contacts();
        Groups beforeGroupList = app.db().groups();

        ContactData selectedContact = beforeContactList.iterator().next();
        Groups groupSelectedForContact = selectedContact.getGroups();
        GroupData selectGroup = beforeGroupList.iterator().next();

        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().addContactToGroup(selectedContact.getId(), selectGroup.getName());
        System.out.println(groupSelectedForContact.withAdded(selectGroup));

        //assertThat(afterGroupList, equalTo(groupSelectedForContact.withAdded(selectGroup)));
        System.out.println(app.db().getContact(selectedContact.getId()).getGroups());
        System.out.println(selectGroup);

        assertThat(app.db().getContact(selectedContact.getId()).getGroups().contains(selectGroup));
        //verifyContactListInUI();
    }

}
