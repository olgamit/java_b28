package ru.stqa.b28.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;


public class ContactInfoTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (! app.contact().isContactExist()) {
            app.contact()
               .create(new ContactData().withFirstname("Ivan")
                                        .withLastname("Ivanov")
                                        .withNickname("tester")
                                        .withAddress("123456, Tomsk, Lenina str. 121-1")
                                        .withMail("testuser@test.com")
                                        .withMail2("test-user123@gmail.com")
                                        .withMobilePhone("8 923 333 1122")
                                        .withHomePhone("+7 (495) 123 45 67"));
        }
    }

    @Test
    public void testContactInfo() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllMails(), equalTo(mergeMails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                     .stream()
                     .filter((s) -> ! s.equals(""))
                     .map(ContactInfoTests::cleanSymbols)
                     .collect(Collectors.joining("\n"));
    }

    public static String cleanSymbols(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    private String mergeMails(ContactData contact) {
        return Arrays.asList(contact.getMail(), contact.getMail2(), contact.getMail3())
                     .stream()
                     .filter((s) -> ! s.equals(""))
                     .map(ContactInfoTests::cleanSpace)
                     .collect(Collectors.joining("\n"));
    }


    public static String cleanSpace(String str) {
        return str.replaceAll("\\s", "");
    }
}
