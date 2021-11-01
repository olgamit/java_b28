package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isContactExist()) {
            app.getContactHelper()
               .createContact(new ContactData("Ivan", "Ivanov", "tester", null, null, null, null, null, null, null));
        }
        List<ContactData> before  = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() -1);
        app.getContactHelper().deleteSelectedContact();
        List<ContactData> after  = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
