package com.kimberlysupport.repository;

import com.kimberlysupport.model.User;
import com.kimberlysupport.util.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByMobile(String mobile);

    int countAllByRole(Role admin);

    Optional<User> findByUniqueId(String uniqueId);

    List<User> findByIdInOrderByNameAsc(List<Long> ids);
    List<User> findAllByNameContaining(String name);
    List<User> findAllByNameStartingWithOrNameLike(String startswith, String samename);
}
