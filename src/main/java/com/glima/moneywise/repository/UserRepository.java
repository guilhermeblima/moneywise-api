package com.glima.moneywise.repository;

import com.glima.moneywise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Guilherme on 17/01/2018.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
