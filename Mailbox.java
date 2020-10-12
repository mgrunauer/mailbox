/*
 * A class representing a mailbox containing folders that hold emails
 * 
 * @author Matthew Grunauer
 */
import java.util.*;
import java.io.*;
public class Mailbox implements Serializable{
    private Folder inbox;
    private Folder trash;
    private ArrayList<Folder> folders;
    public static Mailbox mailbox;

    /*
     * This method is a no-arg constructor for an object of type Mailbox
     */
    public Mailbox(){
    }

    /*
     * This method is an alternate constructor for an object of type Mailbox
     * 
     * @param inbox
     * An object of type Folder representing the inbox were emails are initally stored
     * 
     * @param trash
     * An object of type Folder representing the location where deleted emails go
     */
    public Mailbox(Folder inbox, Folder trash){
        this.inbox = inbox;
        this.trash = trash;
        this.folders = new ArrayList<Folder>();
    }

    /*
     * This method adds an object of type Folder to an ArrayList
     * 
     * @param folder
     * An object of type Folder representing a new folder to be added to the ArrayList
     */
    public void addFolder(Folder folder){
        boolean flag = true;
        for (int i = 0; i < folders.size(); i++){
            if (folder.getName().equals(folders.get(i).getName())){
                flag = false;
            }
        }
        if (flag){
            folders.add(folder);
        }
        else{
            System.out.println("A folder named " + folder.getName() + " already exists.");
        }
    }

    /*
     * This method removes an object of type Folder from an ArrayList
     * 
     * @param name
     * A String object representing the name of the folder to be removed
     */
    public void deleteFolder(String name){
        for (int i = 0; i < folders.size(); i++){
            if (folders.get(i).getName().equals(name)){
                System.out.println(folders.get(i).getName() + " has been removed.");
                folders.remove(i);
            }
        }
    }

    /*
     * This method displays all of the Folder objects in the ArrayList
     */
    public void displayFolders(){
        System.out.println("\nMailbox:");
        System.out.println("---------");
        for (int i = 0; i < folders.size(); i++){
            System.out.println(folders.get(i).getName());
        }
        System.out.println();
    }

    /*
     * This method creates a new Email object based off of user input and adds it to the inbox folder
     */
    public void composeEmail(){
        Scanner input = new Scanner(System.in);
        String to = "";
        String cc = "";
        String bcc = "";
        String subject = "";
        String body = "";
        System.out.print("\nEnter recipient (To): ");
        to = input.nextLine();
        System.out.print("\nEnter carbon copy recipients (CC): ");
        cc = input.nextLine();
        System.out.print("\nEnter blind carbon copy recipients (BCC): ");
        bcc = input.nextLine();
        System.out.print("\nEnter subject line: ");
        subject = input.nextLine();
        System.out.print("\nEnter body: ");
        body = input.nextLine();
        Email email = new Email(to, cc, bcc, subject, body);
        inbox.addEmail(email);
        System.out.println("\nEmail successfully added to Inbox.");
    }

    /*
     * Adds the provided Email object to the trash folder
     * 
     * @param email
     * An object of type Email representing the email to be added to the trash folder
     */
    public void deleteEmail(Email email){
        trash.addEmail(email);
    }

    /*
     * This method removes all of the Email objects from the trash Folder
     */
    public void clearTrash(){
        for (int i = 0; i < trash.getEmails().size(); i++){
            trash.getEmails().remove(0);
        }
    }

    /*
     * This method moves an Email object to a specified Folder
     * 
     * @param email
     * An object of type Email representing the email to be moved
     * 
     * @param target
     * An object of type Folder representing the folder where the email will be moved to
     */
    public void moveEmail(Email email, Folder target){
        target.addEmail(email);
    }

    /*
     * This method finds a Folder object based on its name
     * 
     * @param name
     * A String object representing the name of the desired Folder object
     * 
     * @return
     * An object of type Folder representing the desired Folder
     */
    public Folder getFolder(String name){
        for (int i = 0; i < folders.size(); i++){
            if (folders.get(i).getName().equals(name)){
                return folders.get(i);
            }
        }
        return null;
    }

