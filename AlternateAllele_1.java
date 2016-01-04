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

		BufferedReader br = new BufferedReader(new FileReader(new File("chr22_prefix_suffix.vcf")));
		String line = br.readLine();
		int altAlleles = 1;
		int hamDistPre = 0;
		int hamDistSuf = 0;
		int longestAllele = 0;
		int shortestAllele = 0;
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("AlleleProperties.csv")));
		int linecount=1;
		while (line!=null)
		{
			System.out.println(linecount);
			linecount= linecount+1;
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
				altAlleles=1 + reference.split(",").length;
				String[] Alleles = reference.split(",");
				
				longestAllele = findLongest(Alleles);
				shortestAllele = findShortest(Alleles);
				hamDistPre = getHammingDist(Gene,prefix.substring(prefix.length() - Gene.length()));
				hamDistSuf = getHammingDist(Gene,suffix.substring(0, Gene.length()));
				bw.append(id +"," + altAlleles + "," + hamDistPre + "," + hamDistSuf + "," + longestAllele + "," + shortestAllele +"\n");
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
