public class Game extends Media { //Game class is a SUBCLASS from Media class
    private double weight;

    // default constructor
    public Game(String title, int numberOfCopies,String code ,double weight) {
        super(title, numberOfCopies,code);
        setWeight(weight);
    }

    // default getters and setters
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight >= 0) { // to avoid negative weight "where is no negative weight"
            this.weight = weight;

        } else {
            this.weight = -1;
            System.out.println("ERROR 404 : weight value error (setWeight)");
        }
    }

    //override to string method
    @Override
    public String toString() {
        return "Game " + "," + super.toString() + weight; // commas to easily split
    }
}
