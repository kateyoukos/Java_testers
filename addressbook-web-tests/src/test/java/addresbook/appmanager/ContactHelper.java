package addresbook.appmanager;

import addresbook.model.ContactData;
import addresbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
        //attach(By.name("photo"), contactData.getPhoto());
    }

    public void submitContactCreationForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    /*private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }*/

    //new
    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

    public void pressAddtoGroupButton() {
        click(By.name("add"));
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

    /*public Set<ContactData> getContactAll() {
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
    }*/

    public Contacts getContactAll() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[class]"));
        for(WebElement element: elements) {
            String lastname = element.findElement(By.xpath("td[2]")).getText();
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            String allPhones = element.findElement(By.xpath("td[6]")).getText();
            String allEmails = element.findElement(By.xpath("td[5]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            //String[] phones = allPhones.split("\n");
            //ContactData contact = new ContactData().setId(id).setFirstname(firstname).setLastname(lastname)
            //        .setHomePhone(phones[0]).setMobilePhone(phones[1]).setWorkPhone(phones[2]);
            //contacts.add(contact);

            //метод обратных проверок
            contacts.add(new ContactData().setId(id).setFirstname(firstname).setLastname(lastname)
                    .setAllPhones(allPhones).setAddress(address).setAllEmails(allEmails));
        }
        return contacts;
    }

    public void modifyContact(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        updateContact();
    }

    public void deleteContact(int index) {
        selectContact(index);
        pressDeleteButton();
        closeDialogWindow();
    }

    public void deleteContact(ContactData contact) {
        selectContactById(contact.getId());
        pressDeleteButton();
        closeDialogWindow();
    }

    public void addContactToGroup(int id, String groupName) {
        selectContactById(id);
        selectGroupFromList(groupName);
        pressAddtoGroupButton();
    }

    private void selectGroupFromList(String groupName) {
        wd.findElement(By.name("to_group")).click();
        String xpathCorrect = "//*[@name='to_group']/option[text()='".concat(groupName).concat("']") ;
        wd.findElement(By.xpath(xpathCorrect )).click();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email =  wd.findElement(By.name("email")).getAttribute("value");
        String email2 =  wd.findElement(By.name("email2")).getAttribute("value");
        String email3 =  wd.findElement(By.name("email3")).getAttribute("value");
        String address =  wd.findElement(By.name("address")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().setId(contact.getId()).setFirstname(firstname).setLastname(lastname)
                .setHomePhone(home).setMobilePhone(mobile).setWorkPhone(work).setAddress(address).setEmail(email).setEmail2(email2).setEmail3(email3);

    }

    /*private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../..")); // .. means to parent attr

        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }*/

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" +  id + "']")).click();
        }


    public void deleteContactFromGroup(int id, String groupName) {
        selectGroup(groupName);
        selectContactById(id);
        removeContact();
    }

    private void selectGroup(String groupName) {
        String xpathCorrect = "//*[@name='group']/option[text()='".concat(groupName).concat("']") ;
        wd.findElement(By.xpath(xpathCorrect )).click();
    }

    private void removeContact() {
        click(By.name("remove"));
    }
}
