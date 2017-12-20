package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjector {
    private static String url       = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11211703";
    private static String user      = "sql11211703";
    private static String password  = "dW3EuwxerG";
    private static String table     = "weather";

    private static Connection con = null;

    public static void insertData(WeatherData data) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();

            st.executeUpdate("INSERT INTO " + table + " " +
                    "(`city`, `current_temp`, `sunrise`, `sunset`, `weather_description`, " +
                    "`weather_icon`, `min_temp`, `max_temp`, `wind_speed`, `wind_direction`, " +
                    "`updated_date`, `day_1`, `icon_1`, `type_1`, `min_temp_1`, `max_temp_1`, " +
                    "`day_2`, `icon_2`, `type_2`, `min_temp_2`, `max_temp_2`, `day_3`, `icon_3`, " +
                    "`type_3`, `min_temp_3`, `max_temp_3`, `day_4`, `type_4`, `icon_4`, `min_temp_4`, " +
                    "`max_temp_4` ) " +
                    "VALUES ( '" +
                    data.city + "' , " +
                    data.current_temp + " , " +
                    data.sunrise+ " , " +
                    data.sunset + " , '" +
                    data.weather_description + "' , '" +
                    data.weather_icon + "' , " +
                    data.min_temp + " , " +
                    data.max_temp + " , " +
                    data.wind_speed + " , " +
                    data.wind_direction + " , '" +
                    data.updated_date + "' , " +
                    data.day_1 + " , '" +
                    data.icon_1 + "' , '" +
                    data.type_1 + "' , " +
                    data.min_temp_1 + " , " +
                    data.max_temp_1 + " , " +
                    data.day_2 + " , '" +
                    data.icon_2 + "' , '" +
                    data.type_2 + "' , " +
                    data.min_temp_2 + " , " +
                    data.max_temp_2 + " , " +
                    data.day_3 + " , '" +
                    data.icon_3 + "' , '" +
                    data.type_3 + "' , " +
                    data.min_temp_3 + " , " +
                    data.max_temp_3 + " , " +
                    data.day_4 + " , '" +
                    data.type_4 + "' , '" +
                    data.icon_4 + "' , " +
                    data.min_temp_4 + " , " +
                    data.max_temp_4 +
                    ")");
            con.close();
            System.out.println("Sent");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
