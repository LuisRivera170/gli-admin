package com.applab.gli.repository;

import com.applab.gli.domain.User;
import com.applab.gli.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNameIgnoreCaseAndStatus(String userName, Status status);

}
