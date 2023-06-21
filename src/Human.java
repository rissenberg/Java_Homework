import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public  class Human implements Serializable {
    protected static final long serialVersionUID = 1L;
    protected  String Name;
    protected String TelephoneNumber;
    protected  String Email;
    protected  int Cash = 0;
    protected String Password;
    protected  transient ArrayList<Seat> boughtSeats = new ArrayList<>();
    protected  transient ArrayList<Seat> notifications = new ArrayList<>();
    protected transient ArrayList<Hall> RentedHalls = new ArrayList<>();
    protected ClientStatus Status;
    private transient Scanner scanner = new Scanner(System.in);
    Human(String newName, String newTelephoneNumber, String newEmail, String newPassword, int newCash, ClientStatus NewStatus  ){
        this( newName,  newTelephoneNumber, newEmail,  newPassword,  newCash);
        this.Status= NewStatus;
        boughtSeats =new ArrayList<>(Status.getTarget());
    }
    Human(String newName, String newTelephoneNumber, String newEmail, String newPassword, int newCash){
        Name =newName;
        TelephoneNumber=newTelephoneNumber;
        Email=newEmail;
        CashFillUp(newCash);
        Password=newPassword;
        this.Status=new CommonClient();
        Main.CountOfCommonClients++;

    }
    public void ShowInf(){
        System.out.println("Name:  "+ Name +" , phone: "+TelephoneNumber+" , email: "+Email);
        System.out.println("password: "+Password+" , status: "+Status.getStatusName());
    }
    public void Save (FileWriter fin,char sep)throws Exception{
       // FileWriter fin;
        //fin = new FileWriter("Clients.txt",true);
        fin.write(Name +sep+TelephoneNumber+sep+Email+sep+Password+sep+Cash+sep+Status.getStatusName()+"\n");
        fin.close();
    }
    public void Save (String FileName,String sep)throws Exception{
         FileWriter fin;
        fin = new FileWriter(FileName,true);
        fin.write(Name +sep+TelephoneNumber+sep+Email+sep+Password+sep+Cash+sep+Status.getStatusName()+"\n");
        fin.close();
    }
    public void SaveSer (ObjectOutputStream objectOutputStream, Human PeopleToSave ) throws Exception{
        // FileOutputStream outputStream = new FileOutputStream(FileName);
        // ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(PeopleToSave);

    }
    public Human ReadSer(ObjectInputStream objectInputStream ) throws Exception{
        // FileInputStream fileInputStream = new FileInputStream( FileName);
        // ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Human ReadHuman = (Human) objectInputStream.readObject();
        return ReadHuman;

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
                    ChoiseOfSessionMenu();
                    break;

                case "by time":
                    System.out.println("loading...");
                    if (Main.ShowSessionsWithTime()) ;
                    ChoiseOfSessionMenu();
                    break;
                case "by price":
                    System.out.println("loading...");
                    if (Main.ShowSessionsWithCash())
                        ChoiseOfSessionMenu();
                    //  SearchSessions();
                    break;
                case "by film":
                    System.out.println("loading...");
                    if (Main.ShowSessionsWithNameOfFilm())
                        ChoiseOfSessionMenu();
                    break;
                case "money":
                    NewCashMenu();
                    break;
                case "history":
                    BoughtTicketsMenu();
                    break;
                case "notification":
                    NotificationMenu();
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Bad input. Try again");

            }
            Main.ClientAccount();
            answer = Main.scanner.nextLine();
            Main.CreateSpace();
        }
    }

    protected  void ChoiseOfSessionMenu() {
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
            Main.BuyTicketMenu(ChoiceOfSession,getCash(),Status.getDiscount());
            answer = Main.scanner.nextLine();
            Main.CreateSpace();

        }
    }

    private  void TryToBuyTicket(int ChoiceOfSession) {
        Main.seanceNum.get(ChoiceOfSession).getCinema().ViewHall(Status.getDiscount());
        Status.ClientPerks();
        if (Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().isRented()){
            System.out.println("Hall is already fully rented!");
            Main.Pause(2000);
            return;
        }
        System.out.println("Enter the desired row on which to buy a seat ");
        int ChoiceOfRow;
        ChoiceOfRow = scanner.nextInt();
        ChoiceOfRow--;
        if ((ChoiceOfRow < 0) || (ChoiceOfRow > Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getCountOfRows())) {
            System.out.println("No row with such number, dumbbell");
            Main.Pause(3000);
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
            Main.Pause(2000);
            return;
        }
        if (Cash < Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getPlaceInHall()[ChoiceOfRow][ChoiceOfPlace].getCost()*Status.getDiscount()) {
            System.out.println("Not enough money to buy. Get back when you a little richer!");
            Main.Pause(3000);
            return;
        }


        Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getPlaceInHall()[ChoiceOfRow][ChoiceOfPlace].BuyingPlace(Email);
        Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().UpEarnedCash((int) (Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getPlaceInHall()[ChoiceOfRow][ChoiceOfPlace].getCost()*Status.getDiscount()));
        Cash -= Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getPlaceInHall()[ChoiceOfRow][ChoiceOfPlace].getCost()*Status.getDiscount();
        Main.CountOfEarnedMoney += Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getPlaceInHall()[ChoiceOfRow][ChoiceOfPlace].getCost()*Status.getDiscount();
        Main.CountOfSoldTickets++;
        boughtSeats.add( Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getPlaceInHall()[ChoiceOfRow][ChoiceOfPlace]);
        notifications.add( Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().getPlaceInHall()[ChoiceOfRow][ChoiceOfPlace]);
        System.out.println("Purchase is successful!");
        UpdateStatus();
        Main.Pause(2500);
    }
    private void UpdateStatus(){
        switch (boughtSeats.size()) {
            case FriendlyClient.Target:
                Status= new FriendlyClient();
                Status.Congrats(Name);
                Main.CountOfFriendClients++;
                Main.CountOfCommonClients--;
                Main.Pause(1500);
                break;
            case VipClient.Target:
                Status= new VipClient();
                Status.Congrats(Name);
                Main.CountOfVipClients++;
                Main.CountOfFriendClients--;
                Main.Pause(1500);
                break;
            //break; //а вдруг!
            default:
                System.out.println("You`ve made purchase №"+ boughtSeats.size());
                System.out.println("No status changes yet...");
        }
    }
    private  void TryToRentHall(int ChoiceOfSession){
        Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().ViewTableOfPlaces();
        if (!Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().CheckOfOpportunityToRent()){
            System.out.println("You cant rent this hall: some seats are already purchased");
            Main.Pause(2000);
            return;
        }
        if (Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().CostOfRent()>getCash()){
            System.out.println("We can not offer a rent to someone \"of modest means\"");
            Main.Pause(2000);
            return;
        }
        Main.CountOfEarnedMoneyWithRent+=Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().CostOfRent();
        Cash-=Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().CostOfRent();
        Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().RentingHall(Email);
        Main.CountOfEarnedMoneyWithRent+=Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall().CostOfRent();
        RentedHalls.add( Main.seanceNum.get(ChoiceOfSession).getCinema().getOneHall());
        System.out.println("Rent is successful!");
        if (Status.getStatusName().equals("Guest")){
            Status= new VipClient();
            Status.Congrats(Name);
            Main.CountOfVipClients++;
            Main.CountOfCommonClients--;
            Main.Pause(1500);

        }
        if (Status.getStatusName().equals("Cartel Friend")){
            Status= new VipClient();
            Status.Congrats(Name);
            Main.CountOfVipClients++;
            Main.CountOfFriendClients--;
            Main.Pause(1500);
        }

        Main.Pause(1500);

    }
    private  void NewCashMenu() {
        Main.CreateSpace();
        System.out.println(Name + ", you have " + Cash + " rubles");
        System.out.print("How much money do you want? it`s free : ");
        int newCash = scanner.nextInt();
        if (newCash < 0) {
            System.out.print("You, imbecile, cant get negative money");
            Main.Pause(3500);
            Main.CreateSpace();
            return;
        }
        CashFillUp(newCash);
    }

    private void NotificationMenu() {
        Main.CreateSpace();
        String answer = "begin";
        while (true) {
            switch (answer) {
                case "begin":
                    System.out.println("Purchase history");
                    break;
                case "tickets":
                    System.out.println("loading tickets..");
                    ShowBougthTickets();
                    break;
                case "halls":
                    System.out.println("loading halls...");
                    ShowRentedHalls();
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Bad input, try again");
            }
            Main.BoughtTicketsMenu();
            answer = Main.scanner.nextLine();
            Main.CreateSpace();

        }
    }
    private void BoughtTicketsMenu() {
        Main.CreateSpace();
        String answer = "begin";
        while (true) {
            switch (answer) {
                case "begin":
                    System.out.println("Purchase history");
                    break;
                case "tickets":
                    System.out.println("loading tickets..");
                    ShowBougthTickets();
                    break;
                case "halls":
                    System.out.println("loading halls...");
                    ShowRentedHalls();
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Bad input, try again");
            }
            Main.BoughtTicketsMenu();
            answer = Main.scanner.nextLine();
            Main.CreateSpace();

        }
    }
    private void ShowBougthTickets (){
        for (int i = 0; i< boughtSeats.size(); i++){
            boughtSeats.get(i).ShowInf();
            System.out.println("--------------------------");
        }
        Main.Pause(2000);
    }
    private void ShowRentedHalls(){
        for (int i=0; i<RentedHalls.size();i++) {
            RentedHalls.get(i).ViewTableOfPlaces();
            Main.seanceNum.get(RentedHalls.get(i).getNumberOfSession()).showInf();
            System.out.println("--------------------------");
        }
        Main.Pause(2000);
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

    public ClientStatus getStatus() {
        return Status;
    }

    public void setStatus(ClientStatus status) {
        Status = status;
    }
    public void setFilm (String filmname) {
        Status.setFilm(filmname);
    }
    public boolean setStatus(String NewStatus){
        if (NewStatus.equals("Guest")){
            Status=new CommonClient();
            return true;
        }
        if (NewStatus.equals("Cartel Friend")){
            Status=new FriendlyClient();
            return true;
        }
        if (NewStatus.equals("VIP-client")){
            Status=new VipClient();
            return true;
        }
        Status=new CommonClient();
        return false;
    }

    public  int getCash() {
        return Cash;
    }
}




