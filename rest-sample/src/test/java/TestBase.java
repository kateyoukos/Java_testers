import org.testng.SkipException;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    /*private boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException {
        System.out.println(app.soap().getIssuesCategories(issueId));

        if (app.soap().getIssuesCategories(issueId).equals("resolved")){
            return true;
        }else{
            return false;
        }
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }*/

}
