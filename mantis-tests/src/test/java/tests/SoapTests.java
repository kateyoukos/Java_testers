package tests;

import model.Issue;
import model.Project;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class SoapTests extends TestBase {

    @Test(enabled = false)
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for(Project project : projects){
            System.out.println(project.getName());
        }
    }

    @Test(enabled = false)
    public void testCreateIssue()throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().setSummary("Test issue")
                .setDescription("Test issue description")
                .setProject(projects.iterator().next());
        Issue createdIssue = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), createdIssue.getSummary());
    }


    @Test(enabled = false)
    public void isIssueOpen1() throws RemoteException, ServiceException, MalformedURLException {
        System.out.println(app.soap().getIssuesCategories(0000001));
        if (app.soap().getIssuesCategories(0000001).equals("resolved")){
            System.out.println("true");

        }else{
            System.out.println("false");

        }
    }



}
