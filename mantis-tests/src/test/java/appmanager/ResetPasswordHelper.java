package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordHelper extends HelperBase {

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void resetPasswordPush(String username) {
        openManageUsersPage();
        chooseUser(username);
        clickResetButton();
    }

    private void openManageUsersPage() {
        wd.findElement(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']")).click();
    }

    private void chooseUser(String username) {
        String userSelector = "//a[text()='".concat(username).concat("']");
        wd.findElement(By.xpath(userSelector)).click();
    }

    private void clickResetButton() {
        wd.findElement(By.cssSelector("input[value='Reset Password']")).click();
    }


    public void resetPasswordFinish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
