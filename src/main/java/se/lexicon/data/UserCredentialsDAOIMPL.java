package se.lexicon.data;

import se.lexicon.data.interfaces.UserCredentialsDAO;
import se.lexicon.model.UserCredentials;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCredentialsDAOIMPL implements UserCredentialsDAO {


    private static UserCredentialsDAO INSTANCE;

//    //Eager Instance
//    static {
//        INSTANCE = new UserCredentialsDAOIMPL(new ArrayList<>());
//    }

    //Lazy Instance
    public static UserCredentialsDAO getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new UserCredentialsDAOIMPL(null);
        return INSTANCE;
    }

    static UserCredentialsDAO getTestINSTANCE(List<UserCredentials> credentialsList){
        INSTANCE = new UserCredentialsDAOIMPL(credentialsList);

        return INSTANCE;
    }

    UserCredentialsDAOIMPL(List<UserCredentials> userCredentialsList) {
        if (userCredentialsList == null) userCredentialsList = new ArrayList<>();
        this.userCredentialsList = userCredentialsList;
    }

    private final List<UserCredentials> userCredentialsList;

    @Override
    public UserCredentials create(UserCredentials userCredentials) {

        if (userCredentials == null) throw new IllegalArgumentException("userCredentials Can not be null");

        userCredentialsList.add(userCredentials);
        return userCredentials;
    }

    @Override
    public List<UserCredentials> findAll() {
        return new ArrayList<>(userCredentialsList);
    }

    @Override
    public Optional<UserCredentials> findById(String id) {

        for (UserCredentials uc : userCredentialsList) {
            if (uc.getId().equals(id)){
                return Optional.ofNullable(uc);
            }
        }
        return Optional.empty();
    }

    @Override
    public UserCredentials findByUserName(String userName) {
        for (UserCredentials uc: userCredentialsList){
            if (uc.getUsername().equals(userName)) return uc;
        }
        return null;
    }

    @Override
    public List<UserCredentials> findByRole(String role) {
        List<UserCredentials> found = new ArrayList<>();
        for (UserCredentials uc : userCredentialsList){
            if (uc.getRole().equalsIgnoreCase(role)){
                found.add(uc);
            }
        }
        return found;
    }

    @Override
    public boolean delete(String id) {
        return userCredentialsList.removeIf(userCredentials -> userCredentials.getId().equals(id));
    }
}
