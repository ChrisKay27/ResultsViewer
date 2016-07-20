import javafx.util.Pair;
import javafx.util.converter.IntegerStringConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 7/1/2016.
 */
public class DBConn {


    public static List<Pair<Double,Double>> getSeries(String query) {
        Connection conn = null;

        List<Pair<Double,Double>> series = new ArrayList<>();

        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/ddb_results?" +
                            "user=Mani&password=thesis");


            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                double arrivalRate = resultSet.getDouble(1);
                double PCOT = resultSet.getDouble(2);
                series.add(new Pair<>(arrivalRate,PCOT));
            }


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return series;

    }



}
