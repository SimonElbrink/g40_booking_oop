package se.lexicon.data;

import se.lexicon.model.Address;


import java.util.List;

//CRUD - Create, Read, Update, Delete
//Contract
public interface AddressDAO extends GenericCRUD<Address, String>{

    //CREATE
    Address create(String streetAddress, String zipCode, String city);
    @Override
    Address create(Address address);

    //READ
    @Override
    Address findById(String id);
    @Override
    List<Address> findAll();

    List<Address> findAddressByZipcode(String zipCode);
    List<Address> findAddressByCity(String city);

    //UPDATE
    Address update(String id, Address updated);

    //DELETE
    @Override
    boolean delete(String id);


}
