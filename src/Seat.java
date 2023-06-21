public class Seat extends Ticket {
   // private int NumberOfRow;
  //  private int NumberOfPlace;
   // private int Cost;
    private boolean IsBought=false;
    //private String OwnerOfPlace="";

    Seat() {}
    Seat(int row, int place, int cost, int NumberOfSession) {
       //NumberOfRow=row;
       // NumberOfPlace=place;
       //Cost=cost;
        super ( row, place, cost,NumberOfSession);
    }
    public void BuyingPlace(String NewOwner) {
        IsBought=true;
        OwnerOfPlace=NewOwner;
    }

    public void ShowInf(){
        System.out.println("Ticket on film \""+FilmOfTicket.getName()+"\"");
        System.out.println("Row "+(NumberOfRow+1) + " Seat " + (NumberOfPlace+1));
        System.out.println("Seance ID number: "+NumberOfSession);
        System.out.println("Beginning time:"+Main.seanceNum.get(NumberOfSession).getTimeOfBeginning());
        System.out.println("Ending time:"+Main.seanceNum.get(NumberOfSession).getTimeOfEnding());
    }

    public boolean getIsBought() {
        return IsBought;
    }

    public void setBought(boolean bought) {
        IsBought = bought;
    }
}

