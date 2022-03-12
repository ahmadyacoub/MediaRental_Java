import java.util.ArrayList;

public class Customer implements Comparable<Customer> {
    private String name;
    private String address;
    private String plan;
    private int limited;
    private String ID, mobileNumber;

    public String getID() {
        return ID;
    }


    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    private ArrayList<Media> rented;// declaring 2 array list to organise the rented and interested media
    private ArrayList<String> interested; // string because we add the media title in it

    public Customer(String name, String address, String plan, String ID, String mobileNumber, String limited) {
        // this limited string to give if the plan limited its value

        if (limited == null&&plan.equalsIgnoreCase("unlimited")) {
            this.name = name;
            this.address = address;
            this.ID = ID;
            this.mobileNumber = mobileNumber;
            setPlan(plan); // to avoid unsupported plans
            rented = new ArrayList<Media>();
            interested = new ArrayList<String>();

        } else if (limited != null && plan.equalsIgnoreCase("limited")) {
            this.name = name;
            this.address = address;
            this.ID = ID;
            this.mobileNumber = mobileNumber;
            rented = new ArrayList<Media>();
            interested = new ArrayList<String>();
            setPlan(plan); //
            setLimited(Integer.parseInt(limited.trim()));

        }
        // to give every customer a unique two list for rented and intrested products
    }


    public void setPlan(String plan) {
        if (plan.trim().equalsIgnoreCase("limited")) {
            this.plan = "LIMITED";


            //  the default plan for limited
        } else if (plan.trim().equalsIgnoreCase("unlimited")) {
            this.plan = "UNLIMITED";
        } else { // TO AVOID ERRORS
            this.plan = "LIMITED";


            // we can change the limit from setLimited method
        }
    }


    public void setLimited(int limited) {
        if (limited >= 0) // WHERE 0 IS SAID TO BE THAT THE CUSTOMER CANT RENT MORE
            this.limited = limited;

        else {
            System.out.println("ERROR 404 : WRONG LIMITED NUMBER"); // IF THERE WAS AN ERROR AND KNOW WHY
            this.limited = -1;

        }
    }

    public int getLimited() {
        // to get if its limited will print the limited value else will give -404 means unlimited
        return limited;
    }


    //  SOME DEFAULT GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlan() {
        return plan;
    }

    public ArrayList<Media> getRented() { // RETURN AN ARRAY LIST FOR THE RENTED MEDIA
        return rented;
    }

    public ArrayList<String> getInterested() { // RETURN AN ARRAY LIST FOR THE INTRESTED MEDIA
        return interested;
    }


    @Override
    public String toString() {
        if (plan.equalsIgnoreCase("unlimited")) {
            return "Customer{" +
                    "name='" + name + '\'' +
                    ",ID =" + ID + '\'' + " , PHONE NUMBER =" + mobileNumber + '\'' + //update
                    ", address='" + address + '\'' +
                    ", plan='" + plan + '\'' + ", limited='" + "unlimited plan applied" + '\'' +
                    ", rented=" + rented + '\n' +
                    ", interested=" + interested +
                    '}';

        }
        return "Customer{" +
                "name='" + name + '\'' +
                ",ID =" + ID + '\'' + " , PHONE NUMBER =" + mobileNumber + '\'' + //update
                ", address='" + address + '\'' +
                ", plan='" + plan + '\'' + ", limited='" + limited + '\'' +
                ", rented=" + rented + '\n' +
                ", interested=" + interested +
                '}';
    }


    @Override
    public int compareTo(Customer o) { // COMPARING BY NAME

        return this.getName().trim().compareToIgnoreCase(o.getName().trim());
        // I used trim method to avoid any logical error
    }
}
