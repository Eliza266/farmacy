package com.famacy.modeadmin.domain;
import java.util.List;
import java.util.Optional;

public interface ModeadmiService {
    void createModeadmi(Modeadmi modeadmi);
    void updateModeadmi(Modeadmi modeadmi);
    void deleteModeadmi(int idap);
    Optional<Modeadmi> findModeadmiById(int idap);
    List<Modeadmi> findAllModeadmi();
}
