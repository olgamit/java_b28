package ru.stqa.b28.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()) {
            app.group().create(new GroupData("test", "abc", "def"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        List<GroupData> before = app.group().getGroupList();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupData> after = app.group().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}

