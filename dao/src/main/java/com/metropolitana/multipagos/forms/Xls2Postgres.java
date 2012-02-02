package com.metropolitana.multipagos.forms;

import java.io.File;
import java.io.IOException;
import jxl.BooleanCell;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Xls2Postgres {

	
	public void leerExcel() {
            	
System.out.println("++++++++++++ Dentro ++++++++");

        try {
            //Se abre el fichero Excel
            Workbook workbook = Workbook.getWorkbook(new File("/tmp/multipagos.xls")); 
            //Se obtiene la primera hoja
            Sheet sheet = workbook.getSheet(0);
            
            int filas = sheet.getRows();         
            
            
            //Se leen las columnas
            for(int i=0; i< 22; i++) {
            	//Se leen las filas
            	for(int j=0; j< filas; j++) {
	                //Se obtiene la celda i-esima
	                Cell cell = sheet.getCell(i,j);
	 
	                //Se imprime en pantalla la celda según su tipo
	                if (cell.getColumn() == 0) {
	                    System.out.println("Contrato: " + cell.getContents());
	                }
	                if (cell.getColumn() == 1) {
	                    System.out.println("Suscriptor: " + cell.getContents());
	                }
	                if (cell.getColumn() == 2) {
	                    System.out.println("Nit: " + cell.getContents());
	                }
	                if (cell.getColumn() == 3) {
	                    System.out.println("Direccion: " + cell.getContents());
	                }
	                if (cell.getColumn() == 4) {
	                    System.out.println("Barrio: " + cell.getContents());
	                }
	                if (cell.getColumn() == 5) {
	                    System.out.println("Factura Interna: " + cell.getContents());
	                }
	                if (cell.getColumn() == 6) {
	                    System.out.println("Numero Fiscal: " + cell.getContents());
	                }
	                if (cell.getColumn() == 7) {
	                    System.out.println("Año: " + cell.getContents());
	                }
	                if (cell.getColumn() == 8) {
	                    System.out.println("Mes: " + cell.getContents());
	                }
	                if (cell.getColumn() == 9) {
	                    System.out.println("Saldo pendiente: " + cell.getContents());
	                }
	                if (cell.getColumn() == 10) {
	                    System.out.println("Estado Corte: " + cell.getContents());
	                }
	                if (cell.getColumn() == 11) {
	                    System.out.println("Departamento: " + cell.getContents());
	                }
	                if (cell.getColumn() == 12) {
	                    System.out.println("Localidad: " + cell.getContents());
	                }
	                if (cell.getColumn() == 13) {
	                    System.out.println("Cupon: " + cell.getContents());
	                }
	                if (cell.getColumn() == 14) {
	                    System.out.println("Telefono: " + cell.getContents());
	                }
	                if (cell.getColumn() == 15) {
	                    System.out.println("Descuento: " + cell.getContents());
	                }
	                if (cell.getColumn() == 16) {
	                    System.out.println("Servicio: " + cell.getContents());
	                }
	                if (cell.getColumn() == 17) {
	                    System.out.println("Empleador: " + cell.getContents());
	                }
	                if (cell.getColumn() == 18) {
	                    System.out.println("D Empleador: " + cell.getContents());
	                }
	                if (cell.getColumn() == 19) {
	                    System.out.println("Asignado: " + cell.getContents());
	                }
	                if (cell.getColumn() == 20) {
	                    System.out.println("Cuenta: " + cell.getContents());
	                }
	                if (cell.getColumn() == 21) {
	                    System.out.println("Concepto diferido: " +  cell.getContents());
	                }
	                
	                
	                
	                
	                
	                
	                
	                /**else if (cell.getType() == CellType.LABEL) {
	                    System.out.println("String: " + ((LabelCell)cell).getString());
	                }
	                else if (cell.getType() == CellType.DATE) {
	                    System.out.println("Fecha: " + ((DateCell)cell).getDate());
	                }
	                else if (cell.getType() == null) {
	                    System.out.println("Nulo: " + "NULL");
	                }
	                else if (cell.getType() == CellType.BOOLEAN) {
	                    System.out.println("Boolean: " + ((BooleanCell)cell).getValue());
	                }**/
            	}    
           }
        
        } catch (Exception ex) {
            System.out.println("Error!");
            ex.printStackTrace();
        }
    }
	
	
}