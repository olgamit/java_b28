package ru.stqa.b28.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.*;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactFromGroupRemovalTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().gotoHomePage();
            app.contact()
               .create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withNickname("tester"), false);
        }

        if (app.db().groups().stream().allMatch(g -> g.getContacts().isEmpty()))
        {
            app.contact().addToGroup(app.db().contacts().iterator().next(), app.db().groups().iterator().next());
        }
    }

    @Test
    public void testRemovalContactFromGroup() {
        Contacts contacts = app.db().contacts();
        Iterator<ContactData> iterator = contacts.iterator();
        ContactData contact = null;
        GroupData group = null;

        while (iterator.hasNext()) {
            contact = iterator.next();
            if (! contact.getGroups().isEmpty()) {
                group = contact.getGroups().iterator().next();
                app.contact().removeFromGroup(contact, group);
                break;
            }
        }
        app.db().refresh(contact);
        assertThat(contact.getGroups(), not(hasItem(group)));
    }
}
