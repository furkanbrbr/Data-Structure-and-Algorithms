import java.io.*;
import java.util.* ;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author furkan
 */
public class User implements Human {

    private final String userName ;
    private final String userLastName ;
    private final String userID ;
    /**
     *
     * @param name gelen kullanıcının adı
     * @param lastname gelen kullanıcının soyadı
     * @param ID gelen kullanıcının ID
     */
    public User(String name,String lastname,String ID){

        userName=name ;
        userLastName=lastname ;
        userID=ID ;
    }
    /**
     *
     * @return kullanıcı adı
     */
    @Override
    public String getName(){

        return userName ;
    }
    /**
     *
     * @return kullanıcı soyadı
     */
    @Override
    public String getLastName(){

        return userLastName ;
    }
    /**
     *
     * @return kullanıcı ID
     */
    @Override
    public String getID(){

        return userID ;
    }
    /**
     *
     * @return kullanıcı yetkisi
     */
    @Override
    public char authority(){

        String[] temp ;
        int i,j,flag=0 ;

        Scanner filescan = null ;
        File fileUsersBooks;
        ArrayList<String> mylist=new ArrayList() ;

        fileUsersBooks=new File("Users And Books.csv") ;
        try {
            filescan = new Scanner(new FileReader(fileUsersBooks));
        } catch (FileNotFoundException ex) {
            System.out.printf("Şu an hiç bir kullanıcı bulunmamaktadır");
        }

        /*Users And Books.csv dosyasından okuyarak arrayliste atılır*/
        while(filescan.hasNext()){
            mylist.add(filescan.nextLine()) ;
        }

        for(i=0 ; i<mylist.size() ; i++){

            temp=mylist.get(i).split(",") ;

            if(userID.equals(temp[0]))
                flag=1 ;
        }

        if(flag==0){
            System.out.printf("Böyle bir kullanıcı bulunmamaktadır.\n");
            return 'A' ;
        }
        return 'U' ;
    }

    /**
     * User için gerekli olan menü oluşturulur.
     */
    @Override
    public void getInterface(){

        int choose ;

        Scanner input = new Scanner(System.in);

        System.out.printf("Choose 1 for Barrow Book or Choose 2 for Return Book\n") ;
        System.out.printf("Choose 3 for Excist\n") ;

        choose=input.nextInt() ;

        switch (choose) {
            case 1:
            {
                try {
                    barrowBook();
                } catch (IOException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case 2:
            {
                try {
                    returnBook() ;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case 3:
                break ;
            default:
                System.out.printf("Please press the correct button\n") ;
                getInterface() ;
        }
    }
    /**
     * Kütüphaneden kitap ödünç alınır
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void barrowBook() throws FileNotFoundException, IOException{

        String bookName,bookID = null,line;
        String[] temp ;
        String tempUse="" ;
        int i,j,m,flag=0 ;

        Scanner filescan = null;
        Scanner filescan2 = null;
        ArrayList<String> mylist=new ArrayList() ;
        ArrayList<String> mylist2=new ArrayList() ;

        Scanner input = new Scanner(System.in);
        System.out.printf("Name of Book =") ;
        bookName = input.nextLine();

        File fileBooks,fileUsersBooks,fileBarrowReturn;
        fileUsersBooks=new File("Users And Books.csv") ;
        fileBarrowReturn= new File("Barrow And Return.csv") ;

        filescan = new Scanner(new FileReader(fileBarrowReturn));
        filescan2 = new Scanner(new FileReader(fileUsersBooks));
        /*Barrow And Return.csv dosyasından arrayliste atılır*/
        while(filescan.hasNext()){
            mylist.add(filescan.nextLine()) ;
        }
        while(filescan2.hasNext()){
            mylist2.add(filescan2.nextLine()) ;
        }

        FileWriter fileWriter = new FileWriter(fileUsersBooks, false);
        FileWriter fileWriter2 = new FileWriter(fileBarrowReturn, false);
        /*kontrol edilip kitap verilir*/
        for(i=0 ; i<mylist.size() ; i++){

            if(bookName.equals(mylist.get(i))){

                if (!fileUsersBooks.exists()) {
                    System.out.printf("ilk önce kullanıcı eklenmelidir");
                }
                /*arraylist ile kitap kullanıcıya eklenir*/
                for(j=0 ; j<mylist2.size() ; j++){

                    temp=mylist2.get(j).split(",") ;

                    if(userID.equals(temp[0])){

                        for(m=0 ; m<temp.length ;m++){

                            tempUse+=temp[m] ;
                            tempUse+="," ;
                        }

                        tempUse+=bookName ;
                        mylist2.remove(j) ;
                        mylist2.add(j, tempUse);

                        for(int l=0 ; l<mylist2.size() ; l++){
                            fileWriter.write(mylist2.get(l));
                            fileWriter.write("\n");
                        }
                    }
                }
                /*Barrow And Return.csv dosyasından kitap çıkarılır*/
                mylist.remove(i) ;
                for(int n=0 ; n<mylist.size() ; n++){
                    fileWriter2.write(mylist.get(n));
                    fileWriter2.write("\n");
                }

                flag=1 ;
                fileWriter.close();
            }
        }

        if(flag==0)
            System.out.printf("Böyle Bir Kitap Bulunmamaktadır") ;

        fileWriter.close();
        fileWriter2.close();

        getInterface() ;
    }
    /**
     * Ödünç alınan kiitabı geri iade etmek için kullanılır
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void returnBook() throws FileNotFoundException, IOException{

        String bookName,bookID = null,line;
        String[] temp ;
        int i,j,k,m,flag=0 ;

        Scanner filescan = null;
        ArrayList<String> mylist=new ArrayList() ;

        Scanner input = new Scanner(System.in);
        System.out.printf("Name of Book =") ;
        bookName = input.nextLine();

        File fileUsersBooks,fileBarrowReturn;

        fileUsersBooks=new File("Users And Books.csv") ;
        fileBarrowReturn= new File("Barrow And Return.csv") ;

        filescan = new Scanner(new FileReader(fileUsersBooks));
        /*Users And Books.csv dosyasından okuyup array liste atılır*/
        while(filescan.hasNext()){
            mylist.add(filescan.nextLine()) ;
        }
        /*arraylist den çıkarılan eleman tekrardan yazılır*/
        for(i=0 ; i<mylist.size() ; i++){

            temp=mylist.get(i).split(",") ;

            if(temp[0]==userID){

                for(j=3 ; j<temp.length ;j++){

                    if(bookName.equals(temp[j])){

                        for(k=j ; k<temp.length-1 ;k++)
                            temp[k]=temp[k+1] ;

                        temp[k]="" ;

                        mylist.remove(i);

                        line = "";

                        for(k=0 ; k<temp.length-1 ; k++)
                            line += temp[k] + ",";
                        line += temp[k] ;

                        mylist.add(i, line);

                        FileWriter fileWriter = new FileWriter(fileUsersBooks, false);
                        for(m=0 ; m<mylist.size() ;m++){

                            fileWriter.write(mylist.get(m)) ;
                            fileWriter.write("\n");

                        }

                        FileWriter fileWriter2 = new FileWriter(fileBarrowReturn, true);
                        fileWriter2.write(bookName);
                        fileWriter2.write("\n");

                        fileWriter.close();
                        fileWriter2.close();

                        flag=1 ;
                    }
                }
            }
        }

        if(flag==0)
            System.out.printf("Kullanıcının böyle bir kitabı yok") ;

        getInterface() ;
    }


}
