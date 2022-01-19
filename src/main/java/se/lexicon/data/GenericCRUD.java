package se.lexicon.data;

import java.util.List;

public interface GenericCRUD<T, ID> {

    T create(T t);
    List<T> findAll();
    T findById(ID id);

    boolean delete(ID id);



}
