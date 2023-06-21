import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Client extends Human implements Serializable {
    private transient Scanner scanner = new Scanner(System.in);
    Client(String newName, String newTelephoneNumber,String newEmail, String newPassword, int newCash,ClientStatus NewStatus ){
        super (newName, newTelephoneNumber, newEmail, newPassword, newCash, NewStatus);

    }
    Client(String newName, String newTelephoneNumber,String newEmail, String newPassword, int newCash ){
        super (newName, newTelephoneNumber, newEmail, newPassword, newCash, new CommonClient());

    }
    Client (Client newClient){
        super (newClient.Name, newClient.TelephoneNumber, newClient.getEmail(), newClient.getPassword(), newClient.getCash(), newClient.getStatus());
    }
    public void SaveSer (ObjectOutputStream objectOutputStream ) throws Exception{
        Client ClientToSave = new Client(Name,TelephoneNumber,Email,Password,Cash,Status);
        super.SaveSer(objectOutputStream, ClientToSave);
    }


}