package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import spark.ModelAndView;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.Request;
import spark.Response;

public class Server {
	public static void main(String[] args) {
		Spark.port(9001);
		DebugScreen.enableDebugScreen();
		HandlebarsTemplateEngine engine = 
				new HandlebarsTemplateEngine();
		Spark.get("/hello", (request, response) -> Server.show(request, response), engine);
		Spark.get("/login", (request, response) -> Server.login(request, response), engine);
		Spark.get("/tabla", (request, response) -> Server.tabla(request, response), engine);
		Spark.stop();
	}
	
	private static ModelAndView tabla(Request request, Response response) {
		HashMap<String,Object> viewModel = new HashMap<>();
		Cuenta a = new Cuenta();
		a.setNombre("Facebook");
		a.setPeriodo("2015");
		a.setValor("2015");
		
		Cuenta b = new Cuenta();
		b.setNombre("blablabla");
		b.setPeriodo("2015");
		b.setValor("99191919");
		
		List<Cuenta> list = new ArrayList<Cuenta>();
		list.add(a);
		list.add(b);
		
		viewModel.put("cuentas", list);
		
		
		return new ModelAndView(viewModel, "cuentas.hbs");
	}

	private static ModelAndView login(Request request, Response response) {
		System.out.println("LOGEANDO");
		HashMap<String, Object> viewModel = new HashMap<>();
		String user = request.queryParams("user");
		String pass = request.queryParams("pass");
		System.out.println(user);
		
		if(user.equals("Chris")) {
			System.out.println("OK!");
			return new ModelAndView(viewModel, "badLogin.hbs"); 
		}
		
		return new ModelAndView(viewModel, "index.hbs");
		
	}

	public static ModelAndView show(Request req, Response rep) {
		System.out.println("Inicio");
		HashMap<String, String> viewModel = new HashMap<>();
		viewModel.put("badlogin","");
		return new ModelAndView(viewModel, "login.hbs");
	}

}
