package addresbook.tests;

import addresbook.model.GroupData;
import addresbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData().setName("test group1"));
        }
    }

    @Test
    public void testGroupModification(){
        //Set<GroupData> before = app.getGroupHelper().getGroupAll();
        Groups before = app.getGroupHelper().getGroupAll();
        GroupData modifiedGroup = before.iterator().next();
        //int index = before.size() - 1;
        GroupData group = new GroupData()
                .setId(modifiedGroup.getId()).setName("GR78").setHeader("test Header78").setFooter("test Footer78");
        app.getGroupHelper().modifyGroup(group);
        Groups after = app.getGroupHelper().getGroupAll();
        Assert.assertEquals(after.size(), before.size());
        /*before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);*/

        /*Comparator<? super GroupData> byId = (g1, g2)->Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);*/

        System.out.println("After: " + after);
        System.out.println("Before: " + before.without(modifiedGroup).withAdded(group));
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        }
}