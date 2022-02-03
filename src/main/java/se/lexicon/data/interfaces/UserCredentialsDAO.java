package se.lexicon.data.interfaces;

import se.lexicon.model.UserCredentials;

import java.util.List;
import java.util.Optional;

public interface UserCredentialsDAO extends GenericCRUD<UserCredentials,String>{

    Optional<UserCredentials> findByUserName(String userName);

    List<UserCredentials> findByRole(String role);

}
