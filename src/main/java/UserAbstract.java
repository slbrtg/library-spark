//Superclass for admin and user
import org.sql2o.*;


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


  //DATABASE METHODS
  public void borrowBook(int bookId){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE books SET checkedOut = true, checkedOutBy = :userId WHERE id=:bookId;";
      con.createQuery(sql, true)
        .addParameter("userId", this.id)
        .addParameter("bookId", bookId)
        .executeUpdate();
        this.numOfBooksCheckedOut += 1;
    }
    try(Connection con2 = DB.sql2o.open()){
      String sql = "UPDATE users SET numOfBooksCheckedOut = :num WHERE id=:id;";
      con2.createQuery(sql)
        .addParameter("num", this.getNumofBooksCheckedOut())
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void returnBook(int bookId){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE books SET checkedOut = false, checkedOutBy = 0 WHERE id=:bookId;";
      con.createQuery(sql, true)
        .addParameter("bookId", bookId)
        .executeUpdate();
      if (this.getNumofBooksCheckedOut() == 0){
        System.out.println("You can't return what you don't have");
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
  }
}
