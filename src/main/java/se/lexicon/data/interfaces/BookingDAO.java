package se.lexicon.data.interfaces;

import se.lexicon.model.Booking;


import java.time.LocalDate;
import java.util.List;

public interface BookingDAO extends GenericCRUD<Booking,String>{

    List<Booking> findBookingByDateBetween(LocalDate start, LocalDate end);
    List<Booking> findBookingByDateAfter(LocalDate start);
    List<Booking> findBookingByDateBefore(LocalDate end);
    List<Booking> findBookingByAdministrator(String administrator);
    List<Booking> findBookingByVaccineId(String vaccineId);
    List<Booking> findBookingByVacantStatus(boolean vacantStatus);
    List<Booking> findBookingByPatientId(String patientId);
    List<Booking> findBookingByPremisesId(String premisesId);
    List<Booking> findBookingByCity(String city);

}
