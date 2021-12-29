package se.lexicon;


import se.lexicon.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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


        System.out.println("Booking List");
        System.out.println(testBooking1.bookingData());
        System.out.println(testBooking2.bookingData());
        System.out.println(testBooking3.bookingData());

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

        System.out.println("Booking List");
        System.out.println(testBooking1.bookingData());
        System.out.println(testBooking2.bookingData());
        System.out.println(testBooking3.bookingData());
    }
}
