import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BookTest{
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void book_InitializesCorrectly_title(){
    Book testBook = new Book("title", "author", 1979, "", "");
    assertEquals(true, testBook instanceof Book);
  }

  @Test
  public void getUsername_returnsTitle_title(){
    Book testBook = new Book("title", "author", 1979, "", "");
    assertEquals("title", testBook.getTitle());
  }

  @Test
  public void getPassword_returnsAuthor_author(){
    Book testBook = new Book("title", "author", 1979, "", "");
    assertEquals("author", testBook.getAuthor());
  }

  @Test
  public void getYear_returnsYear_1979(){
    Book testBook = new Book("title", "author", 1979, "", "");
    assertEquals(1979, testBook.getYear());
  }

  @Test
  public void getCheckedOut_returnsCheckedOutStatus_false(){
    Book testBook = new Book("title", "author", 1979, "", "");
    assertEquals(false, testBook.getCheckedOut());
  }

  @Test
  public void setCheckedOut_returnsCheckedOutStatus_true(){
    Book testBook = new Book("title", "author", 1979, "", "");
    testBook.setCheckedOut(true);
    assertEquals(true, testBook.getCheckedOut());
  }

  @Test
  public void getAndsetCheckedOutBy_returnsCheckedOutById_1(){
    Book testBook = new Book("title", "author", 1979, "", "");
    testBook.setCheckedOutBy(1);
    assertEquals(1, testBook.getCheckedOutBy());
  }

  @Test
  public void save_savesBookToBooksTable_true(){
    Book testBook = new Book("title", "author", 1979, "", "");
    testBook.save();
    assertTrue(testBook.getId() > 0);
  }

  @Test
  public void all_returnsAllInstancesOfBook_true(){
    Book testBook = new Book("title", "author", 1979, "", "");
    testBook.save();
    Book testBook2 = new Book("title2", "author2", 1980, "", "");
    testBook2.save();
    assertEquals(Book.all().get(0).getId(), testBook.getId());
    assertEquals(Book.all().get(1).getId(), testBook2.getId());
  }


  @Test
  public void findBook_findsBookByTitleAuthorAndYear_true(){
    Book testBook = new Book("title", "author", 1979, "", "");
    testBook.save();
    List<String> query = Arrays.asList("title", "author", "1979");
    assertEquals(testBook.getTitle(), Book.findBook(query).get(0).getTitle());
  }

  @Test
  public void set_getDueDate_returnsTrue(){
    Date duedate = new Date(System.currentTimeMillis()+5*60*100000);
    Book testBook = new Book("title", "author", 1979, "", "");
    testBook.setDueDate(duedate);
    assertEquals(duedate, testBook.getDueDate());
  }

  @Test
  public void findCheckedOutBy_returnsAllBooksCheckedOutByTestUser_true(){
    User testUser = new User("test", "user");
    testUser.save();
    Book testBook = new Book("title", "author", 1979, "", "");
    testBook.save();
    Book testBook2 = new Book("title2", "author2", 1980, "", "");
    testBook2.save();
    testUser.userBorrowBook(testBook.getId());
    testUser.userBorrowBook(testBook2.getId());
    assertEquals(testBook.getId(), Book.findCheckedOutBy(testUser.getId()).get(0).getId());
    assertEquals(testBook2.getId(), Book.findCheckedOutBy(testUser.getId()).get(1).getId());
  }
}
