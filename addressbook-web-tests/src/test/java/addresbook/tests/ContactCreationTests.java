package addresbook.tests;

import addresbook.model.ContactData;
import addresbook.model.Contacts;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException, SAXException, ParserConfigurationException {
        List<Object[]> list = new ArrayList<Object[]>();
        /*list.add(new Object[] {new ContactData().setFirstname("Fred1")
                .setMiddlename("Middle1").setLastname("Lushin1").setCompany("Comp1").setMobilePhone("1541").setHomePhone("777").setWorkPhone("457")
                .setEmail("2132131@test.com")});
        list.add(new Object[] {new ContactData().setFirstname("Fred2")
                .setMiddlename("Middle2").setLastname("Lushin2").setCompany("Comp2").setMobilePhone("15412").setHomePhone("777").setWorkPhone("457")
                .setEmail("2132132@test.com")});
        list.add(new Object[] {new ContactData().setFirstname("Fred3")
                .setMiddlename("Middle3").setLastname("Lushin3").setCompany("Comp3").setMobilePhone("15413").setHomePhone("777").setWorkPhone("457")
                .setEmail("2132133@test.com")});*/
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document doc = db.parse(new File("src/test/resources/contacts.xml"));
        doc.getDocumentElement().normalize();
        NodeList nodeLst=doc.getElementsByTagName("addresbook.model.ContactData");

        for(int i = 0; i < nodeLst.getLength(); i++) {
            Node fstNode = nodeLst.item(i);
            if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)fstNode;
                NodeList nameNodeList = element.getElementsByTagName("firstname");
                Element elementName = (Element)nameNodeList.item(0);
                NodeList nameNode = elementName.getChildNodes();

                NodeList middleNameNodeList = element.getElementsByTagName("middlename");
                Element elementMiddleName = (Element)middleNameNodeList.item(0);
                NodeList middleNameNode = elementMiddleName.getChildNodes();

                NodeList lastNameNodeList = element.getElementsByTagName("lastname");
                Element elementLastName = (Element)lastNameNodeList.item(0);
                NodeList lastNameNode = elementLastName.getChildNodes();

                NodeList companyNodeList = element.getElementsByTagName("company");
                Element elementCompany = (Element)companyNodeList.item(0);
                NodeList companyNode = elementCompany.getChildNodes();

                NodeList homeNodeList = element.getElementsByTagName("homePhone");
                Element elementHomeName = (Element)homeNodeList.item(0);
                NodeList homeNode = elementHomeName.getChildNodes();

                NodeList mobileNodeList = element.getElementsByTagName("mobilePhone");
                Element elementMobileName = (Element)mobileNodeList.item(0);
                NodeList mobileNode = elementMobileName.getChildNodes();

                NodeList workNodeList = element.getElementsByTagName("workPhone");
                Element elementWorkName = (Element)workNodeList.item(0);
                NodeList workNode = elementWorkName.getChildNodes();

                NodeList emailNodeList = element.getElementsByTagName("email");
                Element elementEmailName = (Element)emailNodeList.item(0);
                NodeList emailNode = elementEmailName.getChildNodes();

                list.add(new Object[] {new ContactData().setFirstname(((Node)nameNode.item(0)).getNodeValue())
                        .setMiddlename(((Node)middleNameNode.item(0)).getNodeValue())
                        .setLastname(((Node)lastNameNode.item(0)).getNodeValue())
                        .setCompany(((Node)companyNode.item(0)).getNodeValue())
                        .setHomePhone(((Node)homeNode.item(0)).getNodeValue())
                        .setMobilePhone(((Node)mobileNode.item(0)).getNodeValue())
                        .setWorkPhone(((Node)workNode.item(0)).getNodeValue())
                        .setEmail(((Node)emailNode.item(0)).getNodeValue())});
            }
        }
        return list.iterator();
    }

    @Test(dataProvider = "validContacts", enabled = false)
    public void testContactCreationTests(ContactData contact ) {
        app.getNavigationHelper().goToHomePage();
        //Set<ContactData> before = app.getContactHelper().getContactAll();
        Contacts before = app.getContactHelper().getContactAll();
        //относительный путь
        File photo = new File("src/test/resources/flowers.jpg");
        //ContactData contact = new ContactData().setFirstname("Fred")
         //      .setMiddlename("Middle").setLastname("Lushin").setCompany("Comp").setMobilePhone("1541").setHomePhone("777").setWorkPhone("457")
         //       .setEmail("213213@test.com").setPhoto(photo);

        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().goToHomePage();
        Contacts after = app.getContactHelper().getContactAll();
        //Assert.assertEquals(after.size(), before.size() + 1);

        //Hamcrest - checker - numbers of objects
        assertThat(after.size(), equalTo(before.size() + 1));

        //contact.setId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()); //max id
        //before.add(contact);
        /*Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);*/

        //Assert.assertEquals(before, after);

        //Hamcrest - checker - objects
        assertThat(after, equalTo(
                before.withAdded(contact.setId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

    }

    @Test(dataProvider = "validContacts", enabled = true)
    public void testContactCreationTestsDB(ContactData contact ) {
        app.getNavigationHelper().goToHomePage();
        Contacts before = app.db().contacts();
        //относительный путь
        File photo = new File("src/test/resources/flowers.jpg");
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().goToHomePage();
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.setId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

        verifyContactListInUI();
    }



    /*@Test
    public void testCurrentDir(){
        File currentDir = new File(".");
        File photo = new File("src/test/resources/flowers.jpg");
        System.out.println(currentDir.getAbsolutePath());
        System.out.println(photo.exists());
    }*/
}
