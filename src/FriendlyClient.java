public class FriendlyClient implements ClientStatus{
    private double Discount =0.9;
    public static final int Target=3;
    private static String StatusName="Cartel Friend";
    public void setFilm(String filmname) {}
    public String getFilm() { return ""; }
    public int PaymentWithDiscount(int cash){
        return (int)(cash* Discount);
    }
    public void ClientPerks(){
        System.out.println("You have a discount of "+Math.ceil((1- Discount)*100)+"%");
        System.out.print("Come back again!");
        System.out.println("After 8th visit, you`ll gain a  VIP status!");
    }
    public void Congrats(String Name){
        System.out.println("No way, " + Name + " !");
        System.out.println("You`ve made "+Target+"th purchase, which means you gain a new status:");
        System.out.println("\""+getStatusName()+"\" !!");
        System.out.print("Your new privileges:");
        ClientPerks();
    }

    public double getDiscount() {
        return Discount;
    }

    public String getStatusName() {
        return StatusName;
    }

    public int getTarget() {
        return Target;
    }
}
