package addresbook.tests;

import addresbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData().setName("test group1"));
        }
    }

    @Test
    public void testGroupDeletion(){
        Set<GroupData> before = app.getGroupHelper().getGroupAll();
        GroupData deletedGroup = before.iterator().next();
        //int index = before.size()-1;
        app.getGroupHelper().deleteGroup(deletedGroup);
        app.getNavigationHelper().goToGroupPage();
        Set<GroupData> after = app.getGroupHelper().getGroupAll();
        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
    }


}
