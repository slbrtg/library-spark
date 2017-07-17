//Superclass for admin and user
import org.sql2o.*;


public abstract class UserAbstract{
  public static final int MAX_BOOKS_CHECKEDOUT = 4;
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
  public void borrowBook(int bookId){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE books SET checkedOut = true, checkedOutBy = :userId WHERE id=:bookId;";
      con.createQuery(sql)
        .addParameter("userId", this.id)
        .addParameter("bookId", bookId)
        .executeUpdate();
    }
  }
}
