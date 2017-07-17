// public class User extends UserAbstract{
//
//   //DATABASE METHODS
//   public void save(){
//     try(Connection con = DB.sql2o.open()){
//       String sql = "INSERT INTO users(username, password) Values(:username, :password);";
//
//       this.id = (int) con.createQuery(sql, true)
//         .addParameter("username", this.username)
//         .addParameter("password", this.password)
//         .executeUpdate()
//         .getKey();
//     }
//   }
// }
