package com.famacy.activeprinciple.domain;


import java.util.List;
import java.util.Optional;

public interface ActiveprincipleService {
    void createActiveprinciple(Activeprinciple activeprinciple);
    void updateActiveprinciple(Activeprinciple activeprinciple);
    void deleteActiveprinciple(int idap);
    Optional<Activeprinciple> findActiveprincipleById(int idap);
    List<Activeprinciple> findAllActiveprinciple();
}
// Activeprinciple
// activeprinciple