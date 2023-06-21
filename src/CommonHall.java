public class CommonHall extends Hall{
    private String TypeOfHall="Common";
    CommonHall(int Rows, int Places, int MaximumCost, int NewNumberOfHall){
        super( Rows,  Places,  (int)(MaximumCost),  "common",NewNumberOfHall);
    }
    CommonHall (CommonHall NewHall){
        super(NewHall);
    }
}
