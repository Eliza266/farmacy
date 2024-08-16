package com.famacy.modeadmin.infrastructure;

import com.famacy.modeadmin.aplication.CreateModeadmiUseCase;
import com.famacy.modeadmin.aplication.DeleteModeadmiUseCase;
import com.famacy.modeadmin.aplication.FindAllModeadmiUseCase;
import com.famacy.modeadmin.aplication.FindModeadmiUseCase;
import com.famacy.modeadmin.aplication.UpdateModeadmiUseCase;
import com.famacy.modeadmin.domain.Modeadmi;
import com.famacy.modeadmin.domain.ModeadmiService;

import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ModeadmiController {
    private ModeadmiService modeadmiService;
    private CreateModeadmiUseCase createModeadmiUseCase;
    private DeleteModeadmiUseCase deleteModeadmiUseCase;
    private FindAllModeadmiUseCase findAllModeadmiUseCase;
    private FindModeadmiUseCase findModeadmiUseCase;
    private UpdateModeadmiUseCase updateModeadmiUseCase;

    public ModeadmiController() {
        this.modeadmiService = new ModeadmiRepository();
        this.createModeadmiUseCase = new CreateModeadmiUseCase(modeadmiService);
        this.deleteModeadmiUseCase = new DeleteModeadmiUseCase(modeadmiService);
        this.findAllModeadmiUseCase = new FindAllModeadmiUseCase(modeadmiService);
        this.findModeadmiUseCase = new FindModeadmiUseCase(modeadmiService);
        this.updateModeadmiUseCase = new UpdateModeadmiUseCase(modeadmiService);
    }

    public void mainMenu() {
        String opciones = "1. Add Mode Administration\n2. Search Mode Administration\n3. Update Mode Administration\n4. Delete Mode Administration\n5. List Mode Administrations\n6. Return to Main Menu";
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
                        addModeadmi();
                        break;
                    case 2:
                        findModeadmi();
                        break;
                    case 3:
                        updateModeadmi();
                        break;
                    case 4:
                        deleteModeadmi();
                        break;
                    case 5:
                        findAllModeadmi();
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

    public void addModeadmi() {
        String name = JOptionPane.showInputDialog(null, "Mode administration Name:");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Modeadmi modeadmi = new Modeadmi();
        modeadmi.setName(name);

        createModeadmiUseCase.execute(modeadmi);

        JOptionPane.showMessageDialog(null, "Mode Administration created:\nId: " + modeadmi.getIdap() + "\nName: " + modeadmi.getName());
    }

    public Optional<Modeadmi> findModeadmi() {
        String input = JOptionPane.showInputDialog(null, "Ingrese el ID de la Mode Administration:");
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }

        int idap;
        try {
            idap = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }

        Optional<Modeadmi> modeadmi = findModeadmiUseCase.execute(idap);
        showModeadmi(modeadmi);
        return modeadmi;
    }

    public void updateModeadmi() {
        Optional<Modeadmi> modeadmiOptional = findModeadmi();
        if (modeadmiOptional.isPresent()) {
            Modeadmi modeadmi = modeadmiOptional.get();

            String newName = JOptionPane.showInputDialog(null, "Insert Mode Administration Name", modeadmi.getName());
            if (newName == null || newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            modeadmi.setName(newName);
            updateModeadmiUseCase.execute(modeadmi);
            showModeadmi(modeadmiOptional);
        }
    }

    public void deleteModeadmi() {
        Optional<Modeadmi> modeadmiOptional = findModeadmi();
        if (modeadmiOptional.isPresent()) {
            Modeadmi modeadmi = modeadmiOptional.get();

            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar la administración?\nId: " + modeadmi.getIdap() + "\nName: " + modeadmi.getName(), "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteModeadmiUseCase.execute(modeadmi.getIdap());
                JOptionPane.showMessageDialog(null, "Mode deleted:\nId: " + modeadmi.getIdap() + "\nName: " + modeadmi.getName());
            }
        }
    }

    public List<Modeadmi> findAllModeadmi() {
        List<Modeadmi> modes = findAllModeadmiUseCase.execute();

        String[] columns = {"ID", "Name"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Modeadmi mode : modes) {
            Object[] row = {mode.getIdap(), mode.getName()};
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Modes List", JOptionPane.PLAIN_MESSAGE);
        return modes;
    }

    public void showModeadmi(Optional<Modeadmi> modeadmi) {
        String[] columns = {"ID", "Name"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (modeadmi.isPresent()) {
            Modeadmi mod = modeadmi.get();
            Object[] row = {mod.getIdap(), mod.getName()};
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Modes found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Mode Details", JOptionPane.PLAIN_MESSAGE);
    }
}
