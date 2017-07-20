import org.sql2o.*;
import java.util.Date;
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

  public boolean adminBorrowBook(int bookId){
    Date duedate = new Date(System.currentTimeMillis()+5*60*1000000);
    Book book = Book.find(bookId);
    book.setDueDate(duedate);
    System.out.println(book.getDueDate());
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE books SET checkedOut = true, checkedOutBy = :adminId, duedate = :duedate WHERE id=:bookId;";
      con.createQuery(sql, true)
        .addParameter("duedate", duedate)
        .addParameter("adminId", this.id)
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
      String sql = "UPDATE admins SET numOfBooksCheckedOut = :num WHERE id=:id;";
      con2.createQuery(sql)
        .addParameter("num", this.getNumofBooksCheckedOut())
        .addParameter("id", this.id)
        .executeUpdate();
    }
    return true;
  }

  public boolean adminReturnBook(int bookId){
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
      String sql = "UPDATE admins SET numOfBooksCheckedOut = :num WHERE id=:id;";
      con2.createQuery(sql)
        .addParameter("num", this.getNumofBooksCheckedOut())
        .addParameter("id", this.id)
        .executeUpdate();
    }
    return true;
  }
}
