import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class VCFtoBED {

	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub
		//System.out.println(args.length);
		String filename=args[0];
		System.out.println(filename);
		String[] Tempvalues = filename.split("\\.");
		for (String value : Tempvalues)
			System.out.println(value);
		
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Tempvalues[0] + ".bed")));
		
		String line = br.readLine();
		Long counter = 1L;
		while (line!=null)
		{
			if (!line.startsWith("#"))
			{
				//System.out.println(line);
				String[] values= line.split("\t");
				Long id = Long.parseLong(values[1]);
				String Gene = values[3];
				Long end = id + Gene.length() ;
				
				
				bw.append("chr" + values[0] + "\t" + id + "\t" + end  + "\t" + counter + "\n");
				counter = counter+1;
			}
			
			line = br.readLine();
		}
		br.close();
		bw.close();
		
	}
			
	}



