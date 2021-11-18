package ru.stqa.b28.addressbook.generators;

import com.beust.jcommander.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.b28.addressbook.model.ContactData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {

        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s\n",
                                       contact.getFirstname(),
                                       contact.getLastname(),
                                       contact.getAddress(),
                                       contact.getMail(),
                                       contact.getMobilePhone()));
        }
        writer.close();

    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("Ivan %s", i))
                                          .withLastname(String.format("Ivanov %s", i))
                                          .withAddress(String.format("123456, Tomsk, Pushkina str, 121-%s", i))
                                          .withMail(String.format("testuser-%s@mail.com", i))
                                          .withMobilePhone(String.format("8 923 123 111%s", i)));
        }
        return contacts;
    }
}

