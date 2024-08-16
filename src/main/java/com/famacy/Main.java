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
        int op;
        do{
            op =Integer.parseInt(JOptionPane.showInputDialog(null,opciones));
            switch (op) {
                case 1:
                        CountryController consoleAdapter = new CountryController();
                        consoleAdapter.mainMenu();
                    break;
                case 2:
                    ModeadmiController consoleMode = new ModeadmiController();
                    consoleMode.mainMenu();
                break;
                case 3:
                    ActiveprincipleController consoleActive = new ActiveprincipleController();
                    consoleActive.mainMenu();
                break;
                case 4:
                    UnitmController consoleUnit = new UnitmController();
                    consoleUnit.mainMenu();
                break;
                case 5:
                    RegionController consoleReg = new RegionController();
                    consoleReg.mainMenu();
                break;
                case 6:
                    CityController consoleCity = new CityController();
                    consoleCity.mainMenu();
                break;
                case 7:
                    LaboratoryController consoleLab = new LaboratoryController();
                    consoleLab.mainMenu();
                break;
                case 8:
                    CustomerController consoleCus = new CustomerController();
                    consoleCus.mainMenu();
                break;
                case 9:
                    MedicineController consoleMed = new MedicineController();
                    consoleMed.mainMenu();
                break;
                case 10:
                    PharmacyController consolePha = new PharmacyController();
                    consolePha.mainMenu();
                break;
                case 11:
                     PharmacymedicineController consolePM = new PharmacymedicineController();
                     consolePM.mainMenu();
                break;
                case 12:
                        JOptionPane.showMessageDialog(null, "Suerte nos vemos....");
                    break;
                default:
                        JOptionPane.showMessageDialog(null, "Error opcion invalida");
                    break;
            }

        }while(op!=12);
    }
}