package utilities;


import java.io.IOException;

import org.testng.annotations.DataProvider;



public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path = "./testData/Opencart_loginData.xlsx"; //taking xl file from testData /backword slas will work for window and linux also
		
		ExcelUtility2 xlutil = new ExcelUtility2(path);   //Creating an object for XL file
		
		int totalrows = xlutil.getrowCount("Sheet1");
		int totalcol = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] =new String[totalrows][totalcol];   //created for two dimension array which can store data
		
		for(int i=1; i<=totalrows; i++)  //1  // read the data from xl Storing in array
		{
			for(int j=0; j<totalcol; j++)  //0 // i is row j is col
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);  //1,0
			}
			
			
		}
		return logindata;
		
	}
	//DataProdier2
	
	//DataProvider3
	
	//DataProvider4
	
}
