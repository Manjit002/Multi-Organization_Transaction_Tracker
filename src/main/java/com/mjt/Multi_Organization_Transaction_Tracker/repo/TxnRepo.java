package com.mjt.Multi_Organization_Transaction_Tracker.repo;

import com.mjt.Multi_Organization_Transaction_Tracker.entities.Organization;
import com.mjt.Multi_Organization_Transaction_Tracker.entities.Txn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TxnRepo extends JpaRepository<Txn, Long> {

    List<Txn> findByOrganization_Id(Long orgId);

    Optional<Txn> findByIdAndOrganization_Id(Long id, Long orgId);
}
