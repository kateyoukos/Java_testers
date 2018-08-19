package addresbook.appmanager;

import addresbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelper extends HelperBase {

    private FirefoxDriver wd;

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
    }

    public void submitContactCreationForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }
}
