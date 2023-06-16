package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        // TODO: Implement the logic to add a sales contract
        String query = "insert into Sales_contracts (contract_id, vin, sale_date, price) values " +
                "(?,?,?,?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);){

            // Set the parameter values for the insert query
            preparedStatement.setInt(1, salesContract.getContractId());
            preparedStatement.setString(2, salesContract.getVin());
            preparedStatement.setDate(3, Date.valueOf(salesContract.getSaleDate()));
            preparedStatement.setDouble(4,salesContract.getPrice());



            // Execute the insert query
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");



        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
