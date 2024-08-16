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
        String opciones = "1. Add Country\n2. Search country\n3. Update Country\n4. Delete Country\n5 List Countries\n6. Return main menu";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, opciones));
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
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }

        } while (op != 6);

    }

    public void addCountry() {
        String codeCountry = JOptionPane.showInputDialog(null, "Country Code:");
        String name = JOptionPane.showInputDialog(null, "Country Name:");

        Country country = new Country();
        country.setCodeCountry(codeCountry);
        country.setName(name);

        createCountryUseCase.execute(country);

        JOptionPane.showMessageDialog(null,
                "Country created:\nCode: " + country.getCodeCountry() + "\nName: " + country.getName());
    }

    public Optional<Country> findCountry() {
        String codeCountry = JOptionPane.showInputDialog(null, "Ingrese el ID de la Country: ");
        Optional<Country> country = findCountryUseCase.execute(codeCountry);
        showCountry(country);
        return country;
    }

    public void updateCountry() {
        Optional<Country> countryOptional = findCountry();
        if (countryOptional.isPresent()) {
            Country country = countryOptional.get();
            country.setName(JOptionPane.showInputDialog(null, "Ingrese el Nombre de la country"));
            updateCountryUseCase.execute(country);
            showCountry(countryOptional);
        }

    }

    public void deleteCountry() {
        Optional<Country> countryOptional = findCountry();
        if (countryOptional.isPresent()) {
            Country country = countryOptional.get();
            deleteCountryUseCase.execute(country.getCodeCountry());
            JOptionPane.showMessageDialog(null,
                    "Country deleted:\nCode: " + country.getCodeCountry() + "\nName: " + country.getName());
        }
    }

    public List<Country> findAllCountry() {
        List<Country> countryes = findAllCountryUseCase.execute();

        String[] columns = { "ID", "Name" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Country country : countryes) {
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
        return countryes;
    }

    public void showCountry(Optional<Country> country) {

        String[] columns = { "ID", "Name" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (country.isPresent()) {
            Country count = country.get();
            Object[] row = {
                    count.getCodeCountry(),
                    count.getName()
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

        JOptionPane.showMessageDialog(null, panel, "Country List", JOptionPane.PLAIN_MESSAGE);

    }

}
