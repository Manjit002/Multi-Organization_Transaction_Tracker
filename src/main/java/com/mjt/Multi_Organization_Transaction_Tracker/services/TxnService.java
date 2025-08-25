package com.mjt.Multi_Organization_Transaction_Tracker.services;

import com.mjt.Multi_Organization_Transaction_Tracker.entities.Organization;
import com.mjt.Multi_Organization_Transaction_Tracker.entities.Txn;
import com.mjt.Multi_Organization_Transaction_Tracker.repo.TxnRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TxnService {
    private final TxnRepo repo;
    private final OrganizationService organizationService;

    public Txn create(String ownerEmail, Long orgId, Txn t) {
        Organization org = organizationService.getOwned(ownerEmail, orgId);
        t.setOrganization(org);
        return repo.save(t);
    }

    public List<Txn> list(String ownerEmail, Long orgId) {
        organizationService.getOwned(ownerEmail, orgId);
        return repo.findByOrganization_Id(orgId);
    }

    public void delete(String ownerEmail, Long orgId, Long txnId) {
        organizationService.getOwned(ownerEmail, orgId);
        Txn t = repo.findByIdAndOrganization_Id(txnId, orgId).orElseThrow(() -> new RuntimeException("Txn not found"));
        repo.delete(t);
    }
}
