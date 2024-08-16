package com.famacy.city.infrastructure;

import com.famacy.city.aplication.CreateCityUseCase;
import com.famacy.city.aplication.DeleteCityUseCase;
import com.famacy.city.aplication.FindAllCityUseCase;
import com.famacy.city.aplication.FindCityUseCase;
import com.famacy.city.aplication.UpdateCityUseCase;
import com.famacy.city.domain.City;
import com.famacy.city.domain.CityService;

import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CityController {
    private CityService cityService;
    private CreateCityUseCase createCityUseCase;
    private DeleteCityUseCase deleteCityUseCase;
    private FindAllCityUseCase findAllCityUseCase;
    private FindCityUseCase findCityUseCase;
    private UpdateCityUseCase updateCityUseCase;

    public CityController() {
        this.cityService = new CityRepository();
        this.createCityUseCase = new CreateCityUseCase(cityService);
        this.deleteCityUseCase = new DeleteCityUseCase(cityService);
        this.findAllCityUseCase = new FindAllCityUseCase(cityService);
        this.findCityUseCase = new FindCityUseCase(cityService);
        this.updateCityUseCase = new UpdateCityUseCase(cityService);
    }

    public void mainMenu() {
        String opciones = "1. Add City\n2. Search City\n3. Update City\n4. Delete City\n5. List Cities\n6. Return to Main Menu";
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
                        addCity();
                        break;
                    case 2:
                        findCity();
                        break;
                    case 3:
                        updateCity();
                        break;
                    case 4:
                        deleteCity();
                        break;
                    case 5:
                        findAllCity();
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

    public void addCity() {
        String codCity = JOptionPane.showInputDialog(null, "City Code:");
        if (codCity == null) return;

        String name = JOptionPane.showInputDialog(null, "City Name:");
        if (name == null) return;

        String codReg = JOptionPane.showInputDialog(null, "Region Code:");
        if (codReg == null) return;

        City city = new City();
        city.setCodCity(codCity);
        city.setName(name);
        city.setCodReg(codReg);

        createCityUseCase.execute(city);
        JOptionPane.showMessageDialog(null, "City created:\nCode: " + city.getCodCity() + "\nName: " + city.getName() + "\nRegion Code: " + city.getCodReg());
    }

    public Optional<City> findCity() {
        String codCity = JOptionPane.showInputDialog(null, "Ingrese el ID de la City:");
        if (codCity == null) return Optional.empty();

        Optional<City> city = findCityUseCase.execute(codCity);
        showCity(city);
        return city;
    }

    public void updateCity() {
        Optional<City> cityOptional = findCity();
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();

            String newName = JOptionPane.showInputDialog(null, "Insert City Name", city.getName());
            if (newName == null) return;

            String newCodReg = JOptionPane.showInputDialog(null, "Insert Region Code", city.getCodReg());
            if (newCodReg == null) return;

            city.setName(newName);
            city.setCodReg(newCodReg);
            updateCityUseCase.execute(city);
            showCity(cityOptional);
        }
    }

    public void deleteCity() {
        Optional<City> cityOptional = findCity();
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();

            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar la ciudad?\nCode: " + city.getCodCity() + "\nName: " + city.getName() + "\nRegion Code: " + city.getCodReg(), "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteCityUseCase.execute(city.getCodCity());
                JOptionPane.showMessageDialog(null, "City deleted:\nCode: " + city.getCodCity() + "\nName: " + city.getName() + "\nRegion Code: " + city.getCodReg());
            }
        }
    }

    public List<City> findAllCity() {
        List<City> cities = findAllCityUseCase.execute();

        String[] columns = {"ID", "Name", "Region Code"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (City city : cities) {
            Object[] row = {city.getCodCity(), city.getName(), city.getCodReg()};
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "City List", JOptionPane.PLAIN_MESSAGE);
        return cities;
    }

    public void showCity(Optional<City> city) {
        String[] columns = {"ID", "Name", "Region Code"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (city.isPresent()) {
            City count = city.get();
            Object[] row = {count.getCodCity(), count.getName(), count.getCodReg()};
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Cities found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "City Details", JOptionPane.PLAIN_MESSAGE);
    }
}
