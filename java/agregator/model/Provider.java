package agregator.model;

import agregator.valueObject.Vacancy;

import java.util.List;

public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public List<Vacancy> getJavaVacancys (String city, String searchString) {
        return strategy.getVacancies(city, searchString);
    }
}
