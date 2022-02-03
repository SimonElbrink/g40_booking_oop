package se.lexicon.data;

import se.lexicon.data.interfaces.AddressDAO;
import se.lexicon.io.JsonManager;
import se.lexicon.model.Address;

import java.io.File;
import java.util.*;

import static se.lexicon.io.URLConstants.ADDRESS_JSON;

public class AddressDAOIMPL implements AddressDAO {

    //SINGLETON, to accomplice Single Source of truth. - One storage of Addresses for this whole program.
    private static AddressDAOIMPL INSTANCE;

    public static AddressDAOIMPL getInstance(){
        if (INSTANCE == null) INSTANCE = new AddressDAOIMPL(null);
        return INSTANCE;
    }

    static AddressDAOIMPL getTestInstance(Collection<Address> addresses){
        if (addresses == null) addresses = new ArrayList<>();
        return new AddressDAOIMPL(addresses);
    }

    static AddressDAOIMPL getTestInstance(List<Address> addressList){
        return new AddressDAOIMPL(addressList);
    }

    private AddressDAOIMPL(Collection<Address> addressList) {
        if (addressList == null) {
            this.addressList = new HashSet<>(JsonManager.getInstance().deserializeFromJson(new File(ADDRESS_JSON),Address.class));
        }else{
            this.addressList = new HashSet<>(addressList);
        }
    }

    //Where our Addresses are stored.
    private final Set<Address> addressList;


    @Override
    public Address create(String streetAddress, String zipCode, String city) {
        Address toSave = new Address(streetAddress, zipCode, city);
        addressList.add(toSave);
        return toSave;
    }

    @Override
    public Address create(Address address) {
        addressList.add(address);
        return address;
    }

    @Override
    public Optional<Address> findById(String id) {
        Optional<Address> found = Optional.empty();
        for (Address address : addressList) {
            if (address.getId().equals(id)){
                found = Optional.ofNullable(address);
                break;
            }
        }
       return found;
    }


    @Override
    public List<Address> findAll() {
        return new ArrayList<>(addressList);
    }

    @Override
    public List<Address> findAddressByZipcode(String zipCode) {

        List<Address> foundMatches = new ArrayList<>();

        for (Address address : addressList) {
            if (address.getZipCode().equalsIgnoreCase(zipCode)) {
                foundMatches.add(address);
            }
        }


        return foundMatches;
    }

    @Override
    public List<Address> findAddressByCity(String city) {
        List<Address> found = new ArrayList<>();
        for (Address address : addressList) {
            if (address.getCity().equalsIgnoreCase(city)) {
                found.add(address);
            }
        }
        return found;
    }

    @Override
    public Address update(String id, Address updated) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return addressList.removeIf(address -> address.getId().equals(id));
    }
}
