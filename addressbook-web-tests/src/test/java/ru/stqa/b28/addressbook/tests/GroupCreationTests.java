package ru.stqa.b28.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.b28.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test", "abc", "def"));
        app.submitGroupCreation();
        app.returnToGroupPage();
        app.logout();
    }

}