package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.Contacts;
import addresbook.model.GroupData;
import addresbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.*;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData()
                    .setFirstname("FirstF").setMiddlename("Middle").setLastname("Last").setCompany("Comp").setMobilePhone("1541")
                    .setWorkPhone("777").setHomePhone("457").setEmail("213213@test.com"));
        }

        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData().setName("test group1"));
        }
    }

    @Test
    public void testContactCreationWithAddToGroupDB() {
        Contacts beforeContactList = app.db().contacts();
        Groups beforeGroupList = app.db().groups();

        ContactData selectedContact = beforeContactList.iterator().next();
        Groups groupSelectedForContact = selectedContact.getGroups();
        GroupData selectedGroup = beforeGroupList.iterator().next();

        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().addContactToGroup(selectedContact.getId(), selectedGroup.getName());
        System.out.println(groupSelectedForContact.withAdded(selectedGroup));

        System.out.println(app.db().getContact(selectedContact.getId()).getGroups());
        System.out.println(selectedGroup);

        assertThat(app.db().getContact(selectedContact.getId()).getGroups().contains(selectedGroup), equalTo(true));
        //verifyContactListInUI();
    }
}