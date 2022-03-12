public class Movie extends Media { // Movie class is a subclassed class from media class
    String rating;

    // default constructor
    public Movie(String title, int numberOfCopies,String code ,String rating) {
        super(title, numberOfCopies,code);
        this.rating = rating;
    }


    //SOME DEFAULT SETTERS & GETTERS
    public String getRating() {

        return rating;
    }

    public void setRating(String rating) {

        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie " + "," + super.toString() + rating;
    }
}
