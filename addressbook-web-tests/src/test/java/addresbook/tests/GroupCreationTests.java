package addresbook.tests;

import addresbook.model.GroupData;
import addresbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new GroupData().setName("test1").setHeader("header1").setFooter("footer1")});
        list.add(new Object[] {new GroupData().setName("test2").setHeader("header2").setFooter("footer2")});
        list.add(new Object[] {new GroupData().setName("test3").setHeader("header3").setFooter("footer3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
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
}
