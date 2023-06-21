public class Cinema {
    private  String Name;
    private  int Capacity; // вместимость
    private Hall OneHall;
    private String Adress;
    private String[] Formats;

Cinema (String name, int capacity, String adress, String[] formats ){
    Name=name;
    Capacity=capacity;
    Adress=adress;
    Formats = new String[formats.length];
    for (int i=0; i<formats.length; i++){
        Formats[i]=formats[i];
    }
   OneHall = new Hall(10,12, 500);
}
Cinema () {}

@Override
public String toString() {
    String StrOfFormats="";
    for (int i=0; i<Formats.length; i++){
        StrOfFormats=StrOfFormats + Formats[i]+", ";
    }
    return "Name = " + Name + " , capacity = " + Capacity + " , address = " +Adress + "; formats = " + StrOfFormats;
}
    public void ViewHall ()
    {
        OneHall.ViewTableOfPlaces();
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

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String address) {
        Adress = address;
    }

    public String [] getFormats() {
        return Formats;
    }

    public void setFormats(String[] formats) {
        Formats = formats;
    }
}