package banking;

import java.util.Scanner;

public class UserVerification {
    static String actualCardNumber;
    Scanner scanner = new Scanner(System.in);
    void logging() {
        if (cardDataVerification()) {
            System.out.println("You have successfully logged in!");
            Main.loggedMenu(actualCardNumber);
        } else {
            System.out.println("Wrong card number or PIN!");
        }
    }

    boolean cardDataVerification() {
        QueryApp appQ = new QueryApp();
        System.out.println("Enter your card number:");
        actualCardNumber = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pinNumber = scanner.nextLine();

        return appQ.isAccountDataValid(pinNumber, actualCardNumber);
    }

    boolean cardVerificationForTransfer(String targetCardNumber, String userCardNumber) {
        QueryApp app = new QueryApp();
        if (!(Random_generator.luhnAlgCheck(targetCardNumber))) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            return false;
        } else if (!(app.isCardNumber(targetCardNumber))) {
            System.out.println("Such a card does not exist.");
            return false;
        } else if (targetCardNumber.equals(userCardNumber)) {
            System.out.println("You can't transfer money to the same account!");
            return false;
        }
        return true;
    }

}
