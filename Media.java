

public class Media implements Comparable<Media> { // implements the comparable interface to compare the media
    private String title;
    private int numberOfCopies;
    private String code; // added from phase II

    public String getCode() {
        return code;
    }



    public Media(String title, int numberOfCopies,String code) { // a constructor to build the media
        this.title = title;
        this.code=code;
        setNumberOfCopies(numberOfCopies);
    }


    // some default getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        if (numberOfCopies >= 0) {  // TO AVOID NEGATIVE COPIES"THERE ARE NO NEGATIVE COPIES"
            this.numberOfCopies = numberOfCopies;
        } else {
            System.out.println("ERROR 404 : NUMBER OF COPIES MUST BE POSITIVE  IN MEDIA CLASS (setNumberOfCopies)");

            this.numberOfCopies = -1;
        }
    }

    //OVERRIDE METHODS
    @Override
    public String toString() {
        return title + " , " + numberOfCopies + ","+code+","; // I USED  COMMAS TO EASILY SPLIT THEM IN DRIVER
    }

    @Override
    public int compareTo(Media o) {
        Media TEMP = (Media) o;
        return (this.getTitle().trim()).compareToIgnoreCase(TEMP.getTitle().trim());
        // comparing between titles and ignoring the letter casses
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
