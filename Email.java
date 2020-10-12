/*
 * A class representing a user generated email
 * 
 * @author Matthew Grunauer
 */
import java.util.GregorianCalendar;
import java.io.Serializable;
public class Email implements Serializable{
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timestamp;

    /*
     * This method is a constructor for an object of type Email
     * 
     * @param to
     * A String object representing the recipient of the email
     * 
     * @param cc
     * A String object representing the carbon copy recipient of the email
     * 
     * @param bcc
     * A String object representing the blind carbon copy recipient of the email
     * 
     * @param subject 
     * A String object representing the subject line of the email
     * 
     * @param body
     * A String object representing the body of the email
     */
    public Email(String to, String cc, String bcc, String subject, String body){
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.timestamp = new GregorianCalendar();
    }

    /*
     * This method is a getter for the to attribute
     * 
     * @return
     * A String object representing the recipient of the email
     */
    public String getTo(){
        return this.to;
    }
    
    /*
     * This method is a getter for the cc attribute
     * 
     * @return
     * A String object representing the carbon copy recipient of the email
     */
    public String getCC(){
        return this.cc;
    }
    
    /*
     * This method is a getter for the bcc attribute
     * 
     * @return
     * A String object representing the blind carbon copy recipient of the email
     */
    public String getBCC(){
        return this.bcc;
    }
    
    /*
     * This method is a getter for the subject attribute
     * 
     * @return
     * A String object representing the subject line of the email
     */
    public String getSubject(){
        return this.subject;
    }
    
    /*
     * This method is a getter for the body of the attribute
     * 
     * @return
     * A String object representing the body of the email
     */
    public String getBody(){
        return this.body;
    }
    
    /*
     * This method is a getter for the timestamp attribute
     * 
     * @return
     * An object of type GregorianCalendar representing the time the email was created
     */
    public GregorianCalendar getTimeStamp(){
        return this.timestamp;
    }
    
    /*
     * This method is a setter for the to attribute
     * 
     * @param newTo
     * A String object representing the new recipient of the email
     */
    public void setTo(String newTo){
        this.to = newTo;
    }
    
    /*
     * This method is a setter for the cc attribute
     * 
     * @param newCC
     * A String object representing the new carbon copy recipient of the email
     */
    public void setCC(String newCC){
        this.cc = newCC;
    }
    
    /*
     * This method is a setter for the bcc attribute
     * 
     * @param newBCC
     * A String object representing the new blind carbon copy recipient of the email
     */
    public void setBCC(String newBCC){
        this.bcc = newBCC;
    }
    
    /*
     * This method is a setter for the subject attribute
     * 
     * @param newSubject
     * A String object representing the new subject line of the email
     */
    public void setSubject(String newSubject){
        this.subject = newSubject;
    }
    
    /*
     * This method is a setter for the body attribute
     * 
     * @param newBody
     * A String object representing the new body of the email
     */
    public void setBody(String newBody){
        this.body = newBody;
    }
    
    /*
     * This method is a setter for the timestamp attribute of the email
     * 
     * @param newTime
     * An object of type GregorianCalendar representing the new information about the time the email was created
     */
    public void setTimeStamp(GregorianCalendar newTime){
        this.timestamp = newTime;
    }
}
