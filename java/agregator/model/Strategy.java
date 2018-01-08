package agregator.model;

import agregator.valueObject.Vacancy;

import java.util.List;

public interface Strategy {
    List<Vacancy> getVacancies(String city, String searchString);
}
