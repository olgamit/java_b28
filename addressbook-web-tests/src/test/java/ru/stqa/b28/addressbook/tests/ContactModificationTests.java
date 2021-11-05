package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;

import java.util.*;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (! app.contact().isContactExist()) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withNickname("tester"));
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData contactToModify = before.iterator().next();
        ContactData contact = new ContactData().withId(contactToModify.getId())
                                               .withFirstname("Petr")
                                               .withLastname("Petrov")
                                               .withNickname("dev")
                                               .withTittle("developer")
                                               .withCompany("IT company")
                                               .withMail("devuser@test.com")
                                               .withPhone("8 923 444 5566")
                                               .withBDay("3")
                                               .withBMonth("March")
                                               .withBYear("2001");

        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(contactToModify);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}

