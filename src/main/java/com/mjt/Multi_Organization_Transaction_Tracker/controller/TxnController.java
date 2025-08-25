package com.mjt.Multi_Organization_Transaction_Tracker.controller;


import com.mjt.Multi_Organization_Transaction_Tracker.dtos.TxnCreateRequest;
import com.mjt.Multi_Organization_Transaction_Tracker.dtos.TxnResponse;
import com.mjt.Multi_Organization_Transaction_Tracker.entities.Txn;
import com.mjt.Multi_Organization_Transaction_Tracker.services.TxnService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orgs/{orgId}/txns")
@RequiredArgsConstructor
public class TxnController {
    private final TxnService service;

    @PostMapping
    public TxnResponse create(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                              @PathVariable Long orgId,
                              @RequestBody @Valid TxnCreateRequest req) {
        Txn t = Txn.builder()
                .type(req.getType())
                .amount(req.getAmount())
                .item(req.getItem())
                .date(req.getDate())
                .party(req.getParty())
                .build();

        Txn saved = service.create(user.getUsername(), orgId, t);
        return new TxnResponse(saved.getId(), saved.getType(), saved.getAmount(), saved.getItem(), saved.getDate(), saved.getParty());
    }

    @GetMapping
    public List<TxnResponse> list(@AuthenticationPrincipal User user, @PathVariable Long orgId) {
        return service.list(user.getUsername(), orgId).stream()
                .map(t -> new TxnResponse(t.getId(), t.getType(), t.getAmount(), t.getItem(), t.getDate(), t.getParty()))
                .toList();
    }

    @DeleteMapping("/{txnId}")
    public void delete(@AuthenticationPrincipal User user, @PathVariable Long orgId, @PathVariable Long txnId) {
        service.delete(user.getUsername(), orgId, txnId);
    }
}
