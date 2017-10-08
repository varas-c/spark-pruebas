package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
	
	public String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	public static boolean writeFile(String content, String path, boolean append) throws IOException {
		FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
        	File file = new File (path);
        	
        	if (file.exists())
        		fichero = new FileWriter(file, append);
        	else
        		fichero = new FileWriter(file);
        	
            pw = new PrintWriter(fichero);
            pw.println(content);

        } catch (Exception e) {
            System.out.println("Error al guardar Indicadores: " + e.getMessage());
            return false;
        } finally {
           try {
        	   if (null != fichero)
               fichero.close();
           } catch (Exception e) {
              e.printStackTrace();
              return false;
           }
        }
        return true;
	}
}
