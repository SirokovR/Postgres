package repository;

import model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    List<Country> findAll();
    Optional<Country> findOne(Long id);
}
