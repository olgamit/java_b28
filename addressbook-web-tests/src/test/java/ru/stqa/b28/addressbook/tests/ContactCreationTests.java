package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;

import java.util.*;


public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreation() throws Exception {
        Set<ContactData> before = app.contact().all();
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
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}

