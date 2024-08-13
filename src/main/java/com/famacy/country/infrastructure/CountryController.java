package com.famacy.country.infrastructure;

import com.famacy.country.aplication.CreateCountryUseCase;
import com.famacy.country.aplication.DeleteCountryUseCase;
import com.famacy.country.aplication.FindAllCountryUseCase;
import com.famacy.country.aplication.FindCountryUseCase;
import com.famacy.country.aplication.UpdateCountryUseCase;
import com.famacy.country.domain.Country;
import com.famacy.country.domain.CountryService;

import javax.swing.JOptionPane;

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
        this.findAllCountryUseCase = new FindAllCountryUseCase();
        this.findCountryUseCase = new FindCountryUseCase();
        // this.updateCountryUseCase = new UpdateCountryUseCase(countryService);
    }
    public void mainMenu(){
        String opciones = "1. Add Country\n2. Search country\n3. Return main menu";
        int op;
        do{
            op =Integer.parseInt(JOptionPane.showInputDialog(null,opciones));
            switch (op) {
                case 1:
                    addCountry();
                    break;
                case 2:
                    //findCountry();
                    break;
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada","Error",JOptionPane.ERROR_MESSAGE);
                    break;
            }

        }while(op!=3);

    }
    public void addCountry() {
        String codeCountry = JOptionPane.showInputDialog(null,"Country Code :");
        String name = JOptionPane.showInputDialog(null,"Country Name :");

        Country country = new Country();
        country.setCodeCountry(codeCountry);
        country.setName(name);

        createCountryUseCase.execute(country);

}


}
