import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Book{
  private int id;
  private String title;
  private String author;
  private int year;
  private boolean checkedOut;
  private int checkedOutBy;

  public Book(String title, String author, int year){
    this.title = title;
    this.author = author;
    this.year = year;
    this.checkedOut = false;
    this.checkedOutBy = 0;
  }

  //GET/SET METHODS
  public int getId(){
    return id;
  }

  public String getTitle(){
    return title;
  }

  public String getAuthor(){
    return author;
  }

  public int getYear(){
    return year;
  }

  public boolean getCheckedOut(){
    return checkedOut;
  }

  public void setCheckedOut(boolean status){
    this.checkedOut = status;
  }

  public int getCheckedOutBy(){
    return checkedOutBy;
  }

  public void setCheckedOutBy(int id){
    this.checkedOutBy = id;
  }

  //DATABASE METHODS
  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO books(title, author, year, checkedOut, checkedOutBy) Values(:title, :author, :year, :checkedOut, :checkedOutBy);";

      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", this.title)
        .addParameter("author", this.author)
        .addParameter("year", this.year)
        .addParameter("checkedOut", this.checkedOut)
        .addParameter("checkedOutBy", this.checkedOutBy)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Book> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM books";
      return con.createQuery(sql).executeAndFetch(Book.class);
    }
  }
}
