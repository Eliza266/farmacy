package com.famacy.pharmacymedicine.infrastructure;

import com.famacy.pharmacymedicine.aplication.CreatePharmacymedicineUseCase;
import com.famacy.pharmacymedicine.aplication.DeletePharmacymedicineUseCase;
import com.famacy.pharmacymedicine.aplication.FindAllPharmacymedicineUseCase;
import com.famacy.pharmacymedicine.aplication.FindPharmacymedicineUseCase;
import com.famacy.pharmacymedicine.aplication.UpdatePharmacymedicineUseCase;
import com.famacy.pharmacymedicine.domain.Pharmacymedicine;
import com.famacy.pharmacymedicine.domain.PharmacymedicineService;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PharmacymedicineController {
    private PharmacymedicineService pharmacymedicineService;
    private CreatePharmacymedicineUseCase createPharmacymedicineUseCase;
    private DeletePharmacymedicineUseCase deletePharmacymedicineUseCase;
    private FindAllPharmacymedicineUseCase findAllPharmacymedicineUseCase;
    private FindPharmacymedicineUseCase findPharmacymedicineUseCase;
    private UpdatePharmacymedicineUseCase updatePharmacymedicineUseCase;

    public PharmacymedicineController() {
        this.pharmacymedicineService = new PharmacymedicineRepository();
        this.createPharmacymedicineUseCase = new CreatePharmacymedicineUseCase(pharmacymedicineService);
        this.deletePharmacymedicineUseCase = new DeletePharmacymedicineUseCase(pharmacymedicineService);
        this.findAllPharmacymedicineUseCase = new FindAllPharmacymedicineUseCase(pharmacymedicineService);
        this.findPharmacymedicineUseCase = new FindPharmacymedicineUseCase(pharmacymedicineService);
        this.updatePharmacymedicineUseCase = new UpdatePharmacymedicineUseCase(pharmacymedicineService);
    }

    public void mainMenu() {
        String opciones = "1. Add Pharmacymedicine\n2. Search pharmacymedicine\n3. Update Pharmacymedicine\n4. Delete Pharmacymedicine\n5 List Pharmacymedicine\n6. Return main menu";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, opciones));
            switch (op) {
                case 1:
                    addPharmacymedicine();
                    break;
                case 2:
                    findPharmacymedicine();
                    break;
                case 3:
                    updatePharmacymedicine();
                    break;
                case 4:
                    deletePharmacymedicine();
                    break;
                case 5:
                    findAllPharmacymedicine();
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

    public void addPharmacymedicine() {
        int idPha = Integer.parseInt(JOptionPane.showInputDialog(null, " id Pharmacy:"));
        int idMed = Integer.parseInt(JOptionPane.showInputDialog(null, " Id Medicine :"));
        Float price = Float.parseFloat(JOptionPane.showInputDialog(null, " Id Price:"));

        Pharmacymedicine pharmacymedicine = new Pharmacymedicine();
        pharmacymedicine.setIdPha(idMed);
        pharmacymedicine.setIdMed(idPha);
        pharmacymedicine.setPrice(price);

        createPharmacymedicineUseCase.execute(pharmacymedicine);
        JOptionPane.showMessageDialog(null,
                "Pharmacymedicine created:\nCode: " + pharmacymedicine.getIdPha() + "\nId Pharmacy: "
                        + pharmacymedicine.getIdMed()
                        + "\nId Medicine: " + pharmacymedicine.getPrice());
    }

    public Optional<Pharmacymedicine> findPharmacymedicine() {
        int idPha = Integer.parseInt(JOptionPane.showInputDialog(null, " id Pharmacy:"));
        int idMed = Integer.parseInt(JOptionPane.showInputDialog(null, " Id Medicine :"));
        Optional<Pharmacymedicine> pharmacymedicine = findPharmacymedicineUseCase.execute(idPha, idMed);
        showPharmacymedicine(pharmacymedicine);
        return pharmacymedicine;
    }

    public void updatePharmacymedicine() {
        Optional<Pharmacymedicine> pharmacymedicineOptional = findPharmacymedicine();
        if (pharmacymedicineOptional.isPresent()) {
            Pharmacymedicine pharmacymedicine = pharmacymedicineOptional.get();

            pharmacymedicine.setIdMed(Integer.parseInt(JOptionPane.showInputDialog(null, " id Pharmacy:")));
            pharmacymedicine.setIdPha(Integer.parseInt(JOptionPane.showInputDialog(null, " Id Medicine :")));
            updatePharmacymedicineUseCase.execute(pharmacymedicine);
            showPharmacymedicine(pharmacymedicineOptional);
        }

    }

    public void deletePharmacymedicine() {
        Optional<Pharmacymedicine> pharmacymedicineOptional = findPharmacymedicine();
        if (pharmacymedicineOptional.isPresent()) {
            Pharmacymedicine pharmacymedicine = pharmacymedicineOptional.get();
            deletePharmacymedicineUseCase.execute(pharmacymedicine.getIdPha(), pharmacymedicine.getIdMed());
            JOptionPane.showMessageDialog(null,
                    "Pharmacymedicine deleted:\nCode Pharmacy: " + pharmacymedicine.getIdPha() + "\nCode Medicine: "
                            + pharmacymedicine.getIdMed()
                            + "\nPrice: " + pharmacymedicine.getPrice());
        }
    }

    public List<Pharmacymedicine> findAllPharmacymedicine() {
        List<Pharmacymedicine> pharmacymedicinees = findAllPharmacymedicineUseCase.execute();

        String[] columns = { "Id Pharmacy", "Id Medicine", "Price" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Pharmacymedicine pharmacymedicine : pharmacymedicinees) {
            Object[] row = {
                    pharmacymedicine.getIdPha(),
                    pharmacymedicine.getIdMed(),
                    pharmacymedicine.getPrice()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Pharmacymedicine List", JOptionPane.PLAIN_MESSAGE);
        return pharmacymedicinees;
    }

    public void showPharmacymedicine(Optional<Pharmacymedicine> pharmacymedicine) {

        String[] columns = { "Id Pharmacy", "Id Medicine", "Price" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (pharmacymedicine.isPresent()) {
            Pharmacymedicine count = pharmacymedicine.get();
            Object[] row = {
                    count.getIdPha(),
                    count.getIdMed(),
                    count.getPrice()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No pharmacymedicine", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "pharmacymedicine List", JOptionPane.PLAIN_MESSAGE);

    }

}
