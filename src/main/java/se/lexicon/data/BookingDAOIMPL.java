package se.lexicon.data;

import se.lexicon.data.interfaces.BookingDAO;
import se.lexicon.io.JsonManager;
import se.lexicon.io.URLConstants;
import se.lexicon.model.Booking;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BookingDAOIMPL implements BookingDAO {


    private static BookingDAOIMPL INSTANCE;

    public static BookingDAOIMPL getInstance() {
        if (INSTANCE == null) INSTANCE = new BookingDAOIMPL(null);
        return INSTANCE;
    }


    public BookingDAOIMPL(Set<Booking> bookings) {
        if (bookings == null) {
            this.bookings = new HashSet<>(JsonManager.getInstance().deserializeFromJson(new File(URLConstants.BOOKING_JSON), Booking.class));
        } else {
            this.bookings = bookings;
        }
    }

    private Set<Booking> bookings;

    @Override
    public List<Booking> findBookingByDateBetween(LocalDate start, LocalDate end) {
        List<Booking> matchingBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            LocalDate date = booking.getDateTime().toLocalDate();

            if ((date.isAfter(start) || date.isEqual(start)) && (date.isBefore(end) || date.equals(end))) {
                matchingBookings.add(booking);
            }
        }

        return matchingBookings;
    }

    @Override
    public List<Booking> findBookingByDateAfter(LocalDate start) {
        return bookings.stream()
                .filter(booking -> booking.getDateTime().toLocalDate().equals(start) || booking.getDateTime().toLocalDate().isAfter(start))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findBookingByDateBefore(LocalDate end) {
        return bookings.stream()
                .filter(booking -> booking.getDateTime().toLocalDate().equals(end) || booking.getDateTime().toLocalDate().isBefore(end))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findBookingByAdministrator(String administrator) {
        return bookings.stream()
                .filter(booking -> booking.getAdministrator().equalsIgnoreCase(administrator))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findBookingByVaccineId(String vaccineId) {
        return bookings.stream()
                .filter(booking -> booking.getVaccineId().equals(vaccineId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findBookingByVacantStatus(boolean vacantStatus) {
        return bookings.stream()
                .filter(booking -> booking.isVacant() == vacantStatus)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findBookingByPatientId(String patientId) {
        return bookings.stream()
                .filter(booking -> booking.getPatient() != null && booking.getPatient().getId().equals(patientId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findBookingByPremisesId(String premisesId) {
        return bookings.stream()
                .filter(booking -> booking.getPremises().getId().equals(premisesId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findBookingByCity(String city) {
        return bookings.stream()
                .filter(booking -> booking.getPremises().getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    @Override
    public Booking create(Booking booking) {
        if (booking ==null) throw new IllegalArgumentException("Booking was null");
        if (booking.getId() == null) throw new IllegalArgumentException("Booking Id was null");
        bookings.add(booking);
        return booking;
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookings);
    }

    @Override
    public Optional<Booking> findById(String id) {
        return bookings.stream()
                .filter(booking -> booking.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean delete(String id) {
        return findById(id).map(bookings::remove).orElse(false);
    }
}
