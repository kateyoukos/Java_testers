package addresbook.appmanager;

import addresbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void editContactButton() {
        click(By.cssSelector("[title=\"Edit\"]"));
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void pressDeleteButton() {
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

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[class]"));
        for(WebElement element: elements) {
            String lastn = element.findElement(By.xpath("td[2]")).getText();
            String firstn = element.findElement(By.xpath("td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().setId(id).setFirstname(firstn).setLastname(lastn);
            contacts.add(contact);
        }
        return contacts;
    }

    public Set<ContactData> getContactAll() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[class]"));
        for(WebElement element: elements) {
            String lastn = element.findElement(By.xpath("td[2]")).getText();
            String firstn = element.findElement(By.xpath("td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().setId(id).setFirstname(firstn).setLastname(lastn);
            contacts.add(contact);
        }
        return contacts;
    }

    public void modifyContact(ContactData contact) {
        selectContact(0);
        editContactButton();
        fillContactForm(contact);
        updateContact();
    }

    public void deleteContact(int index) {
        selectContact(index);
        pressDeleteButton();
        closeDialogWindow();
    }
}
