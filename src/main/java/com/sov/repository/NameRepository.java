package com.sov.repository;

import com.sov.model.NameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends JpaRepository<NameModel, Long> {
}
