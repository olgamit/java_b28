package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (! app.contact().isContactExist()) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withNickname("tester"));
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.contact().getContactList();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
