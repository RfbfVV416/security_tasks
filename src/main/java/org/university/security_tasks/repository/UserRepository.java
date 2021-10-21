package org.university.security_tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.university.security_tasks.model.StoreUser;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<StoreUser, Long> {

    Optional<StoreUser> findByEmail(String email);
}
