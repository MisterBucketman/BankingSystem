package banking;

import java.sql.*;

public class QueryApp {

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

    public int showBalance (String cardNumber) {
        String sql = "SELECT balance FROM card "
                + "WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, cardNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("balance");
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public boolean isCardNumber (String cardNumber) {
        String sql = "SELECT  number FROM card "
                + "WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, cardNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                if (rs.getString(1).equals(cardNumber)) {
                    return true;
                }
            } else {
                return false;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isAccountDataValid (String pinNumber, String cardNumber) {
        String sql = "SELECT pin FROM card WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, cardNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("pin").equals(pinNumber);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean balanceVerification (int amount, String cardNumber) {
        String sql = "SELECT balance FROM card "
                + "WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, cardNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("balance") >= amount;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
