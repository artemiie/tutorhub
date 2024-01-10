package com.tutorhub.service;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;

public interface CrudService<C> {

    C getById(ObjectId id);

    List<C> getAll(Pageable page);

    C create(C entity);

    C update(C entity);

    boolean existsById(ObjectId id);

    void delete(ObjectId id);

}
