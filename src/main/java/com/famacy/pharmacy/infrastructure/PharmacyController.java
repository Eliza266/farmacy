package com.famacy.pharmacy.infrastructure;

import com.famacy.pharmacy.aplication.CreatePharmacyUseCase;
import com.famacy.pharmacy.aplication.DeletePharmacyUseCase;
import com.famacy.pharmacy.aplication.FindAllPharmacyUseCase;
import com.famacy.pharmacy.aplication.FindPharmacyUseCase;
import com.famacy.pharmacy.aplication.UpdatePharmacyUseCase;
import com.famacy.pharmacy.domain.Pharmacy;
import com.famacy.pharmacy.domain.PharmacyService;

import java.awt.Dimension;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PharmacyController {
    private PharmacyService pharmacyService;
    private CreatePharmacyUseCase createPharmacyUseCase;
    private DeletePharmacyUseCase deletePharmacyUseCase;
    private FindAllPharmacyUseCase findAllPharmacyUseCase;
    private FindPharmacyUseCase findPharmacyUseCase;
    private UpdatePharmacyUseCase updatePharmacyUseCase;

    public PharmacyController() {
        this.pharmacyService = new PharmacyRepository();
        this.createPharmacyUseCase = new CreatePharmacyUseCase(pharmacyService);
        this.deletePharmacyUseCase = new DeletePharmacyUseCase(pharmacyService);
        this.findAllPharmacyUseCase = new FindAllPharmacyUseCase(pharmacyService);
        this.findPharmacyUseCase = new FindPharmacyUseCase(pharmacyService);
        this.updatePharmacyUseCase = new UpdatePharmacyUseCase(pharmacyService);
    }

    public void mainMenu() {
        String opciones = "1. Add Pharmacy\n2. Search Pharmacy\n3. Update Pharmacy\n4. Delete Pharmacy\n5. List Pharmacies\n6. Return to Main Menu";
        int op = -1;
        do {
            String input = JOptionPane.showInputDialog(null, opciones);
            if (input == null || input.trim().isEmpty()) {
                return;
            }
            try {
                op = Integer.parseInt(input.trim());
                switch (op) {
                    case 1:
                        addPharmacy();
                        break;
                    case 2:
                        findPharmacy();
                        break;
                    case 3:
                        updatePharmacy();
                        break;
                    case 4:
                        deletePharmacy();
                        break;
                    case 5:
                        findAllPharmacy();
                        break;
                    case 6:
                        break; 
                    default:
                        JOptionPane.showMessageDialog(null, "Error en la opción ingresada", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (op != 6);
    }

    public void addPharmacy() {
        String name = JOptionPane.showInputDialog(null, "Pharmacy Name:");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String address = JOptionPane.showInputDialog(null, "Pharmacy Address:");
        if (address == null || address.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Address cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Float longi;
        try {
            longi = Float.parseFloat(JOptionPane.showInputDialog(null, "Pharmacy Longitude:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Longitude must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Float lat;
        try {
            lat = Float.parseFloat(JOptionPane.showInputDialog(null, "Pharmacy Latitude:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Latitude must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String logo = JOptionPane.showInputDialog(null, "Pharmacy Logo:");
        if (logo == null || logo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Logo cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String codCity = JOptionPane.showInputDialog(null, "Pharmacy Code City:");
        if (codCity == null || codCity.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "City Code cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName(name);
        pharmacy.setAddres(address);
        pharmacy.setLongi(longi);
        pharmacy.setLat(lat);
        pharmacy.setLogo(logo);
        pharmacy.setCodCity(codCity);

        createPharmacyUseCase.execute(pharmacy);

        JOptionPane.showMessageDialog(null,
                "Pharmacy created:\n" +
                        "ID: " + pharmacy.getIdPha() + "\n" +
                        "Name: " + pharmacy.getName() + "\n" +
                        "Address: " + pharmacy.getAddres() + "\n" +
                        "Logo: " + pharmacy.getLogo() + "\n" +
                        "Location (Longitude, Latitude): " + pharmacy.getLongi() + ", " + pharmacy.getLat() + "\n" +
                        "City Code: " + pharmacy.getCodCity());
    }

    public Optional<Pharmacy> findPharmacy() {
        String input = JOptionPane.showInputDialog(null, "Insert ID Pharmacy:");
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }

        int idPha;
        try {
            idPha = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }

        Optional<Pharmacy> pharmacy = findPharmacyUseCase.execute(idPha);
        showPharmacy(pharmacy);
        return pharmacy;
    }

    public void updatePharmacy() {
        Optional<Pharmacy> pharmacyOptional = findPharmacy();
        if (pharmacyOptional.isPresent()) {
            Pharmacy pharmacy = pharmacyOptional.get();

            String newName = JOptionPane.showInputDialog(null, "Insert Name Pharmacy", pharmacy.getName());
            if (newName == null || newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newAddress = JOptionPane.showInputDialog(null, "Insert Address Pharmacy", pharmacy.getAddres());
            if (newAddress == null || newAddress.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Address cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newLogo = JOptionPane.showInputDialog(null, "Insert Logo Pharmacy", pharmacy.getLogo());
            if (newLogo == null || newLogo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Logo cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Float newLongi;
            try {
                newLongi = Float.parseFloat(JOptionPane.showInputDialog(null, "Insert Longitude Pharmacy", pharmacy.getLongi()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Longitude must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Float newLat;
            try {
                newLat = Float.parseFloat(JOptionPane.showInputDialog(null, "Insert Latitude Pharmacy", pharmacy.getLat()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Latitude must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newCodCity = JOptionPane.showInputDialog(null, "Insert Code City", pharmacy.getCodCity());
            if (newCodCity == null || newCodCity.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "City Code cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            pharmacy.setName(newName);
            pharmacy.setAddres(newAddress);
            pharmacy.setLogo(newLogo);
            pharmacy.setLongi(newLongi);
            pharmacy.setLat(newLat);
            pharmacy.setCodCity(newCodCity);

            updatePharmacyUseCase.execute(pharmacy);
            showPharmacy(pharmacyOptional);
        }
    }

    public void deletePharmacy() {
        Optional<Pharmacy> pharmacyOptional = findPharmacy();
        if (pharmacyOptional.isPresent()) {
            Pharmacy pharmacy = pharmacyOptional.get();
            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar la farmacia?\nID: " + pharmacy.getIdPha() + "\nName: " + pharmacy.getName(), "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deletePharmacyUseCase.execute(pharmacy.getIdPha());
                JOptionPane.showMessageDialog(null, "Pharmacy deleted:\nID: " + pharmacy.getIdPha() + "\nName: " + pharmacy.getName());
            }
        }
    }

    public List<Pharmacy> findAllPharmacy() {
        List<Pharmacy> pharmacies = findAllPharmacyUseCase.execute();

        String[] columns = { "ID", "Name", "Address", "Longitude", "Latitude", "Logo", "City Code" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Pharmacy pharmacy : pharmacies) {
            Object[] row = {
                    pharmacy.getIdPha(),
                    pharmacy.getName(),
                    pharmacy.getAddres(),
                    pharmacy.getLongi(),
                    pharmacy.getLat(),
                    pharmacy.getLogo(),
                    pharmacy.getCodCity()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        table.setPreferredSize(new Dimension(800, 400));
        scrollPane.setPreferredSize(new Dimension(800, 400));
        panel.setPreferredSize(new Dimension(800, 400));

        JOptionPane.showMessageDialog(null, panel, "Pharmacy List", JOptionPane.PLAIN_MESSAGE);
        return pharmacies;
    }

    public void showPharmacy(Optional<Pharmacy> pharmacy) {
        String[] columns = { "ID", "Name", "Address", "Longitude", "Latitude", "Logo", "City Code" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (pharmacy.isPresent()) {
            Pharmacy pha = pharmacy.get();
            Object[] row = {
                    pha.getIdPha(),
                    pha.getName(),
                    pha.getAddres(),
                    pha.getLongi(),
                    pha.getLat(),
                    pha.getLogo(),
                    pha.getCodCity()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Pharmacies found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        table.setPreferredSize(new Dimension(800, 400));
        scrollPane.setPreferredSize(new Dimension(800, 400));
        panel.setPreferredSize(new Dimension(800, 400));

        JOptionPane.showMessageDialog(null, panel, "Pharmacy Details", JOptionPane.PLAIN_MESSAGE);
    }
}
