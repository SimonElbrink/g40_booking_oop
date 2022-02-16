package se.lexicon.data.interfaces;

import se.lexicon.model.Premises;

import java.util.List;

public interface PremisesDAO extends GenericCRUD<Premises, String>, Update<Premises> {

    List<Premises> findPremisesByName(String premisesName);
    List<Premises> findPremisesByAddress(String addressID);
    List<Premises> findPremisesByZipcode(String zipcode);


}
