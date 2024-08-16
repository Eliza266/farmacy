package com.famacy.region.infrastructure;

import com.famacy.region.aplication.CreateRegionUseCase;
import com.famacy.region.aplication.DeleteRegionUseCase;
import com.famacy.region.aplication.FindAllRegionUseCase;
import com.famacy.region.aplication.FindRegionUseCase;
import com.famacy.region.aplication.UpdateRegionUseCase;
import com.famacy.region.domain.Region;
import com.famacy.region.domain.RegionService;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RegionController {
    private RegionService regionService;
    private CreateRegionUseCase createRegionUseCase;
    private DeleteRegionUseCase deleteRegionUseCase;
    private FindAllRegionUseCase findAllRegionUseCase;
    private FindRegionUseCase findRegionUseCase;
    private UpdateRegionUseCase updateRegionUseCase;


    public RegionController() {
        this.regionService = new RegionRepository();
        this.createRegionUseCase = new CreateRegionUseCase(regionService);
        this.deleteRegionUseCase = new DeleteRegionUseCase(regionService);
        this.findAllRegionUseCase = new FindAllRegionUseCase(regionService);
        this.findRegionUseCase = new FindRegionUseCase(regionService);
        this.updateRegionUseCase = new UpdateRegionUseCase(regionService);
    }
    public void mainMenu(){
        String opciones = "1. Add Region\n2. Search region\n3. Update Region\n4. Delete Region\n5 List Countries\n6. Return main menu";
        int op;
        do{
            op =Integer.parseInt(JOptionPane.showInputDialog(null,opciones));
            switch (op) {
                case 1:
                    addRegion();
                    break;
                case 2:
                    findRegion();
                    break;
                case 3:
                    updateRegion();
                    break;
                case 4:
                    deleteRegion();
                    break;
                case 5:
                    findAllRegion();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada","Error",JOptionPane.ERROR_MESSAGE);
                    break;
            }

        }while(op!=6);

    }

    public void addRegion() {
        String codReg = JOptionPane.showInputDialog(null, "Region Code:");
        String name = JOptionPane.showInputDialog(null, "Region Name:");
        String codeCountry = JOptionPane.showInputDialog(null, "Country Code:");
    
        Region region = new Region();
        region.setCodReg(codReg);
        region.setName(name);
        region.setCodeCountry(codeCountry);
    
        createRegionUseCase.execute(region);
        JOptionPane.showMessageDialog(null, "Region created:\nCode: " + region.getCodReg() + "\nName: " + region.getName() +"\nCodeCountry"+ region.getCodeCountry());
    }

    public Optional<Region> findRegion() {
        String codeRegion = JOptionPane.showInputDialog(null, "Ingrese el ID de la Region: ");
        Optional<Region> region = findRegionUseCase.execute(codeRegion);
        showRegion(region);
        return region;
    }

    public void updateRegion(){
        Optional<Region> regionOptional = findRegion();
        if (regionOptional.isPresent()) {
            Region region = regionOptional.get();
            region.setName(JOptionPane.showInputDialog(null, "Insert Name Region"));
            region.setCodeCountry(JOptionPane.showInputDialog(null, "Insert Code country"));
            updateRegionUseCase.execute(region);
            showRegion(regionOptional);
            }

    }

    public void deleteRegion(){
        Optional<Region> regionOptional = findRegion();
        if ( regionOptional.isPresent()) {
            Region region = regionOptional.get();
            deleteRegionUseCase.execute(region.getCodReg());
            JOptionPane.showMessageDialog(null, "Region deleted:\nCode: " + region.getCodReg() + "\nName: " + region.getName()+ "\nCode Country: " + region.getCodeCountry());
        }
    }

    public List<Region> findAllRegion(){
        List<Region> regiones = findAllRegionUseCase.execute();

        String[] columns = {"ID", "Name", "CODE COUNTRY"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Region region : regiones) {
            Object[] row = {
                    region.getCodReg(),
                    region.getName(),
                    region.getCodeCountry()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Region List", JOptionPane.PLAIN_MESSAGE);
        return regiones;
    }

    public void showRegion(Optional<Region> region){

        String[] columns = {"ID", "Name", "CODE COUNTRY"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (region.isPresent()) {
            Region count = region.get();
            Object[] row = {
                    count.getCodReg(),
                    count.getName(),
                    count.getCodeCountry()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Countries", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Region List", JOptionPane.PLAIN_MESSAGE);
    

    }
    


}
