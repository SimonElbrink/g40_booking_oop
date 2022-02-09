package se.lexicon;


import se.lexicon.data.*;
import se.lexicon.data.interfaces.*;
import se.lexicon.data.jdbc.ContactInfoDAOJdbcImpl;
import se.lexicon.io.JsonManager;
import se.lexicon.model.ContactInfo;
import se.lexicon.model.UserCredentials;

import java.io.File;

import static se.lexicon.io.URLConstants.*;

public class App
{
    public static void main( String[] args ){

        AddressDAO addressDAO = AddressDAOIMPL.getInstance();

        BookingDAO bookingDAO = BookingDAOIMPL.getInstance();

        ContactInfoDAO contactInfoDAO = ContactInfoDAOImpl.getInstance();

        PatientDAO patientDAO = PatientDAOIMPL.getInstance();

        PremisesDAO premisesDAO = PremisesDAOIMPL.getInstance();

        UserCredentialsDAO userCredentialsDAO  = UserCredentialsDAOIMPL.getINSTANCE();
        UserCredentials userCredentials = new UserCredentials("simonelbrink", "secretPassword","SUPER_ADMIN");
        userCredentialsDAO.create(userCredentials);


        ContactInfoDAO contactInfoDAOJdbc = new ContactInfoDAOJdbcImpl();


        contactInfoDAOJdbc.create(new ContactInfo("01234", "info@lexicon.se", "123456789"));
        contactInfoDAOJdbc.create(new ContactInfo("1", "support@lexicon.se", "0987654321"));

        System.out.println("finding all contact infos");
        contactInfoDAOJdbc.findAll().forEach(System.out::println);

        System.out.println("Contact_info found by id: "+ contactInfoDAOJdbc.findById("01234"));

        contactInfoDAOJdbc.delete("1");
        contactInfoDAOJdbc.delete("01234");

        shutdown(); // Serialize just Before exiting program.
    }

    /**
     * Save all Objects from Data Access Objects in to json files found in "src/main/resources/json" directory.
     */
    public static void shutdown(){
        JsonManager.getInstance().serializeToJson(AddressDAOIMPL.getInstance().findAll(),new File(ADDRESS_JSON));
        JsonManager.getInstance().serializeToJson(BookingDAOIMPL.getInstance().findAll(), new File(BOOKING_JSON));
        JsonManager.getInstance().serializeToJson(PatientDAOIMPL.getInstance().findAll(), new File(PATIENTS_JSON));
        JsonManager.getInstance().serializeToJson(ContactInfoDAOImpl.getInstance().findAll(), new File(CONTACT_INFO_JSON));
        JsonManager.getInstance().serializeToJson(PremisesDAOIMPL.getInstance().findAll(), new File(PREMISES_JSON));
        JsonManager.getInstance().serializeToJson(UserCredentialsDAOIMPL.getINSTANCE().findAll(), new File(CREDENTIALS_JSON));
    }
}
