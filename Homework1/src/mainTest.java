import java.io.* ;
import java.util.Scanner;
/**
 * The mainTest program implements an application that
 simply library system for users and staff.
 *
 * @author  Furkan Berber 141044059
 */

public class mainTest {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * @param name kullanıcıdan alınan isim
         */
        String name ;
        String lastname ;
        String ID;
        int id ;

        Scanner input = new Scanner(System.in);

        System.out.printf("(For Test)Staff's ID= 123\n") ;
        System.out.printf("Name ") ;
        System.out.printf("Lastname = ") ;
        name = input.next();
        lastname = input.next();

        System.out.printf("ID = ") ;
        id= input.nextInt() ;

        ID=""+id ;

        if(id==123){
            Staff testStaff = new Staff(name,lastname,ID) ;
            FonkHuman(testStaff) ;
        }

        else if(id>=1000 && id<=10000000){
            User testUser = new User(name,lastname,ID) ;
            FonkHuman(testUser) ;
        }

        else
        {
            System.out.printf("Invalid ID") ;
        }

    }
    /**
     * Run time da karar vererek kullanıcı için arayüz oluşuturulur.
     * @param test
     */
    static void FonkHuman(Human test) {

        int choose ;
        char statu = test.authority();

        if(statu=='S'){
            Staff temp=(Staff)test ;
            temp.getInterface();
        }

        if(statu=='U'){
            User temp=(User)test ;
            temp.getInterface();
        }

    }




}