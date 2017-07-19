//Superclass for admin and user
import org.sql2o.*;
import java.util.HashMap;
import java.util.Map;



public abstract class UserAbstract{
  public static final int MAX_BOOKS_CHECKEDOUT = 4;
  public String username;
  public String password;
  public int numOfBooksCheckedOut;
  public int id;

  //GET/SET METHODS
  public String getUsername(){
    return username;
  }


  public String getPassword(){
    return password;
  }

  public int getId(){
    return id;
  }

  public int getNumofBooksCheckedOut(){
    return numOfBooksCheckedOut;
  }

}
