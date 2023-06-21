public class Hall {
    private Seat[][] seatInHall;
    private int CountOfRows;
    private int CountOfPlacesInRow;
    private int MaxCost=1000;
    private boolean IsRented=false;
    public Seat[][] getPlaceInHall() {
        return seatInHall;
    }
    Hall(int Rows, int Places, int MaximumCost){
        CountOfRows=Rows;
        CountOfPlacesInRow=Places;
        Generator (Rows,Places,MaximumCost);
    }
    Hall(){}
    public void Generator(int Rows, int Places, int MaximumCost){
        MaxCost=MaximumCost;
        seatInHall = new Seat[Rows][Places];
        for (int i=1; i<=Rows; i++){
            for (int j=1; j<=Places; j++) {
                double CostOfPlace=MaximumCost-Math.abs(i-Rows/2)*10-Math.abs(j-Places/2)*10;
                seatInHall[i-1][j-1]= new Seat(i,j, (int)CostOfPlace);
            }
        }
    }
    public void ViewTableOfPlaces(){
        System.out.print("    ");
        for (int i=1; i<=CountOfPlacesInRow; i++) {
            AlingmentOfNumber((int)Math.log10((double)MaxCost)+1 , i); // используем для выравнивание количество цифр самого дорогого билета
            System.out.print(" ");
        }
        System.out.println(" "); //переход на следущую строку
        for (int i=0; i<CountOfRows; i++){
            AlingmentOfNumber((int)Math.log10((double)MaxCost)+1,i+1);
            System.out.print(" ");
            for (int j=0; j<CountOfPlacesInRow; j++) {
                if (seatInHall[i][j].getIsBought()){
                    AlingmentOfString((int)(Math.log10((double)MaxCost)+1),"BUS");
                }
                else {
                    AlingmentOfNumber(      (int)(Math.log10((double)MaxCost)+1), seatInHall[i][j].getCost());
                }
                System.out.print(" ");
            }
            System.out.println(" "); //переход на следущую строку
        }
    }
    public void AlingmentOfNumber (int MaxOfSymbols, int number){
        for (int i=0; i<MaxOfSymbols-(  Math.log10((double)number)+1  );i++){
            System.out.print("_");
        }
        System.out.print(number);

    }
    public void AlingmentOfString (int MaxOfSymbols, String newString){
        for (int i=0; i<MaxOfSymbols-(  newString.length()  );i++){
            System.out.print("_");
        }
        System.out.print(newString);

    }
    public boolean CheckOfOpportunityToBuy (int Budget){
        for (int i=0; i<CountOfRows; i++){
            for (int j=0; j<CountOfPlacesInRow; j++){
                if ( (!seatInHall[i][j].getIsBought())&&(seatInHall[i][j].getCost()<=Budget)){  //нашли хотя бы один :)
                    return true;
                }
            }
        }
        return false;
    }
    public  int CostOfRent(){
        int SummOfTickets=0;
        for (int i=0; i<CountOfRows; i++){
            for (int j=0; j<CountOfPlacesInRow; j++){
               SummOfTickets+= seatInHall[i][j].getCost();
            }
        }
        return SummOfTickets;
    }
    public boolean CheckOfOpportunityToRent () {
        for (int i=0; i<CountOfRows; i++){
            for (int j=0; j<CountOfPlacesInRow; j++){
                if ( (seatInHall[i][j].getIsBought())){  //нашли хотя бы одно занятое место :(
                    return false;
                }
            }
        }
        return true;
    }
    public void RentingHall(String NewOwner){
        for (int i=0; i<CountOfRows; i++){
            for (int j=0; j<CountOfPlacesInRow; j++){
                seatInHall[i][j].BuyingPlace(NewOwner);
                }
            }
        IsRented=true;
        }


    public int getCountOfRows() {
        return CountOfRows;
    }

    public void setCountOfRows(int countOfRows) {
        CountOfRows = countOfRows;
    }

    public int getCountOfPlacesInRow() {
        return CountOfPlacesInRow;
    }

    public void setCountOfPlacesInRow(int countOfPlacesInRow) {
        CountOfPlacesInRow = countOfPlacesInRow;
    }

    public void setPlaceInHall(Seat[][] seatInHall) {
        this.seatInHall = seatInHall;
    }

    public int getMaxCost() {
        return MaxCost;
    }

    public void setMaxCost(int maxCost) {
        MaxCost = maxCost;
    }

    public boolean isRented() {
        return IsRented;
    }

    public void setRented(boolean rented) {
        IsRented = rented;
    }

}
