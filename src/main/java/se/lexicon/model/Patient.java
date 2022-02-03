package se.lexicon.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Patient implements Serializable {

  private String id;
  private String ssn;
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private ContactInfo contactInfo; // association relationships exist when classes have variables of other class type
  private UserCredentials credentials; // ...


  public Patient(String id,
                 String ssn,
                 String firstName,
                 String lastName,
                 LocalDate birthDate,
                 ContactInfo contactInfo,
                 UserCredentials credentials) {
    if (id == null) throw new RuntimeException("id was null");
    this.id = id;
    this.ssn = ssn;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.contactInfo = contactInfo;
    this.credentials = credentials;
  }

  public Patient(String ssn,
                 String firstName,
                 String lastName,
                 LocalDate birthDate,
                 ContactInfo contactInfo,
                 UserCredentials credentials) {
    this(UUID.randomUUID().toString(), ssn, firstName, lastName, birthDate, contactInfo, credentials);
  }

  public Patient() {
  }

  public String getId() {
    return id;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public ContactInfo getContactInfo() {
    return contactInfo;
  }

  public void setContactInfo(ContactInfo contactInfo) {
    this.contactInfo = contactInfo;
  }

  public UserCredentials getCredentials() {
    return credentials;
  }

  public void setCredentials(UserCredentials credentials) {
    if (credentials == null) throw new IllegalArgumentException("UserCredentials was null");
    this.credentials = credentials;
  }
}
