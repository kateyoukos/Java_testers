package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.Contacts;
import addresbook.model.GroupData;
import addresbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact() ) {
            app.getContactHelper().createContact(new ContactData()
                .setFirstname("FirstF").setMiddlename("Middle").setLastname("Last").setCompany("Comp").setMobilePhone("1541")
                .setWorkPhone("777").setHomePhone("457").setEmail("213213@test.com"));


        }
        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData().setName("test group1"));
        }
        else{
                Contacts beforeContactList = app.db().contacts();
                ContactData selectedContact = beforeContactList.iterator().next();
                Groups beforeGroupList = app.db().groups();
                GroupData selectedGroup = beforeGroupList.iterator().next();
                app.getContactHelper().addContactToGroup(selectedContact.getId(), selectedGroup.getName());
            }
        }

    @Test
    public void testContactRemoveFromGroupDB() {
        Contacts beforeContactList = app.db().contacts();
        Groups beforeGroupList = app.db().groups();
        ContactData selectedContact = beforeContactList.iterator().next();
        GroupData selectedGroup = beforeGroupList.iterator().next();
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().deleteContactFromGroup(selectedContact.getId(), selectedGroup.getName());
        assertThat(app.db().getContact(selectedContact.getId()).getGroups().contains(selectedGroup), equalTo(false));

        //verifyContactListInUI();
    }
}
