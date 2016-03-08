package lab;

//imports
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
//import java.util.Arrays;
//import org.apache.commons.lang.ArrayUtils;

/**
 * Created by Mike Plucker
 * Date: 2/23/2016
 * Class: CSCI 1933-12
 */
public class ContactList {

    //instance variable
    private int ptr; //holds the index of the most recent contact referenced

    //contacts array
    private Contact[] contacts;

    //declare scanner/writer
    private Scanner scan = null;
    private PrintWriter p = null;



    //default constructor
    public ContactList(){
        contacts = new Contact[20]; //creates a ContactList that is of length 20
        ptr = -1; //ContactList is initially empty
    }


    //constructor
    public ContactList(int length){
        contacts = new Contact[length]; //creates a ContactList with whatever length is passed in by the parameter
        ptr = -1; //ContactList is initially empty
    }


    //add a new Contact at the end of ContactList and return true if successful
    public boolean add(Contact c){

        if(ptr == contacts.length - 1){ //if array is full
            System.out.println("Error: Can't Add Contact, Contact List is Full!");
            return false;
        }
        else{ //if array isn't full yet
            ptr++; //move ptr ahead to next contact
            contacts[ptr] = c; //inserts the contact where the ptr counter is currently indexed
            return true;
        }
    }


    //returns the first Contact found, relative to the current position in the ContactList
    public Contact find(String name){

        for(int i = 0; i < contacts.length; i++){ //will iterate over all names in contracts array

            int length = contacts[i].getName().length(); //finds the length of the contact's name at that index

            if(length == name.length()){ //if contact's name's length is the same as the length of the name passed in to the parameter
                for(int j = 0; j < length; j++){ //iterates over the length of the contact's name

                    char character = contacts[i].getName().charAt(j); //takes each character from contact's name

                    if(character == name.charAt(j)){
                        if(j == length - 1){
                            return contacts[i]; //if all characters found in name...return that contact
                        }
                    }
                    else{
                        break; //if character is not the same, then break and check for next name
                    }
                }
            }
        }
        return null;
    }


    //returns the first contact found, relative to the current position in the ContactList...using the contains() method
    public Contact findFast(String name){

        //enhanced for loop to search for name among contacts
        for(Contact findName : contacts){ //iterates over the contacts array
            if(findName.getName().contains(name)){ //if a contact's name contains the name passed in thru the parameter
                return findName; //return that contact
            }
        }
        return null;
    }


    //removes the "current" contact pointed to and returns it
    public Contact remove(){

        if(ptr == -1){ //ContactList is empty
            System.out.println("Error: Can't Remove Contact, No contacts found in Contact List.");
            return null;
        }
        else{
            Contact current = contacts[ptr]; //takes contact that is at the ptr index
            System.arraycopy(contacts, ptr + 1, contacts, ptr, contacts.length - 1 - ptr);

            //Contact current = ArrayUtils.removeElement(contacts, ptr); //need to add apache library to work!

            ptr--; //move ptr back to previous contact
            return current; //returns the contact that was removed
        }
    }


    //prints out the contacts in the ContactList to the console
    public void printToConsole(){

        //enhanced for loop to print out contacts
        for(Contact print : contacts){
            System.out.println(print);
        }
    }


    //writes the ContactList information to a textfile named with the String provided
    public boolean write(String fileName){

        try{ //test for Exception in call to File constructor
            p = new PrintWriter(new File(fileName));
        }
        catch(Exception e){}

        //enhanced for loop to print contacts to file
        for(Contact fileWrite : contacts){ //iterates over contacts array
            p.println(fileWrite); //prints the contacts to the file
        }

        p.close(); //closes the file

        return true;
    }


    //reads the ContactList information from the textfile named with the String provided
    public boolean read(String fileName){

        try{ //test for Exception in call to File constructor
            scan = new Scanner(new File(fileName)); //reads information from file
        } //end try block for File usage
        catch(Exception e){}

        System.out.println("\nContacts in Contact List: ");

        printToConsole(); //print all the contacts to the console

        return true;
    }


    //returns the contact currently pointed to
    public Contact getCurrent(){
        return contacts[ptr]; //return current contact
    }


    //returns the contact at the location represented by i
    public Contact get(int i){

        //local variable
        int index = i - 1; //subtract 1 from input number to get the appropriate index

        if(ptr == -1){ //ContactList is empty
            System.out.println("Error: Can't Get Contact, No contacts found in Contact List.");
            return null;
        }
        else if(index < 0 || index > contacts.length){ //if index is outside the bounds of the array
            return null;
        }
        else{
            ptr = index; //sets ptr to current contact
            return contacts[index]; //returns contact at that index
        }
    }


    //moves ptr ahead to the next contact and returns the contact
    public Contact next(){

        if(ptr == -1){ //ContactList is empty
            System.out.println("Error: Can't Get Next Contact, No contacts found in Contact List.");
            return null;
        }
        else if(ptr == (contacts.length - 1)){ //ptr is at end of list
            ptr = 0; //set ptr to first contact
            return contacts[ptr]; //return first contact
        }
        else{
            ptr++; //move ptr ahead to next contact
            return contacts[ptr]; //return next contact
        }
    }


