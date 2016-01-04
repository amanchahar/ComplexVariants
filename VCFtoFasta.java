import java.io.*;


public class VCFtoFasta {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filename = args[0];
		//System.out.println(filename);
		//System.out.println(filename.split(".").length);
		String newFile = filename.split("\\.")[0] + ".fa";
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(newFile)));
		
		String line = br.readLine();
		
		while (line!=null)
		{
			if (!line.startsWith("#"))
			{
				//System.out.println(line);
				String[] values= line.split("\t");
				String id = values[1];
				String Gene = values[3];
				String reference = values[4];
				String properties = values[7];
				
				String prefix="";
				String suffix="";

				String[] DiffProp = properties.split(";");
				int index=0;
				for (int j=0;j<DiffProp.length;j++)
				{
					if (DiffProp[j].contains("PX="))
					{
						prefix = DiffProp[j].substring(3);
					}
					if (DiffProp[j].contains("SX="))
					{

						suffix = DiffProp[j].substring(3);
						index=j;
					}
					
				}
				
				
				String prop = "";
				for (int i=index;i<DiffProp.length; i++)
				{
					prop = prop + DiffProp[i] + ";";
				}
				bw.append(">" + id + " | " + prop + reference + "\n");
				bw.append(prefix + Gene + suffix + "\n");
			}
			
			line = br.readLine();
		}
		br.close();
		bw.close();
		
	}

}
