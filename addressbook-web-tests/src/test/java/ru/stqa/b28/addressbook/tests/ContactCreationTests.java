package ru.stqa.b28.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;
import ru.stqa.b28.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Ivan")
                                               .withLastname("Ivanov")
                                               .withNickname("tester")
                                               .withTittle("QA engineer")
                                               .withCompany("IT company")
                                               .withMail("testuser@test.com")
                                               .withPhone("8 923 333 1122")
                                               .withBDay("1")
                                               .withBMonth("January")
                                               .withBYear("2001");

        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);
        assertThat(after,
                   equalTo(before.withAdded(contact.withId(after.stream()
                                                                .mapToInt((g) -> g.getId())
                                                                .max()
                                                                .getAsInt()))));
    }
}

