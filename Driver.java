
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Driver   {
    static mediaRentalMangerDraft m = new mediaRentalMangerDraft();

    public static void main(String[] args) throws IOException {
        ArrayList<String> cID = new ArrayList<>();
        // CUSTOMERS TEXT FILE READ BEGINS

        File cust = new File("customersLeftLimited.txt"); // reading customers and store them
        Scanner scanner = new Scanner(cust);

        while (scanner.hasNextLine()) {
            // read the customers with last customers in their last limited
            String line = scanner.nextLine();
            String[] array = line.split(",");
            if (array.length == 5) {
                if (!cID.contains(array[3].trim())) {
                    m.customers.add(new Customer(array[0].trim(), array[1].trim()
                            , array[2].trim(), array[3].trim(), array[4].trim(), null));
                    cID.add(array[3].trim());

                }
            } else if (array.length == 6) {
                if (!cID.contains(array[3].trim())) {
                    m.customers.add(new Customer(array[0].trim(), array[1].trim()
                            , array[2].trim(), array[3].trim(), array[4].trim(), array[5].trim()));
                    cID.add(array[3].trim());

                }

            }


        }


        //CUSTOMER TEXT FILE READING FINISHED


        //MEDIA FILE READING BEGINS

        File file = new File("mediaLeft.txt"); // with the file with last copies
        Scanner scan = new Scanner(file);

        // to read media file and store it

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] array = line.split(",");

            if (array.length == 6) {
                m.media.add(new Album(array[1].trim(), Integer.parseInt(array[2].trim()),
                        array[3].trim(), array[4].trim(), array[5].trim()));

            } else if (array.length == 5) {

                if (array[0].trim().equalsIgnoreCase("Game")) {
                    //  checkMedia.add(m.media.get(i).getTitle() + array[0]);
                    m.media.add(new Game(array[1].trim(),
                            Integer.parseInt(array[2].trim()), array[3].trim(),
                            Double.parseDouble(array[4].trim())));

                } else if (array[0].trim().equalsIgnoreCase("Movie")) {
                    // checkMedia.add(m.media.get(i).getTitle() + array[0]);
                    m.media.add(new Movie(array[1].trim(), Integer.parseInt(array[2].trim())
                            , array[3].trim(), array[4].trim()));


                }


            }


        }
        System.out.println(m.getAllMediaInfo());

        //MEDIA FILE READING FINISHED

        //INTRESTED FILE READING BEGINS

        File intr = new File("Intrested.txt");
        // reading from file to add into intrested for every customer
        Scanner intScan = new Scanner(intr);
        while (intScan.hasNextLine()) {         // to read intrested and store it
            String line = intScan.nextLine().trim();
            String[] array = line.split(",");

            if (array.length == 2) {
                for (int i = 0; i < m.customers.size(); i++) {
                    if (m.customers.get(i).getName().equalsIgnoreCase(array[0].trim())) {
                        m.addToCart(array[0].trim(), array[1].trim());

                    }

                }

            }


        }

        //INTRESTED FILE READING FINISHED

        writeText("intrested.txt", "", false); // TO REFRESH INTRESTED FILE

        // BY REMOVING previous intrested that MAY BEREMVED FROM CART LATER AFTER PROCESS

        // RENTED FILE READING BEGINS
        File rent = new File("Rented.txt");
        Scanner scanRent = new Scanner(rent);
        //to read rented file and store ittttt

        while (scanRent.hasNextLine()) {
            String line = scanRent.nextLine();
            String[] array = line.split(",");
            if (array.length == 8 || array.length == 7 || array.length == 6) {
                // to avoid the reading errors while the maximum texts in the line spreaded by ',' is 8

                int d = -404;// to get the id of media


                for (int i = 0; i < m.media.size(); i++) {
                    if (m.media.get(i).getCode().equalsIgnoreCase(array[5].trim()))
                        d = i;

                }

                for (int i = 0; i < m.customers.size(); i++) {
                    if (array[0].trim().equalsIgnoreCase(m.customers.get(i).getID())) {
                        m.customers.get(i).getRented().add(m.media.get(d));

                    }


                }


            }

        }

        writeText("Rented.txt", "", false); // to remove text from file to re type it next
        //RENTED FILE READING FINISHED

