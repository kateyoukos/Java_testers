package addresbook.tests;

import addresbook.model.ContactData;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones(){
        app.getNavigationHelper().goToHomePage();
        ContactData contact = app.getContactHelper().getContactAll().iterator().next();
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        assertThat(contact.getHomePhone(), equalTo(cleanedPhone(contactInfoFromEditForm.getHomePhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleanedPhone(contactInfoFromEditForm.getMobilePhone())));
        assertThat(contact.getWorkPhone(), equalTo(cleanedPhone(contactInfoFromEditForm.getWorkPhone())));
    }

    public String cleanedPhone(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}

