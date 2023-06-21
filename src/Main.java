import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Main {
public static Cinema FirstCinema;
public static Scanner scanner;
public static final String Sep = ";";
public static String AdminFileName="Admins.txt";
public static String CinemaFileName="Cinemas.txt";
public static String FilmFileName="Films.txt";
public static String ClientFileName="Clients.txt";
public static final String SepArray="&";
public static String[] Formats = {"2D","3D","4D","5D"};
//public static String[] TypeOfHall = {"Common", "VIP"};
    public static ArrayList<String> TypesOfHall = new ArrayList<>();
    public static ArrayList<String> TypesOfClients = new ArrayList<>();
public static ArrayList<Client> ClientNum = new ArrayList<>();
public static ArrayList<Seance> seanceNum = new ArrayList<>();
public static ArrayList<Film> Films = new ArrayList<>();
public static ArrayList<Admin> AdminNum = new ArrayList<>();
public static ArrayList<Hall> HallNum = new ArrayList<>();
public static ArrayList<Cinema> CinemaNum = new ArrayList<>();
public static ArrayList<Notification> NotificationNum = new ArrayList<>();
public static ArrayList<Integer> EarnedMoneyFromHallNum = new ArrayList<>();
    public static int CountOfSoldTickets=0;
    public static int CountOfEarnedMoney=0;
    public static int CountOfEarnedMoneyWithRent=0;
    public static int CountOfVipClients=0;
    public static int CountOfFriendClients=0;
    public static int CountOfCommonClients=0;
    public static String ScanMyAss;
    public static void main(String[] args)throws Exception {
        scanner = new Scanner (System.in);
        DefaultInicialization();
        Menu();
        SuperWriter();
    }
    public static void DefaultInicialization(){
        DefaultHalls();
        //DefaultFilms();
        try {
            FilmReading(FilmFileName,Sep);
        } catch(FileNotFoundException e) {
            System.out.println("Error! No Film.txt file. Loading default...");
           DefaultFilms(); //фигушки а не катапультация
        } catch (IOException e) {
            System.out.println("Error! No default. Exiting app...");
            throw new RuntimeException(e);
        }
        //DefaultCinemas();
        try {
            CinemaReading(CinemaFileName,Sep);
        } catch(FileNotFoundException e) {
            System.out.println("Error! No Cinema.txt file. Loading default...");
            DefaultCinemas();
        } catch (IOException e) {
            System.out.println("Error! No default. Exiting app...");
            throw new RuntimeException(e);
        }
        DefaultSessions();

//        try {
//            ClientReadingSer("ClientsSer");
//        } catch (IOException e) {
//            if (ClientNum.size()==0) {
//                System.out.println("11");
//                DefaultClients();
//            }
//        } catch (ClassNotFoundException e) {
//            System.out.println("22");
//             DefaultClients();
//        }
        try {
            ClientReading(ClientFileName,Sep);
        } catch(FileNotFoundException e) {
            System.out.println("Error! No Client.txt file. Loading default...");
            DefaultClients();
        } catch (IOException e) {
            System.out.println("Error! No default. Exiting app...");
            throw new RuntimeException(e);
        }

        try {
            AdminReading(AdminFileName,Sep);
        } catch(FileNotFoundException e) {
            System.out.println("Error! No Admin.txt file. Loading default...");
            DefaultAdmins();
        } catch (IOException e) {
            System.out.println("Error! No default. Exiting app...");
            throw new RuntimeException(e);
        }
    }
    public static void DefaultHalls(){
        HallNum.add(new CommonHall(12,12,500,HallNum.size())); // init a few halls
        HallNum.add(new  VipHall(8,8,700,HallNum.size()));
        HallNum.add(new CommonHall(6,6,2000,HallNum.size()));
        for (int i=0; i<HallNum.size(); i++){
            if (!TypesOfHall.contains(HallNum.get(i).TypeOfHall)){
                TypesOfHall.add(HallNum.get(i).TypeOfHall);
            }
        }
        // EarnedMoneyFromHallNum= new ArrayList<>(HallNum.size());
        for (int i=0; i<HallNum.size();i++) EarnedMoneyFromHallNum.add(0);
    }
    public static void DefaultFilms(){
        Films.add(new Film ("Fight club",2012,"action",120,Formats[1],Films.size()));
        Films.add(new Film ("Barbie starring Gosling",2012,"horror",120,Formats[0],Films.size()));
        Films.add(new Film ("Экзамен по физике",2021,"horror, terror psychic thiller",180,Formats[3],Films.size()));
    }
    public static void DefaultCinemas(){
        CinemaNum.add(new Cinema("BMSTU", 120, "Moscow, 2nd Bauman street", Formats,HallNum,CinemaNum.size()));
        CinemaNum.add(new Cinema("Pioneer", 120, "идешь по мостовой, на третьем повороте налево и еще метров 100", Formats, HallNum,CinemaNum.size()));
    }
    public static void DefaultSessions(){
        seanceNum.add(new Seance(  Films.get(0), new Cinema ( CinemaNum.get(0),2, seanceNum.size() ),"12:00","14:30")   ) ;
        //SessionNum.add(new Session (Films.get(0),FirstCinema,"14:30","17:00"));
        seanceNum.get(seanceNum.size()-1).UpdateInformation();
        seanceNum.add(new Seance(  Films.get(2), new Cinema ( CinemaNum.get(0),0, seanceNum.size() ),"14:30","17:00" )   ) ;
        seanceNum.get(seanceNum.size()-1).UpdateInformation();
        seanceNum.add(new Seance(  Films.get(1), new Cinema ( CinemaNum.get(1),1, seanceNum.size() ),"17:00","19:30" )   ) ;
        seanceNum.get(seanceNum.size()-1).UpdateInformation();
    }
    public static void DefaultClients(){
        ClientNum.add(new Client("123","123","123","123", 999999));
        ClientNum.add(new Client("Tester","12345678","12345678@gmail.com","12345678", 12345, new FriendlyClient()));
        UpdateTypesOfClients();
    }
    public static void DefaultAdmins(){
        AdminNum.add(new Admin("CEO of Java","79857339833","12345678","12345678", 100000));
    }
    public static void SuperWriter()throws Exception{
        FileWriter fin;
        fin = new FileWriter(ClientFileName, false);
        fin.close();
        for (int i=0; i<ClientNum.size(); i++){
            ClientNum.get(i).Save(ClientFileName,Sep);
        }

        fin = new FileWriter(FilmFileName, false);
        fin.close();
        for (int i=0; i<Films.size(); i++){
            Films.get(i).Save(FilmFileName,Sep);
        }
        fin.close();
        fin = new FileWriter(CinemaFileName, false);
        fin.close();
        for (int i=0; i<CinemaNum.size(); i++){
            CinemaNum.get(i).Save(CinemaFileName,Sep);
        }
        fin.close();
        fin = new FileWriter(AdminFileName, false);
        fin.close();
        for (int i=0; i<AdminNum.size(); i++){
            AdminNum.get(i).SaveStatistic(AdminFileName, Sep);
        }
        fin.close();

    }
    public static void CreateSpace(){
        for (int i=0; i<10; i++)
            System.out.println(" ");
    }
    public static void Pause(int timeOfPause){
        try {
            Thread.sleep(timeOfPause);
        } catch (InterruptedException e) {
            //  throw new RuntimeException(e);
        }
    }

    public static void ErrorNotExist(){
        System.out.println("Error: object does not exist!");
        Pause(1500);
    }
    public static void ErrorIsNegative0rZero(){
        System.out.println("Error: input can not be negative!");
        Pause(2000);
    }
    public static void ClientReading(String FileName, String sep) throws  IOException{
        File file=new File(FileName);
        FileReader fout =new FileReader(file);
        BufferedReader br = new BufferedReader(fout);
        while (br.ready()){
            System.out.println("Client found!");
            String [] params = br.readLine().split(sep);
            ClientNum.add(new Client(params[0],params[1],params[2],params[3],Integer.parseInt(params[4])));
            ClientNum.get(ClientNum.size()-1).setStatus(params[5]);
            if (params[5] == "Director")
            {
                ClientNum.get(ClientNum.size()-1).setFilm(params[6]);
            }
        }
    }
    public static void ClientReadingSer(String FileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream( FileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Client ReadPeople =(Client) objectInputStream.readObject();
        while (ReadPeople!=null){
            ClientNum.add(new Client( ReadPeople));
            ReadPeople =(Client) objectInputStream.readObject();
        }
        objectInputStream.close();
    }
    public static void FilmReading(String FileName, String sep) throws IOException{
        File file=new File(FileName);
        FileReader fout =new FileReader(file);
        BufferedReader br = new BufferedReader(fout);
        while (br.ready()){
            System.out.println("Film found!");
            String [] params = br.readLine().split(sep);
            Films.add(new Film(params[0],Integer.parseInt(params[1]),params[2],Integer.parseInt(params[3]),params[4],Films.size()));
        }
    }
    public static void CinemaReading(String FileName, String sep) throws IOException{
        File file=new File(FileName);
        FileReader fout =new FileReader(file);
        BufferedReader br = new BufferedReader(fout);
        while (br.ready()){
            System.out.println("Cinema found!");
            String [] params = br.readLine().split(sep);
            String [] NewFormats = params[3].split("&");
            CinemaNum.add(new Cinema(params[0],Integer.parseInt(params[1]),params[2],NewFormats,HallNum,CinemaNum.size()));
        }
    }
    public static void NotificationReading(String FileName, String sep) throws IOException{
        File file=new File(FileName);
        FileReader fout =new FileReader(file);
        BufferedReader br = new BufferedReader(fout);
        while (br.ready()){
            System.out.println("Notification found!");
            String [] params = br.readLine().split(sep);
            //NotificationNum.add(new Notification(params[0],Integer.parseInt(params[1]),params[2], NewFormats,HallNum,CinemaNum.size()));
        }
    }
    public static void AdminReading(String FileName, String sep) throws IOException{
        File file=new File(FileName);
        FileReader fout =new FileReader(file);
        BufferedReader br = new BufferedReader(fout);
        while (br.ready()){
            System.out.println("Found admin!");
            String [] params = br.readLine().split(sep);
            CountOfSoldTickets=Integer.parseInt(params[0]);
            CountOfEarnedMoney=Integer.parseInt(params[1]);
            CountOfEarnedMoneyWithRent=Integer.parseInt(params[2]);
            CountOfVipClients=Integer.parseInt(params[3]);
            CountOfFriendClients=Integer.parseInt(params[4]);
            CountOfCommonClients=Integer.parseInt(params[5]);
            //6-заглушка, чтобы чертов write понял, что имеет дело со строкой
            AdminNum.add(new Admin(params[7],params[8],params[9],params[10],Integer.parseInt(params[11])));
            AdminNum.get(AdminNum.size()-1).setStatus(params[12]);
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
    public static void clientAuthorization (){
        String checkEmail;
        String checkPassword;
        for (int j=0; j<3; j++){  // 3 attempts
            System.out.println("Log in");
            System.out.println("Enter Email");
            checkEmail = scanner.nextLine();
            System.out.println("Enter password");
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
            System.out.println("Enter password");
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

    public static void AdminAccount (){
        System.out.println("Choose action:");
        System.out.println("To see the total revenue for all sessions, enter \"revenue\" ");
        System.out.println("To see statistics on a hall, enter  \"stats\" ");
        System.out.println("To see total count of sold tickets, enter \"tickets\" ");
        System.out.println("To see rented halls, enter \"rent\"");
        System.out.println("To see seances, enter \"seances\" ");
        System.out.println("To see clients, enter \"clients\" ");
        System.out.println("To buy ticket, enter \"purchase\" ");
        System.out.println("To create and edit objects, enter \"godmode\" ");
        System.out.println("To get all information, enter \"all\" ");
        System.out.println("To exit, enter \"quit\" ");
    }
    public static void AdminGodMod (){
        System.out.println("You have power of God!");
        System.out.println("You cn now create/edit/delete  seances/cinemas/halls");
        System.out.println("Choose an object:");
        System.out.println("To edit seances, enter \"seance\" ");
        System.out.println("To edit cinemas, enter \"cinema\" ");
        System.out.println("To edit halls, enter \"hall\" ");
        System.out.println("To exit, enter \"quit\" ");
    }
    public static void AdminGodProcesses(String SlaveObject){
        System.out.println("You`ve chosen: \""+SlaveObject+"\" ");
        System.out.println("Выберите действие:");
        System.out.println("To create new, enter \"create\" ");
        System.out.println("To edit, enter \"edit\" ");
        System.out.println("To delete, enter \"delete\" ");
        System.out.println("To exit in peace, enter \"quit\" ");
    }
    public static void GodOfSession(){
        String answer="begin";
        while (true){
            switch (answer) {
                case "begin":
                    System.out.println("loading seance menu...");
                    break;
                case "create":
                    CreateSession();
                    break;
                case "edit":
                    EditSession();
                    break;
                case "delete":
                    DeleteSession();
                    break;
                case "quit":
                    return;
                //break; //а вдруг!
                default:
                    System.out.println("Bad input. Try again");

            }
            Main.AdminGodProcesses("seance");
            answer = scanner.nextLine();
            Main.CreateSpace();
            Main.CreateSpace();
        }
    }
    public static void GodAttention(){
        System.out.println("Если где-то ошибся -- жди до конца, там я первый и последний раз спрошу \"Всё верно!?\"");
        System.out.println("Тогда и отменю создание.");
    }
    public static void CreateSession(){
        GodAttention();
        Pause(2000);
        System.out.println("List of cinemas and halls inside them:");
        Pause(1000);
        ShowAllAboutCinemas();
        System.out.println("Enter cinema ID number: ");
        int ChosenCinema=scanner.nextInt();
        if ((ChosenCinema<0)||(ChosenCinema>=CinemaNum.size())){
            ErrorNotExist();
            return;
        }
        System.out.println("Enter hall ID number: ");
        int ChosenHall=scanner.nextInt();
        if ((ChosenHall<0)||(ChosenHall>=HallNum.size())){
            ErrorNotExist();
            return;
        }
        System.out.println("List of avalible films: ");
        ShowAllExistFilms();
        System.out.println("Enter film ID number: ");
        int ChosenFilm=scanner.nextInt();
        if ((ChosenFilm<0)||(ChosenFilm>=Films.size())){
            ErrorNotExist();
            return;
        }
        System.out.println("Enter starting time:");
        //String ChosenBeginningTime = scanner.nextLine();
        String ChosenBeginningTime = scanner.next();
        System.out.println("Enter ending time:");
        //String ChosenEndingTime=scanner.nextLine();
        String ChosenEndingTime=scanner.next();
        System.out.println("You entered:");
        System.out.println("Cinema: "+ChosenCinema+"  ("+CinemaNum.get(ChosenCinema).getName()+"), Hall: "+ChosenHall+"   ("+HallNum.get(ChosenHall).getTypeOfHall()+")");
        System.out.println("Film: "+ChosenFilm+"  ("+Films.get(ChosenFilm).getName()+") , start time: "+ChosenBeginningTime+" , ending time: "+ChosenEndingTime);
        System.out.println("Enter \"create\" if everything is ok, or anything else to discard changes");
        //String ChosenAnswer=scanner.nextLine();
        String ChosenAnswer= scanner.next();
        if (ChosenAnswer.equals("create")){
            seanceNum.add(new Seance(  Films.get(ChosenFilm), new Cinema ( CinemaNum.get(ChosenCinema),ChosenHall, seanceNum.size() ),ChosenBeginningTime,ChosenEndingTime )   ) ;
             seanceNum.get(seanceNum.size()-1).UpdateInformation();
            System.out.println("Created new seance: ");
            seanceNum.get(seanceNum.size()-1).showInf();
            Pause(1500);
        }
        else System.out.println("CANCELED");
    }
    public static void DeleteSession(){
        GodAttention();
        Pause(1000);
        System.out.println("List of seances:");
        Pause(1500);
        Main.ShowSessions();
        System.out.println("Enter seance ID you want to delete, to cancel - enter  0");
        int ChosenSession=scanner.nextInt(); //мы облажались со старой функцией, она выводит i+1 значения на экран
        ChosenSession--; //поэтому понижаем...
        if ((ChosenSession<0)||(ChosenSession>= seanceNum.size())){
            ErrorNotExist();
            return;
        }
        System.out.println("You want to delete:");
        System.out.println("Seance №"+(ChosenSession+1));
        seanceNum.get(ChosenSession).showInf();
        System.out.println("Enter \"delete\" to apply changes, or anything else to discard");
        // String ChosenAnswer=scanner.nextLine();
        String ChosenAnswer=scanner.next();
        if (ChosenAnswer.equals("delete")){
            seanceNum.remove(ChosenSession); //удаляем
            for (int i = ChosenSession; i< seanceNum.size(); i++){
                seanceNum.get(i).DecramentNumberOfSession();
            }
            System.out.println("Result: ");
            System.out.println("Seance №"+(ChosenSession+1));
            seanceNum.get(ChosenSession).showInf();
        }else System.out.println("CANCELED");
    }
    public static void EditSession(){
        GodAttention();
        Pause(2000);
        System.out.println("List of seances:");
        Pause(1500);
        Main.ShowSessions();
        System.out.println("Enter seance ID you want to edit, to cancel enter 0");
        int ChosenSession=scanner.nextInt(); //мы облажались со старой функцией, она выводит i+1 значения на экран
        ChosenSession--; //поэтому понижаем...
        if ((ChosenSession<0)||(ChosenSession>= seanceNum.size())){
            ErrorNotExist();
            return;
        }
        seanceNum.get(ChosenSession).showInf();
        System.out.println("You want to change film? (yes/no)");
        String BoolEdit=scanner.next(); //здесь будем хранить выбор пользователя

        if (BoolEdit.equals("yes")){
            System.out.println("List of avalible films: ");
            ShowAllExistFilms();
            System.out.println("Enter film ID: ");
            int ChosenFilm=scanner.nextInt();
            if ((ChosenFilm<0)||(ChosenFilm>=Films.size())){
                ErrorNotExist();
                return;
            }
            seanceNum.get(ChosenSession).setFilm(Films.get(ChosenFilm));
        }
        System.out.println("Result:");
        Pause(800);
        seanceNum.get(ChosenSession).showInf();
        System.out.println("You want to change time? (yes/no)");
        BoolEdit=scanner.next();
        if (BoolEdit.equals("yes")){
            System.out.println(" Enter starting time:");
            //String ChosenBeginningTime = scanner.nextLine();
            String ChosenBeginningTime = scanner.next();
            System.out.println("Enter ending time:");
            //String ChosenEndingTime=scanner.nextLine();
            String ChosenEndingTime=scanner.next();
            seanceNum.get(ChosenSession).setTimeOfBeginning(ChosenBeginningTime);
            seanceNum.get(ChosenSession).setTimeOfEnding(ChosenEndingTime);
        }
        System.out.println("Result:");
        Pause(800);
        seanceNum.get(ChosenSession).showInf();
        System.out.println("You want to change cinema? (yes/no)");
        BoolEdit=scanner.next();

        if (BoolEdit.equals("yes")){
            System.out.println("СList of cinemas:");
            Pause(1500);
            ShowCinemas();
            System.out.println("Enter cinema ID:");
            int ChosenCinema=scanner.nextInt();
            if ((ChosenCinema<0)||(ChosenCinema>=CinemaNum.size())){
                ErrorNotExist();
                return;
            }
            Hall Buffer= seanceNum.get(ChosenSession).getCinema().getOneHall();
            seanceNum.get(ChosenSession).setCinema(new Cinema(CinemaNum.get(ChosenCinema),0,ChosenSession));
            seanceNum.get(ChosenSession).getCinema().setOneHall(Buffer);
        }
        System.out.println("Result:");
        Pause(800);
        seanceNum.get(ChosenSession).showInf();
        System.out.println("You want to change hall? (yes/no)");
        BoolEdit=scanner.next();
        if (BoolEdit.equals("yes")){
            System.out.println("List of avalible halls:");
            Pause(1500);
            seanceNum.get(ChosenSession).getCinema().ShowHallsInf();
            System.out.println("Enter hall ID: ");
            int ChosenHall=scanner.nextInt();
            Hall Buffer= seanceNum.get(ChosenSession).getCinema().getHallNum().get(ChosenHall);
            seanceNum.get(ChosenSession).getCinema().setOneHall(new CommonHall((CommonHall) Buffer));

        }
        System.out.println("Result:");
        Pause(800);
        seanceNum.get(ChosenSession).showInf();
            System.out.println("Enter anything to finish:");
         ScanMyAss =scanner.next();
    }
    public static void GodOfCinema(){
        String answer="begin";
        while (true){
            switch (answer) {
                case "begin":
                    System.out.println("loading cinema menu...");
                    break;
                case "create":
                    CreateCinema();
                    break;
                case "edit":
                    EditCinema();
                    break;
                case "delete":
                    DeleteCinema();
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Bad input. Try again");

            }
            Main.AdminGodProcesses("cinema");
            answer = scanner.nextLine();
            Main.CreateSpace();
            Main.CreateSpace();
        }

    }
    public static void CreateCinema(){
        GodAttention();
        Pause(2000);
        System.out.println("Enter cinema name:");
        String ChosenName=scanner.nextLine();
        System.out.println("Enter capacity:");
        int ChosenCapacity=scanner.nextInt();
        System.out.println("anything to continue: ");
        ScanMyAss =scanner.nextLine();
        System.out.println("Enter address:");
        String ChosenAdress=scanner.nextLine();
        ArrayList<String> NewFormats = new ArrayList<>();
        while (true){
            System.out.println("Formats list:");
            ShowExistFormats();
            System.out.print("already choosen:   ");
            for (int i=0; i<NewFormats.size(); i++) System.out.print(NewFormats.get(i)+", ");
            System.out.println(". ");
            System.out.println("Enter number of format to add, or  \"-1\" to continue");
            int ChosenFormat = scanner.nextInt();
            if ((ChosenFormat<0)||(ChosenFormat>Formats.length)) break;
            if (NewFormats.contains(Formats[ChosenFormat])){
                System.out.println("Format already chosen");
            }
            else NewFormats.add(Formats[ChosenFormat]);
        }
         String[] NewFormatsArr = new String[NewFormats.size()];
        for (int i=0; i<NewFormatsArr.length; i++){
            NewFormatsArr[i]=NewFormats.get(i);
        }
        ArrayList<Hall> NewHalls = new ArrayList<>();
        while (true){
            System.out.println("Avalible halls:");
            ShowHalls();
            System.out.print("already chosen:   ");
            for (int i=0; i<NewHalls.size(); i++) System.out.print(" Hall № "+NewHalls.get(i).getNumberOfHall()+", ");
            System.out.println(". "); //вдруг там пусто
            System.out.println("Enter number of hall to add, or\"-1\" to continue");
            int ChosenHall = scanner.nextInt();
            if ((ChosenHall<0)||(ChosenHall>HallNum.size())) break;
            if (HallNum.get(ChosenHall).getTypeOfHall().equals("VIP")){
                NewHalls.add(new VipHall((VipHall) HallNum.get(ChosenHall)));
            }
             else NewHalls.add(new CommonHall((CommonHall) HallNum.get(ChosenHall)));
            NewHalls.get(NewHalls.size()-1).setNumberOfHall(NewHalls.size()-1);
        }
        System.out.println("Result. You entered:");
        System.out.println("Name: \""+ChosenName+"\" , capacity: "+ChosenCapacity+"  , address: "+ChosenAdress);
        System.out.print("Formats:");
        for (int i=0; i<NewFormats.size(); i++) System.out.print(NewFormats.get(i)+", ");
        System.out.println(" "); //след. строка
        System.out.println("Halls: ");
        for (int i=0; i<NewHalls.size(); i++){
            NewHalls.get(i).ShowInf();
        }
        System.out.println("Enter \"create\" to apply, or anything to discard");
        //String ChosenAnswer=scanner.nextLine();
        String ChosenAnswer= scanner.next();
        if (ChosenAnswer.equals("create")){
            CinemaNum.add(new Cinema(ChosenName, ChosenCapacity, ChosenAdress, NewFormatsArr,NewHalls,CinemaNum.size()));
            System.out.println("Result: ");
            CinemaNum.get(CinemaNum.size()-1).ShowInf();
            Pause(1500);
        }
        else System.out.println("CANCELED");

    }
    public static void EditCinema(){
        GodAttention();
        Pause(2000);
        System.out.println("List of existing cinemas and halls:");
        Pause(1500);
        Main.ShowCinemas();
        System.out.println("Enter cinema ID you want to edit: ");
        int ChosenCinema=scanner.nextInt();
        if ((ChosenCinema<0)||(ChosenCinema>=CinemaNum.size())){
            ErrorNotExist();
            return;
        }
        System.out.println("Want to change a name? (yes/no)");
        String BoolEdit=scanner.next(); //здесь будем хранить выбор пользователя
        if (BoolEdit.equals("yes")){
            System.out.println("Current name:");
        }
    }
    public static void DeleteCinema(){
        GodAttention();
        Pause(1000);
        System.out.println("List of existing cinemas and halls:");
        Pause(1000);
        ShowAllAboutCinemas();
        System.out.println("Ented cinema ID you want to delete, or \"-1\" to cancel ");
        int ChosenCinema=scanner.nextInt();
        if ((ChosenCinema<0)||(ChosenCinema>= seanceNum.size())){
            ErrorNotExist();
            return;
        }
        System.out.println("You want to delete:");
        System.out.println("Cinema №"+ChosenCinema);
        //SessionNum.get(ChosenSession).showInf();
        CinemaNum.get(ChosenCinema).ShowInf();
        System.out.println("Enter \"delete\" to apply, or anything to cancel");
        // String ChosenAnswer=scanner.nextLine();
        String ChosenAnswer=scanner.next();
        if (ChosenAnswer.equals("delete")){
            seanceNum.remove(ChosenCinema);
            for (int i=ChosenCinema; i<CinemaNum.size(); i++){
                CinemaNum.get(i).setNumberOfCinema(CinemaNum.get(i).getNumberOfCinema()-1);
            }
            System.out.println("Result: ");
            System.out.println("Cinema №"+ChosenCinema);
            CinemaNum.get(ChosenCinema).ShowInf();
        }else System.out.println("CANCEL ");
    }
    public static void GodOfHall(){
        String answer="begin";
        while (true){
            switch (answer) {
                case "begin":
                    System.out.println("loading hall menu...");
                    break;
                case "create":
                    CreateHall();
                    break;
                case "edit":
                    break;
                case "delete":
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Bad input. Try again");

            }
            Main.AdminGodProcesses("hall");
            answer = scanner.nextLine();
            Main.CreateSpace();
            Main.CreateSpace();
        }
    }
    public static void CreateHall(){
        GodAttention();
        Pause(2000);
        System.out.println("Enter row number in new hall:");
        int ChosenCountOfRows=scanner.nextInt();
        if (ChosenCountOfRows<=0) ErrorIsNegative0rZero();
        System.out.println("Enter number of seats in a row: ");
        int ChosenCountOfPlacesInRaw= scanner.nextInt();
        if (ChosenCountOfPlacesInRaw<=0) ErrorIsNegative0rZero();
        System.out.println("Enter max ticket price");
        int ChosenMaxCost= scanner.nextInt();
        if (ChosenMaxCost<=0) ErrorIsNegative0rZero();
        System.out.println("Enter anything to continue: ");
        ScanMyAss =scanner.nextLine();
        System.out.println("Enter hall type: (VIP/Common)");
        String ChosenTypeOfHall=scanner.nextLine();
        System.out.println("You entered:");
        System.out.println("Rows: "+ChosenCountOfRows+" , Seats in a row: "+ChosenCountOfPlacesInRaw);
        System.out.println("Max ticket price: "+ChosenMaxCost+", type: "+ChosenTypeOfHall);
        System.out.println("Enter \"create\" to apply, or anything to discard");
        //String ChosenAnswer=scanner.nextLine();
        String ChosenAnswer= scanner.next();
        if (ChosenAnswer.equals("create")){
            switch (ChosenTypeOfHall){
                case "VIP":
                    HallNum.add(new  VipHall(ChosenCountOfRows,ChosenCountOfPlacesInRaw,ChosenMaxCost,HallNum.size()));
                    break;
                case "Common": HallNum.add(new  CommonHall(ChosenCountOfRows,ChosenCountOfPlacesInRaw,ChosenMaxCost,HallNum.size()));
                default: HallNum.add(new  CommonHall(ChosenCountOfRows,ChosenCountOfPlacesInRaw,ChosenMaxCost,HallNum.size()));

            }
        }
    }
    public static void AdminShowAll(){
        System.out.println("All info on server:");
        Pause(1500);
        ShowExistFormats();
        ShowAllExistFilms();
        ShowCinemas();
        ShowHalls();
        ShowSessions();
        ShowAllClients();

    }
    public static void ClientAccount (){
        System.out.println("Choose action:");
        System.out.println("To see your notifications, enter \"notification\" ");
        System.out.println("To see seances, enter \"seances\" ");
        System.out.println("To see seances by time, enter \"by time\" ");
        System.out.println("To see seances by ticket price, enter \"by price\" ");
        System.out.println("To see seances by film name, enter \"by film\" ");
        System.out.println("To get more pocket money, enter \"money\" ");
        System.out.println("To see purchase history, enter \"history\" ");
        System.out.println("To Log out, enter \"quit\" ");
    }
    public static void BuyTicketMenu(int ChoiceOfSession, int Cash, double Discount){
        System.out.println("Seance menu " + (ChoiceOfSession + 1));
        Main.seanceNum.get(ChoiceOfSession).getCinema().ViewHall();
        System.out.println("Your balance " + Cash + " rubles");
        System.out.println("Choose action:");
        System.out.println("To buy ticket, enter \"purchase\"");
        System.out.println("To rent a hall, enter \"rent\"");
        System.out.println("To exit, enter \"quit\"");
    }
    public static void BoughtTicketsMenu(){
        System.out.println("Choose action:");
        System.out.println("to see all purchased tickets, enter \"tickets\" ");
        System.out.println("To see all rented halls, enter \"rent\" ");
        System.out.println("To log out, enter \"quit\" ");
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
       return ShowSessionsWithCash(1.0);
    }
    public static boolean ShowSessionsWithCash(double Discount){
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
    public static void ShowAllExistFilms(){
        for (int i=0; i<Films.size(); i++){
            Films.get(i).ShowInf();
        }
    }
    public static void ShowCinemas(){
        for (int i=0; i<CinemaNum.size(); i++){
            System.out.println("Cinema №"+i);
            System.out.println(CinemaNum.get(i).toString());
        }
    }
    public static void ShowAllAboutCinemas(){
        for (int i=0; i<CinemaNum.size(); i++){
            CinemaNum.get(i).ShowInf();
        }
    }
    public static void ShowHalls(){
        for (int i=0; i<HallNum.size();i++){
            //System.out.println("Зал №"+i);
            HallNum.get(i).ShowInf();
        }
    }
    public static void ShowExistFormats(){
        for (int i=0; i<Formats.length; i++){
            System.out.print("        №"+i+": "+Formats[i]);
        }
        System.out.println(" ");
    }
    public static void ShowAllClients(){
        for (int i=0; i<ClientNum.size(); i++){
            ClientNum.get(i).ShowInf();
        }
    }
    public static void HallStatistic(){
        System.out.println("Statistics on existing halls:");
        ArrayList<Integer> CountingOfEarnedMoneyFromHallType = new ArrayList<>();
        for (int i=0;i<Main.TypesOfHall.size(); i++){
            CountingOfEarnedMoneyFromHallType.add(0);
        }
        for (int i=0; i<Main.HallNum.size(); i++){
            Main.HallNum.get(i).ShowInf();
            System.out.println("Hall revenue: "+Main.EarnedMoneyFromHallNum.get(i));
            System.out.println("------------------------");
            if (Main.TypesOfHall.indexOf(Main.HallNum.get(i).getTypeOfHall())>-1){
                int Buffer=CountingOfEarnedMoneyFromHallType.get(Main.TypesOfHall.indexOf(Main.HallNum.get(i).getTypeOfHall()));
                CountingOfEarnedMoneyFromHallType.set(Main.TypesOfHall.indexOf(Main.HallNum.get(i).getTypeOfHall()),Buffer+Main.EarnedMoneyFromHallNum.get(i));
            }
        }
        System.out.println(" ");
        System.out.println("Stats on types:");
        for (int i=0; i<Main.TypesOfHall.size(); i++){
            System.out.println("From halls of type \""+Main.TypesOfHall.get(i)+"\" revenue: "+CountingOfEarnedMoneyFromHallType.get(i));
        }
        Main.Pause(3500);
        Main.CreateSpace();
    }
    public static void ClientStatusStatistic(){
        UpdateTypesOfClients();
        System.out.println("Stats on client number:");
        ArrayList<Integer> CountingClientsOfStatusType = new ArrayList<>();
        for (int i=0;i<Main.TypesOfClients.size(); i++){
            CountingClientsOfStatusType.add(0);
        }
        for (int i=0; i<Main.ClientNum.size(); i++){
            if (Main.TypesOfClients.indexOf(Main.ClientNum.get(i).getStatus().getStatusName())>-1){ // такой  статус вообще существует?
                int Buffer=CountingClientsOfStatusType.get(Main.TypesOfClients.indexOf(Main.ClientNum.get(i).getStatus().getStatusName())); //скачали число найденных клиентов этого статуса
                CountingClientsOfStatusType.set(Main.TypesOfClients.indexOf(Main.ClientNum.get(i).getStatus().getStatusName()),Buffer+1);
            }
        }
        for (int i=0;i<Main.TypesOfClients.size(); i++){
            System.out.println("Clinets \""+Main.TypesOfClients.get(i)+"\" in hall: "+CountingClientsOfStatusType.get(i));
        }
        Main.Pause(3500);
        Main.CreateSpace();
    }
    public static void UpdateTypesOfClients(){
        TypesOfClients = new ArrayList<>();
        for (int i=0; i<Main.ClientNum.size(); i++){
            if (!TypesOfClients.contains(Main.ClientNum.get(i).getStatus().getStatusName())) //заносим различные типы статусов в списочек
                TypesOfClients.add(Main.ClientNum.get(i).getStatus().getStatusName());

        }

    }

    public void CreateSessionMenu() {

    }
}


