
import java.util.ArrayList;
import java.util.Collections;

public class mediaRentalMangerDraft implements MediaRentalInt {
    protected ArrayList<Customer> customers = new ArrayList<>();
    protected ArrayList<Media> media = new ArrayList<>();

    //default overrided methods
    @Override
    public void addCustomer(String name, String address, String plan, String ID, String mobileNumber, String limited) {
        int d = -404; //check
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getID().equalsIgnoreCase(ID))
                d = i;
        }
        if (d == -404)
            customers.add(new Customer(name, address, plan, ID, mobileNumber, limited));
        else System.out.println("CUSTOMER ID MUST BE UNIQUE");


    }

    @Override
    public void addMovie(String title, int copiesAvailable, String code, String rating) {
        int d = -404; //checker
        for (int i = 0; i < media.size(); i++) {
            if (media.get(i).getCode().equalsIgnoreCase(code.trim()))
                d = i;
        }
        if (d == -404)
            media.add(new Movie(title, copiesAvailable, code, rating));
        else System.out.println("MEDIA CODE MUT BE UNIQUUE");
    }

    @Override
    public void addGame(String title, int copiesAvailable, String code, double weight) {
        int d = -404; //checker
        for (int i = 0; i < media.size(); i++) {
            if (media.get(i).getCode().equalsIgnoreCase(code.trim()))
                d = i;
        }
        if (d == -404)
            media.add(new Game(title, copiesAvailable, code, weight));
        else System.out.println("MEDIA CODE MUST BE UNIQUE");
    }

    @Override
    public void addAlbum(String title, int copiesAvailable, String code, String artist, String songs) {
        int d = -404; //checker
        for (int i = 0; i < media.size(); i++) {
            if (media.get(i).getCode().equalsIgnoreCase(code.trim()))
                d = i;
        }
        if (d == -404)
            media.add(new Album(title, copiesAvailable, code, artist, songs));
        else System.out.println("MEDIA CODE MUST BE UNIIIQUE");
    }

    @Override
    public void setLimitedPlanLimit(int limitedPlan) {
        System.out.println("I DIDNT USE ( setLimitedPlanLimi) I USED INSTED (getLimited&setLimited)");
    }

    @Override
    public String getAllCustomersInfo() {
        Collections.sort(customers);
        String string = "\t\n";
        for (int i = 0; i < customers.size(); i++) {
            string += customers.get(i).toString() + '\n';
        }
        return string;
    }

    @Override
    public String getAllMediaInfo() {
        Collections.sort(media);
        String string = " \t\n";
        for (int i = 0; i < media.size(); i++) {
            string += media.get(i).toString() + '\n';
        }
        return string;
    }


    @Override
    public boolean addToCart(String customerId, String mediaCode) {
        int d = -404; // this -404 number is to check if its in the array list or no
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getID().trim().equalsIgnoreCase(customerId.trim()))
                d = i;
        }
        if (d == -404)
            return false; // the customer is not in the list
        ArrayList<String> interested = customers.get(d).getInterested(); // to minimize the code(shortcut)

        for (int i = 0; i < interested.size(); i++) {
            if (interested.get(i).trim().equalsIgnoreCase(mediaCode))
                return false;
        }
        customers.get(d).getInterested().add(mediaCode);
        return true;
    }


    @Override
    public boolean removeFromCart(String customerId, String mediaCode) {
        int d = -404;  // this -404 number is to check if its in the array list or no
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getID().trim().equalsIgnoreCase(customerId.trim()))
                d = i;
        }
        if (d == -404)
            return false;   // the customer is not in the list
        ArrayList<String> intrested = customers.get(d).getInterested(); // to minimize the code(shortcut)

        for (int i = 0; i < intrested.size(); i++) {
            if (intrested.get(i).trim().equalsIgnoreCase(mediaCode)) {
                customers.get(d).getInterested().remove(i);
                return true;
            }


        }
        return false;

    }

    @Override
    public String processRequests() {

        String sending = "";
        for (int i = 0; i < customers.size(); i++) {


            for (int j = 0; j < media.size(); j++) {
                if (!customers.get(i).getRented().contains(media.get(j))) {
                    if (customers.get(i).getInterested().contains(media.get(j).getCode())) {
                        if (media.get(j).getNumberOfCopies() > 0) {
                            if (customers.get(i).getPlan().equalsIgnoreCase("unlimited")) {  //checking the plan
                                customers.get(i).getRented().add(media.get(j));
                                customers.get(i).getInterested().remove(media.get(j).getCode());
                                media.get(j).setNumberOfCopies(media.get(j).getNumberOfCopies() - 1); // we took an item

                                sending += "Sending " + "[" + media.get(j).getTitle() + "]" + " to " + "["
                                        + customers.get(i).getName() + "]" + "\n";
                            } else if (customers.get(i).getLimited() > 0) { // checking customers plan
                                customers.get(i).getRented().add(media.get(j));
                                media.get(j).setNumberOfCopies(media.get(j).getNumberOfCopies() - 1);
                                customers.get(i).setLimited(((customers.get(i).getLimited()) - 1));
                                sending += "Sending " + "[" + media.get(j).getTitle() + "]" + " to " + "[" // to show in console
                                        + customers.get(i).getName() + "]" + "\n";
                                customers.get(i).getInterested().remove(media.get(j).getCode());


                            } else System.out.println("limit exceeded");
                        } else System.out.println("NUMBER OF COPIES IS NEGATIVE");


                    }
                }


            }


        }

        return sending;
    }


    @Override
    public boolean returnMedia(String customerId, String mediaCode) {
        int d = -404;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getID().trim().equalsIgnoreCase(customerId.trim()))
                d = i;
        }
        if (d == -404) {
            System.out.println("ERROR 404 : IN (returnMedia) THAT I DIDNT FIND THE CUSTOMER");
            return false;
        }
        int m = -404;
        for (int i = 0; i < media.size(); i++) {
            if (media.get(i).getCode().equalsIgnoreCase(mediaCode))
                m = i;
        }
        if (m==-404){
            System.out.println("ERROR 404 : IN (returnMedia) THAT I DIDNT FIND THE MEDIA");
            return false;

        }

        ArrayList<Media> list = customers.get(d).getRented();
        int t = -404;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equalsIgnoreCase(mediaCode))
                t = i;

        }
        if (t == -404) {
            System.out.println("Item is not rented  (returnMedia)");
            return false;
        } else {
            customers.get(d).getRented().remove(t);
            media.get(m).setNumberOfCopies(media.get(m).getNumberOfCopies() + 1);


            if (customers.get(d).getPlan().equalsIgnoreCase("unlimited"))
                return true;
            else {
                customers.get(d).setLimited(customers.get(d).getLimited() + 1);
                return true;
            }


        }

    }// checkkkkk


    @Override
    public ArrayList<String> searchMedia(String title, String rating, String artist, String songs,String mediaCode) {
        ArrayList<String> list = new ArrayList<>();


        if (rating == null && artist == null && songs == null && title == null&&mediaCode == null) {

            for (int i = 0; i < media.size(); i++) {
                list.add(media.get(i).getTitle());


            }
            Collections.sort(media);
            return list;

        } else if (rating != null || artist != null || songs != null || title != null ||mediaCode!=null) {
            for (int i = 0; i < media.size(); i++) {

                if (artist != null) {
                    if (media.get(i) instanceof Album) {
                        if (((Album) media.get(i)).getArtist().equalsIgnoreCase(artist.trim()))
                            list.add(media.get(i).getTitle());
                    }

                } else if (rating != null) {
                    if (media.get(i) instanceof Movie) {
                        if (((Movie) media.get(i)).getRating().equalsIgnoreCase(rating.trim()))
                            list.add(media.get(i).getTitle());
                    }

                } else if (songs != null) {
                    if (media.get(i) instanceof Album) {
                        if (((Album) media.get(i)).getSongs().equalsIgnoreCase(songs.trim()))
                            list.add(media.get(i).getTitle());
                    }


                } else if (title != null) {
                    if (media.get(i).getTitle().equalsIgnoreCase(title.trim()))
                        list.add(media.get(i).getTitle());


                }
                else if (mediaCode != null) {
                    if (media.get(i).getCode().equalsIgnoreCase(mediaCode.trim()))
                        list.add(media.get(i).getTitle());


                }
            }
        }
        Collections.sort(list);
        return list;

    }
}


