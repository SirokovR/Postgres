package model;

public class City {
    private long id;
    private String name;
    private long population;
    private String description;
    private long country_id;

    public City(long id, String name, long population, String description, long country_id) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.description = description;
        this.country_id = country_id;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", description='" + description + '\'' +
                ", country_id=" + country_id +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(long country_id) {
        this.country_id = country_id;
    }
}
