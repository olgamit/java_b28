package ru.stqa.b28.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.*;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactToGroupAddingTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact()
               .create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withNickname("tester"), false);
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test"));
        }
    }

    @Test
    public void testAddingContactToGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        Groups groups = app.db().groups();
        Iterator<GroupData> iterator = groups.iterator();
        GroupData group = null;

        if (contact.getGroups().equals(groups)) {
            app.goTo().groupPage();
            group = new GroupData().withName("extraGroup");
            app.group().create(group);
            int extraGroupId = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();
            app.contact().addToGroup(contact, extraGroupId);
        } else {
            while (iterator.hasNext()) {
                group = iterator.next();
                if (! contact.getGroups().contains(group)) {
                    app.contact().addToGroup(contact, group);
                    break;
                }
            }
        }
        app.db().refresh(contact);
        assertThat(contact.getGroups(), hasItem(group));
    }
}
