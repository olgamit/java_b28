package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.b28.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        int before  = app.getGroupHelper().getGroupCount();
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test", "abc", "def"));
        //app.getSessionHelper().logout();
        int after  = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before +1);
    }

}