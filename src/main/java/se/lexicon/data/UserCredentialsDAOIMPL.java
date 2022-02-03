package se.lexicon.data;

import se.lexicon.data.interfaces.UserCredentialsDAO;
import se.lexicon.io.JsonManager;
import se.lexicon.io.URLConstants;
import se.lexicon.model.UserCredentials;

import java.io.File;
import java.util.*;

public class UserCredentialsDAOIMPL implements UserCredentialsDAO {


    private static UserCredentialsDAOIMPL INSTANCE;

//    //Eager Instance
//    static {
//        INSTANCE = new UserCredentialsDAOIMPL(new ArrayList<>());
//    }

    //Lazy Instance
    public static UserCredentialsDAOIMPL getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new UserCredentialsDAOIMPL(null);
        return INSTANCE;
    }

    static UserCredentialsDAOIMPL getTestINSTANCE(Collection<UserCredentials> credentialsList){
        if (credentialsList == null) credentialsList = new HashSet<>();
        return new UserCredentialsDAOIMPL(credentialsList);
    }

    private UserCredentialsDAOIMPL(Collection<UserCredentials> userCredentialsList) {
        if (userCredentialsList == null) userCredentialsList = new HashSet<>(JsonManager.getInstance().deserializeFromJson(new File(URLConstants.CREDENTIALS_JSON), UserCredentials.class));
        this.userCredentialsList = new HashSet<>(userCredentialsList);
    }

    private final Set<UserCredentials> userCredentialsList;

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
                return Optional.of(uc);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserCredentials> findByUserName(String userName) {
        for (UserCredentials uc: userCredentialsList){
            if (uc.getUsername().equals(userName)){
                Optional.of(uc);
            }
        }
        return Optional.empty();
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
