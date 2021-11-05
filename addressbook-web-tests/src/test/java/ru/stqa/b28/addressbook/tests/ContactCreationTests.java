package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;

import java.util.*;


public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreation() throws Exception {
        List<ContactData> before = app.contact().getContactList();
        ContactData contact = new ContactData("Ivan",
                                              "Ivanov",
                                              "tester",
                                              "QA engineer",
                                              "IT company",
                                              "testuser@test.com",
                                              "8 923 333 1122",
                                              "1",
                                              "January",
                                              "2001");
        app.contact().create(contact);
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);


        before.add(contact);
        final Comparator<? super ContactData> byID = Comparator.comparingInt(ContactData::getId);
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before, after);
    }
}

