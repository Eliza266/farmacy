package com.famacy.activeprinciple.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.famacy.activeprinciple.domain.Activeprinciple;
import com.famacy.activeprinciple.domain.ActiveprincipleService;


public class ActiveprincipleRepository implements ActiveprincipleService {

    private Connection connection;
    
    public ActiveprincipleRepository() {
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
    public void createActiveprinciple(Activeprinciple activeprinciple) {
        try {
            String query = "INSERT INTO activeprinciple(name) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, activeprinciple.getName());
            ps.executeUpdate();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Override
    public void updateActiveprinciple(Activeprinciple activeprinciple) {
        String query = "UPDATE activeprinciple SET name = ? WHERE idap = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, activeprinciple.getName());
            ps.setInt(2, activeprinciple.getIdap());
            ps.executeUpdate();
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void deleteActiveprinciple(int idap) {
        String query = "DELETE FROM activeprinciple WHERE idap = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idap);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Activeprinciple> findActiveprincipleById(int idap) {
        String query = "SELECT idap, name FROM activeprinciple WHERE idAP = ?";
        try {
             PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idap);
            try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Activeprinciple activeprinciple = new Activeprinciple(rs.getInt("idap"), rs.getString("name"));
                        return Optional.of(activeprinciple);
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Activeprinciple> findAllActiveprinciple() {
        List<Activeprinciple> activeprincipleses = new ArrayList<>();
        String query = "SELECT idap, name FROM activeprinciple";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int idap = rs.getInt("idap");
                String name = rs.getString("name");
                Activeprinciple activeprinciple = new Activeprinciple(idap, name);
                activeprincipleses.add(activeprinciple);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activeprincipleses;
    }



}