    //moves ptr back to the previous contact and returns the contact
    public Contact previous(){

        if(ptr == -1){ //ContactList is empty
            System.out.println("Error: Can't Get Previous Contact, No contacts found in Contact List.");
            return null;
        }
        else if(ptr == 0){ //if ptr is at the start of the list
            ptr = (contacts.length - 1); //set ptr to the last contact
            return contacts[ptr]; //return last contact
        }
        else{
            ptr--; //move ptr back to previous contact
            return contacts[ptr]; //return previous contact
        }
    }


    //sorts the ContactList according to the name attribute (bubble sort)
    public void sort(){

        //local variables
        boolean swapped = true; //determines when the sort is finished
        Contact temp;

        while(swapped){
            swapped = false;
            for(int i = 0; i < contacts.length - 1; i++){ //iterates over array (minus the last element)
                if(contacts[i].getName().compareToIgnoreCase(contacts[i + 1].getName()) > 0){ //ascending sort
                    temp = contacts[i]; //places contact in temp variable
                    contacts[i] = contacts[i + 1]; //swapping
                    contacts[i + 1] = temp; //swapping
                    swapped = true;
                }
            }
        }
    }



    //tests ContactList class
    public static void main(String[] args) {

        //create ContactList objects
        ContactList list = new ContactList(3); //list has a length of 3
        ContactList list2 = new ContactList(1);

        list.scan = new Scanner(System.in); //create scanner

        //create Contact objects
        Contact mike = new Contact("Mike", 8309991234L, "1234 Pleasent Street", "Cool dude");
        Contact greg = new Contact("Greg", 9521231234L, "1324 Main Street"); //no comments
        Contact al = new Contact("Al", 6123258594L); //no address or comments
        Contact bill = new Contact("Bill", 7639874343L, "14 Central Way", "Classmate"); //won't get added initially, because list will be full


        System.out.println("The length of the Contact List is: " + list.contacts.length + "\n");

        list.remove(); //tests remove method when there are no contacts (error message)


        System.out.println("\nNow adding Contacts.\n");

        //add contacts to Contact List
        list.add(mike);
        list.add(greg);
        list.add(al);
        list.add(bill); //doesn't add because list is full (prints error message)


        System.out.println("\nContacts in Contact List: ");
        list.printToConsole();  //print out all contacts in list


        list.sort(); //sort contact list alphabetically
        System.out.println("\nContacts in List are now sorted alphabetically!");
        list.printToConsole(); //print out all contacts in list


        System.out.println(); //spacing


        //check whether Mike is found in the contact list or not (works!)
        String name1 = "Mike";
        Contact check1 = list.find(name1);

        if(check1 != null){
            System.out.println("Search Found: " + check1);
        }
        else{
            System.out.println(name1 + " is not found in the Contact List.");
        }


        System.out.println(); //spacing


        //check whether Susan is found in the contact list or not (doesn't work!)
        String name2 = "Susan";
        Contact check2 = list.findFast(name2);

        if(check2 != null){
            System.out.println("Search Found: " + check2);
        }
        else{
            System.out.println(name2 + " is not found in the Contact List.");
        }


        System.out.println(); //spacing


        //check whether Al is found in the contact list or not (works!)
        String name3 = "Al";

        Contact check3 = list.findFast(name3); //test findFast() method with contains()

        if(check3 != null){
            System.out.println("Search Found: " + check3);
        }
        else{
            System.out.println(name3 + " is not found in the Contact List.");
        }


        System.out.println(); //spacing


        //check remove method
        Contact removeContact = list.remove(); //removes last contact (Mike) from ContactList
        System.out.println("Removed Contact \n " + removeContact);


        System.out.println(); //spacing


        //Contact removeContact2 = list.remove(); //removes Greg from ContactList
        //System.out.println("Removed Contact \n " + removeContact2);


        //re-add a contact
        list.add(bill); //now Bill is 3rd contact instead of Mike


        //test writing to a file (writes Al, Greg and Bill)
        String fileName;
        System.out.print("What file do you want to write to? ");
        fileName = list.scan.next();
        list.write(fileName);


        //test reading from a file (reads Al, Greg and Bill)
        System.out.print("What file do you want to read from? ");
        fileName = list.scan.next();
        list.read(fileName);


        list.sort(); //sort the contact list alphabetically
        System.out.println("\nContacts in List are now sorted alphabetically!");
        list.printToConsole(); //print out all contacts in list


		/*//write sorted contacts to file
		String newFileName;
		System.out.print("\nWhat file do you want to write to? ");
		newFileName = list.scan.next();
		list.write(newFileName);*/


        //test getCurrent() method
        System.out.println("\nThe Current contact is: " + list.getCurrent()); //Greg

        //test get() method
        System.out.println("\nThe First Contact is: " + list.get(1)); //Al

        //test next() method
        System.out.println("\nThe next Contact is: " + list.next()); //Bill
        System.out.println("\nThe next Contact is: " + list.next()); //Greg
        System.out.println("\nThe next Contact is: " + list.next()); //Al

        //test previous() method
        System.out.println("\nThe previous Contact is: " + list.previous()); //Greg
        System.out.println("\nThe previous Contact is: " + list.previous()); //Bill
        System.out.println("\nThe previous Contact is: " + list.previous()); //Al


        //create new ContactList
        System.out.println("\n\nNew Contact List!");

        //test previous() method with only one contact in ContactList
        list2.add(mike); //add contact to list
        list2.printToConsole(); //print out contacts in list
        System.out.println("\nThe previous Contact is: " + list2.previous()); //Mike
    }
}
