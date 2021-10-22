package ru.stqa.b28.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() throws Exception {

        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test", "abc", "def"));
        submitGroupCreation();
        returnToGroupPage();
        logout();
    }

}