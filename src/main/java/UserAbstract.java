//Superclass for admin and user
//Shares:
//Search method to find book by author or title or both

public abstract class UserAbstract{
  public String username;
  public String password;
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


  //DATABASE METHODS

}
