package model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Company;

public class ParserCompany {

	public List<Company> parseCompanies(String fileContent) {
		List<Company> aux = null;
		try {
			aux = new ObjectMapper().readValue(fileContent, new TypeReference<List<Company>>() {});
		} catch (IOException e) {}
		return aux;
	}
}
