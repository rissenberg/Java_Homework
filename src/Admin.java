import java.io.FileWriter;
import java.util.Scanner;

public class Admin extends Human {
    private int id;
     public Scanner scanner = new Scanner(System.in);
    Admin (String newName, String newTelephoneNumber,String newEmail, String newPassword, int newCash ){
        super (newName, newTelephoneNumber, newEmail, newPassword, newCash);
    }
    public void SaveStatistic (String FileName,String sep)throws Exception{
        FileWriter fin= new FileWriter(FileName,true);
        fin.write(Main.CountOfSoldTickets+sep+Main.CountOfEarnedMoney+sep+Main.CountOfEarnedMoneyWithRent+sep+Main.CountOfVipClients
                +sep+Main.CountOfFriendClients+ sep+Main.CountOfCommonClients+sep+"ЭТО СТРОКА А НЕ \":C\""+sep);
        fin.close();
        super.Save(FileName,sep); //я офигею если это стработает (сохраняем личные данные админа!)
        //fin.close();
    }
    private void GodModOn(){
        String answer="begin";
        while (true){
            switch (answer) {
                case "begin":
                    System.out.println("You can now shoot lightning bolts from your fingertips!");
                    break;
                case "seance":
                    Main.GodOfSession();
                    break;
                case "cinema":
                    Main.GodOfCinema();
                    break;
                case "hall":
                    Main.GodOfHall();
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Bad input. Try again");

            }
            Main.AdminGodMod();
            answer = scanner.nextLine();
            Main.CreateSpace();
            Main.CreateSpace();
        }
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
                case "clients":
                    Main.ClientStatusStatistic();
                    break;
                case "stats":
                    Main.HallStatistic();
                    break;
                case "purchase":
                    super.Account();
                    break;
                case "godmode":
                    GodModOn();
                    break;
                case "all":
                    Main.AdminShowAll();
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Bad input, try again");

            }
            Main.AdminAccount();
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
        System.out.println("a little pause...");
        Main.Pause(4999);

    }

}
