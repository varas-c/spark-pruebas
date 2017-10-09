package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import spark.ModelAndView;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.Request;
import spark.Response;
import model.CompanyLoader;
import model.InvalidFileException;
import model.Company;

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
	
	private static ModelAndView tabla(Request request, Response response) throws InvalidFileException, IOException {
		HashMap<String,Object> viewModel = new HashMap<>();
		CompanyLoader a = new CompanyLoader();
		List<Company> companies = a.getCompanyList("/home/chris/Documents/DisenoSistemas/2017-jm-group-02/src/test/resources/cuentasHector3.json");
		
		viewModel.put("companies", companies);
		
		return new ModelAndView(viewModel, "cuentas.hbs");
	}

	private static ModelAndView login(Request request, Response response) throws InvalidFileException, IOException {
		System.out.println("LOGEANDO");
		HashMap<String, Object> viewModel = new HashMap<>();
		String user = request.queryParams("user");
		String pass = request.queryParams("pass");
		System.out.println(user);
		
		if(user.equals("Chris")) {
			System.out.println("OK!");
			return new ModelAndView(viewModel, "badLogin.hbs"); 
		}
		
		CompanyLoader a = new CompanyLoader();
		List<Company> companies = a.getCompanyList("src/test/resources/cuentasHector3.json");
		
		viewModel.put("companies", companies);
		
		return new ModelAndView(viewModel, "index.hbs");
		
	}

	public static ModelAndView show(Request req, Response rep) {
		System.out.println("Inicio");
		HashMap<String, String> viewModel = new HashMap<>();
		viewModel.put("badlogin","");
		return new ModelAndView(viewModel, "login.hbs");
	}

}
