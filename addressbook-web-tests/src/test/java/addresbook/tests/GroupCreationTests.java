package addresbook.tests;

import addresbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationTests() {
        app.getNavigationHelper().goToGroupPage();
        Set<GroupData> before = app.getGroupHelper().getGroupAll();
        GroupData group = new GroupData().setName("N1").setHeader("HHHEADER").setFooter("FOOTER");
        app.getGroupHelper().createGroup(group);

        Set<GroupData> after = app.getGroupHelper().getGroupAll();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.setId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()); //max id
        before.add(group);
        /*Comparator<? super GroupData> byId = (g1, g2)->Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);*/
        Assert.assertEquals(before, after);
    }
}
