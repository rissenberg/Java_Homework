import java.util.Scanner;

public class Client extends Human {
    private Scanner scanner = new Scanner(System.in);
    Client(String newName, String newTelephoneNumber,String newEmail, String newPassword, int newCash ){
    super (newName, newTelephoneNumber, newEmail, newPassword, newCash);
    }

}