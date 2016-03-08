package lab;

/**
 * Created by Mike Plucker
 * Date: 2/23/2016
 * Class: CSCI 1933-12
 */
public class Contact {

    //private instance variables
    private String name, address, comments;
    private long phone;


    //constructor without address or comments attribute
    public Contact(String name, long phone){
        this.name = name;
        this.phone = phone;
        this.address = "";
        this.comments = "";
    }


    //constructor without comments attribute
    public Contact(String name, long phone, String address){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.comments = "";
    }


    //constructor with all attributes
    public Contact(String name, long phone, String address, String comments){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.comments = comments;
    }


    //getters and setters for each attribute of the class
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPhone(long phone){
        this.phone = phone;
    }

    public long getPhone(){
        return phone;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setComments(String comments){
        this.comments = comments;
    }

    public String getComments(){
        return comments;
    }


    //returns a string containing all the attributes of the class with the most significant attribute appearing first.
    @Override
    public String toString(){
        return "\nName: " + getName() + "\nPhone: " + getPhone() + "\nAddress: " + getAddress() + "\nComments: " + getComments();
    }


    //will check to see if each attribute is the same between two classes
    public boolean equals(Contact c){

        if(c.getName().equals(getName()) && c.getPhone() == getPhone() && c.getAddress().equals(getAddress()) && c.getComments().equals(getComments())){
            return true;
        }

        return false;
    }


    //tests Contact class
    public static void main(String[] args) {

        //create contact objects
        Contact mike = new Contact("Mike", 8309991234L, "1234 Pleasent Street", "Cool dude");
        Contact michael = new Contact("Mike", 8309991234L, "1234 Pleasent Street", "Cool dude");
        Contact greg = new Contact("Greg", 9521231234L, "1324 Main Street"); //no comments
        Contact al = new Contact("Al", 6123258594L); //no address or comments


        //test toString method
        System.out.println(mike.toString());
        //System.out.println(frank.toString());


        //test equals method (true)
        if(mike.equals(michael)){
            System.out.println("\nMike has the same contact info as Michael!");
        }
        else{
            System.out.println("\nMike is not Michael!");
        }

        //test equals method (false)
        if(mike.equals(al)){
            System.out.println("\nMike has the same contact info as Al!");
        }
        else{
            System.out.println("\nMike is not Al!");
        }


        //test other two constructors
        System.out.println(greg.toString());
        System.out.println(al.toString());


        System.out.println("\nContacts with added information");

        //test some setters
        greg.setComments("Work Buddy");
        al.setAddress("1999 Prince Lane");

        //print out contacts
        System.out.println(greg.toString());
        System.out.println(al.toString());
    }
}
