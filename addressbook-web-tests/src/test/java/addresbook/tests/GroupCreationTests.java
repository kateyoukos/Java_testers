package addresbook.tests;

import addresbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationTests() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test group1", "test Header", "test Footer"));
        app.getGroupHelper().submitGroupCreation("submit");
        app.getGroupHelper().returnToGroupPage();
    }

}
