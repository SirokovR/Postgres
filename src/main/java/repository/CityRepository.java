package repository;

import model.City;

import java.util.List;
import java.util.Optional;

public interface CityRepository {
    List<City> findAll();

    Optional<City> findOne(Long id);
}
