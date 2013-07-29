package generic.fps.ranking.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	
	public static  List<String> readAllFileLines(String fileLocation){
		List<String> lines = new ArrayList<String>();
		
		File file = new File(fileLocation);
		
		if(!file.exists()){
			return lines;
		}
		if(!file.canRead()){
			return lines;
		}
		
		FileReader reader;
		try {
			reader = new FileReader(file);
		
		    BufferedReader bReader = new BufferedReader(reader);
		    
		    
		    String line = bReader.readLine();
		    while(line != null  ){
		    	 lines.add(line);
		    	 line  = bReader.readLine();
		    }
		    
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines;
	}
}
