import java.io.*;


public class AlternateAllele {

	
	static int getHammingDist(String a, String b)
	{
		if (a.length()!=b.length())
		{
			System.out.println(a);
			System.out.println(b);
			throw new Error("Unequal Equal length ");
		}
		int count =0;
		for (int i=0;i<a.length();i++)
		{
			if (a.charAt(i)!=b.charAt(i))
				count++;
		}
		
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
		String line = br.readLine();
		int altAlleles = 1;
		int hamDistPre = 0;
		int hamDistSuf = 0;
		int longestAllele = 0;
		int shortestAllele = 0;
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("AlleleProperties.csv")));
		int linecount=1;
		bw.append("Alternate Alleles, Hamming Distance Prefix, Hamming Distance Suffix, Longest Allele, Shortest Allele, Mutation\n");
		while (line!=null)
		{
			//System.out.println(linecount);
			linecount= linecount+1;
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
				String mutation=",";
				String[] DiffProp = properties.split(";");
				for (int j=0;j<DiffProp.length;j++)
				{
					if (DiffProp[j].contains("PX="))
					{
						prefix = DiffProp[j].substring(3);
					}
					if (DiffProp[j].contains("SX="))
					{

						suffix = DiffProp[j].substring(3);
					}
					if (DiffProp[j].contains("TP="))
					{
						mutation = DiffProp[j].substring(3);
					}
				}
				
				
					

				altAlleles=1 + reference.split(",").length;
				String[] Alleles = reference.split(",");
				
				longestAllele = findLongest(Alleles);
				shortestAllele = findShortest(Alleles);
				if (!(mutation.contains("INDEL")))
					System.out.println(mutation);
					
				//System.out.println(prefix);
				//System.out.println(Gene);
				//System.out.println(prefix.length() + "," + Gene.length());
				hamDistPre = getHammingDist(Gene,prefix.substring(prefix.length() - Gene.length()));
				hamDistSuf = getHammingDist(Gene,suffix.substring(0, Gene.length()));
				bw.append(altAlleles + "," + hamDistPre + "," + hamDistSuf + "," + longestAllele + "," + shortestAllele + "," + mutation + "\n");
			}
			line = br.readLine();
		}
		br.close();
		bw.close();
		
	}

	private static int findLongest(String[] alleles) {
		// TODO Auto-generated method stub
		int max=-1;
		for (String allele : alleles)
		{
			if (allele.length()>max)
			{
				max = allele.length();
			}
		}
		
		return max;
	}
	
	private static int findShortest(String[] alleles)
	{
		int min = Integer.MAX_VALUE;
		
		for (String allele : alleles)
		{
			if (allele.length() < min)
			{
				min = allele.length();
			}
		}
		
		return min;
		
	}

}
