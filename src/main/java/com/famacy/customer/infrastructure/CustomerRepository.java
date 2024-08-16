package com.famacy.customer.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.famacy.customer.domain.Customer;
import com.famacy.customer.domain.CustomerService;

public class CustomerRepository implements CustomerService {

    private Connection connection;

    public CustomerRepository() {
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
    public void createCustomer(Customer customer) {
        try {
            String query = "INSERT INTO customer(idCust, name, lastName, email, birthDate, longi, lat, codCity) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getIdCust());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getLastName());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getBirthDate());
            ps.setFloat(6, customer.getLonge());
            ps.setFloat(7, customer.getLat());
            ps.setString(8, customer.getCodCity());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateCustomer(Customer customer) {

        String query = "UPDATE customer SET name = ?, lastName = ?, email = ?, birthDate = ?, longi = ?, lat = ?, codCity = ? WHERE idCust = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getBirthDate());
            ps.setFloat(5, customer.getLonge());
            ps.setFloat(6, customer.getLat());
            ps.setString(7, customer.getCodCity());
            ps.setString(8, customer.getIdCust());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(String idCust) {
        String query = "DELETE FROM customer WHERE idCust = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, idCust);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Customer> findCustomerById(String idCust) {
        String query = "SELECT idCust, name, lastName, email, birthDate, longi, lat, codCity FROM customer WHERE idCust = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, idCust);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer(rs.getString("idCust"),
                            rs.getString("name"),
                            rs.getString("lastName"),
                            rs.getString("email"),
                            rs.getString("birthDate"),
                            rs.getFloat("longi"),
                            rs.getFloat("lat"),
                            rs.getString("codCity"));
                    return Optional.of(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> findAllCustomer() {
        List<Customer> customeres = new ArrayList<>();
        String query = "SELECT idCust, name, lastName, email, birthDate, longi, lat, codCity FROM customer";
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String idCust = rs.getString("idCust");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String birthDate = rs.getString("birthDate");
                float longi = rs.getFloat("longi");
                float lat = rs.getFloat("lat");
                String codCity = rs.getString("codCity");

                Customer customer = new Customer(idCust, name, lastName, email, birthDate, longi, lat, codCity);
                customeres.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customeres;
    }

}
