package com.famacy;

import javax.swing.JOptionPane;

import com.famacy.activeprinciple.infrastructure.ActiveprincipleController;
import com.famacy.country.infrastructure.CountryController;
import com.famacy.modeadmin.infrastructure.ModeadmiController;

public class Main {
    public static void main(String[] args) {
        String opciones = "1. Country\n2. Mode Administration\n3. Active Principle\n4. Exit...oooo";
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
                        JOptionPane.showMessageDialog(null, "Suerte nos vemos....");
                    break;
                default:
                        JOptionPane.showMessageDialog(null, "Error opcion invalida");
                    break;
            }

        }while(op!=4);
    }
}