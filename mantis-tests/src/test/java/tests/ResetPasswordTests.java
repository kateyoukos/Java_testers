package tests;

import appmanager.DbHelper;
import model.MailMessage;
import model.UserData;
import model.Users;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ResetPasswordTests extends TestBase {

    @Test
    public void testResetPassword() throws IOException, MessagingException, InterruptedException {
        app.resetPassword().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        Users users = app.db().users();
        UserData chosenUser = users.iterator().next();
        System.out.println(chosenUser + "" + chosenUser.getId());
        String email = chosenUser.getEmail();
        String user =  chosenUser.getUsername();
        String password = "password";

        //search specific user in users list
        app.resetPassword().resetPasswordPush(user);

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.resetPassword().resetPasswordFinish(confirmationLink, password);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
