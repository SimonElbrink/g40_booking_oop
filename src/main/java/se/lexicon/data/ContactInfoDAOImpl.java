package se.lexicon.data;

import se.lexicon.data.interfaces.ContactInfoDAO;
import se.lexicon.io.JsonManager;
import se.lexicon.io.URLConstants;
import se.lexicon.model.ContactInfo;

import java.io.File;
import java.util.*;

public class ContactInfoDAOImpl implements ContactInfoDAO {

    private static ContactInfoDAOImpl INSTANCE;

    public static ContactInfoDAOImpl getInstance(){
        if (INSTANCE == null) INSTANCE = new ContactInfoDAOImpl(null);
        return INSTANCE;
    }

    static ContactInfoDAOImpl getTestInstance(Collection<ContactInfo> collection){
        if (collection == null) collection = new ArrayList<>();
        return new ContactInfoDAOImpl(collection);
    }

    private ContactInfoDAOImpl(Collection<ContactInfo> contactInfoSet) {
        if(contactInfoSet == null){
            this.contactInfoSet = new HashSet<>(JsonManager.getInstance().deserializeFromJson(new File(URLConstants.CONTACT_INFO_JSON), ContactInfo.class));
        }else{
            this.contactInfoSet = new HashSet<>(contactInfoSet);
        }
    }
    private final Set<ContactInfo> contactInfoSet;


    @Override
    public Optional<ContactInfo> findByEmail(String email) {
        return contactInfoSet.stream()
                .filter(contactInfo -> contactInfo.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public ContactInfo create(ContactInfo contactInfo) {
        if (contactInfo == null) throw new IllegalArgumentException("ContactInfo was null.");
        if (contactInfo.getId() == null) throw new IllegalArgumentException("ContactInfo.id was null.");

        return contactInfoSet.add(contactInfo) ? contactInfo : null;
    }

    @Override
    public List<ContactInfo> findAll() {
        return new ArrayList<>(contactInfoSet);
    }

    @Override
    public Optional<ContactInfo> findById(String id) {
        return contactInfoSet.stream()
                .filter(contactInfo -> contactInfo.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean delete(String id) {
        return contactInfoSet.removeIf(contactInfo -> contactInfo.getId().equals(id));
    }
}
