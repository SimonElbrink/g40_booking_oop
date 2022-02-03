package se.lexicon;


import se.lexicon.data.*;
import se.lexicon.data.interfaces.*;
import se.lexicon.io.JsonManager;

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
