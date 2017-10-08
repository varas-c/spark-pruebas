package model;

import java.io.IOException;
import java.util.List;

import model.InvalidFileException;
import model.Company;


public class CompanyLoader {
	
	public List<Company> getCompanyList(String pathFile) throws InvalidFileException, IOException {
	
		List<Company> aux = null;
		String fileContent = new FileReader().readFile(pathFile);
		aux = new ParserCompany().parseCompanies(fileContent);
		if (aux == null) {
			throw new InvalidFileException("Contenido Vacío");			
		}
		
		/*aux.forEach(c -> {
			c.getAccounts().forEach(a -> a.setCompany(c));
		});*/
		
		return aux;
	}
}


