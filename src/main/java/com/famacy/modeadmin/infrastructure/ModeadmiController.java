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
    public void mainMenu(){
        String opciones = "1. Add Mode Administration\n2. Search Mode Administration\n3. Update Mode Administration\n4. Delete Mode Administration\n5 List Mode Administrations\n6. Return main menu";
        int op;
        do{
            op =Integer.parseInt(JOptionPane.showInputDialog(null,opciones));
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
                    findAllmModeadmi();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada","Error",JOptionPane.ERROR_MESSAGE);
                    break;
            }

        }while(op!=6);

    }

    public void addModeadmi() {
        String name = JOptionPane.showInputDialog(null, "Mode administration Name:");
    
        Modeadmi modeadmi = new Modeadmi();
        modeadmi.setName(name);
    
        createModeadmiUseCase.execute(modeadmi);
    
        JOptionPane.showMessageDialog(null, "Mode Administration created:\nId: " + modeadmi.getIdap() + "\nName: " + modeadmi.getName());
    }

    public Optional<Modeadmi> findModeadmi() {
        int idap = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la Country: "));
        Optional<Modeadmi> modeadmi = findModeadmiUseCase.execute(idap);
        showModeadmi(modeadmi);
        return modeadmi;
    }

    public void updateModeadmi(){
        Optional<Modeadmi> ModeOption = findModeadmi();
        if (ModeOption.isPresent()) {
            Modeadmi modeadmi = ModeOption.get();
            modeadmi.setName(JOptionPane.showInputDialog(null, "Ingrese el Nombre de la country"));
            updateModeadmiUseCase.execute(modeadmi);
            showModeadmi(ModeOption);
            }

     }

    public void deleteModeadmi(){
        Optional<Modeadmi> modeOption = findModeadmi();
        if ( modeOption.isPresent()) {
            Modeadmi modeadmi = modeOption.get();
            deleteModeadmiUseCase.execute(modeadmi.getIdap());
            JOptionPane.showMessageDialog(null, "Mode deleted:\nId: " + modeadmi.getIdap() + "\nName: " + modeadmi.getName());
        }
    }

    public List<Modeadmi> findAllmModeadmi(){
        List<Modeadmi> modes = findAllModeadmiUseCase.execute();

        String[] columns = {"ID", "Name"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Modeadmi country : modes) {
            Object[] row = {
                    country.getIdap(),
                    country.getName()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Modes List", JOptionPane.PLAIN_MESSAGE);
        return modes;
    }

    public void showModeadmi(Optional<Modeadmi> modeadmi){

        String[] columns = {"ID", "Name"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (modeadmi.isPresent()) {
            Modeadmi mod = modeadmi.get();
            Object[] row = {
                    mod.getIdap(),
                    mod.getName()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Modes", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Modes List", JOptionPane.PLAIN_MESSAGE);
    

    }
    


}
