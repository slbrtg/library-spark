import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Map;
import java.util.HashMap;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });

    post("/authenticating-admin", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String username = request.queryParams("adminUsername");
      String password = request.queryParams("adminPassword");
      if (Admin.login(username,password) != -1){
        model.put("adminId", Admin.login(username,password));
        model.put("template", "templates/admin-authenticated.vtl");
      }else {
        model.put("template", "templates/login-fail.vtl");
      }
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, layout)
      );
    });
  }
}
