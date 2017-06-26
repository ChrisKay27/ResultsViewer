package database;

import javafx.util.Pair;
import javafx.util.converter.IntegerStringConverter;
import ui.ExperimentParams;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 7/1/2016.
 */
public class DBConn {


    public static List<Pair<Double,Double>> getSeries(String query) {
        Connection conn;

        List<Pair<Double,Double>> series = new ArrayList<>();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/ddb_results?" +
                            "user=Mani&password=thesis&useSSL=false");


            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                double PCOT = resultSet.getDouble(1);
                double arrivalRate = resultSet.getDouble(2);

                series.add(new Pair<>(PCOT,arrivalRate));
            }


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return series;

    }



    public static List<String> getPossibleValues( ExperimentParams column) {
        Connection conn = null;

        List<String> possibleValues = new ArrayList<>();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/ddb_results?" +
                            "user=Mani&password=thesis");


            PreparedStatement statement = conn.prepareStatement("SELECT DISTINCT "+column.name()+" FROM results");

//            statement.setString(1, column.name());


            ResultSet resultSet = statement.executeQuery();


            while(resultSet.next())
                possibleValues.add(resultSet.getString(1));


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return possibleValues;
    }


    public static void main(String[] args) {
        System.out.println(getPossibleValues(ExperimentParams.deadlockDetectionProtocol));
    }
}
