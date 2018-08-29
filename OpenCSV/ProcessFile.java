import au.com.bytecode.opencsv.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProcessFile {

    public static void main(String[] args) {
        String fileName= "read_ex.csv";

		try {
	 
				 CSVReader reader = new CSVReader(new FileReader(fileName));
				 String [] nextLine;
				 Integer line = 0;
				 while ((nextLine = reader.readNext()) != null) {
					// nextLine[] is an array of values from the line
					System.out.println("Line# " + line);
					line = line + 1;
					for(String cell : nextLine){
						System.out.print(cell + " / ");
					}
					System.out.println(" ");
				 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		
    }

}