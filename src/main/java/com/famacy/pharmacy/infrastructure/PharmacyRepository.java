package com.famacy.pharmacy.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.famacy.pharmacy.domain.Pharmacy;
import com.famacy.pharmacy.domain.PharmacyService;

public class PharmacyRepository implements PharmacyService {

    private Connection connection;

    public PharmacyRepository() {
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
    public void createPharmacy(Pharmacy pharmacy) {
        try {
            String query = "INSERT INTO pharmacy(name, addres longi, lat, logo, codCity) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, pharmacy.getName());
            ps.setString(2, pharmacy.getAddres());
            ps.setFloat(3, pharmacy.getLongi());
            ps.setFloat(4, pharmacy.getLat());
            ps.setString(5, pharmacy.getLogo());
            ps.setString(6, pharmacy.getCodCity());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
public void updatePharmacy(Pharmacy pharmacy) {

    String query = "UPDATE pharmacy SET name = ?, addres = ?, longi = ?, lat = ?, logo = ?, codCity = ? WHERE idPha = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, pharmacy.getName());
        ps.setString(2, pharmacy.getName());
        ps.setFloat(3, pharmacy.getLongi());
        ps.setFloat(4, pharmacy.getLat());
        ps.setString(5, pharmacy.getLogo());
        ps.setString(6, pharmacy.getCodCity());
        ps.setInt(7, pharmacy.getIdPha());
        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @Override
    public void deletePharmacy(int idPha) {
        String query = "DELETE FROM pharmacy WHERE idPha = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idPha);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Optional<Pharmacy> findPharmacyById(int idPha) {
        String query = "SELECT idPha, name, addres, longi, lat, logo, codCity FROM pharmacy WHERE idPha = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idPha);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pharmacy pharmacy = new Pharmacy(
                            rs.getInt("idPha"),
                            rs.getString("name"),
                            rs.getString("addres"),
                            rs.getFloat("longi"),
                            rs.getFloat("lat"),
                            rs.getString("logo"),
                            rs.getString("codCity"));
                    return Optional.of(pharmacy);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    

    @Override
    public List<Pharmacy> findAllPharmacy() {
        List<Pharmacy> pharmacies = new ArrayList<>();
        String query = "SELECT idPha, name, addres, longi, lat, logo, codCity FROM pharmacy";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int idPha = rs.getInt("idPha");
                String name = rs.getString("name");
                String addres = rs.getString("addres");
                float longi = rs.getFloat("longi");
                float lat = rs.getFloat("lat");
                String logo = rs.getString("logo");
                String codCity = rs.getString("codCity");
    
                Pharmacy pharmacy = new Pharmacy(idPha, name,addres, longi, lat, logo, codCity);
                pharmacies.add(pharmacy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pharmacies;
    }
    

}
