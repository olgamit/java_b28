package ru.stqa.b28.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreation() throws Exception {
        app.addNewContact();
        app.fillContactInfo(new ContactData("Ivan",
                                            "Ivanov",
                                            "tester",
                                            "QA engineer",
                                            "IT company",
                                            "testuser@test.com",
                                            "8 923 333 1122",
                                            "1",
                                            "January",
                                            "2001"));
        app.viewContactDetails();
    }

}

