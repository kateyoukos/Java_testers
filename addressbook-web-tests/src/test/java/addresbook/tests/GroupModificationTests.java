package addresbook.tests;

import addresbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test group1", "test Header", "test Footer"));
        }
    }

    @Test
    public void testGroupModification(){
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "GR1", "test Header", "test Footer");
        app.getGroupHelper().modifyGroup(before, group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(group);

        Comparator<? super GroupData> byId = (g1, g2)->Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }


}
