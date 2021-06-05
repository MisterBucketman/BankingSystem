package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertApp {

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

    public void insertNewAccount (String number, String pin, int balance) {
        String sql = "INSERT INTO card (number, pin, balance) VALUES (?, ?, ?)";

        try (Connection con = this.connect();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, number);
            pstmt.setString(2, pin);
            pstmt.setInt(3, balance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateIncome (int balance, String cardNumber) {
        String sql = "UPDATE card SET balance = balance + ? "
                    + "WHERE number = ?";

        try (Connection con = this.connect();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, balance);
            pstmt.setString(2, cardNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void transfer (String targetCardNumber, String userCardNumber, int amount) {


        String sqlTransferFrom = "UPDATE card SET balance = balance - ? "
                    + "WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sqlTransferFrom)) {
            pstmt.setInt(1, amount);
            pstmt.setString(2, userCardNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sqlTransferTo = "UPDATE card SET balance = balance + ? "
                    + "WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sqlTransferTo)) {
            pstmt.setInt(1, amount);
            pstmt.setString(2, targetCardNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}