package ru.stqa.b28.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
                app.getGroupHelper().createGroup(new GroupData("test", "abc", "def"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }
}

