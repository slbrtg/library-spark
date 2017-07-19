import org.sql2o.*;
public class Admin extends UserAbstract{

  public Admin(String username, String password){
    this.username = username;
    this.password = password;
    numOfBooksCheckedOut = 0;
  }

  public void addBookToLibrary(String title, String author, int year, String description, String imageUrl){
    Book newBook = new Book(title, author, year, description, imageUrl);
    newBook.save();
  }

  public static Admin find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM admins where id=:id;";
      Admin admin = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Admin.class);
    return admin;
    }
  }

  public static int login(String username, String password){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM admins WHERE username=:username AND password=:password;";

      Admin admin = con.createQuery(sql)
        .addParameter("username", username)
        .addParameter("password", password)
        .executeAndFetchFirst(Admin.class);
      if (admin == null){
        return -1;
      } else {
        return admin.getId();
      }
    }
  }
}
