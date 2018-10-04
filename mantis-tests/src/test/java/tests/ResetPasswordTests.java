package tests;

import model.MailMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ResetPasswordTests extends TestBase {



    @Test
    public void testResetPassword() throws IOException, MessagingException, InterruptedException {
        app.resetPassword().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        String email = String.format("user1@localhost");
        String user =  String.format("user1");
        String password = "password";

        //search specific user in users list
        app.resetPassword().resetPasswordPush(user);


        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.resetPassword().finish(confirmationLink, password);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
