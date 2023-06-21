public class Notification {
    public double Payment;
    public Human addressor;
    private Seance seance;
    Notification( double paym, String email)
    {
        Payment = paym;
        addressor = new Human("", "", email, "", 0);
    }
}
