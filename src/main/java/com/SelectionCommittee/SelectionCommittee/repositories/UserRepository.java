package com.SelectionCommittee.SelectionCommittee.repositories;

import com.SelectionCommittee.SelectionCommittee.models.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
}
