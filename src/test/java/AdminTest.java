import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void admin_InitializesCorrectly_true(){
    Admin testAdmin = new Admin("test", "admin");
    assertEquals(true, testAdmin instanceof Admin);
  }

  @Test
  public void getAdminname_returnsAdminname_test(){
    Admin testAdmin = new Admin("test", "admin");
    assertEquals("test", testAdmin.getUsername());
  }

  @Test
  public void getPassword_returnsPassword_test(){
    Admin testAdmin = new Admin("test", "admin");
    assertEquals("admin", testAdmin.getPassword());
  }

  @Test
  public void addBookToLibrary_addsTestBookToLibrary_true(){
    Admin testAdmin = new Admin("test", "admin");
    testAdmin.addBookToLibrary("test", "book", 1979);
    assertTrue(Book.all().get(0).getId() > 0); 
  }

}
