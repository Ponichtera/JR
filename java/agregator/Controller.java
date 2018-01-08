package agregator;

import agregator.model.Model;

public class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void onCitySelect(String city) {
        model.selectCity(city);
    }
}
