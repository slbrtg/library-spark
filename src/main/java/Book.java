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
      String sql = "INSERT INTO books(title, author, year, description, imageUrl, checkedOut, checkedOutBy, duedate) Values(:title, :author, :year, :description, :imageUrl, :checkedOut, :checkedOutBy, :duedate);";

      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", this.title)
        .addParameter("author", this.author)
        .addParameter("year", this.year)
        .addParameter("description", this.description)
        .addParameter("imageUrl", this.imageUrl)
        .addParameter("checkedOut", this.checkedOut)
        .addParameter("checkedOutBy", this.checkedOutBy)
        .addParameter("duedate", this.duedate)
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

  public static Book find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM books where id=:id;";
      Book book = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Book.class);
    return book;
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
}
