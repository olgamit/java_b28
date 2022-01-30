package ru.stqa.b28.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.b28.mantis.model.*;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private ApplicationManager app;
    private String username;
    private String password;
    private String soapURL;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
        this.soapURL = app.getProperty("soap.baseUrl");
        this.username = app.getProperty("web.adminLogin");
        this.password = app.getProperty("web.adminPassword");
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().getMantisConnectPort(new URL(soapURL));
    }

    public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible(username, password);
        return Arrays.asList(projects)
                     .stream()
                     .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                     .collect(Collectors.toSet());
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(username,
                                                           password,
                                                           BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()),
                                           issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(username, password, issueData);
        IssueData createdIssueData = mc.mc_issue_get(username, password, issueId);
        return new Issue().withId(createdIssueData.getId().intValue())
                          .withSummary(createdIssueData.getSummary())
                          .withDescription(createdIssueData.getDescription())
                          .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                                    .withName(createdIssueData.getProject().getName()));
    }

    public Set<Issue> getIssuesForProject() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        Set<Project> projects = getProjects();
        Project someProject = projects.iterator().next();
        IssueData[] issues = mc.mc_project_get_issues(username,
                                                      password,
                                                      BigInteger.valueOf(someProject.getId()),
                                                      BigInteger.valueOf(0),
                                                      BigInteger.valueOf(- 1));
        return Arrays.asList(issues)
                     .stream()
                     .map((i) -> new Issue().withId(i.getId().intValue())
                                            .withSummary(i.getSummary())
                                            .withDescription(i.getDescription())
                                            .withStatus(new Status().withId(i.getStatus().getId().intValue())
                                                                    .withName(i.getStatus().getName())))
                     .collect(Collectors.toSet());
    }

    public Issue getIssueById(int id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData foundIssue = mc.mc_issue_get(username, password, BigInteger.valueOf(id));

        return new Issue().withId(foundIssue.getId().intValue())
                          .withSummary(foundIssue.getSummary())
                          .withDescription(foundIssue.getDescription())
                          .withStatus(new Status().withId(foundIssue.getStatus().getId().intValue())
                                                  .withName(foundIssue.getStatus().getName()));
    }
}
