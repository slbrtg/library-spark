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
}
