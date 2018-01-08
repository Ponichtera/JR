package agregator;


import agregator.model.InfoPracaStrategy;
import agregator.model.Model;
import agregator.model.Provider;
import agregator.view.HtmlView;

import java.io.IOException;

public class _AgregatorLauncher {
    public static void main(String[] args) throws IOException {
        Provider infoPraca = new Provider(new InfoPracaStrategy());
        HtmlView html = new HtmlView();
        Model model = new Model(html, infoPraca);
        Controller controller = new Controller(model);
        html.setController(controller);

        controller.onCitySelect("");
    }
}
