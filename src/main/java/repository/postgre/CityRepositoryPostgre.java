package repository.postgre;

import model.City;
import o.MainX;
import repository.CityRepository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class CityRepositoryPostgre  implements CityRepository {

    private Properties connectionProperties;

    public CityRepositoryPostgre() {
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
    public List<City> findAll() {
        List<City> cities =new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection(
                    this.connectionProperties.getProperty("db.url"),
                    this.connectionProperties.getProperty("db.username"),
                    this.connectionProperties.getProperty("db.password"));


            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from city");

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Long population = resultSet.getLong("population");
                String description = resultSet.getString("description");
                Long country_id = resultSet.getLong("country_id");
                cities.add(new City(id,name,population,description,country_id));
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return cities;
    }





    @Override
    public Optional<City> findOne(Long id) {
        City city = null;
        try{
            Connection connection = DriverManager.getConnection(
                    this.connectionProperties.getProperty("db.url"),
                    this.connectionProperties.getProperty("db.username"),
                    this.connectionProperties.getProperty("db.password"));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from city where id = " + id);

            while (resultSet.next()){
                String name = resultSet.getString("name");
                Long population = resultSet.getLong("population");
                String description = resultSet.getString("description");
                Long country_id = resultSet.getLong("country_id");
                city = new City(id,name,population,description,country_id);
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return Optional.ofNullable(city);
    }
}
