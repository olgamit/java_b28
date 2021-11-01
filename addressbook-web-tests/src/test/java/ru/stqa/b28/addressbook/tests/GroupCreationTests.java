package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.b28.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test", "abc", "def");
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        final Comparator<? super GroupData> byID = Comparator.comparingInt(GroupData::getId);
        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before, after);
    }
}