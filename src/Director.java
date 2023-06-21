public class Director implements ClientStatus{
    private double Discount = 0.8;
    public String film;
    private String StatusName="Director";
    public int PaymentWithDiscount(int cash){

        return (int)(cash* Discount);
    }
    public void ClientPerks(){
        System.out.println("Discount applied: "+Math.ceil((1- Discount)*100)+"%");
        System.out.print("As a Director, you can get a drink absolutely free!");
        System.out.println("Get it in our canteen");
    }
    public int getTarget() { return -1; }
    public void setFilm(String filmname) { film = filmname; }
    public String getFilm() { return film; }
    public double getDiscount() {
        return Discount;
    }
    public void Congrats(String Name){
        System.out.println("No way, " + Name + " !");
        System.out.println("You`ve made none`th purchase, which means you gain a new status:");
        System.out.println("\""+getStatusName()+"\" !!");
        System.out.print("Your new privileges:");
        ClientPerks();
    }
    public String getStatusName() {
        return StatusName;
    }

}
