package addresbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationTests() {
        app.goToGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test group1", "test Header", "test Footer"));
        app.submitGroupCreation("submit");
        app.returnToGroupPage();
    }

}
