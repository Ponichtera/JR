package agregator.model;

import agregator.valueObject.Vacancy;
import agregator.view.View;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private Provider[] providers;
    private View view;

    public Model(View view, Provider ... providers) {
        if (providers == null || providers.length == 0 || view == null) throw new IllegalArgumentException();
        this.providers = providers;
        this.view = view;
    }

    public void selectCity(String city){
        List<Vacancy> vacancies = new LinkedList<>();

        for (Provider provider : providers)
            vacancies.addAll(provider.getJavaVacancys(city, "java"));

        view.update(vacancies);
    }
}