    /*
     * This method is a getter for the Inbox attribute
     * 
     * @return
     * An object of type Folder representing the Inbox folder of the Mailbox
     */
    public Folder getInbox(){
        return this.inbox;
    }

    /*
     * This method is a getter for the Trash attribute
     * 
     * @return
     * An object of type Folder representing the Trash folder of the Mailbox
     */
    public Folder getTrash(){
        return this.trash;
    }

    /*
     * This method is a getter for the list of folders of the Mailbox
     * 
     * @return
     * An ArrayList containing all of the Folder objects associated with the Mailbox
     */
    public ArrayList<Folder> getFolders(){
        return this.folders;
    }

    /*
     * This method handles the loading of the mailbox file
     * 
     * @throws IOException
     * When there is an error loading the mailbox file
     * 
     * @throws ClassNotFoundException
     * When the specified Class is not found
     * 
     * @return
     * An object of type Mailbox representing the mailbox of this simulation
     */
    public static Mailbox loadMailbox(){
        Mailbox fileMailbox = null;
        try {
            FileInputStream   file = new FileInputStream("mailbox.obj");
            ObjectInputStream fin  = new ObjectInputStream(file);
            fileMailbox = (Mailbox) fin.readObject();
            file.close();
        } 
        catch(IOException a){}
        catch(ClassNotFoundException c){}
        if (fileMailbox != null){
            System.out.println("Previous save found. Loading mailbox.");
            return fileMailbox;
        }
        else{
            System.out.println("Previous save not found, starting with an empty mailbox.");
            return new Mailbox(new Folder("Inbox"), new Folder("Trash"));
        }
    }

    /*
     * This method handles the saving of the mailbox file
     * 
     * @throws IOException
     * When there is an error saving the mailbox file
     */
    public void saveMailbox(){
        try {
            FileOutputStream   file = new FileOutputStream("mailbox.obj");
            ObjectOutputStream fout = new ObjectOutputStream(file);
            fout.writeObject(mailbox);
            fout.close();
        } catch(IOException a) {
            System.out.println(a.getMessage());
        }
    }

    /*
     * This method displays the main menu to the user
     */
    public void userMenu(){
        System.out.println("A - Add folder");
        System.out.println("R - Remove folder");
        System.out.println("C - Compose email");
        System.out.println("F - Open folder");
        System.out.println("I - Open Inbox");
        System.out.println("T - Open Trash");
        System.out.println("E - Empty Trash");
        System.out.println("Q - Quit");
    }

