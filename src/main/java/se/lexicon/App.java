package se.lexicon;


import se.lexicon.data.*;
import se.lexicon.data.interfaces.*;
import se.lexicon.data.jdbc.*;
import se.lexicon.io.JsonManager;
import se.lexicon.model.*;

import java.io.File;
import java.util.Optional;

import static se.lexicon.io.URLConstants.*;

public class App
{
    public static void main( String[] args ){

        DatabaseCredentials.initialize("database/mysql.properties");

        //Make sure address and ContactInfo is saved in the Database.
        AddressDAO addressDAOJDBC = new AddressDAOJDBCImpl();
        Optional<Address> address1 = addressDAOJDBC.findById("address1");


        ContactInfoDAO contactInfoJDBCDAO = new ContactInfoDAOJdbcImpl();
        Optional<ContactInfo> contactInfoNorr = contactInfoJDBCDAO.findById("contactInfoNorr");


        PremisesDAO premisesDAOJDBC = new PremisesDaoJDBCImpl();
        Premises vårdCentralenNorr = new Premises(
                "VårdCentralen Norr",
                address1.orElse(null),
                contactInfoNorr.orElse(null)
                );

        premisesDAOJDBC.create(vårdCentralenNorr);


//        premisesDAOJDBC.findAll().forEach(System.out::println);

        premisesDAOJDBC.findById(vårdCentralenNorr.getId());







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