//SAMPLE INPUT
/*
       testAddingMedia("movie","Spider Man",2,"mv1","A"
                               ,null,null,0);
        testAddingMedia("Album","Palestine",2,"mv2",null
                ,"Adham","ramallah",0);
        testAddingMedia("Game","FiFa",2,"mv3",null
                ,null,null,10.1);
        testAddingCustomer("ahmad", "ein yabroud", "unlimited",
                "1202450", "0595389009", null);
        testAddingCustomer("Ismail", "ein yabroud", "unlimited"
                , "112", "005", null);
        testAddingCustomer("Sleman", "ein yabroud", "limited"
                , "111", "006", "1");

        testingAddingToCart("ahmad", "FiFa");

              testingAddingToCart("1202450", "mv3");
        testingAddingToCart("112", "mv3");
        testingAddingToCart("111", "mv2");
        testingAddingToCart("1202450", "mv2");
        testingAddingToCart("111", "mv1");
          testingAddingToCart("Sleman","FiFa");
   */



     /*  //  THE CODE DOWN PROVES THAT THE PROCESS METHOD WORKS 100/100 !!!!!!
         System.out.println(m.customers.get(0));
        System.out.println(m.getAllMediaInfo());

         m.addCustomer("A","B","unlimited");
        m.addGame("gta",2,20);
         System.out.println(m.addToCart("A","gta"));
         System.out.println(m.getAllCustomersInfo());
         m.processRequests();
         System.out.println(m.getAllCustomersInfo());

        // THE CODE ABOVE PROVESSSSSSSSS THAT THE PROCESS METHOD WORKS 100/100 !!!!! */// WAS BEFORE

