package ru.stqa.b28.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType());
        return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testNewContactCreation(ContactData contact) {
        Contacts before = app.db().contacts();
        Groups beforeGroups = app.db().groups();
        app.contact().create(contact, false);
        assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after,
                   equalTo(before.withAdded(contact.withId(after.stream()
                                                                .mapToInt((g) -> g.getId())
                                                                .max()
                                                                .getAsInt()))));
    }
}

