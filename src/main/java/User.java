import org.sql2o.*;

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
}
