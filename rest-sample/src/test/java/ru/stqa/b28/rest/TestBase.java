package ru.stqa.b28.rest;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    public Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json"))
                                   .returnContent()
                                   .asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement foundIssues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issues = new Gson().fromJson(foundIssues, new TypeToken<Set<Issue>>() {}.getType());
        Issue issue = issues.iterator().next();
        if (! issue.getState_name().equals("closed")) {
            return true;
        } else {
            return false;
        }
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
                                   .returnContent()
                                   .asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                                                   .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                                                             new BasicNameValuePair("description",
                                                                                    newIssue.getDescription())))
                                   .returnContent()
                                   .asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
