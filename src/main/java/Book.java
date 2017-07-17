public class Book{
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
}
