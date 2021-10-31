package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreation() throws Exception {
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper()
           .createContact(new ContactData("Ivan",
                                          "Ivanov",
                                          "tester",
                                          "QA engineer",
                                          "IT company",
                                          "testuser@test.com",
                                          "8 923 333 1122",
                                          "1",
                                          "January",
                                          "2001"));
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }

}