    /*
     * This method handles the logic of the folder submenu
     * 
     * @param folder
     * An object of type Folder representing the folder that is being manipulated by the submenu
     * 
     * @throws IndexOutOfBoundsException
     * When a user attempts to access an email from an invalid index
     */
    public void folderSubmenu(Folder folder){
        Scanner input = new Scanner(System.in);
        String userInput = "";
        Email email = null;
        Folder moveFolder = null;
        while(true){
            email = null;
            moveFolder = null;
            userInput = "";
            System.out.println("\n" + folder.getName());
            folder.displayEmails();
            System.out.println("\nM - Move email");
            System.out.println("D - Delete email");
            System.out.println("V - View email contents");
            System.out.println("SA - Sort by subject line in ascending order");
            System.out.println("SD - Sort by subject line in descending order");
            System.out.println("DA - Sort by date in ascending order");
            System.out.println("DD - Sort by date in descending order");
            System.out.println("R - Return to mailbox");
            System.out.print("\nEnter a user option: ");
            userInput = input.nextLine();
            if (userInput.equalsIgnoreCase("M")){
                System.out.print("\nEnter the index of the email to move: ");
                userInput = input.nextLine();
                try{
                    email = folder.getEmails().get(Integer.parseInt(userInput) - 1);
                }
                catch (IndexOutOfBoundsException err){
                    System.out.println("There is no email at this index.");
                }
                if (email != null){
                    System.out.println("\nFolders:");
                    for (int i = 0; i < folders.size(); i++){
                        System.out.println(folders.get(i).getName());
                    }
                    System.out.print("\nSelect a folder to move \"" + email.getSubject() + "\" to: ");
                    moveFolder = getFolder(input.nextLine());
                    if (moveFolder != null){
                        moveEmail(folder.removeEmail(Integer.parseInt(userInput) - 1), moveFolder);
                        System.out.println(email.getSubject() + " moved to " + moveFolder.getName());
                    }
                    else{
                        System.out.println("Folder does not exist.");
                    }
                }
            }
            else if (userInput.equalsIgnoreCase("D")){
                System.out.println("\nEnter email index: ");
                userInput = input.nextLine();
                try{
                    email = folder.getEmails().get(Integer.parseInt(userInput) - 1);
                }
                catch (IndexOutOfBoundsException err){
                    System.out.println("There is no email at this index.");
                }
                if (email != null){
                    moveFolder = getTrash();
                    moveEmail(folder.removeEmail(Integer.parseInt(userInput) - 1), moveFolder);
                    System.out.println("\n\"" + email.getSubject() + "\" moved to " + moveFolder.getName());
                }
            }
            else if (userInput.equalsIgnoreCase("V")){
                System.out.print("\nEnter email index: ");
                userInput = input.nextLine();
                try{
                    email = folder.getEmails().get(Integer.parseInt(userInput) - 1);
                }
                catch (IndexOutOfBoundsException err){
                    System.out.println("There is no email at this index.");
                }
                if (email != null){
                    System.out.println("To: " + email.getTo());
                    System.out.println("CC: " + email.getCC());
                    System.out.println("BCC: " + email.getBCC());
                    System.out.println("Subject: " + email.getSubject());
                    System.out.println(email.getBody());
                }
            }
            else if (userInput.equalsIgnoreCase("SA")){
                folder.sortBySubjectAscending();
            }
            else if (userInput.equalsIgnoreCase("SD")){
                folder.sortBySubjectDescending();
            }
            else if (userInput.equalsIgnoreCase("DA")){
                folder.sortByDateAscending();
            }
            else if (userInput.equalsIgnoreCase("DD")){
                folder.sortByDateDescending();
            }
            else if (userInput.equalsIgnoreCase("R")){
                break;
            }
        }
    }

    public static void main(String[] args){
        mailbox = loadMailbox();
        Scanner input = new Scanner(System.in);
        String folder = "";
        String userInput = "";
        if (mailbox.getFolders().size() == 0){
            mailbox.addFolder(mailbox.getInbox());
            mailbox.addFolder(mailbox.getTrash());
        }
        while (true){
            mailbox.displayFolders();
            mailbox.userMenu();
            System.out.print("\nEnter a user option: ");
            userInput = input.nextLine();
            if (userInput.equalsIgnoreCase("A")){
                System.out.print("\nEnter folder name: ");
                userInput = input.nextLine();
                mailbox.addFolder(new Folder(userInput));
            }
            else if (userInput.equalsIgnoreCase("R")){
                System.out.print("\nEnter folder name: ");
                userInput = input.nextLine();
                Folder temp = mailbox.getFolder(userInput);
                if (temp != null){
                    mailbox.deleteFolder(userInput);
                }
                else{
                    System.out.println("\nFolder not found.");
                }
            }
            else if (userInput.equalsIgnoreCase("C")){
                mailbox.composeEmail();
            }
            else if (userInput.equalsIgnoreCase("F")){
                System.out.print("\nEnter folder name: ");
                userInput = input.nextLine();
                Folder temp = mailbox.getFolder(userInput);
                if (temp != null){
                    mailbox.folderSubmenu(temp);
                }
                else{
                    System.out.println("\nFolder not found.");
                }
            }
            else if (userInput.equalsIgnoreCase("I")){
                mailbox.folderSubmenu(mailbox.getInbox());
            }
            else if (userInput.equalsIgnoreCase("T")){
                mailbox.folderSubmenu(mailbox.getTrash());
            }
            else if (userInput.equalsIgnoreCase("E")){
                mailbox.clearTrash();
                System.out.println("\nTrash has been cleared.");
            }
            else if (userInput.equalsIgnoreCase("Q")){
                mailbox.saveMailbox();
                System.out.println("\nProgram successfully exited and mailbox saved.");
                break;
            }
        }
    }
}
