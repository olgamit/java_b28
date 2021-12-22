package ru.stqa.b28.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;
import ru.stqa.b28.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() ==0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withNickname("tester"), false);
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData contactToModify = before.iterator().next();
        ContactData contact = new ContactData().withId(contactToModify.getId())
                                               .withFirstname("Petr")
                                               .withLastname("Petrov")
                                               .withNickname("dev")
                                               .withTittle("developer")
                                               .withCompany("IT company")
                                               .withMail("devuser@test.com")
                                               .withAddress("Tomsk")
                                               .withMobilePhone("8 923 444 5566")
                                               .withBDay("3")
                                               .withBMonth("March")
                                               .withBYear("2001");

        app.contact().modify(contact);
        assertThat(app.contact().getContactCount(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(contactToModify).withAdded(contact)));
    }
}

