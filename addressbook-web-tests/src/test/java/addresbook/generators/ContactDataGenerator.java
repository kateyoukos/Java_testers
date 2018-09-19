package addresbook.generators;

import addresbook.model.ContactData;
import addresbook.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
        }catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();

        /*int count = Integer.parseInt((args[0]));
        File file = new File(args[1]);
        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);*/
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(ContactData contact : contacts){
            writer.write(String.format("%s;%s\n", contact.getFirstname(), contact.getLastname()));
        }
        writer.close();
    }

    private List<ContactData> generateContacts(int count){
        List<ContactData> contacts = new ArrayList<ContactData>();
        for(int i = 0; i < count; i++){
            contacts.add(new ContactData().setFirstname(String.format("First %s", i)).setLastname(String.format("Last %s", i)));
        }
        return contacts;
    }
}
