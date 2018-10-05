package tests;

import model.UserData;
import model.Users;
import org.testng.annotations.Test;
import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("select id, username from mantis_user_table where username != 'administrator'");

            Users users = new Users();
            while (resultSet.next()){
                users.add(new UserData().setId(resultSet.getInt("id"))
                        .setUsername(resultSet.getString("username")));
            }
            resultSet.close();
            st.close();
            conn.close();

            System.out.println("****" + users);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
}
