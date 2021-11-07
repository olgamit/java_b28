package ru.stqa.b28.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.GroupData;
import ru.stqa.b28.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName("test"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        Groups before = app.group().all();
        GroupData groupToDelete = before.iterator().next();
        app.group().delete(groupToDelete);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(groupToDelete)));
    }
}

