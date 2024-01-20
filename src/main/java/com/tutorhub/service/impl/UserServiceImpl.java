package com.tutorhub.service.impl;

import com.tutorhub.model.User;
import com.tutorhub.model.exception.ResourceNotFoundException;
import com.tutorhub.repository.UserRepository;
import com.tutorhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(
            final ObjectId id
    ) {
        return userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public User getByUsername(
            final String username
    ) {
        return userRepository.findByUsername(username)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Page<User> getAll(
            final Pageable page
    ) {
       return userRepository.findAll(page);
    }

    @Override
    public User create(
            final User entity
    ) {
        if (entity.getId() == null && !existsByUsername(entity.getUsername())) {
            userRepository.save(entity);
        }
        return entity;
    }

    @Override
    public User update(
            final User entity
    ) {
        return userRepository.save(entity);
    }

    @Override
    public boolean existsById(
            final ObjectId id
    ) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean existsByUsername(
            final String username
    ) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void delete(
            final ObjectId id
    ) {
        userRepository.deleteById(id);
    }

}
