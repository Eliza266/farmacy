package com.famacy.unitmeasurement.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;

import com.famacy.unitmeasurement.domain.Unitm;
import com.famacy.unitmeasurement.domain.UnitmService;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UnitmRepository implements UnitmService {

    private Connection connection;

    public UnitmRepository() {
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
    public void createUnitm(Unitm unitm) {
        try {
            String query = "INSERT INTO unitmeasurement(name) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, unitm.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUnitm(Unitm unitm) {
        String query = "UPDATE unitmeasurement SET name = ? WHERE idum = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, unitm.getName());
            ps.setInt(2, unitm.getIdum());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUnitm(int idum) {
        String query = "DELETE FROM unitmeasurement WHERE idum = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idum);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Unitm> findUnitmById(int idum) {
        String query = "SELECT idum, name FROM unitmeasurement WHERE idum = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idum);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Unitm unitm = new Unitm(rs.getInt("idum"), rs.getString("description"));
                    return Optional.of(unitm);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Unitm> findAllUnitm() {
        List<Unitm> unitms = new ArrayList<>();
        String query = "SELECT idMode, description FROM modeadmin";
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int idum = rs.getInt("idMode");
                String name = rs.getString("description");
                Unitm unitm = new Unitm(idum, name);
                unitms.add(unitm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unitms;

    }

}
