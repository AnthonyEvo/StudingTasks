package tasks;

import java.io.*;

public class BiologyCalculation {
	BiologyCalculation()
	{
		File input = new File("bcIn.txt");
		 File output = new File("output.txt");
		 try(FileInputStream iS = new FileInputStream(input); FileOutputStream oS = new FileOutputStream(output))
		 {
			 String basicGene = "";
			 String probableGene = "";
			 int i = 0;
			 while((i = iS.read()) != 10) basicGene += (char) i;
			 while((i = iS.read()) != -1) probableGene += (char) i;
			 output.createNewFile();
			 oS.write(check(basicGene, probableGene).getBytes());
		 }
		 catch(IOException Ex)
		 {
			 Ex.printStackTrace();
		 }
	}
	
	String check(String basicGene, String probableGene)
	{
		int count = 0, y = 0;
		int accLevel = 0;
		char arr[] = basicGene.toCharArray();
		
		for(char x : arr)
		{
			while(y + count < probableGene.length())
			{
				if(x == (char) probableGene.charAt(y + count))
				{
					accLevel++;
					break;
				}
				y++;
			}
			count++;
		}
		
		if(accLevel == basicGene.length() - 1)return "Yes";
		else return "No";
	}
}
