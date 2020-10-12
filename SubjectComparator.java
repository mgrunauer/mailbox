/*
 * A class used to compare two Email objects by comparing their subject lines
 * 
 * @author Matthew Grunauer
 */
import java.util.*;
public class SubjectComparator implements Comparator{
    /*
     * This method compares the subjects of two provided Email objects
     * 
     * @return
     * An integer representing whether the first subject comes lexigraphically before or after the second subject
     */
    public int compare(Object o1, Object o2){
        Email e1 = (Email) o1;
        Email e2 = (Email) o2;
        return(e1.getSubject().compareTo(e2.getSubject()));
    }
}
