package com.kimberlysupport.repository;

import com.kimberlysupport.model.Complain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainRepository extends JpaRepository<Complain,Long> {
}
