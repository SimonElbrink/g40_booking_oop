package se.lexicon.data.interfaces;

import se.lexicon.model.Address;
import se.lexicon.model.Premises;

import java.util.List;

public interface PremisesDAO extends GenericCRUD<Premises, String> {

    List<Premises> findPremisesByName(String premisesName);
    List<Premises> findPremisesByAddress(String addressID);

}
