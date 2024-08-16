package com.famacy.city.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.famacy.city.domain.City;
import com.famacy.city.domain.CityService;

public class CityRepository implements CityService {

    private Connection connection;

    public CityRepository() {
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
    public void createCity(City city) {
        try {
            String query = "INSERT INTO city(codCity, name, codReg) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, city.getCodCity());
            ps.setString(2, city.getName());
            ps.setString(3, city.getCodReg());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCity(City city) {

        String query = "UPDATE city SET name = ?, codReg = ? WHERE codCity = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, city.getName());
            ps.setString(3, city.getCodReg());
            ps.setString(2, city.getCodCity());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCity(String codCity) {
        String query = "DELETE FROM city WHERE codCity = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, codCity);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<City> findCityById(String codReg) {
        String query = "SELECT codCity, name, codReg FROM city WHERE codCity = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, codReg);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    City city = new City(rs.getString("codCity"), rs.getString("name"), rs.getString("codReg"));
                    return Optional.of(city);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<City> findAllCity() {
        List<City> cityes = new ArrayList<>();
        String query = "SELECT codCity, name, codReg FROM city";
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String codCity = rs.getString("codCity");
                String nombre = rs.getString("name");
                String codReg = rs.getString("codReg");

                City city = new City(codCity, nombre, codReg);
                cityes.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityes;
    }

}
