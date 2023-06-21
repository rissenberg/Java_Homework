public class Seat {
    private int NumberOfRow;
    private int NumberOfPlace;
    private int Cost;
    private boolean IsBought=false;
    private String OwnerOfSeat = "";

    Seat() {}
    Seat(int row, int place, int cost) {
        NumberOfRow=row;
        NumberOfPlace=place;
        Cost=cost;
    }
    public void BuyingPlace(String NewOwner) {
        IsBought=true;
        OwnerOfSeat =NewOwner;
    }
    public int getNumberOfRow() {
        return NumberOfRow;
    }
    public void setNumberOfRow(int numberOfRow) {
        NumberOfRow = numberOfRow;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public int getNumberOfPlace() {
        return NumberOfPlace;
    }

    public void setNumberOfPlace(int numberOfPlace) {
        NumberOfPlace = numberOfPlace;
    }

    public boolean getIsBought() {
        return IsBought;
    }

    public void setBought(boolean bought) {
        IsBought = bought;
    }
}

