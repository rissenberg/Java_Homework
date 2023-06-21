public class Film {
    private String Name;
    private int Year;
    private String Genre; // жанр
    private int DurationOfFilm;
    private String Format;
    private int count = 0;
    Film (String newName, int newYear, String newGenre, int newDurationOfFilm, String newFormat) {
        Name=newName;
        Year=newYear;
        Genre=newGenre;
        DurationOfFilm=newDurationOfFilm;
        Format=newFormat;
    }

    public void showInf (){
        System.out.println("Title: \""+Name+ "\" ");
        System.out.print("Genre: "+Genre+", year: "+Year );
        System.out.println(", length: "+DurationOfFilm+", format: "+Format);
        System.out.println(" Tickets sold: "+count);
    }
    Film (){}
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public int getDurationOfFilm() {
        return DurationOfFilm;
    }

    public void setDurationOfFilm(int durationOfFilm) {
        DurationOfFilm = durationOfFilm;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

// выввести самые рентабельные фильмы, вывести их по длительности
// к третьей: написать сериалайзеры к классам клиент, админ, кинотеатр