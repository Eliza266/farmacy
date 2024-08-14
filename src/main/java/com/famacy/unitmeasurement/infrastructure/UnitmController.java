package com.famacy.unitmeasurement.infrastructure;

import com.famacy.unitmeasurement.aplication.CreateUnitmUseCase;
import com.famacy.unitmeasurement.aplication.DeleteUnitmUseCase;
import com.famacy.unitmeasurement.aplication.FindAllUnitmUseCase;
import com.famacy.unitmeasurement.aplication.FindUnitmUseCase;
import com.famacy.unitmeasurement.aplication.UpdateUnitmUseCase;
import com.famacy.unitmeasurement.domain.Unitm;
import com.famacy.unitmeasurement.domain.UnitmService;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UnitmController {
    private UnitmService unitmService;
    private CreateUnitmUseCase createUnitmUseCase;
    private DeleteUnitmUseCase deleteUnitmUseCase;
    private FindAllUnitmUseCase findAllUnitmUseCase;
    private FindUnitmUseCase findUnitmUseCase;
    private UpdateUnitmUseCase updateUnitmUseCase;


    public UnitmController() {
        this.unitmService = new UnitmRepository();
        this.createUnitmUseCase = new CreateUnitmUseCase(unitmService);
        this.deleteUnitmUseCase = new DeleteUnitmUseCase(unitmService);
        this.findAllUnitmUseCase = new FindAllUnitmUseCase(unitmService);
        this.findUnitmUseCase = new FindUnitmUseCase(unitmService);
        this.updateUnitmUseCase = new UpdateUnitmUseCase(unitmService);
    }
    public void mainMenu(){
        String opciones = "1. Add Mode Administration\n2. Search Mode Administration\n3. Update Mode Administration\n4. Delete Mode Administration\n5 List Mode Administrations\n6. Return main menu";
        int op;
        do{
            op =Integer.parseInt(JOptionPane.showInputDialog(null,opciones));
            switch (op) {
                case 1: 
                    addUnitm();
                    break;
                case 2:
                    findUnitm();
                    break;
                case 3:
                    updateUnitm();
                    break;
                case 4:
                    deleteUnitm();
                    break;
                case 5:
                    findAllUnitm();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada","Error",JOptionPane.ERROR_MESSAGE);
                    break;
            }

        }while(op!=6);

    }

    public void addUnitm() {
        String name = JOptionPane.showInputDialog(null, "Unit measurement Name:");
    
        Unitm unitm = new Unitm();
        unitm.setName(name);
    
        createUnitmUseCase.execute(unitm);
    
        JOptionPane.showMessageDialog(null, "Unit measurement created:\nId: " + unitm.getIdum() + "\nName: " + unitm.getName());
    }

    public Optional<Unitm> findUnitm() {
        int idum = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la Unit : "));
        Optional<Unitm> unitm = findUnitmUseCase.execute(idum);
        showUnitm(unitm);
        return unitm;
    }

    public void updateUnitm(){
        Optional<Unitm> UniOption = findUnitm();
        if (UniOption.isPresent()) {
            Unitm unitm = UniOption.get();
            unitm.setName(JOptionPane.showInputDialog(null, "Ingrese el Nombre de la Unit"));
            updateUnitmUseCase.execute(unitm);
            showUnitm(UniOption);
            }

     }

    public void deleteUnitm(){
        Optional<Unitm> UniOption = findUnitm();
        if ( UniOption.isPresent()) {
            Unitm unitm = UniOption.get();
            deleteUnitmUseCase.execute(unitm.getIdum());
            JOptionPane.showMessageDialog(null, "Unit deleted:\nId: " + unitm.getIdum() + "\nName: " + unitm.getName());
        }
    }

    public List<Unitm> findAllUnitm(){
        List<Unitm> unit = findAllUnitmUseCase.execute();

        String[] columns = {"ID", "Name"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Unitm country : unit) {
            Object[] row = {
                    country.getIdum(),
                    country.getName()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Unit List", JOptionPane.PLAIN_MESSAGE);
        return unit;
    }

    public void showUnitm(Optional<Unitm> unitm){

        String[] columns = {"ID", "Name"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (unitm.isPresent()) {
            Unitm mod = unitm.get();
            Object[] row = {
                    mod.getIdum(),
                    mod.getName()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Unit", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Unit List", JOptionPane.PLAIN_MESSAGE);
    

    }
    


}
