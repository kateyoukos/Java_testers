package addresbook.tests;

import addresbook.model.GroupData;
import addresbook.model.Groups;
import com.cedarsoft.serialization.ToString;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.Nonnull;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {


    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException, ParserConfigurationException, SAXException {

        //read data from csv
        /*BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while(line != null){
            String [] split = line.split(";");
            list.add(new Object[] {new GroupData().setName(split[0]).setHeader(split[1]).setFooter(split[2])});
            list.add(new Object[] {new GroupData().setName(split[0]).setHeader(split[1]).setFooter(split[2])});
            line = reader.readLine();
        }*/

        //read data from xml
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document doc = db.parse(new File("src/test/resources/groups.xml"));
        doc.getDocumentElement().normalize();
        NodeList nodeLst=doc.getElementsByTagName("addresbook.model.GroupData");


        List<Object[]> list = new ArrayList<Object[]>();
        for(int je=0;je<nodeLst.getLength();je++) {
            Node fstNode = nodeLst.item(je);
            if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)fstNode;
                NodeList nameNodeList = element.getElementsByTagName("name");
                Element elementName = (Element)nameNodeList.item(0);
                NodeList nameNode = elementName.getChildNodes();
                NodeList headerNodeList = element.getElementsByTagName("header");
                Element elementHeader = (Element)headerNodeList.item(0);
                NodeList headerNode = elementHeader.getChildNodes();
                NodeList footerNodeList = element.getElementsByTagName("footer");
                Element elementFooter = (Element)footerNodeList.item(0);
                NodeList footerNode = elementFooter.getChildNodes();

                System.out.println(
                        ((Node)nameNode.item(0)).getNodeValue()+", "+((Node)headerNode.item(0)).getNodeValue());
                list.add(new Object[] {new GroupData().setName(((Node)nameNode.item(0)).getNodeValue()).setHeader(((Node)headerNode.item(0)).getNodeValue())
                        .setFooter(((Node)footerNode.item(0)).getNodeValue())});
            }
        }

        /*list.add(new Object[] {new GroupData().setName("test1").setHeader("header1").setFooter("footer1")});
        list.add(new Object[] {new GroupData().setName("test2").setHeader("header2").setFooter("footer2")});
        list.add(new Object[] {new GroupData().setName("test3").setHeader("header3").setFooter("footer3")});*/
        return list.iterator();
    }

    @Test(dataProvider = "validGroups", enabled = false)
    public void testGroupCreationTests(GroupData group) {
        app.getNavigationHelper().goToGroupPage();
        //Set<GroupData> before = app.getGroupHelper().getGroupAll();
        Groups before = app.getGroupHelper().getGroupAll();
        //GroupData group = new GroupData().setName(name).setHeader(header).setFooter(footer);
        app.getGroupHelper().createGroup(group);

        //Set<GroupData> after = app.getGroupHelper().getGroupAll();
        Groups after = app.getGroupHelper().getGroupAll();
        //Assert.assertEquals(after.size(), before.size() + 1);
        //Hamcrest - checker
        assertThat(after.size(), equalTo(before.size()+1));


        group.setId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()); //max id
        //before.add(group);

        /*Comparator<? super GroupData> byId = (g1, g2)->Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);*/

        //Assert.assertEquals(before, after);

        //Hamcrest - checker
        assertThat(after, equalTo(
                before.withAdded(group)));
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreationTestsDB(GroupData group) {
        app.getNavigationHelper().goToGroupPage();
        Groups before = app.db().groups();
        app.getGroupHelper().createGroup(group);
        Groups after = app.db().groups();
        //Hamcrest - checker
        assertThat(after.size(), equalTo(before.size()+1));
        group.setId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()); //max id
        //Hamcrest - checker
        assertThat(after, equalTo(before.withAdded(group)));
    }
}