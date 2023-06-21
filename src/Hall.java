public abstract class Hall {
    protected Seat[][] seatInHall;
    protected int CountOfRows;
    protected int CountOfPlacesInRow;
    protected int MaxCost=1000;
    protected boolean IsRented=false;
    protected   String TypeOfHall="common";
    protected int NumberOfSession;
    protected int NumberOfHall;
    protected int EarnedCash=0;
    protected Film FilmOfHall;
    public Seat[][] getPlaceInHall() {
        return seatInHall;
    }
    Hall(int Rows, int Places, int MaximumCost, String NewType,int NewNumOfHall){
        CountOfRows=Rows;
        CountOfPlacesInRow=Places;
        MaxCost=MaximumCost;
        Generator (Rows,Places,MaximumCost);
        TypeOfHall=NewType;
        NumberOfHall=NewNumOfHall;

    }
    Hall (Hall NewHall){
        CountOfRows=NewHall.getCountOfRows();
        CountOfPlacesInRow=NewHall.getCountOfPlacesInRow();
        MaxCost=NewHall.getMaxCost();
        Generator (CountOfRows,CountOfPlacesInRow,NewHall.getMaxCost());
        TypeOfHall=NewHall.getTypeOfHall();
        NumberOfHall=NewHall.getNumberOfHall();
        NumberOfSession= NewHall.NumberOfSession;
    }
    Hall(){}
    public void Generator(int Rows, int Places, int MaximumCost){
        MaxCost=MaximumCost;
        seatInHall = new Seat[Rows][Places];
        for (int i=1; i<=Rows; i++){
            for (int j=1; j<=Places; j++) {
                double CostOfPlace=MaximumCost-Math.abs(i-Rows/2)*10-Math.abs(j-Places/2)*10;
                seatInHall[i-1][j-1]= new Seat(i,j, (int)CostOfPlace, NumberOfSession );
            }
        }
    }
    public void ShowInf(){
        System.out.println("Hall №"+NumberOfHall);
        System.out.println("Type: "+TypeOfHall);
        System.out.println("Max ticket price: "+MaxCost);
        System.out.println("Number of rows: "+CountOfRows);
        System.out.println("Number of seats in a row: "+CountOfPlacesInRow);
        System.out.println("-------------------------");
    }
    public void ViewTableOfPlaces() {
        ViewTableOfPlaces(1.0);
    }
        public void ViewTableOfPlaces(double Discount){
            System.out.println("Discount of "+Math.ceil((1-Discount)*100)+"% is applied");
        System.out.print("    ");
        for (int i=1; i<=CountOfPlacesInRow; i++) {
            AlingmentOfNumber((int)Math.log10(Discount*(double)MaxCost)+1 , i); // используем для выравнивание количество цифр самого дорогого билета
            System.out.print(" ");
        }
        System.out.println(" ");
        for (int i=0; i<CountOfRows; i++){
            AlingmentOfNumber((int)Math.log10(Discount*(double)MaxCost)+1,i+1);
            System.out.print(" ");
            for (int j=0; j<CountOfPlacesInRow; j++) {
                if (seatInHall[i][j].getIsBought()){
                    AlingmentOfString((int)(Math.log10(Discount*(double)MaxCost)+1),"BUS");
                }
                else {
                    AlingmentOfNumber(      (int)(Math.log10(Discount*(double)MaxCost)+1), (int)(Discount* seatInHall[i][j].getCost()) );
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
                if ( (!seatInHall[i][j].getIsBought())&&(seatInHall[i][j].getCost()<=Budget)){
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
                if ( (seatInHall[i][j].getIsBought())){
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

    public String getTypeOfHall() {
        return TypeOfHall;
    }

    public void setTypeOfHall(String typeOfHall) {
        TypeOfHall = typeOfHall;
    }
    public void DecrementNumberOfHall(){NumberOfHall--;}
    public void DecrementNumberOfSession(){
        NumberOfSession--;
        for (int i=0; i<CountOfRows; i++){
            for (int j=0; j<CountOfPlacesInRow; j++){
                seatInHall[i][j].setNumberOfSession(NumberOfSession);
            }
        }
    }

    public int getNumberOfSession() {
        return NumberOfSession;
    }

    public int getEarnedCash() {
        return EarnedCash;
    }

    public void UpEarnedCash(int earnedCash) {
        EarnedCash += earnedCash;
        int shitBuffer=Main.EarnedMoneyFromHallNum.get(NumberOfHall);
        Main.EarnedMoneyFromHallNum.set(NumberOfHall,shitBuffer+earnedCash);
    }

    public int getNumberOfHall() {
        return NumberOfHall;
    }

    public void setNumberOfHall(int numberOfHall) {
        NumberOfHall = numberOfHall;
    }

    public void setNumberOfSession(int numberOfSession) {
        NumberOfSession = numberOfSession;
        for (int i=0; i<CountOfRows;i++){
            for (int j=0; j<CountOfPlacesInRow; j++){
                seatInHall[i][j].setNumberOfSession(numberOfSession);
            }
        }
    }
    //  @Override
   // public String toString() {
       // return
   // }
}
