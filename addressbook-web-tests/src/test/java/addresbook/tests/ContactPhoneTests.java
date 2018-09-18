package addresbook.tests;

import addresbook.model.ContactData;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones(){
        app.getNavigationHelper().goToHomePage();
        ContactData contact = app.getContactHelper().getContactAll().iterator().next();
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        /*assertThat(contact.getHomePhone(), equalTo(cleanedPhone(contactInfoFromEditForm.getHomePhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleanedPhone(contactInfoFromEditForm.getMobilePhone())));
        assertThat(contact.getWorkPhone(), equalTo(cleanedPhone(contactInfoFromEditForm.getWorkPhone())));*/

        //метод обратных проверок
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

    }

    private String mergePhones(ContactData contact) {
        /*String result = "";
        if(contact.getHomePhone() != null){
            result = result + contact.getHomePhone();
        }
        if(contact.getWorkPhone() != null){
            result = result + contact.getWorkPhone();
        }
        if(contact.getMobilePhone() != null){
            result = result + contact.getMobilePhone();
        }
        return result;*/
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleanedPhone)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhone(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}

