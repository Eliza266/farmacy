package com.famacy.laboratory.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.famacy.laboratory.domain.Laboratory;
import com.famacy.laboratory.domain.LaboratoryService;

public class LaboratoryRepository implements LaboratoryService {

    private Connection connection;

    public LaboratoryRepository() {
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
    public void createLaboratory(Laboratory laboratory) {
        try {
            String query = "INSERT INTO laboratory( name, codCity) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, laboratory.getName());
            ps.setString(2, laboratory.getCodCity());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLaboratory(Laboratory laboratory) {

        String query = "UPDATE laboratory SET name = ?, codCity = ? WHERE idLab = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, laboratory.getName());
            ps.setString(2, laboratory.getCodCity());
            ps.setInt(3, laboratory.getIdLab());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLaboratory(int idLab) {
        String query = "DELETE FROM laboratory WHERE idLab = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idLab);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Laboratory> findLaboratoryById(int idLab) {
        String query = "SELECT idLab, name, codCity FROM laboratory WHERE idLab = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idLab);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Laboratory laboratory = new Laboratory(rs.getInt("idLab"), rs.getString("name"),
                            rs.getString("codCity"));
                    return Optional.of(laboratory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Laboratory> findAllLaboratory() {
        List<Laboratory> laboratoryes = new ArrayList<>();
        String query = "SELECT idLab, name, codCity FROM laboratory";
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int idLab = rs.getInt("idLab");
                String nombre = rs.getString("name");
                String codCity = rs.getString("codCity");

                Laboratory laboratory = new Laboratory(idLab, nombre, codCity);
                laboratoryes.add(laboratory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laboratoryes;
    }

}
