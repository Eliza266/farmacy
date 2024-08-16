package com.famacy.region.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.famacy.region.domain.Region;
import com.famacy.region.domain.RegionService;

public class RegionRepository implements RegionService {

    private Connection connection;
    
    public RegionRepository() {
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
    public void createRegion(Region region) {
        try {
            String query = "INSERT INTO region(codReg, name, codeCountry) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, region.getCodReg());
            ps.setString(2, region.getName());
            ps.setString(3, region.getCodeCountry());
            ps.executeUpdate();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
 

    @Override
    public void updateRegion(Region region) {
       
        String query = "UPDATE region SET name = ?, codeCountry = ? WHERE codReg = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, region.getName());
            ps.setString(2, region.getCodeCountry());
            ps.setString(3, region.getCodReg());
            ps.executeUpdate();
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void deleteRegion(String codReg) {
        String query = "DELETE FROM region WHERE codReg = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, codReg);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Region> findRegionById(String codReg) {
        String query = "SELECT codReg, name, codeCountry FROM region WHERE codReg = ?";
        try {
             PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, codReg);
            try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Region region = new Region(rs.getString("codReg"), rs.getString("name"), rs.getString("codeCountry"));
                        return Optional.of(region);
                    }
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Region> findAllRegion() {
        List<Region> regiones = new ArrayList<>();
        String query = "SELECT codReg, name, codeCountry FROM region";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String codReg = rs.getString("codReg");
                String nombre = rs.getString("name");
                String codeCountry = rs.getString("codeCountry");

                Region region = new Region(codReg, nombre,codeCountry);
                regiones.add(region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regiones;
    }

    

}
