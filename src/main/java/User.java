import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class User extends UserAbstract {

  //CONSTRUCTOR
  public User(String username, String password){
    this.username = username;
    this.password  = password;
  }

  //DATABASE METHODS
  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO users(username, password) Values(:username, :password);";

      this.id = (int) con.createQuery(sql, true)
        .addParameter("username", this.username)
        .addParameter("password", this.password)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<User> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM users;";
      return con.createQuery(sql).executeAndFetch(User.class);
    }
  }

  public static User find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM users where id=:id;";
      User user = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(User.class);
    return user;
    }
  }
}
