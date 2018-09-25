package addresbook.tests;

import addresbook.model.GroupData;
import addresbook.model.Groups;
import org.testng.annotations.Test;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            Groups groups = new Groups();
            while (resultSet.next()){
                groups.add(new GroupData().setId(resultSet.getInt("group_id")).setName(resultSet.getString("group_name"))
                        .setHeader(resultSet.getString("group_header"))
                        .setFooter(resultSet.getString("group_footer")));
            }
            resultSet.close();
            st.close();
            conn.close();
            System.out.println(groups);

      } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
