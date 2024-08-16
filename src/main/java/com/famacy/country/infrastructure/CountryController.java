package com.famacy.country.infrastructure;

import com.famacy.country.aplication.CreateCountryUseCase;
import com.famacy.country.aplication.DeleteCountryUseCase;
import com.famacy.country.aplication.FindAllCountryUseCase;
import com.famacy.country.aplication.FindCountryUseCase;
import com.famacy.country.aplication.UpdateCountryUseCase;
import com.famacy.country.domain.Country;
import com.famacy.country.domain.CountryService;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CountryController {
    private CountryService countryService;
    private CreateCountryUseCase createCountryUseCase;
    private DeleteCountryUseCase deleteCountryUseCase;
    private FindAllCountryUseCase findAllCountryUseCase;
    private FindCountryUseCase findCountryUseCase;
    private UpdateCountryUseCase updateCountryUseCase;

    public CountryController() {
        this.countryService = new CountryRepository();
        this.createCountryUseCase = new CreateCountryUseCase(countryService);
        this.deleteCountryUseCase = new DeleteCountryUseCase(countryService);
        this.findAllCountryUseCase = new FindAllCountryUseCase(countryService);
        this.findCountryUseCase = new FindCountryUseCase(countryService);
        this.updateCountryUseCase = new UpdateCountryUseCase(countryService);
    }

    public void mainMenu() {
        String opciones = "1. Add Country\n2. Search country\n3. Update Country\n4. Delete Country\n5 List Countries\n6. Return to Main Menu";
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
                        addCountry();
                        break;
                    case 2:
                        findCountry();
                        break;
                    case 3:
                        updateCountry();
                        break;
                    case 4:
                        deleteCountry();
                        break;
                    case 5:
                        findAllCountry();
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

    public void addCountry() {
        String codeCountry = JOptionPane.showInputDialog(null, "Country Code:");
        if (codeCountry == null) return;

        String name = JOptionPane.showInputDialog(null, "Country Name:");
        if (name == null) return; 

        Country country = new Country();
        country.setCodeCountry(codeCountry);
        country.setName(name);

        createCountryUseCase.execute(country);

        JOptionPane.showMessageDialog(null, "Country created:\nCode: " + country.getCodeCountry() + "\nName: " + country.getName());
    }

    public Optional<Country> findCountry() {
        String codeCountry = JOptionPane.showInputDialog(null, "Ingrese el ID del Country:");
        if (codeCountry == null) return Optional.empty();  

        Optional<Country> country = findCountryUseCase.execute(codeCountry);
        showCountry(country);
        return country;
    }

    public void updateCountry() {
        Optional<Country> countryOptional = findCountry();
        if (countryOptional.isPresent()) {
            Country country = countryOptional.get();
            String newName = JOptionPane.showInputDialog(null, "Ingrese el Nombre de la Country", country.getName());
            if (newName == null) return;  

            country.setName(newName);
            updateCountryUseCase.execute(country);
            showCountry(countryOptional);
        }
    }

    public void deleteCountry() {
        Optional<Country> countryOptional = findCountry();
        if (countryOptional.isPresent()) {
            Country country = countryOptional.get();

            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el Country?\nCode: " + country.getCodeCountry() + "\nName: " + country.getName(), "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteCountryUseCase.execute(country.getCodeCountry());
                JOptionPane.showMessageDialog(null, "Country deleted:\nCode: " + country.getCodeCountry() + "\nName: " + country.getName());
            }
        }
    }

    public List<Country> findAllCountry() {
        List<Country> countries = findAllCountryUseCase.execute();

        String[] columns = { "ID", "Name" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Country country : countries) {
            Object[] row = {
                country.getCodeCountry(),
                country.getName()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Country List", JOptionPane.PLAIN_MESSAGE);
        return countries;
    }

    public void showCountry(Optional<Country> country) {
        String[] columns = { "ID", "Name" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (country.isPresent()) {
            Country c = country.get();
            Object[] row = {
                c.getCodeCountry(),
                c.getName()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Countries found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Country Details", JOptionPane.PLAIN_MESSAGE);
    }
}
