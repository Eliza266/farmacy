package com.famacy.customer.infrastructure;

import com.famacy.customer.aplication.CreateCustomerUseCase;
import com.famacy.customer.aplication.DeleteCustomerUseCase;
import com.famacy.customer.aplication.FindAllCustomerUseCase;
import com.famacy.customer.aplication.FindCustomerUseCase;
import com.famacy.customer.aplication.UpdateCustomerUseCase;
import com.famacy.customer.domain.Customer;
import com.famacy.customer.domain.CustomerService;

import java.awt.Dimension;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerController {
    private CustomerService customerService;
    private CreateCustomerUseCase createCustomerUseCase;
    private DeleteCustomerUseCase deleteCustomerUseCase;
    private FindAllCustomerUseCase findAllCustomerUseCase;
    private FindCustomerUseCase findCustomerUseCase;
    private UpdateCustomerUseCase updateCustomerUseCase;

    public CustomerController() {
        this.customerService = new CustomerRepository();
        this.createCustomerUseCase = new CreateCustomerUseCase(customerService);
        this.deleteCustomerUseCase = new DeleteCustomerUseCase(customerService);
        this.findAllCustomerUseCase = new FindAllCustomerUseCase(customerService);
        this.findCustomerUseCase = new FindCustomerUseCase(customerService);
        this.updateCustomerUseCase = new UpdateCustomerUseCase(customerService);
    }

    public void mainMenu() {
        String opciones = "1. Add Customer\n2. Search customer\n3. Update Customer\n4. Delete Customer\n5 List Customers\n6. Return to Main Menu";
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
                        addCustomer();
                        break;
                    case 2:
                        findCustomer();
                        break;
                    case 3:
                        updateCustomer();
                        break;
                    case 4:
                        deleteCustomer();
                        break;
                    case 5:
                        findAllCustomer();
                        break;
                    case 6:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error en la opción ingresada", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (op != 6);
    }

    public void addCustomer() {
        String idCust = JOptionPane.showInputDialog(null, "Customer Id:");
        if (idCust == null) return;

        String name = JOptionPane.showInputDialog(null, "Customer Name:");
        if (name == null) return;

        String lastName = JOptionPane.showInputDialog(null, "Customer LastName:");
        if (lastName == null) return;

        String email = JOptionPane.showInputDialog(null, "Customer Email:");
        if (email == null) return;

        String birthDate = JOptionPane.showInputDialog(null, "Customer BirthDate:");
        if (birthDate == null) return;

        float longe = 0;
        try {
            longe = Float.parseFloat(JOptionPane.showInputDialog(null, "Customer Longitude:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Longitude must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        float lat = 0;
        try {
            lat = Float.parseFloat(JOptionPane.showInputDialog(null, "Customer Latitude:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Latitude must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String codCity = JOptionPane.showInputDialog(null, "Customer Code City:");
        if (codCity == null) return;

        Customer customer = new Customer();
        customer.setIdCust(idCust);
        customer.setName(name);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setBirthDate(birthDate);
        customer.setLonge(longe);
        customer.setLat(lat);
        customer.setCodCity(codCity);

        createCustomerUseCase.execute(customer);
        JOptionPane.showMessageDialog(null,
                "Customer created:\n" +
                        "ID: " + customer.getIdCust() + "\n" +
                        "Name: " + customer.getName() + " " + customer.getLastName() + "\n" +
                        "Email: " + customer.getEmail() + "\n" +
                        "Birth Date: " + customer.getBirthDate() + "\n" +
                        "Location (Longitude, Latitude): " + customer.getLonge() + ", " + customer.getLat() + "\n" +
                        "City Code: " + customer.getCodCity());
    }

    public Optional<Customer> findCustomer() {
        String idCust = JOptionPane.showInputDialog(null, "Ingrese el ID del Customer:");
        if (idCust == null) return Optional.empty();

        Optional<Customer> customer = findCustomerUseCase.execute(idCust);
        showCustomer(customer);
        return customer;
    }

    public void updateCustomer() {
        Optional<Customer> customerOptional = findCustomer();
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            String newName = JOptionPane.showInputDialog(null, "Insert Name Customer", customer.getName());
            if (newName == null) return;

            String newLastName = JOptionPane.showInputDialog(null, "Insert LastName Customer", customer.getLastName());
            if (newLastName == null) return;

            String newEmail = JOptionPane.showInputDialog(null, "Insert Email Customer", customer.getEmail());
            if (newEmail == null) return;

            String newBirthDate = JOptionPane.showInputDialog(null, "Insert BirthDate Customer", customer.getBirthDate());
            if (newBirthDate == null) return;

            float newLonge = 0;
            try {
                newLonge = Float.parseFloat(JOptionPane.showInputDialog(null, "Insert Longitude Customer", customer.getLonge()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Longitude must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            float newLat = 0;
            try {
                newLat = Float.parseFloat(JOptionPane.showInputDialog(null, "Insert Latitude Customer", customer.getLat()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Latitude must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newCodCity = JOptionPane.showInputDialog(null, "Insert Code City", customer.getCodCity());
            if (newCodCity == null) return;

            customer.setName(newName);
            customer.setLastName(newLastName);
            customer.setEmail(newEmail);
            customer.setBirthDate(newBirthDate);
            customer.setLonge(newLonge);
            customer.setLat(newLat);
            customer.setCodCity(newCodCity);

            updateCustomerUseCase.execute(customer);
            showCustomer(customerOptional);
        }
    }

    public void deleteCustomer() {
        Optional<Customer> customerOptional = findCustomer();
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el Customer?\nID: " + customer.getIdCust() + "\nName: " + customer.getName(), "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteCustomerUseCase.execute(customer.getIdCust());
                JOptionPane.showMessageDialog(null, "Customer deleted:\nID: " + customer.getIdCust() + "\nName: " + customer.getName());
            }
        }
    }

    public List<Customer> findAllCustomer() {
        List<Customer> customers = findAllCustomerUseCase.execute();

        String[] columns = { "ID", "Name", "LastName", "Email", "BirthDate", "Longe", "Lat", "CODE CITY" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Customer customer : customers) {
            Object[] row = {
                    customer.getIdCust(),
                    customer.getName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getBirthDate(),
                    customer.getLonge(),
                    customer.getLat(),
                    customer.getCodCity()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        table.setPreferredSize(new Dimension(800, 400));
        scrollPane.setPreferredSize(new Dimension(800, 400));
        panel.setPreferredSize(new Dimension(800, 400));

        JOptionPane.showMessageDialog(null, panel, "Customer List", JOptionPane.PLAIN_MESSAGE);
        return customers;
    }

    public void showCustomer(Optional<Customer> customer) {
        String[] columns = { "ID", "Name", "LastName", "Email", "BirthDate", "Longe", "Lat", "CODE CITY" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (customer.isPresent()) {
            Customer cust = customer.get();
            Object[] row = {
                    cust.getIdCust(),
                    cust.getName(),
                    cust.getLastName(),
                    cust.getEmail(),
                    cust.getBirthDate(),
                    cust.getLonge(),
                    cust.getLat(),
                    cust.getCodCity()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Customers found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        table.setPreferredSize(new Dimension(800, 400));
        scrollPane.setPreferredSize(new Dimension(800, 400));
        panel.setPreferredSize(new Dimension(800, 400));

        JOptionPane.showMessageDialog(null, panel, "Customer Details", JOptionPane.PLAIN_MESSAGE);
    }
}
