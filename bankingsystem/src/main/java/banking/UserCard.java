package banking;

public class UserCard {
     private String cardNumber;
     private String pinNumber;

    UserCard() {
        setCardNumber();
        setPinNumber();
    }

     public String getCardNumber() {
         return cardNumber;
     }

     public String getPinNumber() {
         return pinNumber;
     }

     public void setCardNumber() {
        this.cardNumber = Random_generator.checksum(Random_generator.firstDigitsOfCardNum());
     }
     public void setPinNumber() {
        this.pinNumber = Random_generator.pinNumber();
     }

 }
