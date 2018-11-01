/**
 * Super class
 * It has methods for base classes
 * @author furkan
 */
public interface Human {

    /**
     * @return kullanıcı adı
     */
    public String getName();
    /**
     * @return Kullanıcı soyadı
     */
    public String getLastName() ;
    /**
     * @return kullanıcı ID
     */
    public String getID() ;
    /**
     * @return kullanıcının yetkisi
     */
    public char authority() ;
    /**
     * kullanıcı için arayüz
     */
    public void getInterface();

}