package se.lexicon.data;

import se.lexicon.exceptions.ObjectNotFoundException;
import se.lexicon.model.Address;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressDAOIMPL implements AddressDAO{

    //SINGLETON, to accomplice Single Source of truth. - One storage of Addresses for this whole program.
    private static final AddressDAOIMPL INSTANCE;

    static {
        INSTANCE = new AddressDAOIMPL();
    }

    public static AddressDAOIMPL getInstance(){
        return INSTANCE;
    }

    protected static AddressDAOIMPL getTestInstance(List<Address> addressList){
        return new AddressDAOIMPL(addressList);
    }


    private List<Address> addressList;

    private AddressDAOIMPL() {
        this.addressList = new ArrayList<>();
    }

    private AddressDAOIMPL(List<Address> addressList) {
        this.addressList = addressList;
    }

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
    public Address findById(String id) {

        for (Address address : addressList) {
            if (address.getId().equals(id)){
                return address;
            }
        }

        throw new ObjectNotFoundException("Could Not Find Address with ID:" + id);
    }


    @Override
    public List<Address> findAll() {
        return Collections.unmodifiableList(addressList);
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
        return addressList.remove(findById(id));
    }
}