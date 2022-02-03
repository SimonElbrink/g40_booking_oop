package se.lexicon;


import se.lexicon.data.*;
import se.lexicon.data.interfaces.*;
import se.lexicon.io.JsonManager;
import se.lexicon.model.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static se.lexicon.io.URLConstants.*;

public class App
{
    public static void main( String[] args ){

        Address testAddress = new Address("Teleborg", "35252", "Växjö");
        ContactInfo testContactInfo = new ContactInfo("teleborg.test@test.se","0123456789");

        Premises testVaccineCenter = new Premises("test vaccine center", testAddress, testContactInfo);


        UserCredentials testUserCredentials1 = new UserCredentials("user1","123456", "role1");
        ContactInfo contactInfoPatient1 = new ContactInfo("user1.test@test.se","0123456788");
        Patient patient1 = new Patient("19890202-1234", "Test", "Test", LocalDate.parse("1989-02-02"), contactInfoPatient1, testUserCredentials1);


        UserCredentials testUserCredentials2 = new UserCredentials("user2","123456", "role1");
        ContactInfo contactInfoPatient2 = new ContactInfo("user2.test@test.se","0123456788");
        Patient patient2 = new Patient("19900101-1234", "Test", "Test", LocalDate.parse("1990-01-01"), contactInfoPatient2, testUserCredentials2);


        Booking testBooking1 = new Booking(LocalDateTime.parse("2021-12-30T10:00"),0, "ad1", "v123", testVaccineCenter );
        Booking testBooking2 = new Booking(LocalDateTime.parse("2021-12-30T10:30"),0, "ad1", "v124", testVaccineCenter );
        Booking testBooking3 = new Booking(LocalDateTime.parse("2021-12-30T11:00"),0, "ad1", "v125", testVaccineCenter );


        if (testBooking1.isVacant()){
            testBooking1.setPatient(patient1);
            System.out.println("Booking Id: " + testBooking1.getId());
        } else {
            System.out.println(" booking time is not available");
        }

        if (testBooking2.isVacant()){
            testBooking2.setPatient(patient2);
            System.out.println("Booking Id: " + testBooking2.getId());
        } else {
            System.out.println(" booking time is not available");
        }



        AddressDAO addressDAO = AddressDAOIMPL.getInstance();
        addressDAO.create(testAddress);

        BookingDAO bookingDAO = BookingDAOIMPL.getInstance();
        bookingDAO.create(testBooking1);
        bookingDAO.create(testBooking2);
        bookingDAO.create(testBooking3);

        ContactInfoDAO contactInfoDAO = ContactInfoDAOImpl.getInstance();
        contactInfoDAO.create(testContactInfo);
        contactInfoDAO.create(contactInfoPatient1);
        contactInfoDAO.create(contactInfoPatient2);

        PatientDAO patientDAO = PatientDAOIMPL.getInstance();
        patientDAO.create(patient1);
        patientDAO.create(patient2);

        PremisesDAO premisesDAO = PremisesDAOIMPL.getInstance();
        premisesDAO.create(testVaccineCenter);

        UserCredentialsDAO userCredentialsDAO  = UserCredentialsDAOIMPL.getINSTANCE();
        userCredentialsDAO.create(testUserCredentials1);
        userCredentialsDAO.create(testUserCredentials2);


        shutdown(); // At the end.
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
