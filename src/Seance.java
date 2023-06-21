public class Seance { //Сеанс
    private Cinema cinema;
    private Film film;
    private String timeOfBeginning;
    private String timeOfEnding;
    private int NumOfSession=0;
    Seance(Film newFilm, Cinema newCinema, String newTimeOfBeginning, String newTimeOfEnding) {
        cinema=newCinema;
        film=newFilm;
        timeOfBeginning=newTimeOfBeginning;
        timeOfEnding=newTimeOfEnding;
       // NumOfSession=NewNum;
    }
    Seance() {}
    public void showInf (){
        if (cinema.getOneHall().isRented()) System.out.println("!!!This hall is rented!!!");
        film.ShowInf();
        System.out.println("Type: "+cinema.getOneHall().getTypeOfHall() );
        System.out.println(" Seance beginning: "+timeOfBeginning+", ending: "+timeOfEnding+", max ticket price: "+cinema.getOneHall().getMaxCost());
        System.out.println(" Hall rent price: "+ cinema.getOneHall().CostOfRent());
        System.out.println(" Theatre info: Theatre \""+ cinema.getName()+"\" address: "+cinema.getAddress());
    }
    public void UpdateInformation(){
        cinema.UpdateInformation();
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getTimeOfEnding() {
        return timeOfEnding;
    }

    public void setTimeOfEnding(String timeOfEnding) {
        this.timeOfEnding = timeOfEnding;
    }

    public String getTimeOfBeginning() {
        return timeOfBeginning;
    }
    public void DecramentNumberOfSession(){
        NumOfSession--;
        cinema.DecrementNumberOfSession();
    }

    public void setTimeOfBeginning(String timeOfBeginning) {
        this.timeOfBeginning = timeOfBeginning;
    }
}

