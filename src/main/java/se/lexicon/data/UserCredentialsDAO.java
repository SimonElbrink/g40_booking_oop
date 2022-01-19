package se.lexicon.data;

import se.lexicon.model.UserCredentials;

import java.util.List;

public interface UserCredentialsDAO extends GenericCRUD<UserCredentials,String>{

    UserCredentials findByUserName(String userName);

    List<UserCredentials> findByRole(String role);

}
