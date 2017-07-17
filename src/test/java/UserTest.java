import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void user_InitializesCorrectly_true(){
    User testUser = new User("test", "user");
    assertEquals(true, testUser instanceof User);
  }

  @Test
  public void getUsername_returnsUsername_test(){
    User testUser = new User("test", "user");
    assertEquals("test", testUser.getUsername());
  }

  @Test
  public void getPassword_returnsPassword_test(){
    User testUser = new User("test", "user");
    assertEquals("user", testUser.getPassword());
  }

  @Test
  public void all_ReturnsAllUsersInDB_true(){
    User testUser = new User("test", "user");
    testUser.save();
    User testUser2 = new User("test2", "user2");
    testUser2.save();
    assertEquals(User.all().get(0).getId(), testUser.getId());
    assertEquals(User.all().get(1).getId(), testUser2.getId());
  }

  @Test
  public void borrowBook_changesBookBorrowedStateInDB_true(){
    User testUser = new User("test", "user");
    Book testBook = new Book("title", "author", 1979);
    testBook.save();
    testUser.borrowBook(testBook.getId());
    assertEquals(testBook.getCheckedOutBy(), testUser.getId());

  }

}