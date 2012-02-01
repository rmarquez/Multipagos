package com.metropolitana.multipagos.forms;

import java.io.*;
import java.util.Date;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*; 

public class Xls2Postgres {

	
	public static void leerExcel() {
            	
System.out.println("++++++++++++ Dentro ++++++++");

        try
        {
            //Se abre el fichero Excel
            Workbook workbook = Workbook.getWorkbook(new File("resources/files/multipagos.xls"));
 
            //Se obtiene la primera hoja
            Sheet sheet = workbook.getSheet(0);
 
            //Se leen las primeras 5 celdas
            for(int i=0; i<5; i++)
            {
                //Se obtiene la celda i-esima
                Cell cell = sheet.getCell(i,0);
 
                //Se imprime en pantalla la celda según su tipo
                if (cell.getType() == CellType.NUMBER)
                {
                    System.out.println("Número: " + ((NumberCell)cell).getValue());
                }
                else if (cell.getType() == CellType.LABEL)
                {
                    System.out.println("String: " + ((LabelCell)cell).getString());
                }
                else if (cell.getType() == CellType.BOOLEAN)
                {
                    System.out.println("Boolean: " + ((BooleanCell)cell).getValue());
                }
                else if (cell.getType() == CellType.DATE)
                {
                    System.out.println("Fecha: " + ((DateCell)cell).getDate());
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error!");
        }
    }
	
	
}