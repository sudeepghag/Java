import au.com.bytecode.opencsv.*;
import java.io.*;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProcessFile {

	String fileName;
	
    public static void main(String[] args) {
				
		List<String[]> recs = ProcessFile.readFile("read_ex.csv");
		
		System.out.println("---------------------------");
		System.out.println("Size : " + recs.size());
		System.out.println("---------------------------");
		
		for (String[] record : recs) {
			System.out.println("Name : " + record[0]);
			System.out.println("Email : " + record[1]);
			System.out.println("Phone : " + record[2]);
			System.out.println("Country : " + record[3]);
			System.out.println("---------------------------");
		}
						
		
    }//main    
	
	public ProcessFile(){
		fileName= "read_ex.csv";
	}//ProcessFile
		
	public static List<String[]> readFile(String fileName){	

		System.out.println("readFile: fileName = "+fileName);
		
		try {				
				CSVReader reader = new CSVReader(new FileReader(fileName));
				 
				List<String[]> records = reader.readAll();
				

				return records;

	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
    }

}