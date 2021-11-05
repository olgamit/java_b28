package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.b28.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        List<GroupData> before = app.group().getGroupList();
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);
        List<GroupData> after = app.group().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        final Comparator<? super GroupData> byID = Comparator.comparingInt(GroupData::getId);
        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before, after);
    }
}