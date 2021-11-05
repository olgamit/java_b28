package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName("test"));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().getGroupList();
        int index = before.size() - 1;
        GroupData group = new GroupData().withId(before.get(index).getId())
                                         .withName("test1")
                                         .withHeader("test2")
                                         .withFooter("test3");
        app.group().modify(index, group);
        List<GroupData> after = app.group().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);

        final Comparator<? super GroupData> byID = Comparator.comparingInt(GroupData::getId);
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before, after);
    }
}
