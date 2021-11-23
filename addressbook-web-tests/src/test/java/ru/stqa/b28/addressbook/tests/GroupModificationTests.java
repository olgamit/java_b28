package ru.stqa.b28.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.GroupData;
import ru.stqa.b28.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData groupToModify = before.iterator().next();
        GroupData group = new GroupData().withId(groupToModify.getId())
                                         .withName("test1")
                                         .withHeader("test2")
                                         .withFooter("test3");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().getGroupCount(), equalTo(before.size()));
        Groups after = app.db().groups();

        assertThat(after, equalTo(before.without(groupToModify).withAdded(group)));
    }
}