/*
        try {
            for (int i = 0; i < m.customers.size(); i++) {


                File fOut = new File("Rented.txt");
                FileWriter writer = new FileWriter(fOut, true);
                PrintWriter printWriter = new PrintWriter(writer);
                printWriter.println(m.customers.get(i).getName() + " "
                        + m.customers.get(i).getRented());
                printWriter.close();
                File fIntrested = new File("intrested.txt");
                FileWriter wINTR = new FileWriter(fIntrested, true);
                PrintWriter printWriterred = new PrintWriter(wINTR);
                printWriterred.println(m.customers.get(i).getName() + " "
                        + m.customers.get(i).getInterested());
                printWriterred.close();

            }
        } catch (Exception exception) {


        }  */   // was in the phase one before update


        intrestedFile(); // TO GET INTRESTED FILE

        testProcessingRequestsOne(); // process request


        rentedFile(); // to add THE TEXT to  the rented file
        writeText("mediaLeft.txt", "", false);//creating new file to see left media
        // delete previous data from mediaLeft.txt


        // WRITING ON mediaLeft.txt  DATA ADDING BEGINS
        for (int i = 0; i < m.media.size(); i++) {
            if (m.media.get(i) instanceof Movie) {
                writeText("mediaLeft.txt", "Movie ," + m.media.get(i).getTitle()
                        + " , " + m.media.get(i).getNumberOfCopies() + "," + m.media.get(i).getCode()
                        + " , " + ((Movie) m.media.get(i)).rating.trim(), true);
            } else if (m.media.get(i) instanceof Album) {
                writeText("mediaLeft.txt", "Album ," + m.media.get(i).getTitle().trim()
                        + " , " + m.media.get(i).getNumberOfCopies()
                        + "," + m.media.get(i).getCode() + " , " + ((Album) m.media.get(i)).getArtist().trim()
                        + " , " + ((Album) m.media.get(i)).getSongs(), true);
            } else if (m.media.get(i) instanceof Game) {
                writeText("mediaLeft.txt", "Game ," + m.media.get(i).getTitle().trim()
                        + " , " + m.media.get(i).getNumberOfCopies()
                        + "," + m.media.get(i).getCode() + " , " + ((Game) m.media.get(i)).getWeight(), true);
            }


        }

        // ADDING DATA ON mediaLeft.txt FINISHED
        writeText("intrested.txt", " ", false); // to refresh intrested file text
        intrestedFile(); // TO GET INTRESTED FILE

        // WRITING ON customersLeftLimited.txt  DATA ADDING BEGINS
        writeText("customersLeftLimited.txt", "", false); // to refresh the file
        for (int i = 0; i < m.customers.size(); i++) {
            //  a for loop to print customers with new limited for limited plans and store it in new file
            Customer customer = m.customers.get(i);
            if (customer.getPlan().equalsIgnoreCase("unlimited")) {
                writeText("customersLeftLimited.txt",
                        customer.getName().trim() + "," + customer.getAddress().trim() + ","
                                + customer.getPlan().trim().toLowerCase() + "," + customer.getID() + "," + customer.getMobileNumber(), true);
            } else if (customer.getPlan().trim().equalsIgnoreCase("limited")) {

                writeText("customersLeftLimited.txt",
                        customer.getName().trim() + "," + customer.getAddress().trim() + "," +
                                customer.getPlan().trim().toLowerCase() + "," + customer.getID() + "," + customer.getMobileNumber() + ","
                                + customer.getLimited(), true);


            }

        }

        //  // ADDING DATA ON customersLeftLimited.txt FINISHED

    }

    private static void intrestedFile() throws IOException { // extra method to get intrested text file
        for (int i = 0; i < m.customers.size(); i++) {



            for (int j = 0; j < m.customers.get(i).getInterested().size(); j++) {
                writeText("intrested.txt", m.customers.get(i).getName() + " , " +
                        m.customers.get(i).getInterested().get(j), true);

            }


        }


    }

    private static void rentedFile() throws IOException {

        for (int i = 0; i < m.customers.size(); i++) {
            for (int j = 0; j < m.customers.get(i).getRented().size(); j++) {

                writeText("Rented.txt", m.customers.get(i).getID() + "," + m.customers.get(i).getName() +
                                ", " + m.customers.get(i).getRented().get(j)
                        , true);
            }


        }
    }


    public static void writeText(String fileName, String text, boolean append) throws IOException {
        // static method to write strings to the file
        // the boolean all f its true it will keep the previous data
        //else if falsse it will only print the new data
        File fOut = new File(fileName);
        FileWriter writer = new FileWriter(fOut, append);
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(text);
        printWriter.close();


    }


    public static void testAddingCustomer(String name, String address, String plan, String ID, String mobileNumber,
                                          String limited) throws IOException { // this limited used to give the limited if it was limited (the plan)
        int d = -404;  // checker
        for (int i = 0; i < m.customers.size(); i++) {
            if (m.customers.get(i).getID().equalsIgnoreCase(ID.trim()))
                d = i;
        }
        if (d == -404) {
            if (plan.equalsIgnoreCase("unlimited") && limited == null) {
                writeText("customers.txt",
                        name.trim() + "," + address.trim() + ","
                                + plan.trim().toLowerCase() + "," + ID + "," + mobileNumber, true);
                m.addCustomer(name, address, plan, ID, mobileNumber, null);
            } else if (plan.equalsIgnoreCase("limited")) {
                if (limited != null) {
                    writeText("customers.txt",
                            name.trim() + "," + address.trim() + "," + plan.trim().toLowerCase() + "," + ID + "," + mobileNumber + ","
                                    + Integer.parseInt(limited.trim()), true);
                    m.addCustomer(name, address, plan, ID, mobileNumber, limited);
                }
            }
        } else System.out.println("CUSTOMER ID MUST BE UNIQUE");
    }

    public static void testAddingMedia(String type, String title,
                                       int copiesAvailable, String code,
                                       String rating, String artist, String songs,
                                       double weight) throws IOException {
        int d = -404; // checker
        for (int i = 0; i < m.media.size(); i++) {
            if (m.media.get(i).getCode().equalsIgnoreCase(code.trim()))
                d = i;
        }
        if (d == -404) {
            if (type.trim().equalsIgnoreCase("Movie")) {
                m.addMovie(title, copiesAvailable, code, rating);
                writeText("media.txt", "Movie ," + title.trim()
                        + " , " + copiesAvailable + "," + code + " , " + rating.trim(), true);

            } else if (type.trim().equalsIgnoreCase("Album")) {
                m.addAlbum(title, copiesAvailable, code, artist, songs);
                writeText("media.txt", "Album ," + title.trim()
                        + " , " + copiesAvailable + "," + code + " , " + artist.trim() + " , " + songs, true);
            } else if (type.trim().equalsIgnoreCase("Game")) {
                writeText("media.txt", "Game ," + title.trim()
                        + " , " + copiesAvailable + "," + code + " , " + weight, true);
                m.addGame(title, copiesAvailable, code, weight);
            }
        } else System.out.println("MEDIA CODE MUST BE UNIQUE");

    }

    public static void testingAddingToCart(String customerId, String mediaCode) {
        m.addToCart(customerId, mediaCode);

    }

    public static void testingRemovingFromCart(String customerId,
                                               String mediaCode) {
        m.removeFromCart(customerId, mediaCode);
    }

    public static void testProcessingRequestsOne() {
        m.processRequests();

    }

    //  public static void testProcessingRequestsTwo(){}
    // i have used only one processing method in rental manager
    public static void testReturnMedia(String customerId, String mediaCode) {
        m.returnMedia(customerId, mediaCode);

    }

    public static void testSearchMedia(String title,
                                       String rating, String artist, String songs,String mediaCode) {
        m.searchMedia(title, rating, artist, songs,mediaCode);

    }


}





