//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import org.apache.poi.hssf.usermodel.*;

//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.*;
import java.util.*;

class ExcelDemo
{

   public static void main(String[] arg)
   {
      String path="inven1.xls";
     try {

        Workbook wb = new HSSFWorkbook(); 
        Sheet xlxsheet = wb.createSheet("Inventory");
        Row xlxrow[]= new Row[1000];
        
         xlxrow[0]= xlxsheet.createRow(0);
         Cell xlxcell[]= new Cell[1000];
        
            xlxcell[0] = xlxrow[0].createCell(0);
            xlxcell[0].setCellValue("Unique Number");
            xlxcell[1] = xlxrow[0].createCell(1);
            xlxcell[1].setCellValue("Name");
            xlxcell[2] = xlxrow[0].createCell(2);
            xlxcell[2].setCellValue("Amount");
            xlxcell[3] = xlxrow[0].createCell(3);
            xlxcell[3].setCellValue("Price");
            
        for(int i=1;i<=3;i++)
        {
           xlxrow[i]=xlxsheet.createRow(i);
           for(int j=0;j<4;j++)
           {
             xlxcell[i]= xlxrow[i].createCell(j);
             xlxcell[i].setCellValue(i);
           
           }
        }
        FileOutputStream fop= new FileOutputStream(path);
        wb.write(fop);
     }
     catch(Exception e)
     {
     
     }
   
   }

}
