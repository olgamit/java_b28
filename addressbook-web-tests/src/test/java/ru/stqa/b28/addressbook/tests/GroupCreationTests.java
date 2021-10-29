package ru.stqa.b28.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.b28.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test", "abc", "def"));
        app.getSessionHelper().logout();
    }

}