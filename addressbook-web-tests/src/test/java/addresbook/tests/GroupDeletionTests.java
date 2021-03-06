package addresbook.tests;

import addresbook.model.GroupData;
import addresbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().createGroup(new GroupData().setName("test group1"));
        }

        /*app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData().setName("test group1"));
        }*/
    }

    @Test(enabled = false)
    public void testGroupDeletion(){
        //Set<GroupData> before = app.getGroupHelper().getGroupAll();
        Groups before = app.getGroupHelper().getGroupAll();
        GroupData deletedGroup = before.iterator().next();
        //int index = before.size()-1;
        app.getGroupHelper().deleteGroup(deletedGroup);
        app.getNavigationHelper().goToGroupPage();
        Groups after = app.getGroupHelper().getGroupAll();
        Assert.assertEquals(after.size(), before.size()-1);
        //before.remove(deletedGroup);
        assertThat(after, equalTo(before.without(deletedGroup)));
        //Assert.assertEquals(before, after);
    }

    @Test(enabled = true)
    public void testGroupDeletionDB(){
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().deleteGroup(deletedGroup);
        app.getNavigationHelper().goToGroupPage();
        Groups after = app.db().groups();
        Assert.assertEquals(after.size(), before.size()-1);
        assertThat(after, equalTo(before.without(deletedGroup)));

        verifyGroupListInUI();
    }
}
