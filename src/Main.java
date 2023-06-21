import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
public static Cinema FirstCinema;
public static Scanner scanner;
public static String[] Formats = {"2D","3D","4D","5D"};
public static ArrayList<Client> ClientNum = new ArrayList<>();
public static ArrayList<Seance> seanceNum = new ArrayList<>();
public static ArrayList<Film> Films = new ArrayList<>();
public static ArrayList<Admin> AdminNum = new ArrayList<>();
    public static int CountOfSoldTickets=0;
    public static int CountOfEarnedMoney=0;
    public static int CountOfEarnedMoneyWithRent=0;
    public static void main(String[] args) {
        scanner = new Scanner (System.in);
        DefaultInicialization();
        Menu ();
    }
    public static void DefaultInicialization(){
        FirstCinema = new Cinema("BMSTU",120, "Moscow, 2nd Bauman street", Formats);
        Films.add(new Film ("Fight club",2012,"action",120,Formats[1]));
        Films.add(new Film ("Barbie starring Gosling",2012,"horror",120,Formats[0]));
        Films.add(new Film ("Экзамен по физике",2021,"horror, terror, psychic thriller",180,Formats[3]));

        seanceNum.add(new Seance(  Films.get(0), new Cinema ( FirstCinema.getName(), FirstCinema.getCapacity(), FirstCinema.getAdress(), FirstCinema.getFormats() ),"12:00","14:30" )   ) ;
        seanceNum.add(new Seance(  Films.get(0), new Cinema ( FirstCinema.getName(), FirstCinema.getCapacity(), FirstCinema.getAdress(), FirstCinema.getFormats() ),"14:30","17:00" )   ) ;
        seanceNum.add(new Seance(  Films.get(1), new Cinema ( FirstCinema.getName(), FirstCinema.getCapacity(), FirstCinema.getAdress(), FirstCinema.getFormats() ),"17:00","19:30" )   ) ;

        ClientNum.add(new Client("123","123","123","123", 999999));
        ClientNum.add(new Client("Tester","12345678","12345678@gmail.com","12345678", 1000));

        AdminNum.add(new Admin("CEO of Java","79857339833","12345678","12345678", 100000));

    }
    public static void CreateSpace(){
        for (int i=0; i<10; i++)
            System.out.println(" ");
    }
    public static void Pause(int timeOfPause){
        try {
            Thread.sleep(timeOfPause);
        } catch (InterruptedException e) {
            //  throw new RuntimeException(e); господи какие уроды придумали это ограничение и главное зачем!??!?!
        }
    }
    public static void Menu () {

        String Role="begin";
        while (true)
        {

            switch (Role) {
                case "begin":
                    System.out.println("Welcome!");
                    break;
                case "guest":
                    clientMenu ();
                    break;
                case "admin":
                    adminAuthorization();
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Bad input. Try again");
            }
            System.out.println("Choose your role? (\"guest\"/\"admin\")");
            System.out.println("To exit the app, type \"quit\"");
            Role = scanner.nextLine();
            CreateSpace();

        }

    }
    public static void clientMenu () {
        String answer="begin";
        while (true){
            switch (answer) {
                case "begin":
                    System.out.println("Welcome, guest!");
                    break;
                case "Log in":
                    clientAuthorization();
                    break;
                case "Sign up":
                    clientRegistration();
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Bad input");

            }
            System.out.println("Choose your option \"Log in\" or \"Sign up\"");
            System.out.println("To exit the app, type \" quit \" ");
            answer = scanner.nextLine();
            CreateSpace();

        }
    }
    public static void clientRegistration (){
        CreateSpace();
        String newEmail;
        System.out.println("Sign up form");
        System.out.println("Enter your Email");
        while (true){
            newEmail = scanner.nextLine();
            boolean isFound= false;
            for (int i=0; i<ClientNum.size(); i++){
                if (  ClientNum.get(i).getEmail().equals(newEmail)  ){
                    isFound=true;
                    System.out.println("This email is already taken, you can Log in or choose another:");
                }
            }
            if (!isFound) break;
        }
        System.out.println("Enter password:");
        String newPassword = scanner.nextLine();
        System.out.println("Enter your Name:");
        String newName=scanner.nextLine();
        System.out.println("Enter your phone number:");
        String newTelephone=scanner.nextLine();
        System.out.println("How much pocket money do you want, my dear?");
        int newCash=scanner.nextInt();
        System.out.println("Have fun!");
        ClientNum.add(new Client(newName,newTelephone,newEmail,newPassword, newCash));

    }
    public static void clientAuthorization(){
        String checkEmail;
        String checkPassword;
        for (int j=0; j<3; j++){  // 3 attempts
            System.out.println("Log in");
            System.out.println("Enter Email");
            checkEmail = scanner.nextLine();
            System.out.println("Enter пароль");
            checkPassword = scanner.nextLine();
            boolean isFound= false;
            for (int i=0; i<ClientNum.size(); i++){
                if (  ClientNum.get(i).getEmail().equals(checkEmail) && ClientNum.get(i).isPassword(checkPassword)   ){
                    isFound=true;
                    ClientNum.get(i).Account();
                    return;
                }
            }
            CreateSpace();
            System.out.println("Incorrect email or password. You have " + (3 - j - 1) + " attempts left");
        }
        System.out.println("Quiting after 3 unsuccessful attempts");
    }
    public static void adminAuthorization () {
        String checkEmail;
        String checkPassword;
        for (int j=0; j<3; j++){  // 3 attempts
            System.out.println("Log in");
            System.out.println("Enter Email");
            checkEmail = scanner.nextLine();
            System.out.println("Enter пароль");
            checkPassword = scanner.nextLine();
            boolean isFound= false;
            for (int i=0; i<AdminNum.size(); i++){
                if (  AdminNum.get(i).getEmail().equals(checkEmail) && AdminNum.get(i).isPassword(checkPassword)   ){
                    isFound=true;
                    AdminNum.get(i).AdminAccount();
                    return;
                }
            }
            CreateSpace();
            System.out.println("Incorrect email or password. You have " + (3 - j - 1) + " attempts left");
        }
        System.out.println("Quiting after 3 unsuccessful attempts");
    }

    public static void AdminAccount (String answer){
        System.out.println("Choose action:");
        System.out.println("To see the total revenue for all sessions, enter \"revenue\" ");
        System.out.println("To see total count of sold tickets, enter \"tickets\" ");
        System.out.println("To see seances, enter \"seances\" ");
        System.out.println("To see top most popular films, enter \"most popular\" ");
        System.out.println("To see top films by duration, enter \"by duration\" ");
        System.out.println("To buy ticket, enter \"purchase\" ");
        System.out.println("To exit, enter \"quit\" ");
    }
    public static void ClientAccount (){
        System.out.println("Choose action:");
        System.out.println("To see seances, enter \"seances\" ");
        System.out.println("To see seances by time, enter \"by time\" ");
        System.out.println("To see seances by ticket price, enter \"by price\" ");
        System.out.println("To see seances by film name, enter \"by film\" ");
        System.out.println("To see top films by duration, enter \"by duration\" ");
        System.out.println("To get more pocket money, enter \"money\" ");
        System.out.println("To Log out, enter \"quit\" ");
    }
    public static void BuyTicketMenu(int ChoiceOfSession, int Cash){
        System.out.println("Seance menu " + (ChoiceOfSession + 1));
        Main.seanceNum.get(ChoiceOfSession).getCinema().ViewHall();
        System.out.println("Your balance " + Cash + " rubles");
        System.out.println("Choose action:");
        System.out.println("To buy ticket, enter \"purchase\"");
        System.out.println("To rent a hall, enter \"rent\"");
        System.out.println("To exit, enter \"quit\"");
    }
    public static void ShowRentedSessions(){
        System.out.println("Rented halls info:");
        boolean isFound=false;
        for (int i = 0; i< seanceNum.size(); i++){
            if (seanceNum.get(i).getCinema().getOneHall().isRented()){
                isFound=true;
                System.out.println("Seance №"+(i+1));
                seanceNum.get(i).showInf();
                System.out.println("---------------------------------------------------");
            }
        }
        if (!isFound){
            System.out.println("No halls are rented");
            return;
        }
        System.out.println("Revenue from rent: "+CountOfEarnedMoneyWithRent);
    }

    public static void ShowSessions () {
        System.out.println("Seances info:");
        for (int i = 0; i< seanceNum.size(); i++){
            System.out.println("Seance №"+(i+1));
            seanceNum.get(i).showInf();
            System.out.println("---------------------------------------------------");
        }
    }
    public static boolean ShowSessionsWithTime() {
        ShowAllSessionPeriods();
        System.out.print("Enter time of seance: ");
        String searchTime=scanner.nextLine();
        boolean isFound=false;
        for (int i = 0; i< seanceNum.size(); i++){
            if (seanceNum.get(i).getTimeOfBeginning().equals(searchTime)) {
                isFound=true;
                System.out.println("Seance №"+(i+1));
                seanceNum.get(i).showInf();
                System.out.println("---------------------------------------------------");
            }
        }
        if (!isFound){
            System.out.println("No seances are found. Exiting...");
            Pause(1500);
            return false;
        }
        return true;
    }
    public static boolean ShowSessionsWithCash(){
        System.out.print("Enter ticket price: ");
        int searchCash=scanner.nextInt();
        boolean isFound=false;
        for (int i = 0; i< seanceNum.size(); i++){
            if (seanceNum.get(i).getCinema().getOneHall().CheckOfOpportunityToBuy(searchCash)) {
                isFound=true;
                System.out.println("Seance №"+(i+1));
                seanceNum.get(i).showInf();
                System.out.println("---------------------------------------------------");
            }
        }
        if (!isFound){
            System.out.print("No seances are found. Exiting...");
            Pause(1500);
            return false;
        }
        return true;
    }
    public static boolean ShowSessionsWithNameOfFilm(){
        ShowAllFilmNames();
        System.out.print("Enter film name: ");
        String searchName=scanner.nextLine();
        boolean isFound=false;
        for (int i = 0; i< seanceNum.size(); i++){
            if (seanceNum.get(i).getFilm().getName().equals(searchName)) {
                isFound=true;
                System.out.print("Seance №"+(i+1));
                seanceNum.get(i).showInf();
                System.out.println("---------------------------------------------------");
            }
        }
        if (!isFound){
            System.out.println("No seances are found. Exiting...");
            Pause(1500);
            return false;
        }
        return true;
    }
    public static void ShowAllSessionPeriods(){
        for (int i = 0; i< seanceNum.size(); i++){
            System.out.print(seanceNum.get(i).getTimeOfBeginning()+"-"+ seanceNum.get(i).getTimeOfEnding()+" ");
        }
        System.out.println(" ");
    }
    public static void ShowAllFilmNames(){
        for (int i = 0; i< seanceNum.size(); i++){
            System.out.print("\""+ seanceNum.get(i).getFilm().getName()+"\", ");
        }
        System.out.println(" ");
    }
    
    public static void GetMostRentableFilms() {
        ArrayList<Film> films = Films;
        films.sort(Comparator.comparing(Film::getCount).reversed());
        System.out.println("Top 3 most popular Films:");
        for (int i = 0; i< films.size() && i < 3; i++){
            System.out.println("Film №"+(i+1));
            films.get(i).showInf();
            System.out.println("---------------------------------------------------");
        }
    }

    public static void GetByDurationFilms() {
        ArrayList<Film> films = Films;
        films.sort(Comparator.comparing(Film::getDurationOfFilm).reversed());
        System.out.println("Top 3 longest Films:");
        for (int i = 0; i< films.size() && i < 3; i++){
            System.out.println("Film №"+(i+1));
            films.get(i).showInf();
            System.out.println("---------------------------------------------------");
        }
    }

}




