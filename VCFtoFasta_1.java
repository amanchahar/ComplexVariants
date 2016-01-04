import java.io.*;


public class VCFtoFasta {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File("chr22_prefix_suffix.vcf")));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("chr22_prefix_suffix.fa")));
		
		String line = br.readLine();
		
		while (line!=null)
		{
			if (!line.startsWith("#"))
			{
				System.out.println(line);
				String[] values= line.split("\t");
				String id = values[1];
				String Gene = values[3];
				String reference = values[4];
				String properties = values[7];
				
				String[] DiffProp = properties.split(";");
				String prefix = DiffProp[0].substring(3);
				String suffix = DiffProp[1].substring(3);
				
				String prop = "";
				for (int i=2;i<DiffProp.length; i++)
				{
					prop = prop + DiffProp[3] + ";";
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
