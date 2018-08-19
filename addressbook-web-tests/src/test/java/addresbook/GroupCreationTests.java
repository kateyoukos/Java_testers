package addresbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationTests() {
        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test group1", "test Header", "test Footer"));
        submitGroupCreation("submit");
        returnToGroupPage();
    }

}
