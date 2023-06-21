import java.io.FileWriter;
import java.util.ArrayList;

public class Cinema {
    private  String Name;
    private  int Capacity; // вместимость
    private Hall OneHall;
    private int NumberOfOneHall;
    private String Address;
    private String[] Formats;
    private ArrayList<Hall> HallNum = new ArrayList<>();
    private int NumberOfSession=0;
    private int NumberOfCinema=0;

Cinema (String name, int capacity, String address, String[] formats, ArrayList<Hall> NewHalls, int NewNumOfCinema ){
    Name=name;
    Capacity=capacity;
    Address = address;
    Formats = new String[formats.length];
    NumberOfCinema=NewNumOfCinema;
    for (int i=0; i<formats.length; i++){
        Formats[i]=formats[i];
    }
   OneHall = new CommonHall(10,12, 500,0);
    HallNum= (ArrayList<Hall>) NewHalls.clone();
}
Cinema (Cinema NewCinema, int NumberOfHall, int NewNumOfSes){
    NumberOfSession=NewNumOfSes;
    Name=NewCinema.getName();
    Capacity=NewCinema.getCapacity();
    Address =NewCinema.getAddress();
    Formats = new String[NewCinema.getFormats().length];
    for (int i=0; i<NewCinema.getFormats().length; i++){
        Formats[i]=NewCinema.getFormats()[i];
    }
    NumberOfOneHall=NumberOfHall;
    OneHall = NewCinema.getHallNum().get(NumberOfHall);
    OneHall.setNumberOfHall(NumberOfHall);
    HallNum=(ArrayList<Hall>) NewCinema.getHallNum().clone();
}
    public void Save (String FileName,String sep)throws Exception{
        FileWriter fin= new FileWriter(FileName,true);
        String StrOfFormats="";
        for (int i=0; i<Formats.length; i++){
            StrOfFormats=StrOfFormats+Formats[i]; // & - разделитель для массива
            if (i!=Formats.length-1) StrOfFormats=StrOfFormats+"&";
        }
        fin.write(Name+sep+Capacity+sep+ Address +sep+StrOfFormats+"\n");
        fin.close();
    }
public void UpdateInformation(){
    OneHall.setNumberOfSession(NumberOfSession);
    for (int i=0; i<HallNum.size(); i++){
        HallNum.get(i).setNumberOfSession(NumberOfSession);
        HallNum.get(i).setNumberOfHall(i);
    }
}
Cinema () {}

@Override
public String toString() {
    String StrOfFormats="";
    for (int i=0; i<Formats.length; i++){
        StrOfFormats=StrOfFormats+Formats[i]+", ";
    }
    return "Name: " + Name + " , capacity = " + Capacity + " , address = " + Address + "; formats = " + StrOfFormats+"; total halls: "+HallNum.size();
}
    public void ViewHall ()
    {
        //OneHall.ViewTableOfPlaces();
        ViewHall (1.0);
    }
    public void ViewHall (double Discount)
    {
        OneHall.ViewTableOfPlaces(Discount);
    }
    public void ShowInf(){
        System.out.println("Information on cinema №"+NumberOfCinema);
        System.out.println(toString());
        ShowHallsInf();
    }
    public void ShowHallsInf(){
        System.out.println("Information of halls in cinema №"+NumberOfCinema);
        for (int i=0; i<HallNum.size(); i++){
            HallNum.get(i).ShowInf();
        }
        System.out.println("------------------------------------------------");
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public Hall getOneHall() {
        return OneHall;
    }

    public void setOneHall(Hall oneHall) {
        OneHall = oneHall;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String [] getFormats() {
        return Formats;
    }

    public void setFormats(String[] formats) {
        Formats = formats;
    }

    public ArrayList<Hall> getHallNum() {
        return HallNum;
    }
    public void DecrementNumberOfSession(){
        NumberOfSession--;
        OneHall.DecrementNumberOfSession();
    }

    public int getNumberOfCinema() {
        return NumberOfCinema;
    }

    public void setNumberOfCinema(int numberOfCinema) {
        NumberOfCinema = numberOfCinema;
    }

    public void setHallNum(ArrayList<Hall> hallNum) {
        HallNum = hallNum;
    }
}