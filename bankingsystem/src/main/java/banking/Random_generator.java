package banking;

public class Random_generator {

    static String firstDigitsOfCardNum() {
        long identifier = (long) Math.floor(Math.random() * 900_000_000L) + 100_000_000L;
        return "400000".concat(String.valueOf(identifier));
    }

    static String checksum(String cardNumber) {
        int sum = 0;
        int checkSum = 0;

        for (int i = 0; i < cardNumber.length(); i++) {
            int k = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
            if (i % 2 == 0) {
                k *= 2;
            }
            if (k > 9) {
                k -= 9;
            }
            sum += k;
        }
        for (int i = sum; i < 100; i++) {
            if ((sum + checkSum) % 10 == 0) {
                return cardNumber + checkSum;
            } else {
                checkSum++;
            }
        }
        return cardNumber.concat(String.valueOf(checkSum));
    }

    static boolean luhnAlgCheck (String targetCardNumber) {
        String cardNumber = targetCardNumber.substring(0, targetCardNumber.length() - 1);

        return checksum(cardNumber).equals(targetCardNumber);
    }

    static String pinNumber() {
        return  String.valueOf((int) Math.floor(Math.random() * 9000) + 1000);
    }
}
