package com.famacy.modeadmin.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.famacy.modeadmin.domain.Modeadmi;
import com.famacy.modeadmin.domain.ModeadmiService;

public class ModeadmiRepository implements ModeadmiService {

    private Connection connection;
    
    public ModeadmiRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createModeadmi(Modeadmi modeadmi) {
        try {
            String query = "INSERT INTO modeadmin(description) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, modeadmi.getName());
            ps.executeUpdate();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Override
    public void updateModeadmi(Modeadmi modeadmi) {
        String query = "UPDATE modeadmin SET description = ? WHERE idMode = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, modeadmi.getName());
            ps.setInt(2, modeadmi.getIdap());
            ps.executeUpdate();
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void deleteModeadmi(int idap) {
        String query = "DELETE FROM modeadmin WHERE idMode = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idap);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Modeadmi> findModeadmiById(int idap) {
        String query = "SELECT idMode, description FROM modeadmin WHERE idMode = ?";
        try {
             PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idap);
            try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Modeadmi modeadmi = new Modeadmi(rs.getInt("idMode"), rs.getString("description"));
                        return Optional.of(modeadmi);
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Modeadmi> findAllModeadmi() {
        List<Modeadmi> modeadmins = new ArrayList<>();
        String query = "SELECT idMode, description FROM modeadmin";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int idap = rs.getInt("idMode");
                String description = rs.getString("description");
                Modeadmi modeadmi = new Modeadmi(idap, description);
                modeadmins.add(modeadmi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeadmins;
    }


}
