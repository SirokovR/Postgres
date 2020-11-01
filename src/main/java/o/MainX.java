package o;

import model.City;
import model.Country;
import repository.CityRepository;
import repository.CountryRepository;
import repository.postgre.CityRepositoryPostgre;
import repository.postgre.CountryRepositoryPostgre;

import java.util.List;

import static java.util.stream.Collectors.toList;


public class MainX {
    public static void main(String[] args) {
        CountryRepository countryRepository = new CountryRepositoryPostgre();
        final List<Country> countries = countryRepository.findAll();
        System.out.println(countries.stream().map(Country::getId).collect(toList()));

        final Country country = countryRepository.findOne(4L).orElseThrow(RuntimeException::new);
        System.out.println(country);


        CityRepository cityRepository = new CityRepositoryPostgre();
        final List<City> cities = cityRepository.findAll();
        System.out.println(cities.stream().map(City::getId).collect(toList()));

        final City city = cityRepository.findOne(2L).orElseThrow(RuntimeException::new);
        System.out.println(city);

    }
}