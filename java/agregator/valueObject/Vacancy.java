package agregator.valueObject;

public class Vacancy {
    private String title;
    private String city;
    private String company;
    private String url;
    private String salary;
    private String site;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy vacancy = (Vacancy) o;

        if (title != null ? !title.equals(vacancy.title) : vacancy.title != null) return false;
        if (city != null ? !city.equals(vacancy.city) : vacancy.city != null) return false;
        if (company != null ? !company.equals(vacancy.company) : vacancy.company != null) return false;
        if (url != null ? !url.equals(vacancy.url) : vacancy.url != null) return false;
        if (salary != null ? !salary.equals(vacancy.salary) : vacancy.salary != null) return false;
        return site != null ? site.equals(vacancy.site) : vacancy.site == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (site != null ? site.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "title='" + title + '\'' +
                ", city='" + city + '\'' +
                ", company='" + company + '\'' +
                ", url='" + url + '\'' +
                ", salary=" + salary +
                ", site='" + site + '\'' +
                '}';
    }
}
