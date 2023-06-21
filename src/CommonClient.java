public class CommonClient implements ClientStatus {
    private double Discount =1.0;
    public static final int Target=0;
    public void setFilm(String filmname) {}
    public String getFilm() { return ""; }
    private String StatusName="Guest";
    public int PaymentWithDiscount(int cash){
        return (int)(cash* Discount);
    }
    public void ClientPerks(){
        System.out.println("Your status: "+StatusName);
        System.out.println("No discount was applied.");
        System.out.println("The more you visit our cinemas, the more toy gain! ");
        System.out.println("You`ll get 10% discounts on 4th visit");
        System.out.println("And after 8th - you`l become a VIP user!");
    }

    public double getDiscount() {
        return Discount;
    }
    public int getTarget(){return Target;}
    public void Congrats(String Name){
        System.out.println("Good news, " + Name + " !");
        System.out.println("You`ve made "+Target+"th purchase, which means you gained a new status:");
        System.out.println("\""+getStatusName()+"\" !!");
        System.out.print("Your new privileges:");
        ClientPerks();
    }

    public String getStatusName() {
        return StatusName;
    }
}
