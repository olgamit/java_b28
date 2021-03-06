package ru.stqa.b28.mantis.tests;


import org.testng.annotations.Test;
import ru.stqa.b28.mantis.model.Issue;
import ru.stqa.b28.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;


public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue")
                                 .withDescription("Test issue description")
                                 .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }

    @Test
    public void testIssueStatus() throws MalformedURLException, ServiceException, RemoteException {
        Set<Issue> issues = app.soap().getIssuesForProject();
        Issue issue = issues.iterator().next();
        skipIfNotFixed(issue.getId());
        System.out.println(issue.getStatus().getName());
    }
}
