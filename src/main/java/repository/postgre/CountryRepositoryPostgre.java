package repository.postgre;

import model.Country;
import o.MainX;
import repository.CountryRepository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class CountryRepositoryPostgre implements CountryRepository {

    private Properties connectionProperties;

    public CountryRepositoryPostgre() {
        this.connectionProperties = getConnectionProperties();
    }

    private Properties getConnectionProperties() {
        Properties properties = new Properties();
        try (

                InputStream resourceAsStream = MainX.class.getClassLoader()
                        .getResourceAsStream("config.properties")
        ) {
            properties.load(resourceAsStream);

        } catch (IOException e) {
            System.out.println("Problem with config file");
        }
        return properties;
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();

        try {


            Connection connection = DriverManager.getConnection(
                    this.connectionProperties.getProperty("db.url"),
                    this.connectionProperties.getProperty("db.username"),
                    this.connectionProperties.getProperty("db.password"));


            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from country");

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                //final model.Country country = new model.Country(id,code,name,description);
                countries.add(new Country(id, code, name, description));


            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return countries;

    }

    @Override
    public Optional<Country> findOne(Long id) {
        Country country = null;
        try {


            Connection connection = DriverManager.getConnection(
                    this.connectionProperties.getProperty("db.url"),
                    this.connectionProperties.getProperty("db.username"),
                    this.connectionProperties.getProperty("db.password"));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from country where id = " + id);

            while (resultSet.next()) {
                //Long id = resultSet.getLong("id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                //final model.Country country = new model.Country(id,code,name,description);
                country = new Country(id, code, name, description);


            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return Optional.ofNullable(country);

    }
}
