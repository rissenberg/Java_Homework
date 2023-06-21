import java.util.Scanner;

public class Human {
    protected  String Name;
    protected String TelephoneNumber;
    protected  String Email;
    protected  int Cash = 0;
    protected String Password;
    private Scanner scanner = new Scanner(System.in);
    Human(String newName, String newTelephoneNumber, String newEmail, String newPassword, int newCash  ){
        Name =newName;
        TelephoneNumber=newTelephoneNumber;
        Email=newEmail;
        CashFillUp(newCash);
        Password=newPassword;
    }

    public void Account() {

        String answer = "begin";
        while (true) {
            switch (answer) {
                case "begin":
                    System.out.println("Welcome, " + Name + " !");
                    break;
                case "seances":
                    System.out.println("loading...");
                    Main.ShowSessions();
                    ChoiseOfSeanceMenu();
                    break;

                case "by time":
                    System.out.println("loading...");
                    if (Main.ShowSessionsWithTime()) ;
                    ChoiseOfSeanceMenu();
                    break;
                case "by price":
                    System.out.println("loading...");
                    if (Main.ShowSessionsWithCash())
                        ChoiseOfSeanceMenu();
                    //  SearchSessions();
                    break;
                case "by film":
                    System.out.println("loading...");
                    if (Main.ShowSessionsWithNameOfFilm())
                        ChoiseOfSeanceMenu();
                    break;
                case "by duration":
                    Main.GetByDurationFilms();
                    break;
                case "money":
                    NewCashMenu();
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Bad input. Try again.");

            }
            Main.ClientAccount();
            answer = Main.scanner.nextLine();
            Main.CreateSpace();
        }
    }

    protected  void ChoiseOfSeanceMenu() {
        System.out.println("Choose seance id number, to exit, enter \"0\"");
        System.out.println("But do it very carefully!");
        int ChoiceOfSession;
        ChoiceOfSession = scanner.nextInt();
        ChoiceOfSession--;
        if ((ChoiceOfSession < 0) || (ChoiceOfSession > Main.seanceNum.size()))
            return;
        BuyTicketMenu(ChoiceOfSession);
    }
    private  void BuyTicketMenu(int ChoiceOfSession) {
        Main.CreateSpace();
        String answer = "begin";
        while (true) {
            switch (answer) {
                case "begin":
                    System.out.println("Welcome, " + Name + " !");
                    break;
                case "purchase":
                    System.out.println("loading purchase menu...");
                    TryToBuyTicket(ChoiceOfSession);
                    break;
                case "rent":
                    System.out.println("loading rent menu...");
                    TryToRentHall(ChoiceOfSession);
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Bad input. try again");
            }
            Main.BuyTicketMenu(ChoiceOfSession,getCash());
            answer = Main.scanner.nextLine();
            Main.CreateSpace();

        }
    }

    private  void TryToBuyTicket(int ChoiceOfSession) {
        Main.seanceNum.get(ChoiceOfSession).getCinema().ViewHall();
        if (Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().isRented()){
            System.out.println("Hall is already fully rented!");
            Main.Pause(1500);
            return;
        }
        System.out.println("Enter the desired row on which to buy a seat ");
        int ChoiceOfRow;
        ChoiceOfRow = scanner.nextInt();
        ChoiceOfRow--;
        if ((ChoiceOfRow < 0) || (ChoiceOfRow > Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getCountOfRows())) {
            System.out.println("No row with such number, dumbbell");
            Main.Pause(4000);
            return;
        }
        System.out.println("Enter the desired seat number");
        int ChoiceOfPlace;
        ChoiceOfPlace = scanner.nextInt();
        ChoiceOfPlace--;
        Main.CreateSpace();
        if ((ChoiceOfPlace < 0) || (ChoiceOfPlace > Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getCountOfPlacesInRow())) {
            System.out.println("No seat with such number. Killing app in 4 sec.");
            Main.Pause(4000);
            return;
        }

        if (Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getPlaceInHall()[ChoiceOfRow][ChoiceOfPlace].getIsBought()) {
            System.out.println("This seat is taken. Exiting...");
            Main.Pause(1500);
            return;
        }
        if (Cash < Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getPlaceInHall()[ChoiceOfRow][ChoiceOfPlace].getCost()) {
            System.out.println("Not enough money to buy. Get back when you a little richer!");
            Main.Pause(3000);
            return;
        }


        Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getPlaceInHall()[ChoiceOfRow][ChoiceOfPlace].BuyingPlace(Email);
        Cash -= Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getPlaceInHall()[ChoiceOfRow][ChoiceOfPlace].getCost();
        int temp = Main.seanceNum.get(ChoiceOfSession).getFilm().getCount();
        temp++;
        Main.seanceNum.get(ChoiceOfSession).getFilm().setCount(temp);
        Main.CountOfEarnedMoney += Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getPlaceInHall()[ChoiceOfRow][ChoiceOfPlace].getCost();
        Main.CountOfSoldTickets++;
        System.out.println("Purchase is successful!");
        Main.Pause(1500);
    }
    private  void TryToRentHall(int ChoiceOfSession){
        Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().ViewTableOfPlaces();
        if (!Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().CheckOfOpportunityToRent()){
            System.out.println("You cant rent this hall: some seats are already purchased");
            Main.Pause(1500);
            return;
        }
        if (Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().CostOfRent()>getCash()){
            System.out.println("We can not offer a rent to someone \"of modest means\"");
            Main.Pause(2000);
            return;
        }
        Main.CountOfEarnedMoneyWithRent+=Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().CostOfRent();
        Cash-=Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().CostOfRent();
        Main.seanceNum.get(ChoiceOfSession).getFilm().setCount(100);
        Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().RentingHall(Email);
        Main.CountOfEarnedMoneyWithRent+=Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().CostOfRent();
        System.out.println("Rent is successful!");
        Main.Pause(1500);

    }
    private  void NewCashMenu() {
        Main.CreateSpace();
        System.out.println(Name + ", you have " + Cash + " rubles");
        System.out.print("How much money do you want? it`s free : ");
        int newCash = scanner.nextInt();
        if (newCash < 0) {
            System.out.print("You, imbecile, cant get negative money");
            Main.Pause(3000);
            Main.CreateSpace();
            return;
        }
        CashFillUp(newCash);
    }

    protected  void CashFillUp(int newCash) {
        Cash += newCash;
    }
    public boolean isPassword (String checkPassword){
        return Password.equals(checkPassword);
    }
    //private void SearchSessions() {}
    private void SearchSessionWithTime() {
        Main.ShowSessionsWithTime();
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getTelephoneNumber() {
        return TelephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        TelephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public  int getCash() {
        return Cash;
    }
}




