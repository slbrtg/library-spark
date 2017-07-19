import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

public class Book{
  private int id;
  private String title;
  private String author;
  private String description;
  private String imageUrl;
  private int year;
  private boolean checkedOut;
  private int checkedOutBy;
  private Timestamp duedate;

  public Book(String title, String author, int year, String description, String imageUrl){
    this.title = title;
    this.author = author;
    this.year = year;
    this.description = description;
    this.imageUrl = imageUrl;
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

  public String getDescription(){
    return description;
  }

  public String getImageUrl(){
    return imageUrl;
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

  public Timestamp getDueDate(){
    return duedate;
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

  public static List<Book> findBook(List<String> search){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM books WHERE title = :title OR author = :author;";
      String title = search.get(0);
      String author = search.get(1);
      List<Book> searchResults =  con.createQuery(sql)
        .addParameter("title", title)
        .addParameter("author", author)
        .executeAndFetch(Book.class);
      return searchResults;
    }

  }

  // public static List<Book> findBook(List<String> search){
  //   try(Connection con = DB.sql2o.open()){
  //     String sql = "SELECT * FROM books WHERE title = :title AND author = :author AND year = :year OR title = :title AND author = :author OR title = :title AND year = :year OR author = :author AND year = :year OR title = :title OR author = :author OR year = :year;";
  //     String title = search.get(0);
  //     String author = search.get(1);
  //     String year = search.get(2);
  //     List<Book> searchResults =  con.createQuery(sql)
  //       .addParameter("title", title)
  //       .addParameter("author", author)
  //       .addParameter("year", Integer.parseInt(year))
  //       .executeAndFetch(Book.class);
  //     return searchResults;
  //   }
  // }
}
