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
        String opciones = "1. Add Pharmacy\n2. Search pharmacy\n3. Update Pharmacy\n4. Delete Pharmacy\n5 List Pharmacy\n6. Return main menu";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, opciones));
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
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }

        } while (op != 6);

    }

    public void addPharmacy() {
        String name = JOptionPane.showInputDialog(null, "Pharmacy Name:");
        String addres = JOptionPane.showInputDialog(null, "Pharmacy Address:");
        float longi = Float.parseFloat(JOptionPane.showInputDialog(null, "Pharmacy longi:"));
        float lat = Float.parseFloat(JOptionPane.showInputDialog(null, "Pharmacy Lat:"));
        String logo = (JOptionPane.showInputDialog(null, "Pharmacy Logo:"));
        String codCity = JOptionPane.showInputDialog(null, "Pharmacy Code City:");

        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName(name);
        pharmacy.setAddres(addres);
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
        int idPha = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert ID Pharmacy: "));
        Optional<Pharmacy> pharmacy = findPharmacyUseCase.execute(idPha);
        showPharmacy(pharmacy);
        return pharmacy;
    }

    public void updatePharmacy() {
        Optional<Pharmacy> pharmacyOptional = findPharmacy();
        if (pharmacyOptional.isPresent()) {
            Pharmacy pharmacy = pharmacyOptional.get();
            pharmacy.setName(JOptionPane.showInputDialog(null, "Insert Name Pharmacy"));
            pharmacy.setAddres(JOptionPane.showInputDialog(null, "Insert Name Address"));
            pharmacy.setLogo(JOptionPane.showInputDialog(null, "Insert Logo Pharmacy"));
            pharmacy.setLongi(Float.parseFloat(JOptionPane.showInputDialog(null, "Insert Longitude Pharmacy")));
            pharmacy.setLat(Float.parseFloat(JOptionPane.showInputDialog(null, "Insert Latitude Pharmacy")));
            pharmacy.setCodCity(JOptionPane.showInputDialog(null, "Insert Code City"));
            updatePharmacyUseCase.execute(pharmacy);
            showPharmacy(pharmacyOptional);
        }
    }

    public void deletePharmacy() {
        Optional<Pharmacy> pharmacyOptional = findPharmacy();
        if (pharmacyOptional.isPresent()) {
            Pharmacy pharmacy = pharmacyOptional.get();
            deletePharmacyUseCase.execute(pharmacy.getIdPha());
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
