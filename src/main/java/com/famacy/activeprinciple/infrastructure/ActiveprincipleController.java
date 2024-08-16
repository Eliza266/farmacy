package com.famacy.activeprinciple.infrastructure;

import com.famacy.activeprinciple.aplication.CreateActiveprincipleUseCase;
import com.famacy.activeprinciple.aplication.DeleteActiveprincipleUseCase;
import com.famacy.activeprinciple.aplication.FindActiveprincipleUseCase;
import com.famacy.activeprinciple.aplication.FindAllActiveprincipleUseCase;
import com.famacy.activeprinciple.aplication.UpdateActiveprincipleUseCase;
import com.famacy.activeprinciple.domain.Activeprinciple;
import com.famacy.activeprinciple.domain.ActiveprincipleService;

import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ActiveprincipleController {
    private ActiveprincipleService activeprincipleService;
    private CreateActiveprincipleUseCase createActiveprincipleUseCase;
    private DeleteActiveprincipleUseCase deleteActiveprincipleUseCase;
    private FindAllActiveprincipleUseCase findAllActiveprincipleUseCase;
    private FindActiveprincipleUseCase findActiveprincipleUseCase;
    private UpdateActiveprincipleUseCase updateActiveprincipleUseCase;

    public ActiveprincipleController() {
        this.activeprincipleService = new ActiveprincipleRepository();
        this.createActiveprincipleUseCase = new CreateActiveprincipleUseCase(activeprincipleService);
        this.deleteActiveprincipleUseCase = new DeleteActiveprincipleUseCase(activeprincipleService);
        this.findAllActiveprincipleUseCase = new FindAllActiveprincipleUseCase(activeprincipleService);
        this.findActiveprincipleUseCase = new FindActiveprincipleUseCase(activeprincipleService);
        this.updateActiveprincipleUseCase = new UpdateActiveprincipleUseCase(activeprincipleService);
    }

    public void mainMenu() {
        String opciones = "1. Add Active Principle\n2. Search Active Principle\n3. Update Active Principle\n4. Delete Active Principle\n5. List Active Principles\n6. Return to Main Menu";
        int op= -1;

        do {
            String input = JOptionPane.showInputDialog(null, opciones);
            if (input == null || input.trim().isEmpty()) {
                break;
            }
            try {
                op = Integer.parseInt(input);
                switch (op) {
                    case 1:
                        addActiveprinciple();
                        break;
                    case 2:
                        findActiveprinciple();
                        break;
                    case 3:
                        updateActiveprinciple();
                        break;
                    case 4:
                        deleteActiveprinciple();
                        break;
                    case 5:
                        findAllActiveprinciple();
                        break;
                    case 6:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error en la opción ingresada", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error en la opción ingresada", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (op != 6);
    }

    public void addActiveprinciple() {
        String name = JOptionPane.showInputDialog(null, "Active Principle Name:");
        if (name == null) {
            return;
        }

        Activeprinciple activeprinciple = new Activeprinciple();
        activeprinciple.setName(name);

        createActiveprincipleUseCase.execute(activeprinciple);

        JOptionPane.showMessageDialog(null,
                "Active Principle created:\nId: " + activeprinciple.getIdap() + "\nName: " + activeprinciple.getName());
    }

    public Optional<Activeprinciple> findActiveprinciple() {
        String input = JOptionPane.showInputDialog(null, "Ingrese el ID del Active Principle:");
        if (input == null) {
            return Optional.empty();
        }
        try {
            int idap = Integer.parseInt(input);
            Optional<Activeprinciple> activeprinciple = findActiveprincipleUseCase.execute(idap);
            showActiveprinciple(activeprinciple);
            return activeprinciple;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }
    }

    public void updateActiveprinciple() {
        Optional<Activeprinciple> activeOption = findActiveprinciple();
        if (activeOption.isPresent()) {
            Activeprinciple activeprinciple = activeOption.get();
            String name = JOptionPane.showInputDialog(null, "Ingrese el Nombre del Active Principle",
                    activeprinciple.getName());
            if (name == null) {
                return;
            }
            activeprinciple.setName(name);
            updateActiveprincipleUseCase.execute(activeprinciple);
            showActiveprinciple(activeOption);
        }
    }

    public void deleteActiveprinciple() {
        Optional<Activeprinciple> activeOption = findActiveprinciple();
        if (activeOption.isPresent()) {
            Activeprinciple activeprinciple = activeOption.get();
            int confirm = JOptionPane
                    .showConfirmDialog(null,
                            "¿Está seguro de eliminar el Active Principle?\nId: " + activeprinciple.getIdap()
                                    + "\nName: " + activeprinciple.getName(),
                            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteActiveprincipleUseCase.execute(activeprinciple.getIdap());
                JOptionPane.showMessageDialog(null, "Active Principle deleted:\nId: " + activeprinciple.getIdap()
                        + "\nName: " + activeprinciple.getName());
            }
        }
    }

    public List<Activeprinciple> findAllActiveprinciple() {
        List<Activeprinciple> active = findAllActiveprincipleUseCase.execute();

        String[] columns = { "ID", "Name" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Activeprinciple activeprinciple : active) {
            Object[] row = {
                    activeprinciple.getIdap(),
                    activeprinciple.getName()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Active Principles List", JOptionPane.PLAIN_MESSAGE);
        return active;
    }

    public void showActiveprinciple(Optional<Activeprinciple> activeprinciple) {
        if (activeprinciple.isPresent()) {
            Activeprinciple active = activeprinciple.get();
            String[] columns = { "ID", "Name" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            Object[] row = {
                    active.getIdap(),
                    active.getName()
            };
            model.addRow(row);

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            JPanel panel = new JPanel();
            panel.add(scrollPane);

            JOptionPane.showMessageDialog(null, panel, "Active Principle Details", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No Active Principle found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
