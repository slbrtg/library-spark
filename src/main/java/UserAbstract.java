//Superclass for admin and user
//Shares:
//Search method to find book by author or title or both


public class UserAbstract{
  private String username;
  private String password;

  //CONSTRUCTOR
  public UserAbstract(String username, String password){
    this.username = username;
    this.password = password;
  }


  //GET/SET METHODS
  public String getUsername(){
    return username;
  }


  public String getPassword(){
    return password;
  }


  //DATABASE METHODS

}
