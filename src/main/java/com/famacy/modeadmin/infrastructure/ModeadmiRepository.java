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

import com.famacy.country.domain.Country;
import com.famacy.country.domain.CountryService;

public class ModeadmiRepository implements CountryService {

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
    public void createCountry(Country country) {
        try {
            String query = "INSERT INTO country(codeCountry, name) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, country.getCodeCountry());
            ps.setString(2, country.getName());
            ps.executeUpdate();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Override
    public void updateCountry(Country country) {
       
        String query = "UPDATE country SET name = ? WHERE codeCountry = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, country.getName());
            ps.setString(2, country.getCodeCountry());
            ps.executeUpdate();
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void deleteCountry(String codeCountry) {
        String query = "DELETE FROM country WHERE codeCountry = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, codeCountry);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Country> findCountryById(String codeCountry) {
        String query = "SELECT codeCountry, name FROM country WHERE codeCountry = ?";
        try {
             PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, codeCountry);
            try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Country country = new Country(rs.getString("codeCountry"), rs.getString("name"));
                        return Optional.of(country);
                    }
                }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Country> findAllCountry() {
        List<Country> countryes = new ArrayList<>();
        String query = "SELECT codeCountry, name FROM country";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String codeCountry = rs.getString("codeCountry");
                String nombre = rs.getString("name");
                Country country = new Country(codeCountry, nombre);
                countryes.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryes;
    }

    

}
