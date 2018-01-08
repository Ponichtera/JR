package agregator.view;

import agregator.Controller;
import agregator.valueObject.Vacancy;

import java.util.List;

public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
