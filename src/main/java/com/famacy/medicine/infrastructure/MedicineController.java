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
        String opciones = "1. Add Medicine\n2. Search medicine\n3. Update Medicine\n4. Delete Medicine\n5 List Countries\n6. Return main menu";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, opciones));
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
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }

        } while (op != 6);

    }

    public void addMedicine() {
        String name = JOptionPane.showInputDialog(null, "Medicine Name:");
        String procedings = JOptionPane.showInputDialog(null, "Medicine procedings:");
        String healthregister = JOptionPane.showInputDialog(null, "Medicine healthregister:");
        String description = JOptionPane.showInputDialog(null, "Medicine description:");
        String desShort = JOptionPane.showInputDialog(null, "Medicine desShort:");
        String nameRol = JOptionPane.showInputDialog(null, "Medicine nameRol:");
        int idMode = Integer.parseInt(JOptionPane.showInputDialog(null, "Medicine idMode:"));
        int idum = Integer.parseInt(JOptionPane.showInputDialog(null, "Medicine idum:"));
        int idLab = Integer.parseInt(JOptionPane.showInputDialog(null, "Medicine idLab:"));
        int idap = Integer.parseInt(JOptionPane.showInputDialog(null, "Medicine idap:"));

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
                        "Procedings: " + medicine.getProcedings() + "\n" +
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
        int idLab = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert ID Medicine: "));
        Optional<Medicine> medicine = findMedicineUseCase.execute(idLab);
        showMedicine(medicine);
        return medicine;
    }

    public void updateMedicine() {
        Optional<Medicine> medicineOptional = findMedicine();
        if (medicineOptional.isPresent()) {
            Medicine medicine = medicineOptional.get();
            medicine.setName(JOptionPane.showInputDialog(null, "Insert Name Medicine"));
            medicine.setProcedings(JOptionPane.showInputDialog(null, "Insert procedings "));
            medicine.setHealthregister(JOptionPane.showInputDialog(null, "Insert  Healthregister"));
            medicine.setDescription(JOptionPane.showInputDialog(null, "Insert  Description"));
            medicine.setDesShort(JOptionPane.showInputDialog(null, "Insert  Description Short"));
            medicine.setNameRol(JOptionPane.showInputDialog(null, "Insert  Name Rol"));
            medicine.setIdMode(Integer.parseInt(JOptionPane.showInputDialog(null, "Insert  Id Mode administration")));
            medicine.setIdum(Integer.parseInt(JOptionPane.showInputDialog(null, "Insert  Id Unit ")));
            medicine.setIdLab(Integer.parseInt(JOptionPane.showInputDialog(null, "Insert  Id Lab")));
            medicine.setIdap(Integer.parseInt(JOptionPane.showInputDialog(null, "Insert  Id Active principle")));
            updateMedicineUseCase.execute(medicine);
            showMedicine(medicineOptional);
        }

    }

    public void deleteMedicine() {
        Optional<Medicine> medicineOptional = findMedicine();
        if (medicineOptional.isPresent()) {
            Medicine medicine = medicineOptional.get();
            deleteMedicineUseCase.execute(medicine.getIdMed());
        }
    }

    public List<Medicine> findAllMedicine() {
        List<Medicine> medicinees = findAllMedicineUseCase.execute();

        String[] columns = { "ID", "Name", "PROCEDINGS", "HEALTHREGISTER", "DESCRIPTION", "DESSHORT", "NAME ROL",
                "ID MODE ADMI", "ID UNIT M", "ID LAB", "ID ACTIVE PRIN" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Medicine medicine : medicinees) {
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
        return medicinees;
    }

    public void showMedicine(Optional<Medicine> medicine) {

        String[] columns = { "ID", "Name", "PROCEDINGS", "HEALTHREGISTER", "DESCRIPTION", "DESSHORT", "NAME ROL",
                "ID MODE ADMI", "ID UNIT M", "ID LAB", "ID ACTIVE PRIN" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (medicine.isPresent()) {
            Medicine count = medicine.get();
            Object[] row = {
                    count.getIdMed(),
                    count.getName(),
                    count.getProcedings(),
                    count.getHealthregister(),
                    count.getDescription(),
                    count.getDesShort(),
                    count.getNameRol(),
                    count.getIdMode(),
                    count.getIdum(),
                    count.getIdLab(),
                    count.getIdap()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Cities", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        table.setPreferredSize(new Dimension(800, 400));
        scrollPane.setPreferredSize(new Dimension(800, 400));
        panel.setPreferredSize(new Dimension(800, 400));

        JOptionPane.showMessageDialog(null, panel, "Medicine List", JOptionPane.PLAIN_MESSAGE);

    }

}
