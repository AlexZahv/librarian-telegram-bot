package com.alexzahv.business.persistance.repositories;

import com.alexzahv.business.persistance.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
