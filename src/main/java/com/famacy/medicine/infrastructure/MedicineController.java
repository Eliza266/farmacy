package com.famacy.medicine.infrastructure;

import com.famacy.medicine.aplication.CreateMedicineUseCase;
import com.famacy.medicine.aplication.DeleteMedicineUseCase;
import com.famacy.medicine.aplication.FindAllMedicineUseCase;
import com.famacy.medicine.aplication.FindMedicineUseCase;
import com.famacy.medicine.aplication.UpdateMedicineUseCase;
import com.famacy.medicine.domain.Medicine;
import com.famacy.medicine.domain.MedicineService;

import java.util.List;
import java.util.Optional;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MedicineController {
    private MedicineService medicineService;
    private CreateMedicineUseCase createMedicineUseCase;
    private DeleteMedicineUseCase deleteMedicineUseCase;
    private FindAllMedicineUseCase findAllMedicineUseCase;
    private FindMedicineUseCase findMedicineUseCase;
    private UpdateMedicineUseCase updateMedicineUseCase;

    public MedicineController() {
        this.medicineService = new MedicineRepository();
        this.createMedicineUseCase = new CreateMedicineUseCase(medicineService);
        this.deleteMedicineUseCase = new DeleteMedicineUseCase(medicineService);
        this.findAllMedicineUseCase = new FindAllMedicineUseCase(medicineService);
        this.findMedicineUseCase = new FindMedicineUseCase(medicineService);
        this.updateMedicineUseCase = new UpdateMedicineUseCase(medicineService);
    }

    public void mainMenu() {
        String opciones = "1. Add Medicine\n2. Search Medicine\n3. Update Medicine\n4. Delete Medicine\n5. List Medicines\n6. Return to Main Menu";
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
                        addMedicine();
                        break;
                    case 2:
                        findMedicine();
                        break;
                    case 3:
                        updateMedicine();
                        break;
                    case 4:
                        deleteMedicine();
                        break;
                    case 5:
                        findAllMedicine();
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

    public void addMedicine() {
        String name = JOptionPane.showInputDialog(null, "Medicine Name:");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String procedings = JOptionPane.showInputDialog(null, "Medicine Proceedings:");
        if (procedings == null || procedings.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Proceedings cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String healthregister = JOptionPane.showInputDialog(null, "Medicine Health Register:");
        if (healthregister == null || healthregister.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Health Register cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String description = JOptionPane.showInputDialog(null, "Medicine Description:");
        if (description == null || description.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String desShort = JOptionPane.showInputDialog(null, "Medicine Short Description:");
        if (desShort == null || desShort.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Short Description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nameRol = JOptionPane.showInputDialog(null, "Medicine Role Name:");
        if (nameRol == null || nameRol.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Role Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idMode = promptForInteger("Medicine Mode ID:");
        int idum = promptForInteger("Medicine Unit Measurement ID:");
        int idLab = promptForInteger("Medicine Laboratory ID:");
        int idap = promptForInteger("Medicine Active Principle ID:");

        Medicine medicine = new Medicine();
        medicine.setName(name);
        medicine.setProcedings(procedings);
        medicine.setHealthregister(healthregister);
        medicine.setDescription(description);
        medicine.setDesShort(desShort);
        medicine.setNameRol(nameRol);
        medicine.setIdMode(idMode);
        medicine.setIdum(idum);
        medicine.setIdLab(idLab);
        medicine.setIdap(idap);

        createMedicineUseCase.execute(medicine);

        JOptionPane.showMessageDialog(null,
                "Medicine created:\n" +
                        "Name: " + medicine.getName() + "\n" +
                        "Proceedings: " + medicine.getProcedings() + "\n" +
                        "Health Register: " + medicine.getHealthregister() + "\n" +
                        "Description: " + medicine.getDescription() + "\n" +
                        "Short Description: " + medicine.getDesShort() + "\n" +
                        "Role Name: " + medicine.getNameRol() + "\n" +
                        "Mode ID: " + medicine.getIdMode() + "\n" +
                        "Unit Measurement ID: " + medicine.getIdum() + "\n" +
                        "Active Principle ID: " + medicine.getIdap() + "\n" +
                        "Laboratory ID: " + medicine.getIdLab());
    }

    public Optional<Medicine> findMedicine() {
        int idMed = promptForInteger("Insert ID Medicine:");
        Optional<Medicine> medicine = findMedicineUseCase.execute(idMed);
        showMedicine(medicine);
        return medicine;
    }

    public void updateMedicine() {
        Optional<Medicine> medicineOptional = findMedicine();
        if (medicineOptional.isPresent()) {
            Medicine medicine = medicineOptional.get();
            String newName = JOptionPane.showInputDialog(null, "Insert Name Medicine", medicine.getName());
            if (newName == null || newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            medicine.setName(newName);
            medicine.setProcedings(JOptionPane.showInputDialog(null, "Insert Proceedings", medicine.getProcedings()));
            medicine.setHealthregister(JOptionPane.showInputDialog(null, "Insert Health Register", medicine.getHealthregister()));
            medicine.setDescription(JOptionPane.showInputDialog(null, "Insert Description", medicine.getDescription()));
            medicine.setDesShort(JOptionPane.showInputDialog(null, "Insert Short Description", medicine.getDesShort()));
            medicine.setNameRol(JOptionPane.showInputDialog(null, "Insert Role Name", medicine.getNameRol()));
            medicine.setIdMode(promptForInteger("Insert Mode ID", medicine.getIdMode()));
            medicine.setIdum(promptForInteger("Insert Unit Measurement ID", medicine.getIdum()));
            medicine.setIdLab(promptForInteger("Insert Laboratory ID", medicine.getIdLab()));
            medicine.setIdap(promptForInteger("Insert Active Principle ID", medicine.getIdap()));
            updateMedicineUseCase.execute(medicine);
            showMedicine(medicineOptional);
        }
    }

    public void deleteMedicine() {
        Optional<Medicine> medicineOptional = findMedicine();
        if (medicineOptional.isPresent()) {
            Medicine medicine = medicineOptional.get();
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this medicine?\nID: " + medicine.getIdMed() + "\nName: " + medicine.getName(), "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteMedicineUseCase.execute(medicine.getIdMed());
                JOptionPane.showMessageDialog(null, "Medicine deleted:\nID: " + medicine.getIdMed() + "\nName: " + medicine.getName());
            }
        }
    }

    public List<Medicine> findAllMedicine() {
        List<Medicine> medicines = findAllMedicineUseCase.execute();

        String[] columns = { "ID", "Name", "Proceedings", "Health Register", "Description", "Short Description", "Role Name", "Mode ID", "Unit Measurement ID", "Laboratory ID", "Active Principle ID" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Medicine medicine : medicines) {
            Object[] row = {
                    medicine.getIdMed(),
                    medicine.getName(),
                    medicine.getProcedings(),
                    medicine.getHealthregister(),
                    medicine.getDescription(),
                    medicine.getDesShort(),
                    medicine.getNameRol(),
                    medicine.getIdMode(),
                    medicine.getIdum(),
                    medicine.getIdLab(),
                    medicine.getIdap()
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

        JOptionPane.showMessageDialog(null, panel, "Medicine List", JOptionPane.PLAIN_MESSAGE);
        return medicines;
    }

    public void showMedicine(Optional<Medicine> medicine) {
        String[] columns = { "ID", "Name", "Proceedings", "Health Register", "Description", "Short Description", "Role Name", "Mode ID", "Unit Measurement ID", "Laboratory ID", "Active Principle ID" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (medicine.isPresent()) {
            Medicine m = medicine.get();
            Object[] row = {
                    m.getIdMed(),
                    m.getName(),
                    m.getProcedings(),
                    m.getHealthregister(),
                    m.getDescription(),
                    m.getDesShort(),
                    m.getNameRol(),
                    m.getIdMode(),
                    m.getIdum(),
                    m.getIdLab(),
                    m.getIdap()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Medicine found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        table.setPreferredSize(new Dimension(800, 400));
        scrollPane.setPreferredSize(new Dimension(800, 400));
        panel.setPreferredSize(new Dimension(800, 400));

        JOptionPane.showMessageDialog(null, panel, "Medicine Details", JOptionPane.PLAIN_MESSAGE);
    }

    private int promptForInteger(String message) {
        return promptForInteger(message, -1);
    }

    private int promptForInteger(String message, int defaultValue) {
        String input = JOptionPane.showInputDialog(null, message, defaultValue);
        if (input == null || input.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return defaultValue;
        }
    }
}
