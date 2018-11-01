import java.io.* ;
import java.util.* ;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * çalışan için olan subclass 
 * @author furkan
 */
public class Staff implements Human{

    private final String staffName ;
    private final String staffLastName ;
    private final String staffID ;
    /**
     *
     * @param name
     * @param lastname
     * @param ID
     */
    public Staff(String name,String lastname,String ID){

        staffName=name ;
        staffLastName=lastname ;
        staffID=ID ;
    }
    /**
     *
     * @return çalışanın ismi
     */
    @Override
    public String getName(){

        return staffName ;
    }
    /**
     *
     * @return çalışanın soyadı
     */
    @Override
    public String getLastName() {

        return staffLastName ;
    }
    /**
     *
     * @return çalışanın ID
     */
    @Override
    public String getID(){

        return staffID ;
    }
    /**
     *
     * @return kullanıcının yetkisini belirlemek için
     */
    @Override
    public char authority(){

        return 'S' ;
    }
    /**
     * çalışan için arayüz
     */
    @Override
    public void getInterface(){

        int choose ;

        Scanner input = new Scanner(System.in);

        System.out.printf("Choose 1 for Add Book or Choose 2 for Remove Book\n") ;
        System.out.printf("Choose 3 for Add User or Choose 4 for Remove User\n") ;
        System.out.printf("Choose 5 for Excist \n");

        choose=input.nextInt() ;

        switch (choose) {
            case 1:
            {
                try {
                    addBook();
                }
                catch (IOException ex) {
                    System.out.printf("Dosya Hatası");

                }
            }
            break;
            case 2:
            {
                try {
                    removeBook() ;
                } catch (IOException ex) {
                    System.out.printf("Dosya Hatası");

                }

            }
            break;
            case 3:
            {
                try {
                    addUser() ;
                } catch (IOException ex) {
                    System.out.printf("Dosya Hatası");

                }
            }
            break;
            case 4:
            {
                try {
                    removeUser() ;
                } catch (IOException ex) {
                    System.out.printf("Dosya Hatası");
                }
            }
            break;
            case 5:
                break ;
            default :
                System.out.printf("Please press the correct button\n") ;
                getInterface() ;
        }

    }
    /**
     * küyüphaneye kitap eklenir
     * @throws IOException
     */
    public void addBook() throws IOException{

        String bookName;
        String bookID ;

        Scanner input = new Scanner(System.in);
        System.out.printf("Name of Book =") ;
        bookName = input.nextLine();
        System.out.printf("ID of Book =") ;
        bookID = input.nextLine();

        File fileBooks,fileBarrowReturn;
        fileBooks = new File("Books in Library.csv");
        fileBarrowReturn= new File("Barrow And Return.csv") ;

        if (!fileBooks.exists()) {
            fileBooks.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(fileBooks, true);
        fileWriter.write(bookID);
        fileWriter.write(",");
        fileWriter.write(bookName);
        fileWriter.write("\n");

        FileWriter fileWriter2 = new FileWriter(fileBarrowReturn, true);
        fileWriter2.write(bookName);
        fileWriter2.write("\n");

        fileWriter.close();
        fileWriter2.close();

        getInterface() ;
    }

    /**
     * kütüphaneden kitap çıkarılır.
     * @throws FileNotFoundException
     * @throws IOException
     **/
    public void removeBook() throws FileNotFoundException, IOException{

        String bookID ;/*kulanıcının ID*/
        String[] temp ;/*arraylist elamanları parçalanır string array e atılır*/
        int i;

        Scanner filescan = null;
        ArrayList<String> mylist=new ArrayList() ;

        Scanner input = new Scanner(System.in);
        System.out.printf("ID of Book =") ;
        bookID = input.nextLine();

        File fileBooks;
        File fileBarrowReturn;

        fileBooks = new File("Books in Library.csv");
        fileBarrowReturn= new File("Barrow And Return.csv") ;

        if (!fileBooks.exists()) {
            System.out.printf("İlk önce Kitap Eklemelisin") ;
        }
        if (!fileBarrowReturn.exists()) {
            System.out.printf("İlk önce Kitap Eklemelisin") ;
        }

        filescan = new Scanner(new FileReader(fileBooks));
        /*Books in Library.csv doysasından okunup arrayliste atılır*/
        while(filescan.hasNext()){
            mylist.add(filescan.nextLine()) ;
        }

        for(i=0 ; i<mylist.size() ; i++){
            temp=mylist.get(i).split(",") ;
            if(bookID.equals(temp[0]))
                mylist.remove(i) ;
        }

        FileWriter fileWriter = new FileWriter(fileBooks, false);
        for(i=0 ; i<mylist.size() ; i++){
            fileWriter.write(mylist.get(i));
            fileWriter.write("\n");
        }

        FileWriter fileWriter2 = new FileWriter(fileBarrowReturn, false);
        for(i=0 ; i<mylist.size() ; i++){
            temp=mylist.get(i).split(",");
            fileWriter2.write(temp[1]);
            fileWriter2.write("\n");
        }

        fileWriter.close();
        fileWriter2.close();

        getInterface() ;
    }

    /**
     * Sistemden kullanıcı eklemek içindir.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void addUser() throws FileNotFoundException, IOException{

        String name;/*kulanıcının adı*/
        String lastname;/*kulanıcının soyadı*/
        String ID;/*kulanıcının ID*/
        String[] temp ;/*arraylist elamanları parçalanır string array e atılır*/
        int i;
        int flag=0 ;/*Kullanıcının bulunup bulmadığını belirlemek içindir.*/

        Scanner input = new Scanner(System.in);
        Scanner filescan = null;
        ArrayList<String> mylist=new ArrayList() ;

        System.out.printf("Name ") ;
        System.out.printf("Lastname = ") ;

        name = input.next();
        lastname = input.next();

        System.out.printf("ID = ") ;
        ID= input.next() ;

        File fileUsersBooks;
        fileUsersBooks=new File("Users And Books.csv") ;

        if (!fileUsersBooks.exists()) {
            fileUsersBooks.createNewFile();
        }

        filescan = new Scanner(new FileReader(fileUsersBooks));
        FileWriter fileWriter = new FileWriter(fileUsersBooks, true);        
        /*Users And Books.csv doysasından okunup arrayliste atılır*/
        while(filescan.hasNext()){
            mylist.add(filescan.nextLine()) ;
        }
        
        /*arraylist parçalanılarak temp e atılır.*/
        for(i=0 ; i<mylist.size() ; i++){
            temp=mylist.get(i).split(",") ;
            /*kullanıcının sistemde bulunup bulunmaması kontrol edilir*/
            if(ID.equals(temp[0]))
                flag=1 ;
        }

        if(flag==1)
            System.out.printf("Böyle bir kullanıcı zaten bulunmaktadır") ;

        else{

            fileWriter.write(ID);
            fileWriter.write(",");
            fileWriter.write(name);
            fileWriter.write(",");
            fileWriter.write(lastname);
            fileWriter.write("\n");
        }

        fileWriter.close();

        getInterface() ;
    }
    /**
     * Sistemden kullanıcı çıkarmak içindir.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void removeUser() throws FileNotFoundException, IOException{

        String name,lastname,ID;
        String[] temp ;
        int i,j,flag=0 ;

        Scanner input = new Scanner(System.in);
        Scanner filescan = null;
        ArrayList<String> mylist=new ArrayList() ;

        System.out.printf("Name ") ;
        System.out.printf("Lastname = ") ;

        name = input.next();
        lastname = input.next();

        System.out.printf("ID = ") ;
        ID= input.next() ;

        File fileUsersBooks,fileBarrowReturn;
        fileUsersBooks=new File("Users And Books.csv") ;
        fileBarrowReturn= new File("Barrow And Return.csv") ;

        filescan = new Scanner(new FileReader(fileUsersBooks));
        FileWriter fileWriter = new FileWriter(fileBarrowReturn, true);
        /*Users And Books.csv dosyasından okuyarak arrayliste atılır*/
        while(filescan.hasNext()){
            mylist.add(filescan.nextLine()) ;
        }
        /*kullanıcının yeri tespit edilmeye çalışılır*/
        for(i=0 ; i<mylist.size() ; i++){

            temp=mylist.get(i).split(",") ;

            if(ID.equals(temp[0])){

                if(temp.length>3){
                    System.out.printf("Kullanıcı ilk önce kitapları getirmelidir\n");
                    flag=1 ;
                }
                else{

                    FileWriter fileWriter2 = new FileWriter(fileUsersBooks, false);
                    for(j=3 ; j<temp.length ; j++){
                        fileWriter.write(temp[j]);
                        fileWriter.write("\n");
                    }

                    mylist.remove(i) ;

                    for(j=0 ; j<mylist.size() ;j++){
                        fileWriter2.write(mylist.get(j));
                        fileWriter2.write("\n");

                    }
                    fileWriter2.close();
                }
            }
        }

        fileWriter.close();

        if(flag==0)
            System.out.printf("Böyle bir kullanıcı bulunmamaktadır\n") ;

        getInterface() ;
    }

}