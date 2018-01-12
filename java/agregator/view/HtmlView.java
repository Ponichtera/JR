package agregator.view;

import agregator.Controller;
import agregator.valueObject.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./src/main/java/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";

    private Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("białystok");
    }

    private String getUpdatedFileContent(List <Vacancy> vacancies) {
        String fileContent;
        try {
            Document document = getDocument();

            Element template = document.selectFirst(".template");
            Element tempVacancyElement = template.clone();
            tempVacancyElement.removeAttr("style");
            tempVacancyElement.removeClass("template");

            document.select("tr[class=vacancy]").remove();

            for (Vacancy vacancy : vacancies) {
                Element vacancyElement = tempVacancyElement.clone();
                vacancyElement.getElementsByClass("city").first().text(vacancy.getCity().length() > 21 ? vacancy.getCity().substring(0, 20) : vacancy.getCity());
                vacancyElement.getElementsByClass("companyName").first().text(vacancy.getCompany());
               // vacancyElement.getElementsByClass("salary").first().text(vacancy.getSalary());
                Element link = vacancyElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle())
                    .attr("href", vacancy.getUrl());

                template.before(vacancyElement.outerHtml());
            }

            fileContent = document.html();
        } catch (IOException e) { fileContent = "Ups something went wrong."; }

        return fileContent;
    }

    private void updateFile(String fileContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(fileContent);
        } catch (IOException e) {e.printStackTrace();}
    }


    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
        System.out.println("Done, " + vacancies.size() + " offers found.");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
