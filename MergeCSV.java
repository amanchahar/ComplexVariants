import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class MergeCSV {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		int NoOfFiles = args.length;
		
		BufferedReader br[] = new BufferedReader[NoOfFiles];
		
		for (int i=0;i<NoOfFiles;i++)
		{
			//System.out.println(args[i]);
			br[i] = new BufferedReader(new FileReader(new File(args[i])));	
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("all_combined.csv")));
		
		
		
		while (true)
		{
			StringBuilder line = new StringBuilder("");
			
			for (int i=0;i<NoOfFiles;i++)
			{
				String csvline = br[i].readLine();
				//System.out.println(csvline);
				if (csvline==null)
				{
					bw.close();
					System.exit(0);
				}
				line.append(csvline);
				if (i!=(NoOfFiles-1))
					line.append(",");
				else
					line.append("\n");
			}
			
			//System.out.print(line);
			
			bw.append(line);	
			//Thread.sleep(1000);
		}
		
	}

}
