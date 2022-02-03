package se.lexicon.model;

import java.io.Serializable;
import java.util.UUID;

public class UserCredentials implements Serializable {

  private String id;
  private String username;
  private String password;
  private String role;

  public UserCredentials(String id, String username, String password, String role) {
    if (id == null) throw new IllegalArgumentException("id was null");
    this.id = id;
    setUsername(username);
    setPassword(password);
    setRole(role);
  }

  public UserCredentials(String username, String password, String role) {
    this(UUID.randomUUID().toString(), username,password,role);
  }

  public UserCredentials() {
  }

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    if (username == null) throw new IllegalArgumentException("Username was null");
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    if (password == null) throw new IllegalArgumentException("Password was null");
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    if (role == null) throw new IllegalArgumentException("Role was null");
    this.role = role;
  }

  @Override
  public String toString() {
    return "UserCredentials{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", role='" + role + '\'' +
            '}';
  }
}
