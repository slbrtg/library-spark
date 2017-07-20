import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class User extends UserAbstract {

  //CONSTRUCTOR
  public User(String username, String password){
    this.username = username;
    this.password  = password;
    numOfBooksCheckedOut = 0;
  }

  //DATABASE METHODS
  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO users(username, password) Values(:username, :password);";

      this.id = (int) con.createQuery(sql, true)
        .addParameter("username", this.username)
        .addParameter("password", this.password)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<User> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM users;";
      return con.createQuery(sql).executeAndFetch(User.class);
    }
  }

  public static User find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM users where id=:id;";
      User user = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(User.class);
    return user;
    }
  }

  public static int login(String username, String password){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM users WHERE username=:username AND password=:password;";

      User user = con.createQuery(sql)
        .addParameter("username", username)
        .addParameter("password", password)
        .executeAndFetchFirst(User.class);
      if (user == null){
        return -1;
      } else {
        return user.getId();
      }
    }
  }

  public boolean userBorrowBook(int bookId){
    Date duedate = new Date(System.currentTimeMillis()+5*60*1000000);
    Book book = Book.find(bookId);
    book.setDueDate(duedate);
    System.out.println(book.getDueDate());
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE books SET checkedOut = true, checkedOutBy = :userId, duedate = :duedate WHERE id=:bookId;";
      con.createQuery(sql, true)
        .addParameter("duedate", duedate)
        .addParameter("userId", this.id)
        .addParameter("bookId", bookId)
        .executeUpdate();
        if (this.getNumofBooksCheckedOut() == MAX_BOOKS_CHECKEDOUT){
          System.out.println("Return some books to borrow new books");
          return false;
        } else {
          this.numOfBooksCheckedOut += 1;
        }
    }
    try(Connection con2 = DB.sql2o.open()){
      String sql = "UPDATE users SET numOfBooksCheckedOut = :num WHERE id=:id;";
      con2.createQuery(sql)
        .addParameter("num", this.getNumofBooksCheckedOut())
        .addParameter("id", this.id)
        .executeUpdate();
    }
    return true;
  }

  public boolean userReturnBook(int bookId){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE books SET checkedOut = false, checkedOutBy = 0 WHERE id=:bookId;";
      con.createQuery(sql, true)
        .addParameter("bookId", bookId)
        .executeUpdate();
      if (this.getNumofBooksCheckedOut() == 0){
        System.out.println("You can't return what you don't have");
        return false;
      } else {
        this.numOfBooksCheckedOut -= 1;
      }
    }
    try(Connection con2 = DB.sql2o.open()){
      String sql = "UPDATE users SET numOfBooksCheckedOut = :num WHERE id=:id;";
      con2.createQuery(sql)
        .addParameter("num", this.getNumofBooksCheckedOut())
        .addParameter("id", this.id)
        .executeUpdate();
    }
    return true;
  }
}
