public class VipClient implements ClientStatus{
    private double Discount =0.8;
    public static final int Target=7; //сколько покупок нужно для достижения
    public void setFilm(String filmname) {}
    public String getFilm() { return ""; }
    private String StatusName="VIP-client";
    public int PaymentWithDiscount(int cash){

        return (int)(cash* Discount);
    }
    public void ClientPerks(){
        System.out.println("Discount applied: "+Math.ceil((1- Discount)*100)+"%");
        System.out.print("As VIP client, you can get a drink absolutely free!");
        System.out.println("Get it in our canteen");
    }

    public double getDiscount() {
        return Discount;
    }
    public int getTarget(){ return Target;}
    public void Congrats(String Name){
        System.out.println("No way, " + Name + " !");
        System.out.println("You`ve made "+Target+"th purchase, which means you gain a new status:");
        System.out.println("\""+getStatusName()+"\" !!");
        System.out.print("Your new privileges:");
        ClientPerks();
    }
    public String getStatusName() {
        return StatusName;
    }

}
