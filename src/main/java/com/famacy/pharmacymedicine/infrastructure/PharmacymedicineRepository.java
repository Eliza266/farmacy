package com.famacy.pharmacymedicine.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.famacy.pharmacymedicine.domain.Pharmacymedicine;
import com.famacy.pharmacymedicine.domain.PharmacymedicineService;

public class PharmacymedicineRepository implements PharmacymedicineService {

    private Connection connection;

    public PharmacymedicineRepository() {
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
    public void createPharmacymedicine(Pharmacymedicine pharmacymedicine) {
        try {
            String query = "INSERT INTO pharmacymedicine(idPha, idMed, price) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pharmacymedicine.getIdPha());
            ps.setInt(2, pharmacymedicine.getIdMed());
            ps.setFloat(3, pharmacymedicine.getPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePharmacymedicine(Pharmacymedicine pharmacymedicine) {

        String query = "UPDATE pharmacymedicine SET idPha = ?, idMed = ?, price = ? WHERE idPha = ? AND idMed = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pharmacymedicine.getIdPha());
            ps.setInt(2, pharmacymedicine.getIdMed());
            ps.setFloat(3, pharmacymedicine.getPrice());
            ps.setInt(4, pharmacymedicine.getIdPha());
            ps.setInt(5, pharmacymedicine.getIdMed());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePharmacymedicine(int idPha, int idMed) {
        String query = "DELETE FROM pharmacymedicine WHERE idPha = ? AND idMed =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idPha);
            ps.setInt(2, idMed);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Pharmacymedicine> findPharmacymedicineById(int idPha, int idMed ) {
        String query = "SELECT idPha, idMed, price FROM pharmacymedicine WHERE idPha  = ? AND idMed =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idPha);
            ps.setInt(2, idMed);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pharmacymedicine pharmacymedicine = new Pharmacymedicine(rs.getInt("idPha"), rs.getInt("idMed"), rs.getFloat("price"));
                    return Optional.of(pharmacymedicine);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Pharmacymedicine> findAllPharmacymedicine() {
        List<Pharmacymedicine> pharmacymedicinees = new ArrayList<>();
        String query = "SELECT idPha, idMed, price FROM pharmacymedicine";
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int idPha = rs.getInt("idPha");
                int idMed = rs.getInt("idMed");
                float price = rs.getFloat("price");

                Pharmacymedicine pharmacymedicine = new Pharmacymedicine(idPha, idMed, price);
                pharmacymedicinees.add(pharmacymedicine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pharmacymedicinees;
    }

}
