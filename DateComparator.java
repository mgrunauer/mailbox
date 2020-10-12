/*
 * A class used to compare two Email objects by comparing their dates
 * 
 * @author Matthew Grunauer
 */
import java.util.*;
public class DateComparator implements Comparator{
    /*
     * This method compares the dates of two provided Email objects
     * 
     * @return
     * An integer representing whether the first date came before or after the second date
     */
    public int compare(Object o1, Object o2){
        Email e1 = (Email) o1;
        Email e2 = (Email) o2;
        return(e1.getTimeStamp().compareTo(e2.getTimeStamp()));
    }
}
