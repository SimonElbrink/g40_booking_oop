package se.lexicon;


import se.lexicon.data.AddressDAO;
import se.lexicon.data.AddressDAOIMPL;
import se.lexicon.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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




        AddressDAO addressStorage1 = AddressDAOIMPL.getInstance();

        addressStorage1.create(testAddress);
        addressStorage1.create("Storgatan 20", "123 45", "Växjö");
        addressStorage1.create("VårdGatan 10", "123 45", "Växjö");
        addressStorage1.create("Sjukhusvägen 5", "123 45", "Växjö");
        addressStorage1.create("Sjukhusvägen 1", "543 21", "SjukaStaden");

        System.out.println("_____________ Storage1 - FindAll __________________");

        List<Address> allAddresses = addressStorage1.findAll();

//        for (Address address : allAddresses) {
//            System.out.println(address);
//        }
        allAddresses.forEach((Address address) -> System.out.println(address));

        System.out.println("_____________ FindAddressByCity __________________");

        List<Address> addressesInSjukaStaden = addressStorage1.findAddressByCity("SjukaStaden");

        addressesInSjukaStaden.forEach(System.out::println);


        System.out.println("_____________Storage2 - FindAll __________________");
        AddressDAO addressStorage2 = AddressDAOIMPL.getInstance(); // Same Reference as storage 1
        addressStorage2.create(testAddress);
        addressStorage2.create("Nygatan 8", "360 73", "Lenhovda");

        addressStorage2.findAll().forEach(System.out::println);















    }
}
