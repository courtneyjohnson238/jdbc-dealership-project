package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle
        String query = "insert into Vehicle (vin, make, model, year,sold, color, vehicleType, odometer, price) values " +
                "(?,?,?,?,?,?,?,?,?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);){

            // Set the parameter values for the insert query
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4,vehicle.getYear());
            preparedStatement.setBoolean(5, vehicle.isSold());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7, vehicle.getVehicleType());
            preparedStatement.setDouble(8,vehicle.getOdometer());
            preparedStatement.setDouble(9,vehicle.getPrice());


            // Execute the insert query
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");



        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
        String query = "DELETE FROM vehicles WHERE VIN = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            // Set the parameter value for the delete query
            preparedStatement.setString(1, VIN);

            // Execute the delete query
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE price between ? and ?;";
        Vehicle vehicle = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);
            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                     String vin = results.getString("VIN");
                    String make = results.getString("make");
                    String model = results.getString("model");
                    int year = results.getInt("year");
                    boolean sold = results.getBoolean("SOLD");
                    String color = results.getString("color");
                    String type = results.getString("vehicleType");
                    int odometer = results.getInt("odometer");
                    double price = results.getDouble("price");

                    vehicle = new Vehicle(vin, make, model, year, sold, color, type, odometer, price);
                    vehicles.add(vehicle);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE make = ? and model = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE year between ? and ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setInt(1, minYear);
            preparedStatement.setInt(2, maxYear);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE color = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setString(1, color);


            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return vehicles;

    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE odometer  between ? and ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setInt(1, minMileage);
            preparedStatement.setInt(2, maxMileage);



            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return vehicles;


    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE vehicleType  = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setString(1, type);




            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return vehicles;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
