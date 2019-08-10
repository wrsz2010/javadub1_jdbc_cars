package javadub1.jdbc.auta;

import javax.swing.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarDao {

    private JdbcConnect JdbcConnect;

    public CarDao() {
        try {
            JdbcConnect = new JdbcConnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Blad konfiguracji bazodanowej!");
            System.exit(2);
        }
    }

    private final static String CREATE_TABLE_STATEMENT =
            "create table if not exists cars(\n" +
                    "'id' BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                    "'nazwa' VARCHAR(255) NOT NULL,\n" +
                    "'marka' VARCHAR(255) NOT NULL,\n" +
                    "'przebieg' DOUBLE not null\n" +
                    "`data` DATE not null\n" +
                    ");";
    private final static String INSERT_CAR_STATEMENT =
            "INSERT INTO `car_database`.`cars`\n" +
                    "(`id`,`nazwa`,`marka`,`przebieg`,`data`)\n" +
                    "VALUES\n" +
                    "(NULL, ?, ?, ?, ?);";

    private final static String SELECT_ALL_CARS = "SELECT * FROM car_database.cars;";

    private final static String DELETE_CAR = "DELETE FROM `car_database`.`cars` WHERE `id` = ?;";

    private final static String FIND_CAR_BY_NAZWA = "SELECT * FROM car_database.cars WHERE `nazwa` = ?;";

    private final static String FIND_CAR_BY_PRZEBIEG = "SELECT * FROM car_database.cars WHERE `przebieg` = ?;";

    private final static String FIND_CAR_BY_DATE = "";

    public void deleteCar(Long id) {
        try {
            try (PreparedStatement statement = JdbcConnect.getConnection().prepareStatement(DELETE_CAR)) {
                statement.setLong(1, id);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAllCars() {
        List<Car> list = new ArrayList<>();

        try (PreparedStatement statement = JdbcConnect.getConnection().prepareStatement(SELECT_ALL_CARS)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Car pobrany = new Car();
                pobrany.setId(resultSet.getLong(1));
                pobrany.setNazwa(resultSet.getString(2));
                pobrany.setMarka(resultSet.getString(3));
                pobrany.setPrzebieg(resultSet.getDouble(4));
                pobrany.setDate(resultSet.getDate(5).toLocalDate());
                list.add(pobrany);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public void insertCar(Car car) {
        try {
            try (PreparedStatement statement = JdbcConnect.getConnection().prepareStatement(INSERT_CAR_STATEMENT)) {
                statement.setString(1, car.getNazwa());
                statement.setString(2, car.getMarka());
                statement.setDouble(3, car.getPrzebieg());
                statement.setDate(4, Date.valueOf(car.getDate()));

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getCarByNazwa(String nazwa) {
        List<Car> list = new ArrayList<>();

        try (PreparedStatement statement = JdbcConnect.getConnection().prepareStatement(FIND_CAR_BY_NAZWA)) {
            statement.setString(1, nazwa);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Car pobrany = new Car();
                pobrany.setId(resultSet.getLong(1));
                pobrany.setNazwa(resultSet.getString(2));
                pobrany.setMarka(resultSet.getString(3));
                pobrany.setPrzebieg(resultSet.getDouble(4));
                pobrany.setDate(resultSet.getDate(5).toLocalDate());
                list.add(pobrany);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Car> getCarByPrzebieg(Double przebieg) {
        List<Car> list = new ArrayList<>();

        try (PreparedStatement statement = JdbcConnect.getConnection().prepareStatement(FIND_CAR_BY_PRZEBIEG)) {
            statement.setDouble(1, przebieg);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Car pobrany = new Car();
                pobrany.setId(resultSet.getLong(1));
                pobrany.setNazwa(resultSet.getString(2));
                pobrany.setMarka(resultSet.getString(3));
                pobrany.setPrzebieg(resultSet.getDouble(4));
                pobrany.setDate(resultSet.getDate(5).toLocalDate());
                list.add(pobrany);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
