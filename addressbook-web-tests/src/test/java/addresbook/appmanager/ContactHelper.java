package addresbook.appmanager;

import addresbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
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

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void editContactButton() {
        click(By.cssSelector("[title=\"Edit\"]"));
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.cssSelector("[value=\"Delete\"]"));
    }

    public void closeDialogWindow() {
        wd.switchTo().alert().accept();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact){
        goToNewContactPage();
        fillContactForm(contact);
        submitContactCreationForm();

    }
}
