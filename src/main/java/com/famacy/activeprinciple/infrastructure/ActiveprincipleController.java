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
    public void mainMenu(){
        String opciones = "1. Add activeprincipleService\n2. Search activeprincipleService\n3. Update activeprincipleService\n4. Delete activeprincipleService\n5 List activeprincipleService\n6. Return main menu";
        int op;
        do{
            op =Integer.parseInt(JOptionPane.showInputDialog(null,opciones));
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
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada","Error",JOptionPane.ERROR_MESSAGE);
                    break;
            }

        }while(op!=6);

    }

    public void addActiveprinciple() {
        String name = JOptionPane.showInputDialog(null, "Active Principle Name:");
    
        Activeprinciple activeprinciple = new Activeprinciple();
        activeprinciple.setName(name);
    
        createActiveprincipleUseCase.execute(activeprinciple);
    
        JOptionPane.showMessageDialog(null, "Mode Administration created:\nId: " + activeprinciple.getIdap() + "\nName: " + activeprinciple.getName());
    }

    public Optional<Activeprinciple> findActiveprinciple() {
        int idap = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la Active Principle: "));
        Optional<Activeprinciple> activeprinciple = findActiveprincipleUseCase.execute(idap);
        showActiveprinciple(activeprinciple);
        return activeprinciple;
    }

    public void updateActiveprinciple(){
        Optional<Activeprinciple> ActiveOption = findActiveprinciple();
        if (ActiveOption.isPresent()) {
            Activeprinciple activeprinciple = ActiveOption.get();
            activeprinciple.setName(JOptionPane.showInputDialog(null, "Ingrese el Nombre de la country"));
            updateActiveprincipleUseCase.execute(activeprinciple);
            showActiveprinciple(ActiveOption);
            }

     }

    public void deleteActiveprinciple(){
        Optional<Activeprinciple> activeOption = findActiveprinciple();
        if ( activeOption.isPresent()) {
            Activeprinciple activeprinciple = activeOption.get();
            deleteActiveprincipleUseCase.execute(activeprinciple.getIdap());
            JOptionPane.showMessageDialog(null, "Mode deleted:\nId: " + activeprinciple.getIdap() + "\nName: " + activeprinciple.getName());
        }
    }

    public List<Activeprinciple> findAllActiveprinciple(){
        List<Activeprinciple> active = findAllActiveprincipleUseCase.execute();

        String[] columns = {"ID", "Name"};
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

        JOptionPane.showMessageDialog(null, panel, "Modes List", JOptionPane.PLAIN_MESSAGE);
        return active;
    }

    public void showActiveprinciple(Optional<Activeprinciple> activeprinciple){

        String[] columns = {"ID", "Name"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (activeprinciple.isPresent()) {
            Activeprinciple Active = activeprinciple.get();
            Object[] row = {
                Active.getIdap(),
                Active.getName()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Actives", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Actives List", JOptionPane.PLAIN_MESSAGE);
    

    }
    


}
