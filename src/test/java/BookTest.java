import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BookTest{
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void book_InitializesCorrectly_title(){
    Book testBook = new Book("title", "author", 1979);
    assertEquals(true, testBook instanceof Book);
  }

  @Test
  public void getUsername_returnsTitle_title(){
    Book testBook = new Book("title", "author", 1979);
    assertEquals("title", testBook.getTitle());
  }

  @Test
  public void getPassword_returnsAuthor_author(){
    Book testBook = new Book("title", "author", 1979);
    assertEquals("author", testBook.getAuthor());
  }

  @Test
  public void getYear_returnsYear_1979(){
    Book testBook = new Book("title", "author", 1979);
    assertEquals(1979, testBook.getYear());
  }

  @Test
  public void getCheckedOut_returnsCheckedOutStatus_false(){
    Book testBook = new Book("title", "author", 1979);
    assertEquals(false, testBook.getCheckedOut());
  }

  @Test
  public void setCheckedOut_returnsCheckedOutStatus_true(){
    Book testBook = new Book("title", "author", 1979);
    testBook.setCheckedOut(true);
    assertEquals(true, testBook.getCheckedOut());
  }
}
