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

  public boolean login(String username, String password){
    int userId;

    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT id FROM admins WHERE username=:username AND password=:password;";

      Admin admin = con.createQuery(sql)
        .addParameter("username", username)
        .addParameter("password", password)
        .executeAndFetchFirst(Admin.class);
      if (admin.getId() > 0){
        System.out.println("Authenticated");
        return true;
      } else {
        System.out.println("false");
        return false;
      }
    }
  }
}
