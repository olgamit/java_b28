package ru.stqa.b28.addressbook;

import org.testng.annotations.Test;


public class ContactCreationTests extends TestBase{

    @Test
    public void testNewContactCreation() throws Exception {
        addNewContact();
        fillContactInfo(new ContactData("Ivan",
                                        "Ivanov",
                                        "tester",
                                        "QA engineer",
                                        "IT company",
                                        "testuser@test.com",
                                        "8 923 333 1122",
                                        "1",
                                        "January",
                                        "2001"));
        viewContactDetails();
    }

}

