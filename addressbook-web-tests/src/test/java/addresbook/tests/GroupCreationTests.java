package addresbook.tests;

import addresbook.model.GroupData;
import addresbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationTests() {
        app.getNavigationHelper().goToGroupPage();
        //Set<GroupData> before = app.getGroupHelper().getGroupAll();
        Groups before = app.getGroupHelper().getGroupAll();
        GroupData group = new GroupData().setName("N1").setHeader("HHHEADER").setFooter("FOOTER");
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
