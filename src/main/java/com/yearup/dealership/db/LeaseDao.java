package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        // TODO: Implement the logic to add a lease contract
        String query = "insert into Lease_contracts (contract_id, vin, sale_date, price) values " +
                "(?,?,?,?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);){

            // Set the parameter values for the insert query
            preparedStatement.setInt(1, leaseContract.getContractId());
            preparedStatement.setString(2, leaseContract.getVin());
            preparedStatement.setDate(3, Date.valueOf(leaseContract.getLeaseStart()));
            preparedStatement.setDate(4, Date.valueOf(leaseContract.getLeaseEnd()));
            preparedStatement.setDouble(5, leaseContract.getMonthlyPayment());



            // Execute the insert query
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");



        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
