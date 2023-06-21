public class Seance { //Сеанс
    private Cinema cinema;
    private Film film;
    private String timeOfBeginning;
    private String timeOfEnding;
    Seance(Film newFilm, Cinema newCinema, String newTimeOfBeginning, String newTimeOfEnding) {
        cinema=newCinema;
        film=newFilm;
        timeOfBeginning=newTimeOfBeginning;
        timeOfEnding=newTimeOfEnding;
    }
    Seance() {}
    public void showInf (){
        if (cinema.getOneHall().isRented()) System.out.println("!!!This hall is rented!!!");
        System.out.println("Film: \""+film.getName()+ "\" ");
        System.out.print("Genre: "+film.getGenre()+", year: "+film.getYear() );
        System.out.println(", length: "+film.getDurationOfFilm()+", format: "+film.getFormat());
        System.out.println(" Seance beginning: "+timeOfBeginning+", seance ending: "+timeOfEnding+", max ticket price: "+cinema.getOneHall().getMaxCost());
        System.out.println(" Hall rent price: "+ cinema.getOneHall().CostOfRent());
        System.out.println(" Theatre info: Theatre \""+ cinema.getName()+"\" address: "+cinema.getAdress());
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

    public void setTimeOfBeginning(String timeOfBeginning) {
        this.timeOfBeginning = timeOfBeginning;
    }
}

