public class VipHall extends Hall{
    private String TypeOfHall="VIP";
    VipHall(int Rows, int Places, int MaximumCost, int NewNumberOfHall){
        super( Rows,  Places,  (int)(MaximumCost*1.5),  "VIP", NewNumberOfHall);
    }
    VipHall (VipHall NewHall){
        super(NewHall);
    }
    VipHall(){}
}
