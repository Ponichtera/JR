package agregator.model;

import agregator.valueObject.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class InfoPracaStrategy implements Strategy {

    public Document getDocument(int page, String city, String searchString) {

        final String URL_FORMAT = "https://www.infopraca.pl/praca?q=%s&lc=%s&pg=%d";
        String url = String.format(URL_FORMAT, searchString, city, page);
        Document document = new Document("");
        try {
            document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
                    .timeout(10000)
                    .get();
        } catch (Exception ignored) {}

        return document;
    }


    @Override
    public List<Vacancy> getVacancies(String city, String searchString) {

        List<Vacancy> vacancies = new ArrayList<>();
        int page = 1;

            while (true) {
                Document document = getDocument(page++, city, searchString);

                Elements elements = document.select("div.job-offer-content");
                if (elements.size() == 0) break;

                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(element.select("h2.p-job-title").text());
                    vacancy.setCompany(element.select("h3.p-name.company").text());
                    vacancy.setCity(element.select("h4.serp-one-location span.p-locality").text());
                    String url = element.select("h2.p-job-title a").attr("href");
                    vacancy.setUrl("https://www.infopraca.pl/" + url);
                    vacancy.setSite("infopraca.pl");
                    vacancies.add(vacancy);
                }
            }
        return vacancies;
    }
}
