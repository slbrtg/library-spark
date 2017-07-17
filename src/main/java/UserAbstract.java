//Superclass for admin and user
//Shares:
//Search method to find book by author or title or both


public class UserAbstract{
  private String username;
  private String password;

  public UserAbstract(String username, String password){
    this.username = username;
    this.password = password;
  }

  public String getUsername(){
    return username;
  }

  public String getPassword(){
    return password;
  }
}
