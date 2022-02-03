package se.lexicon.data;

import se.lexicon.data.interfaces.PremisesDAO;
import se.lexicon.io.JsonManager;
import se.lexicon.model.Address;
import se.lexicon.model.Premises;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static se.lexicon.io.URLConstants.PREMISES_JSON;

public class PremisesDAOIMPL implements PremisesDAO {

    private static PremisesDAOIMPL INSTANCE;

    private static PremisesDAOIMPL getInstance(){
        if(INSTANCE == null){
            INSTANCE = new PremisesDAOIMPL(null);
        }
        return INSTANCE;
    }

    public PremisesDAOIMPL(Set<Premises> premisesStorage) {
        if (premisesStorage == null){
            this.premisesStorage = new HashSet<>(JsonManager.getInstance().deserializeFromJson(new File(PREMISES_JSON), Premises.class));
        }

        this.premisesStorage = premisesStorage;
    }

    Set<Premises> premisesStorage;

    @Override
    public Premises create(Premises premises) {
        return premisesStorage.add(premises) ? premises: null;
    }

    @Override
    public List<Premises> findAll() {
        return new ArrayList<>(premisesStorage);
    }

    @Override
    public Optional<Premises> findById(String id) {
        return premisesStorage.stream()
                .filter(premises -> premises.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Premises> findPremisesByName(String premisesName) {
        return premisesStorage.stream()
                .filter(premises -> premises.getName().equals(premisesName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Premises> findPremisesByAddress(String addressID) {
        return premisesStorage.stream()
                .filter(premises -> premises.getAddress().getId().equals(addressID))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(String id) {
        return premisesStorage.removeIf(premises -> premises.getId().equals(id));
    }


}
