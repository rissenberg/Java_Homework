import java.util.Scanner;

public class Admin extends Human {
    private int id;
     private Scanner scanner = new Scanner(System.in);
    Admin (String newName, String newTelephoneNumber,String newEmail, String newPassword, int newCash ){
        super (newName, newTelephoneNumber, newEmail, newPassword, newCash);
    }

    public void AdminAccount(){

        String answer="begin";
        while (true){
            switch (answer) {
                case "begin":
                    System.out.println("Welcome, admin!");
                    break;
                case "revenue":
                    System.out.println("Total revenue from all seances: "+Main.CountOfEarnedMoney+" rubles");
                    break;
                case "tickets":
                    System.out.println("Total number of sold tickets: "+Main.CountOfSoldTickets);
                    break;
                case "rent":
                    Main.ShowRentedSessions();
                    break;
                case "seances":
                    AdminView();
                    break;
                case "most rented":
                    Main.GetMostRentableFilms();
                    break;
                case "by duration":
                    Main.GetByDurationFilms();
                    break;
                case "purchase":
                    super.Account();
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Bad input. Try again");

            }
            Main.AdminAccount(answer);
            answer = scanner.nextLine();
            Main.CreateSpace();
            Main.CreateSpace();
        }
    }
    private void AdminView(){
        Main.ShowSessions();
        System.out.println("Enter seance ID number");
        int NumberOfSession=scanner.nextInt();
        NumberOfSession--;
        Main.seanceNum.get(NumberOfSession).getCinema().ViewHall();

    }
    public static void adminMenu (){

    }
}
