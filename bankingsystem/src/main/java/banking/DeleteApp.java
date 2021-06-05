package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteApp {

    private Connection connect() {
        String url = "jdbc:sqlite:card.s3db";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public void deletingAccount (String cardNumber) {
        String sql = "DELETE FROM card WHERE number = ?";

        try (Connection con = this.connect();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cardNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}