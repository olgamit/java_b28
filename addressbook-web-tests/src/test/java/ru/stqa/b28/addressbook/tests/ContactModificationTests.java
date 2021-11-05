package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (! app.contact().isContactExist()) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withNickname("tester"));
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contact().getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId())
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

        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(contact);

        final Comparator<? super ContactData> byID = Comparator.comparingInt(ContactData::getId);
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before, after);
    }
}

