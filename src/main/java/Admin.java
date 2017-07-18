import org.sql2o.*;
public class Admin extends UserAbstract{

  public Admin(String username, String password){
    this.username = username;
    this.password = password;
    numOfBooksCheckedOut = 0;
  }

  public void addBookToLibrary(String title, String author, int year){
    Book newBook = new Book(title, author, year);
    newBook.save();
  }

  public static boolean login(String username, String password){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM admins WHERE username=:username AND password=:password;";

      Admin admin = con.createQuery(sql)
        .addParameter("username", username)
        .addParameter("password", password)
        .executeAndFetchFirst(Admin.class);
      if (admin == null){
        return false;
      } else {
        return true;
      }
    }
  }
}
