package ru.stqa.b28.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;
import ru.stqa.b28.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size()==0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withNickname("tester"));
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData contactToDelete = before.iterator().next();
        app.contact().delete(contactToDelete);
        assertThat(app.contact().getContactCount(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(contactToDelete)));
    }
}
