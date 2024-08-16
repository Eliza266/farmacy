package com.famacy;

import javax.swing.JOptionPane;
import com.famacy.activeprinciple.infrastructure.ActiveprincipleController;
import com.famacy.city.infrastructure.CityController;
import com.famacy.country.infrastructure.CountryController;
import com.famacy.customer.infrastructure.CustomerController;
import com.famacy.laboratory.infrastructure.LaboratoryController;
import com.famacy.medicine.infrastructure.MedicineController;
import com.famacy.modeadmin.infrastructure.ModeadmiController;
import com.famacy.pharmacy.infrastructure.PharmacyController;
import com.famacy.pharmacymedicine.infrastructure.PharmacymedicineController;
import com.famacy.region.infrastructure.RegionController;
import com.famacy.unitmeasurement.infrastructure.UnitmController;

public class Main {
    public static void main(String[] args) {
        String opciones = "1. Country\n" +
                          "2. Mode Administration\n" +
                          "3. Active Principle\n" +
                          "4. Unit\n" +
                          "5. Region\n" +
                          "6. City\n" +
                          "7. Laboratory\n" +
                          "8. Customer\n" +
                          "9. Medicine\n" +
                          "10. Pharmacy\n" +
                          "11. Pharmacy-Medicine\n" +
                          "12. Exit";
        int op = 0;
        do {
            String input = JOptionPane.showInputDialog(null, opciones);
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Saliendo...");
                break;
            }
            try {
                op = Integer.parseInt(input);
                if (op < 1 || op > 12) {
                    JOptionPane.showMessageDialog(null, "Error opción inválida");
                    continue;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error opción inválida");
                continue;
            }

            switch (op) {
                case 1:
                    new CountryController().mainMenu();
                    break;
                case 2:
                    new ModeadmiController().mainMenu();
                    break;
                case 3:
                    new ActiveprincipleController().mainMenu();
                    break;
                case 4:
                    new UnitmController().mainMenu();
                    break;
                case 5:
                    new RegionController().mainMenu();
                    break;
                case 6:
                    new CityController().mainMenu();
                    break;
                case 7:
                    new LaboratoryController().mainMenu();
                    break;
                case 8:
                    new CustomerController().mainMenu();
                    break;
                case 9:
                    new MedicineController().mainMenu();
                    break;
                case 10:
                    new PharmacyController().mainMenu();
                    break;
                case 11:
                    new PharmacymedicineController().mainMenu();
                    break;
                case 12:
                    JOptionPane.showMessageDialog(null, "Suerte nos vemos....");
                    break;
            }
        } while (op != 12);
    }
}
