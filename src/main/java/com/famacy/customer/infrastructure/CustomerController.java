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
        String opciones = "1. Add Customer\n2. Search customer\n3. Update Customer\n4. Delete Customer\n5 List Countries\n6. Return main menu";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, opciones));
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
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }

        } while (op != 6);

    }

    public void addCustomer() {
        String idCust = JOptionPane.showInputDialog(null, "Customer Id:");
        String name = JOptionPane.showInputDialog(null, "Customer Name:");
        String lastName = JOptionPane.showInputDialog(null, "Customer LastName:");
        String email  = JOptionPane.showInputDialog(null, "Customer email:");
        String birthDate = JOptionPane.showInputDialog(null, "Customer BirthDate:");
        float longe  = Float.parseFloat(JOptionPane.showInputDialog(null, "Customer Long:"));
        float lat  = Float.parseFloat(JOptionPane.showInputDialog(null, "Customer Lat:"));
        String codCity  = JOptionPane.showInputDialog(null, "Customer Code City:");

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
        String idCust = JOptionPane.showInputDialog(null, "Ingrese el ID de la Customer: ");
        Optional<Customer> customer = findCustomerUseCase.execute(idCust);
        showCustomer(customer);
        return customer;
    }

    public void updateCustomer() {
        Optional<Customer> customerOptional = findCustomer();
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(JOptionPane.showInputDialog(null, "Insert Name Customer"));
            customer.setLastName(JOptionPane.showInputDialog(null, "Insert LastName Customer"));
            customer.setEmail(JOptionPane.showInputDialog(null, "Insert email Customer"));
            customer.setBirthDate(JOptionPane.showInputDialog(null, "Insert BirthDate Customer"));
            customer.setLonge(Float.parseFloat(JOptionPane.showInputDialog(null, "Insert Long Customer")));
            customer.setLat(Float.parseFloat(JOptionPane.showInputDialog(null, "Insert Lat Customer")));
            customer.setCodCity(JOptionPane.showInputDialog(null, "Insert Code City"));
            updateCustomerUseCase.execute(customer);
            showCustomer(customerOptional);
        }

    }

    public void deleteCustomer() {
        Optional<Customer> customerOptional = findCustomer();
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            deleteCustomerUseCase.execute(customer.getIdCust());
        }
    }

    public List<Customer> findAllCustomer() {
        List<Customer> customeres = findAllCustomerUseCase.execute();

        String[] columns = { "ID", "Name","LastName","Email","BirthDate", "Longe", "Lati", "CODE CITY" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Customer customer : customeres) {
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
        return customeres;
    }

    public void showCustomer(Optional<Customer> customer) {

        String[] columns = { "ID", "Name","LastName","Email","BirthDate", "Longe", "Lati", "CODE CITY" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (customer.isPresent()) {
            Customer count = customer.get();
            Object[] row = {
                    count.getIdCust(),
                    count.getName(),
                    count.getLastName(),
                    count.getEmail(),
                    count.getBirthDate(),
                    count.getLonge(),
                    count.getLat(),
                    count.getCodCity()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Customers", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        
        table.setPreferredSize(new Dimension(800, 400));
        scrollPane.setPreferredSize(new Dimension(800, 400));
        panel.setPreferredSize(new Dimension(800, 400));

        JOptionPane.showMessageDialog(null, panel, "Customer List", JOptionPane.PLAIN_MESSAGE);

    }

}
