package com.famacy.country.infrastructure;

import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.famacy.country.domain.Country;
import com.famacy.country.domain.CountryService;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

public class CountryRepository implements CountryService {

    private Connection connection;
    @Override
    public void createCountry(Country country) {
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
    public void updateCountry(Country country) {
        String query = "INSERT INTO country( codeCountry, name) VALUES (?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, country.getCodeCountry());
            ps.setString(2, country.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCountry(String codeCountry) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCountry'");
    }

    @Override
    public Optional<Country> findCountryById(String idCountry) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findCountryById'");
    }

    @Override
    public List<Country> findAllCountry() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllCountry'");
    }

}
