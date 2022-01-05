package se.lexicon.model;

import java.util.UUID;

public class Premises {

  private String id;
  private String name;
  private Address address;
  private ContactInfo contactInfo;


  public Premises(String id, String name, Address address, ContactInfo contactInfo) {
    if(id == null) throw new RuntimeException("id was null");
    this.id = id;
    this.name = name;
    setAddress(address);
    this.contactInfo = contactInfo;
  }

  public Premises(String name, Address address, ContactInfo contactInfo) {
    this(UUID.randomUUID().toString(), name, address, contactInfo);
  }

  public Premises() {
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    if (address == null) throw new IllegalArgumentException("address was null");
    this.address = address;
  }

  public ContactInfo getContactInfo() {
    return contactInfo;
  }

  public void setContactInfo(ContactInfo contactInfo) {
    this.contactInfo = contactInfo;
  }
}