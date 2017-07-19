import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    //GET /
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    //SIGNUP ROUTES
    post("/signedup", (request, response) -> {
      Map<String,Object> model = new HashMap<String, Object>();
      String username = request.queryParams("signupUsername");
      String password = request.queryParams("signupPassword");
      User user = new User(username,password);
      user.save();
      model.put("user", user);
      model.put("template", "templates/signedup.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });
    //USER ROUTES
    get("user/:id/home", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      User user = User.find(Integer.parseInt(request.params("id")));
      model.put("user", user);
      model.put("template", "templates/user-home.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    post("/user/authenticated", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String username = request.queryParams("userUsername");
      String password = request.queryParams("userPassword");
      if (User.login(username,password) != -1){
        User user = User.find(User.login(username,password));
        model.put("user", user);
        model.put("id", user.getId());
        model.put("template", "templates/user-authenticated.vtl");
      }else {
        model.put("template", "templates/login-fail.vtl");
      }
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("/user/:id/all-books", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("books", Book.all());
      model.put("template", "templates/user-all-books.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("/user/:id/find-book", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      User user = User.find(Integer.parseInt(request.params("id")));
      model.put("user", user);
      model.put("template", "templates/user-find-book.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    post("/user/:id/find-book", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      List<String> search = new ArrayList<String>();
      User user = User.find(Integer.parseInt(request.params("id")));
      search.add(request.queryParams("bookTitle"));
      search.add(request.queryParams("bookAuthor"));
      //search.add(request.queryParams("bookYear"));
      model.put("books", Book.findBook(search));
      model.put("user", user);
      model.put("template", "templates/user-find-book.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });



    //ADMIN ROUTES
    post("/admin/authenticated", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String username = request.queryParams("adminUsername");
      String password = request.queryParams("adminPassword");
      if (Admin.login(username,password) != -1){
        Admin admin = Admin.find(Admin.login(username,password));
        model.put("admin", admin);
        model.put("template", "templates/admin-authenticated.vtl");
      }else {
        model.put("template", "templates/login-fail.vtl");
      }
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("admin/:id/home", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      Admin admin = Admin.find(Integer.parseInt(request.params("id")));
      model.put("admin", admin);
      model.put("template", "templates/admin-home.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("/admin/:id/add-book", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Admin admin = Admin.find(Integer.parseInt(request.params("id")));
      model.put("admin", admin);
      model.put("template", "templates/admin-add-book.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    post("/admin/:id/add-book/success", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String title = request.queryParams("bookTitle");
      String author = request.queryParams("bookAuthor");
      int year = Integer.parseInt(request.queryParams("bookYear"));
      String description = request.queryParams("bookDescription");
      String imageUrl = request.queryParams("bookImageUrl");
      Admin admin = Admin.find(Integer.parseInt(request.params("id")));
      Book book = new Book(title, author, year, description, imageUrl);
      book.save();
      model.put("admin", admin);
      model.put("book", book);
      model.put("template", "templates/admin-add-book-success.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("/admin/:adminId/book/:bookId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Admin admin = Admin.find(Integer.parseInt(request.params("adminId")));
      Book book = Book.find(Integer.parseInt(request.params("bookId")));
      model.put("book", book);
      model.put("template", "templates/admin-book-view.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("/admin/:id/all-books", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Admin admin = Admin.find(Integer.parseInt(request.params("id")));
      model.put("admin", admin);
      model.put("books", Book.all());
      model.put("template", "templates/admin-all-books.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    get("/admin/:id/find-book", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Admin admin = Admin.find(Integer.parseInt(request.params("id")));
      model.put("admin", admin);
      model.put("template", "templates/admin-find-book.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    post("/admin/:id/find-book", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      List<String> search = new ArrayList<String>();
      Admin admin = Admin.find(Integer.parseInt(request.params("id")));
      search.add(request.queryParams("bookTitle"));
      search.add(request.queryParams("bookAuthor"));
      //search.add(request.queryParams("bookYear"));
      model.put("books", Book.findBook(search));
      model.put("admin", admin);
      model.put("template", "templates/admin-find-book.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });
  }
}
