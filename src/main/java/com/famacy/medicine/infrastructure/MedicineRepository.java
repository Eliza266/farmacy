package com.famacy.medicine.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.famacy.medicine.domain.Medicine;
import com.famacy.medicine.domain.MedicineService;

public class MedicineRepository implements MedicineService {

    private Connection connection;

    public MedicineRepository() {
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
    public void createMedicine(Medicine medicine) {
        try {
            String query = """
                    INSERT INTO medicine
                    ( name ,procedings ,healthregister ,description ,desShort ,nameRol ,idMode ,idum ,idLab ,idap)
                    VALUES (?,?,?,?,?,?,?,?,?,?)
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getProcedings());
            ps.setString(3, medicine.getHealthregister());
            ps.setString(4, medicine.getDescription());
            ps.setString(5, medicine.getDesShort());
            ps.setString(6, medicine.getNameRol());
            ps.setInt(7, medicine.getIdMode());
            ps.setInt(8, medicine.getIdum());
            ps.setInt(9, medicine.getIdLab());
            ps.setInt(10, medicine.getIdap());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMedicine(Medicine medicine) {

        String query = """
                UPDATE medicine SET name = ?,
                procedings = ?,
                healthregister = ?,
                description = ?,
                desShort = ?,
                nameRol = ?,
                idMode = ?,
                idum = ?,
                idLab = ?,
                idap = ?
                WHERE idMed = ?
                """;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getProcedings());
            ps.setString(3, medicine.getHealthregister());
            ps.setString(4, medicine.getDescription());
            ps.setString(5, medicine.getDesShort());
            ps.setString(6, medicine.getNameRol());
            ps.setInt(7, medicine.getIdMode());
            ps.setInt(8, medicine.getIdum());
            ps.setInt(9, medicine.getIdLab());
            ps.setInt(10, medicine.getIdap());
            ps.setInt(11, medicine.getIdMed());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicine(int idMed) {
        String query = "DELETE FROM medicine WHERE idMed = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idMed);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Medicine> findMedicineById(int idLab) {
        String query = """
                SELECT idMed,
                name ,
                procedings ,
                healthregister ,
                description ,
                desShort ,
                nameRol ,
                idMode ,
                idum ,
                idLab ,
                idap
                FROM medicine
                WHERE idMed = ?
                """;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idLab);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Medicine medicine = new Medicine(
                            rs.getInt("idMed"),
                            rs.getString("name"),
                            rs.getString("procedings"),
                            rs.getString("healthregister"),
                            rs.getString("description"),
                            rs.getString("desShort"),
                            rs.getString("nameRol"),
                            rs.getInt("idMode"),
                            rs.getInt("idum"),
                            rs.getInt("idLab"),
                            rs.getInt("idap"));
                    return Optional.of(medicine);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Medicine> findAllMedicine() {
        List<Medicine> medicinees = new ArrayList<>();
        String query = """
                SELECT idMed,
                name ,
                procedings ,
                healthregister ,
                description ,
                desShort ,
                nameRol ,
                idMode ,
                idum ,
                idLab ,
                idap
                FROM medicine
                """;
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int idMed = rs.getInt("idMed");
                String nombre = rs.getString("name");
                String procedings = rs.getString("procedings");
                String healthregister = rs.getString("healthregister");
                String description = rs.getString("description");
                String desShort = rs.getString("desShort");
                String nameRol = rs.getString("nameRol");
                int idMode = rs.getInt("idMode");
                int idum = rs.getInt("idum");
                int idLab = rs.getInt("idLab");
                int idap = rs.getInt("idap");

                Medicine medicine = new Medicine(idMed, nombre, procedings, healthregister, description, desShort,
                        nameRol, idMode, idum, idLab, idap);
                medicinees.add(medicine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicinees;
    }

}
