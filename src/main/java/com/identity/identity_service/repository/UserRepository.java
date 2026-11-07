package com.identity.identity_service.repository;

import com.identity.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JPA tự động generate code cho Interface cho những tác vụ cơ bản
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
}
