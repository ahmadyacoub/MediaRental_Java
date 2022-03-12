
import java.util.ArrayList;
// THE OFFICIAL INTERFACE FOR THE PROJECT

public interface MediaRentalInt {

    void addCustomer(String name, String address, String plan,String ID,String mobileNumber,String limited);
    //Adds the specified customer to the database
    //I ADDED LIMITED STRING TO USE IT TO SET IF THE PLAN IS LIMITED TO SET ITS VALUE

    void addMovie(String title, int copiesAvailable,String code ,String rating); //Adds the specified movie to the database

    void addGame(String title, int copiesAvailable,String code ,double weight); //Adds the specified game to the database

    void addAlbum(String title, int copiesAvailable,String code, String artist, String songs);//Adds the specified album to the database

    void setLimitedPlanLimit(int value); //This set the number of media associated with the LIMITED plan

    String getAllCustomersInfo(); // returns the information about all customers

    String getAllMediaInfo(); // returns the information about all customers
    //  WHERE SORTED BY TITLE!!!!  (FROM THIS METHOD WE WILL NOTICE THE INHERITANCE OF THE CLASSES)

    boolean addToCart(String customerName, String mediaTitle);
    //Adds the specified media title to the cart associated with a customer

    boolean removeFromCart(String customerName, String mediaTitle);
    //Removes the specified media title from the customers cart

    String processRequests(); //

    boolean returnMedia(String customerName, String mediaTitle); //
    //This method will remove the item from the rented cart and adjust any
    //other values that are necessary

    ArrayList<String> searchMedia(String title, String rating, String artist, String songs,String mediaCode);
    // Returns a SORTED ArrayList with media titles that satisfy the provided parameter values.


}
