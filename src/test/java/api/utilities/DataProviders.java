package api.utilities;

import java.io.IOException;

public class DataProviders {


    public static void main(String[] args) throws IOException {
//        String ans[][]=getAllData();
//        System.out.println(ans[1][1]);

        getAllUserNames();
    }


    public static String[][] getAllData() throws IOException {
        String path=System.getProperty("user.dir")+"/testdata/SampleExcel.xlsx";

        XLUtility xl = new XLUtility(path);

        int rowNum= xl.getRowCount("Sheet1");
        int colNum= xl.getCellCount("Sheet1",1);

        String apidata[][] = new String[rowNum][colNum];

        for(int i=1;i<=rowNum;i++)
        {
            for(int j=0;j<colNum;j++)
            {

                String value=xl.getCellData("Sheet1",i,j);
                System.out.println(value);
                apidata[i-1][j]= xl.getCellData("Sheet1",i,j);
            }
        }

        return apidata;

    }



    public static String[] getAllUserNames() throws IOException {
        String path=System.getProperty("user.dir")+"/testdata/SampleExcel.xlsx";

        XLUtility xl = new XLUtility(path);

        int rowNum= xl.getRowCount("Sheet1");

        String apiUserName[] = new String[rowNum];


        for(int i=1;i<=rowNum;i++)
        {
            apiUserName[i-1]=xl.getCellData("Sheet1",i,0);
            System.out.println(apiUserName[i-1]);
        }

        return apiUserName;





    }


}
