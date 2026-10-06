package contacts;
import java.util.*;
class Contact {
    private String name;
    private String surname ;
    private String number = "";
    public Contact(String name , String surname , String number){
        this.name = name ;
        this.surname = surname ;
        this.number = number;
    }
    public void setName(String name ){this.name= name;}
    public void setSurname(String surname ){this.surname = surname;}
    public void setPhoneNumber(String phoneNumber) {
        String regex =
                "^\\+?(" +
                        "(\\([A-Za-z0-9]+\\)|[A-Za-z0-9]+)([ -][A-Za-z0-9]{2,})*" +
                        "|" +
                        "[A-Za-z0-9]+[ -]\\([A-Za-z0-9]{2,}\\)([ -][A-Za-z0-9]{2,})*" +
                        ")$";

        if (phoneNumber.matches(regex)) {
            this.number = phoneNumber;
        }
        else{
            this.number = "[no number]";
        }
    }
    protected static boolean hasNumber(Contact ct ){
        if(ct.getNumber().equals("[no number]")|| ct.getNumber().equals("")){return false;}
        return true;
    }


    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getSurname() {
        return surname;
    }
}

public class Main {
   static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        List<Contact> Contacts =new ArrayList<>();
        System.out.print("Enter action (add, remove, edit, count, list, exit):");

        while(scanner.hasNextLine()){
            String option = scanner.nextLine();
            if(option.equals("exit")){break;}
            else if(option.equals("count")){
                count(Contacts);
            }else if(option.equals("edit")){
               edit(Contacts);
               // System.out.println("The result from the edit - "+res);
            }
            else if(option.equals("add")){
                add(Contacts);
            } else if(option.equals("remove")){
                remove(Contacts);
            }
            else if(option.equals("list")){
                list(Contacts);
            }
            System.out.print("Enter action (add, remove, edit, count, list, exit):");

        }
    }
    public synchronized static void count(List<Contact> Contacts){
        int res =  Contacts.size();
        System.out.printf("The Phone Book has %d records.",res);
    }
    private static boolean isValidPhoneNumber(String num){
        String regex =
                "^\\+?(" +
                        "(\\([A-Za-z0-9]+\\)|[A-Za-z0-9]+)([ -][A-Za-z0-9]{2,})*" +
                        "|" +
                        "[A-Za-z0-9]+[ -]\\([A-Za-z0-9]{2,}\\)([ -][A-Za-z0-9]{2,})*" +
                        ")$";

        if (num.matches(regex)) {
            return true;
        }
        return false;
    }

    public synchronized static void list(List<Contact> contacts){
        for(int i = 0 ; i <contacts.size(); i ++){
            Contact contact = contacts.get(i);
            String number = contact.getNumber();

            if (number == null || number.isEmpty()) {
                number = "[no number]";
            }
            System.out.println(
                    (i + 1) + ". "
                            + contact.getName() + " "
                            + contact.getSurname() + ", "
                            + number
            );
        }
    }


    public synchronized static boolean edit(List<Contact> Contacts){
        if(Contacts.size() ==0 ){System.out.print(
                "No records to edit!");
        }
        else{

            list(Contacts);
            System.out.print("Select a record:");
            int record = scanner.nextInt();
            String line = scanner.nextLine();
            int index = record -1;
            System.out.print("Select a field (name, surname, number):");
            String opt = scanner.nextLine();
            if(opt.equals("name")){System.out.print("Enter name:");
                String name = scanner.nextLine();
                Contacts.get(index).setName(name);
                System.out.print("The record updated!");
            }else if(opt.equals("surname")){System.out.print("Enter surname:");
                String surname = scanner.nextLine();
                Contacts.get(index).setSurname(surname);
                System.out.print("The record updated!");
            }else if(opt.equals("number")){System.out.print("Enter number:");
                String number = scanner.nextLine();
                if(isValidPhoneNumber(number)) {
                    Contacts.get(index).setPhoneNumber(number);
                }
                else{
                    System.out.println("Wrong number format!");
                    Contacts.get(index).setPhoneNumber("[no number]");

                }
                System.out.println("The record updated!");
            }
            return true;
        }
        return false;
    }
    public synchronized static void add(List<Contact> Contacts){
        System.out.print("Enter the name:");
        String name = scanner.nextLine();
        System.out.print("Enter the surname:");
        String surname = scanner.nextLine();
        System.out.print("Enter the number:");
        String number = scanner.nextLine();
        if(!isValidPhoneNumber(number)){
            number ="[no number]";
            System.out.println("Wrong number format!");

        }
        Contact contact = new Contact(name,surname,number);

        Contacts.add(contact);
        if(Contacts.contains(contact)) {
            System.out.println("The record added." );
        }

    }
    public synchronized static  void remove(List<Contact> Contacts){
        if(Contacts.size() ==0 ){System.out.print("No records to remove!");}
        else{
            list(Contacts);
            System.out.print("Select a record:");
            int res = scanner.nextInt();
            String line = scanner.nextLine();
            int index = res -1;
            Contacts.remove(index);
                System.out.println("The record removed!");

        }
    }

}
