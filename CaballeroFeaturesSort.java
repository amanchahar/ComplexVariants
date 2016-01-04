import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CaballeroFeaturesSort {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("CaballeroFeatures.csv")));
		String line = br.readLine();
		line = line.substring(line.indexOf("\t")+1);
		line = line.replaceAll("\t", ",");
		bw.append(line + "\n");
		//System.out.println(br.readLine());
		HashMap<Long,String> prop = new HashMap<Long,String>();
		//System.out.println(br.readLine().indexOf(" "));
		line = br.readLine();
		while (line!=null)
		{
			
			
			Long id = Long.parseLong(line.substring(0,line.indexOf(" ")));
			//System.out.println(id);
			line = line.substring(line.indexOf("\t")+1);
			line = line.replaceAll("\t", ",");
			System.out.println(line);
			prop.put(id, line+"\n");
			line = br.readLine();
			//Thread.sleep(1000);
		}
		 
		Set<Long> keys = prop.keySet();
		Object[] keyArray = keys.toArray();
		List<Long> finalKeys = new ArrayList<Long>();
		for (Object key : keyArray)
		{
			finalKeys.add((Long)key);
		}
			Collections.sort(finalKeys);
			
			for (Long key : finalKeys)
			{
				bw.append(prop.get(key));
			}
			bw.close();
			br.close();
	}
	

}
