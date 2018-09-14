package addresbook.tests;

import addresbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size()-1;
        app.getGroupHelper().deleteGroup(index);
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
