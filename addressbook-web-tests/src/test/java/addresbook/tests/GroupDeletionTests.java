package addresbook.tests;

import addresbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion(){
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test group1", "test Header", "test Footer"));
            }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
    }
}
