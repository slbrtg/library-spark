import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class UserAbstractTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void user_InitializesCorrectly_true(){
    UserAbstract testUser = new UserAbstract("test", "user");
    assertEquals(true, testUser instanceof UserAbstract);
  }

  @Test
  public void getUsername_returnsUsername_test(){
    UserAbstract testUser = new UserAbstract("test", "user");
    assertEquals("test", testUser.getUsername());
  }

  @Test
  public void getPassword_returnsPassword_test(){
    UserAbstract testUser = new UserAbstract("test", "user");
    assertEquals("user", testUser.getPassword());
  }
}
