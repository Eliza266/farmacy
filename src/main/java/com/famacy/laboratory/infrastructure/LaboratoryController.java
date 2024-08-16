package com.famacy.laboratory.infrastructure;

import com.famacy.laboratory.aplication.CreateLaboratoryUseCase;
import com.famacy.laboratory.aplication.DeleteLaboratoryUseCase;
import com.famacy.laboratory.aplication.FindAllLaboratoryUseCase;
import com.famacy.laboratory.aplication.FindLaboratoryUseCase;
import com.famacy.laboratory.aplication.UpdateLaboratoryUseCase;
import com.famacy.laboratory.domain.Laboratory;
import com.famacy.laboratory.domain.LaboratoryService;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LaboratoryController {
    private LaboratoryService laboratoryService;
    private CreateLaboratoryUseCase createLaboratoryUseCase;
    private DeleteLaboratoryUseCase deleteLaboratoryUseCase;
    private FindAllLaboratoryUseCase findAllLaboratoryUseCase;
    private FindLaboratoryUseCase findLaboratoryUseCase;
    private UpdateLaboratoryUseCase updateLaboratoryUseCase;

    public LaboratoryController() {
        this.laboratoryService = new LaboratoryRepository();
        this.createLaboratoryUseCase = new CreateLaboratoryUseCase(laboratoryService);
        this.deleteLaboratoryUseCase = new DeleteLaboratoryUseCase(laboratoryService);
        this.findAllLaboratoryUseCase = new FindAllLaboratoryUseCase(laboratoryService);
        this.findLaboratoryUseCase = new FindLaboratoryUseCase(laboratoryService);
        this.updateLaboratoryUseCase = new UpdateLaboratoryUseCase(laboratoryService);
    }

    public void mainMenu() {
        String opciones = "1. Add Laboratory\n2. Search laboratory\n3. Update Laboratory\n4. Delete Laboratory\n5 List Countries\n6. Return main menu";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, opciones));
            switch (op) {
                case 1:
                    addLaboratory();
                    break;
                case 2:
                    findLaboratory();
                    break;
                case 3:
                    updateLaboratory();
                    break;
                case 4:
                    deleteLaboratory();
                    break;
                case 5:
                    findAllLaboratory();
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

    public void addLaboratory() {
        String name = JOptionPane.showInputDialog(null, "Laboratory Name:");
        String codCity = JOptionPane.showInputDialog(null, "City Code:");

        Laboratory laboratory = new Laboratory();
        laboratory.setName(name);
        laboratory.setCodCity(codCity);

        createLaboratoryUseCase.execute(laboratory);
        JOptionPane.showMessageDialog(null, "Laboratory created:\nCode: " + laboratory.getIdLab() + "\nName: "
                + laboratory.getName() + "\ncodCity: " + laboratory.getCodCity());
    }

    public Optional<Laboratory> findLaboratory() {
        int idLab = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la Laboratory: "));
        Optional<Laboratory> laboratory = findLaboratoryUseCase.execute(idLab);
        showLaboratory(laboratory);
        return laboratory;
    }

    public void updateLaboratory() {
        Optional<Laboratory> laboratoryOptional = findLaboratory();
        if (laboratoryOptional.isPresent()) {
            Laboratory laboratory = laboratoryOptional.get();
            laboratory.setName(JOptionPane.showInputDialog(null, "Insert Name Laboratory"));
            laboratory.setCodCity(JOptionPane.showInputDialog(null, "Insert Code City"));
            updateLaboratoryUseCase.execute(laboratory);
            showLaboratory(laboratoryOptional);
        }

    }

    public void deleteLaboratory() {
        Optional<Laboratory> laboratoryOptional = findLaboratory();
        if (laboratoryOptional.isPresent()) {
            Laboratory laboratory = laboratoryOptional.get();
            deleteLaboratoryUseCase.execute(laboratory.getIdLab());
            JOptionPane.showMessageDialog(null, "Laboratory deleted:\nCode: " + laboratory.getIdLab() + "\nName: "
                    + laboratory.getName() + "\nCode City: " + laboratory.getCodCity());
        }
    }

    public List<Laboratory> findAllLaboratory() {
        List<Laboratory> laboratoryes = findAllLaboratoryUseCase.execute();

        String[] columns = { "ID", "Name", "CODE CITY" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Laboratory laboratory : laboratoryes) {
            Object[] row = {
                    laboratory.getIdLab(),
                    laboratory.getName(),
                    laboratory.getCodCity()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Laboratory List", JOptionPane.PLAIN_MESSAGE);
        return laboratoryes;
    }

    public void showLaboratory(Optional<Laboratory> laboratory) {

        String[] columns = { "ID", "Name", "CODE REGION" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (laboratory.isPresent()) {
            Laboratory count = laboratory.get();
            Object[] row = {
                    count.getIdLab(),
                    count.getName(),
                    count.getCodCity()
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

        JOptionPane.showMessageDialog(null, panel, "Laboratory List", JOptionPane.PLAIN_MESSAGE);

    }

}
