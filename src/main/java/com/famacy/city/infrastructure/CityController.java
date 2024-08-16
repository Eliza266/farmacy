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
        String opciones = "1. Add City\n2. Search city\n3. Update City\n4. Delete City\n5 List Countries\n6. Return main menu";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, opciones));
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
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }

        } while (op != 6);

    }

    public void addCity() {
        String codCity = JOptionPane.showInputDialog(null, "City Code:");
        String name = JOptionPane.showInputDialog(null, "City Name:");
        String codReg = JOptionPane.showInputDialog(null, "Region Code:");

        City city = new City();
        city.setCodCity(codCity);
        city.setName(name);
        city.setCodReg(codReg);

        createCityUseCase.execute(city);
        JOptionPane.showMessageDialog(null, "City created:\nCode: " + city.getCodCity() + "\nName: " + city.getName()
                + "\nCodReg: " + city.getCodReg());
    }

    public Optional<City> findCity() {
        String codCity = JOptionPane.showInputDialog(null, "Ingrese el ID de la City: ");
        Optional<City> city = findCityUseCase.execute(codCity);
        showCity(city);
        return city;
    }

    public void updateCity() {
        Optional<City> cityOptional = findCity();
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            city.setName(JOptionPane.showInputDialog(null, "Insert Name City"));
            city.setCodReg(JOptionPane.showInputDialog(null, "Insert Code Region"));
            updateCityUseCase.execute(city);
            showCity(cityOptional);
        }

    }

    public void deleteCity() {
        Optional<City> cityOptional = findCity();
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            deleteCityUseCase.execute(city.getCodCity());
            JOptionPane.showMessageDialog(null, "City deleted:\nCode: " + city.getCodReg() + "\nName: " + city.getName()
                    + "\nCode Region: " + city.getCodCity());
        }
    }

    public List<City> findAllCity() {
        List<City> cityes = findAllCityUseCase.execute();

        String[] columns = { "ID", "Name", "CODE REGION" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (City city : cityes) {
            Object[] row = {
                    city.getCodReg(),
                    city.getName(),
                    city.getCodCity()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "City List", JOptionPane.PLAIN_MESSAGE);
        return cityes;
    }

    public void showCity(Optional<City> city) {

        String[] columns = { "ID", "Name", "CODE REGION" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (city.isPresent()) {
            City count = city.get();
            Object[] row = {
                    count.getCodReg(),
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

        JOptionPane.showMessageDialog(null, panel, "City List", JOptionPane.PLAIN_MESSAGE);

    }

}
