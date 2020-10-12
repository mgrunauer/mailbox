/*
 * A class representing a email folder containing user generated emails
 * 
 * @author Matthew Grunauer
 */
import java.io.*;
import java.util.*;
import java.text.*;
public class Folder implements Serializable{
    private ArrayList<Email> emails;
    private String name;
    private String currentSortingMethod;

    /*
     * This method is a constructor for an object of type Folder
     * 
     * @param name
     * A String object representing the name of a folder
     */
    public Folder(String name){
        this.name = name;
        this.currentSortingMethod = "DD";
        this.emails = new ArrayList<Email>();
    }

    /*
     * This method is a getter for the name attribute
     * 
     * @return
     * A String object representing the name of a folder
     */
    public String getName(){
        return this.name;
    }

    /*
     * This method is a getter for the currentSortingMethod attribute
     * 
     * @return
     * A String object representing the current sorting method being used to insert Email objects
     */
    public String getCurrentSortingMethod(){
        return this.currentSortingMethod;
    }

    /*
     * This method is a getter for the emails attribute
     * 
     * @return
     * An ArrayList containing all of the Email objects contained within the current Folder
     */
    public ArrayList<Email> getEmails(){
        return this.emails;
    }

    /*
     * This method is a setter for the name attribute
     * 
     * @param newName
     * A String object representing the new name of the current folder
     */
    public void setName(String newName){
        this.name = newName;
    }

    /*
     * This method is a setter for the currentSortingMethod attribute
     * 
     * @param newSort
     * A String object representing the new sorting method being used to insert Email object to the current folder
     */
    public void setCurrentSortingMethod(String newSort){
        this.currentSortingMethod = newSort;
    }

    /*
     * This method is a setter for the emails attribute
     * 
     * @param newEmails
     * An ArrayList representing the new list of emails contained within the current folder
     */
    public void setEmails(ArrayList<Email> newEmails){
        this.emails = newEmails;
    }

    /*
     * This method adds an object of type Email to the emails ArrayList
     * 
     * @param email
     * An object of type Email representing the email to be added to the ArrayList
     */
    public void addEmail(Email email){
        String sort = this.currentSortingMethod;
        if (sort.equals("SA")){
            emails.add(email);
            sortBySubjectAscending();
        }
        else if (sort.equals("SD")){
            emails.add(email);
            sortBySubjectDescending();
        }
        else if (sort.equals("DA")){
            emails.add(email);
            sortByDateAscending();
        }
        else if (sort.equals("DD")){
            emails.add(email);
            sortByDateDescending();
        }
    }

    /*
     * This method displays all of the emails contained within the current folder
     */
    public void displayEmails(){
        System.out.println();
        System.out.printf("%-30s %-30s %-30s", "Index", "Time", "Subject");
        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        String date = "";
        DateFormat estFormat = new SimpleDateFormat("HH:mm yyyy/MM/dd");
        estFormat.setTimeZone(TimeZone.getTimeZone("EST"));
        int index = 1;
        if (emails.isEmpty()){
            System.out.println(getName() + " is empty.");
        }
        else{
            for (Email email : emails){
                date = estFormat.format(email.getTimeStamp().getTime().getTime());
                System.out.printf("%-30d %-30s %-30s%n", index, date, email.getSubject());
                index++;
            }
        }
    }

    /*
     * This method removes an email at a specified index from the emails ArrayList
     * 
     * @return
     * An object of type Email representing the email being removed from the emails ArrayList
     */
    public Email removeEmail(int index){
        return(emails.remove(index));
    }

    /*
     * This method sorts the emails ArrayList by subject in ascending order
     */
    public void sortBySubjectAscending(){
        Collections.sort(emails, new SubjectComparator());
    }

    /*
     * This method sorts the emails ArrayList by subject in descending order
     */
    public void sortBySubjectDescending(){
        sortBySubjectAscending();
        Collections.reverse(emails);
    }

    /*
     * This method sorts the emails ArrayList by date in ascending order
     */
    public void sortByDateAscending(){
        Collections.sort(emails, new DateComparator());
    }

    /*
     * This method sorts the emails ArrayList by date in descending order
     */
    public void sortByDateDescending(){
        sortByDateAscending();
        Collections.reverse(emails);
    }
}
