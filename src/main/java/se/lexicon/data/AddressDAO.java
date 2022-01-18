package se.lexicon.data;

import se.lexicon.model.Address;


import java.util.List;

//CRUD - Create, Read, Update, Delete
public interface AddressDAO {

    //CREATE
    Address create(String streetAddress, String zipCode, String city);
    Address create(Address address);

    //READ
    Address findById(String id);
    List<Address> findAll();
    List<Address> findAddressByZipcode(String zipCode);
    List<Address> findAddressByCity(String city);

    //UPDATE
    Address update(String id, Address updated);

    //DELETE
    boolean delete(String id);


}
