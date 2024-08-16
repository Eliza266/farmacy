package com.famacy.laboratory.infrastructure;

import com.famacy.laboratory.aplication.CreateLaboratoryUseCase;
import com.famacy.laboratory.aplication.DeleteLaboratoryUseCase;
import com.famacy.laboratory.aplication.FindAllLaboratoryUseCase;
import com.famacy.laboratory.aplication.FindLaboratoryUseCase;
import com.famacy.laboratory.aplication.UpdateLaboratoryUseCase;
import com.famacy.laboratory.domain.Laboratory;
import com.famacy.laboratory.domain.LaboratoryService;

import java.awt.Dimension;
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
        String opciones = "1. Add Laboratory\n2. Search Laboratory\n3. Update Laboratory\n4. Delete Laboratory\n5. List Laboratories\n6. Return to Main Menu";
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
                        JOptionPane.showMessageDialog(null, "Error en la opción ingresada", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, ingrese un número válido.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (op != 6);
    }

    public void addLaboratory() {
        String name = JOptionPane.showInputDialog(null, "Laboratory Name:");
        if (name == null) return;

        String codCity = JOptionPane.showInputDialog(null, "City Code:");
        if (codCity == null) return;

        Laboratory laboratory = new Laboratory();
        laboratory.setName(name);
        laboratory.setCodCity(codCity);

        createLaboratoryUseCase.execute(laboratory);
        JOptionPane.showMessageDialog(null, "Laboratory created:\nCode: " + laboratory.getIdLab() + "\nName: "
                + laboratory.getName() + "\nCity Code: " + laboratory.getCodCity());
    }

    public Optional<Laboratory> findLaboratory() {
        String input = JOptionPane.showInputDialog(null, "Ingrese el ID del Laboratory: ");
        if (input == null || input.trim().isEmpty()) {
            return Optional.empty();
        }
        int idLab;
        try {
            idLab = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido. Debe ser un número entero.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }
        Optional<Laboratory> laboratory = findLaboratoryUseCase.execute(idLab);
        showLaboratory(laboratory);
        return laboratory;
    }

    public void updateLaboratory() {
        Optional<Laboratory> laboratoryOptional = findLaboratory();
        if (laboratoryOptional.isPresent()) {
            Laboratory laboratory = laboratoryOptional.get();
            String newName = JOptionPane.showInputDialog(null, "Insert Name Laboratory", laboratory.getName());
            if (newName == null) return;

            String newCodCity = JOptionPane.showInputDialog(null, "Insert Code City", laboratory.getCodCity());
            if (newCodCity == null) return;

            laboratory.setName(newName);
            laboratory.setCodCity(newCodCity);

            updateLaboratoryUseCase.execute(laboratory);
            showLaboratory(laboratoryOptional);
        }
    }

    public void deleteLaboratory() {
        Optional<Laboratory> laboratoryOptional = findLaboratory();
        if (laboratoryOptional.isPresent()) {
            Laboratory laboratory = laboratoryOptional.get();
            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el Laboratory?\nCode: " + laboratory.getIdLab() + "\nName: " + laboratory.getName(), "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteLaboratoryUseCase.execute(laboratory.getIdLab());
                JOptionPane.showMessageDialog(null, "Laboratory deleted:\nCode: " + laboratory.getIdLab() + "\nName: "
                        + laboratory.getName() + "\nCity Code: " + laboratory.getCodCity());
            }
        }
    }

    public List<Laboratory> findAllLaboratory() {
        List<Laboratory> laboratories = findAllLaboratoryUseCase.execute();

        String[] columns = { "ID", "Name", "City Code" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Laboratory laboratory : laboratories) {
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

        table.setPreferredSize(new Dimension(800, 400));
        scrollPane.setPreferredSize(new Dimension(800, 400));
        panel.setPreferredSize(new Dimension(800, 400));

        JOptionPane.showMessageDialog(null, panel, "Laboratory List", JOptionPane.PLAIN_MESSAGE);
        return laboratories;
    }

    public void showLaboratory(Optional<Laboratory> laboratory) {
        String[] columns = { "ID", "Name", "City Code" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (laboratory.isPresent()) {
            Laboratory lab = laboratory.get();
            Object[] row = {
                    lab.getIdLab(),
                    lab.getName(),
                    lab.getCodCity()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Laboratory found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        table.setPreferredSize(new Dimension(800, 400));
        scrollPane.setPreferredSize(new Dimension(800, 400));
        panel.setPreferredSize(new Dimension(800, 400));

        JOptionPane.showMessageDialog(null, panel, "Laboratory Details", JOptionPane.PLAIN_MESSAGE);
    }
}
