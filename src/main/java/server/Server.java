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
	public static List<Company> companies;
	
	public static void main(String[] args) throws InvalidFileException, IOException {
		CompanyLoader a = new CompanyLoader();
		companies = a.getCompanyList("src/test/resources/cuentasHector3.json");
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		HandlebarsTemplateEngine engine = 
				new HandlebarsTemplateEngine();
		Spark.get("/hello", (request, response) -> Server.show(request, response), engine);
		Spark.get("/login", (request, response) -> Server.login(request, response), engine);
		Spark.get("/cuentas", (request, response) -> Server.cuentas(request, response), engine);
		Spark.stop();
	}
	
	private static ModelAndView cuentas(Request request, Response response) throws InvalidFileException, IOException {
		String companyName = request.queryParams("company");
		Company company = companies.stream().filter(x -> x.getName().equals(companyName)).findFirst().orElse(null);
		
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("accounts", company.getAccounts());
		
		return new ModelAndView(viewModel, "cuentas.hbs");
	}

	private static ModelAndView login(Request request, Response response) throws InvalidFileException, IOException {
		
		HashMap<String, Object> viewModel = new HashMap<>();
		String user = request.queryParams("user");
		String pass = request.queryParams("pass");
		
		if(user.equals("Chris")) {
			System.out.println("OK!");
			return new ModelAndView(viewModel, "badLogin.hbs"); 
		}
		
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
