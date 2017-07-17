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

}
