import java.io.Serializable;

public interface ClientStatus extends Serializable {
    public int PaymentWithDiscount(int cash);
    public void ClientPerks();
    public double getDiscount();
    public int getTarget();
    public void setFilm(String name);
    public String getFilm();
    public void Congrats(String Name);
    public  String  getStatusName();
}
