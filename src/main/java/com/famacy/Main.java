package com.famacy;

import javax.swing.JOptionPane;

import com.famacy.country.infrastructure.CountryController;

public class Main {
    public static void main(String[] args) {
        String opciones = "1. Country\n2. Exit...oooo";
        int op;
        do{
            op =Integer.parseInt(JOptionPane.showInputDialog(null,opciones));
            switch (op) {
                case 1:
                        CountryController consoleAdapter = new CountryController();
                        consoleAdapter.mainMenu();
                    break;
                case 2:
                        JOptionPane.showMessageDialog(null, "Suerte nos vemos....");
                    break;
                default:
                        JOptionPane.showMessageDialog(null, "Error opcion invalida");
                    break;
            }

        }while(op!=2);
    }
}