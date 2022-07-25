package regexProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class excelproject {
	ArrayList<String> exceldata = new ArrayList<>();
	InputStream excelIs = getClass().getClassLoader().getResourceAsStream("BL Publisher and Title year Range.xlsx");

	public ArrayList<String> getExcelData() throws Exception
	{
		try
		{
			XSSFWorkbook excelworkbook = new XSSFWorkbook(excelIs);
			XSSFSheet sheet = excelworkbook.getSheet("Title list");
			String data="";
			
			for(int r=sheet.getFirstRowNum();r<=sheet.getLastRowNum(); r++)
			{
				
				XSSFRow row = sheet.getRow(r);
				for(int c=row.getFirstCellNum(); c<row.getLastCellNum(); c++)
				{
					XSSFCell cell = row.getCell(c);
					
					switch(cell.getCellType())
					{
					case STRING:
							data += cell.getStringCellValue();
						break;
					case NUMERIC:
						data += cell.getNumericCellValue();
						break;
					case BLANK: data += "N/A"; break;
					case ERROR: break;
					default:data += "N/A";
						break;
					}data+="|";
				}
				exceldata.add(data);
				data="";
			}
			
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}

		return exceldata;
	}
	
	/*
	public static void main(String[] arg)
	{
		excelproject proj=new excelproject();
		
		ArrayList<String> exdata = proj.getExcelData();
		
		for(String s:exdata)
		{
			if(s.contains("Surgery Research and Practice"))
				System.out.println(s);
		}
		
	}*/
	/*
	 * for(String s:resultAL)
				{
					++foundRecords;
					//System.out.println(s);
					splitText = s.split("\\|");
					jt.add(splitText[2]);
					issn.add(splitText[0]);
					resulttext.appendText("JT: " + splitText[2] + "\n" +s+"\n\n");
				}
	 */
	public void searchInList(String title) throws Exception
	{
		excelproject proj=new excelproject();
		ArrayList<String> exdata = proj.getExcelData();
		
		for(String s:exdata)
		{
			System.out.println(s);
		}
	}
	
	/*
	public static void main(String[] arg) throws Exception
	{
		FileInputStream fis=new FileInputStream(new File("./data/BL Publisher and Title year Range.xlsx"));
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet("Title List");
		Iterator rows=sheet.iterator();
		
		while(rows.hasNext())
		{
			XSSFRow row=(XSSFRow) rows.next();
			Iterator celliterator=row.cellIterator();
			
			while(celliterator.hasNext())
			{
				XSSFCell cell=(XSSFCell) celliterator.next();
				
				switch(cell.getCellType())
				{
				case STRING: System.out.print(cell.getStringCellValue()); break;
				case NUMERIC: System.out.print(cell.getNumericCellValue()); break;
				case BOOLEAN: System.out.print(cell.getBooleanCellValue()); break;
				}
				
				System.out.print("|");
			}System.out.println("");
		}
		
	}*/
	
	
	
	//Writing into Excel
	public void maiin(String[] arg) throws Exception
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Emp Info"); 

		/*
		Object empdata[][]= {
								{"EMP ID", "Name", "Dept"},
								{101, "James", "Engineer"},
								{102, "Smith", "Production"},
								{103, "Ben", "Management"},
								{104, "Nina", "HR"},
								{105, "Cody", "Administration"}
				
							};
		*/

	ArrayList<Object[]> empdata = new ArrayList<>();
	empdata.add(new Object[]{"EMP ID", "Name", "Dept"});
	empdata.add(new Object[]{101, "James", "Engineer"});
	empdata.add(new Object[]{102, "Smith", "Production"});
	empdata.add(new Object[]{103, "Ben", "Management"});
	empdata.add(new Object[]{104, "Nina", "HR"});
	empdata.add(new Object[]{105, "Cody", "Administration"});
		
	/*	
		int rownumbers = empdata.length;
		int colnumber = empdata[0].length;
		
		for(int r=0; r<rownumbers; r++)
		{
			
			XSSFRow row=sheet.createRow(r);
			for(int c=0;c<colnumber;c++)
			{
				XSSFCell cell=row.createCell(c);
				
				if(empdata[r][c] instanceof String)
					cell.setCellValue((String)empdata[r][c]);
				if(empdata[r][c] instanceof Number)
					cell.setCellValue((Integer)empdata[r][c]);
				if(empdata[r][c] instanceof Boolean)
					cell.setCellValue((boolean)empdata[r][c]);
			}
		}
		*/
		///////For...each...loop///////
		
		int rowcount=0;
		for(Object row[]:empdata)
		{
			XSSFRow rrow =sheet.createRow(rowcount++);
		
			int column=0;
			for(Object columns:row)
			{
				XSSFCell cell=rrow.createCell(column++);
				Object value = columns;
				
				if(value instanceof String)
					cell.setCellValue((String)value);
				if(value instanceof Integer)
					cell.setCellValue((Integer)value);
				if(value instanceof Boolean)
					cell.setCellValue((Boolean)value);
			}
			
		}
		
		String filename = ".\\data\\empdata.xlsx";
		FileOutputStream fos = new FileOutputStream(filename);
		workbook.write(fos);
		
		fos.close();
		System.out.println("Operation complete");
		
	}
	
	
	
	public ArrayList<String> search(ArrayList<String> searchtexts) throws Exception
	{
		
		excelproject proj = new excelproject();
		String filename = "./data/BL Publisher and Title year Range.xlsx";
		InputStream is = getClass().getClassLoader().getResourceAsStream("BL Publisher and Title year Range.xlsx");
		ArrayList<XSSFRow> filteredrow = new ArrayList<>();
		
		ArrayList<String> resultAL = new ArrayList<>();
		
		FileInputStream fis;
		//fis = new FileInputStream(new File(filename));
		//XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		XSSFSheet sheet = workbook.getSheet("Title list");
		
		//filteredrow = proj.searchTitle("Maternal Fetal Medicine", sheet);
		for(String s:searchtexts)
		{
		filteredrow = proj.searchTitle(s.trim(), sheet);
		
		//proj.searchTitle("Management & Sustainability: An Arab Review", sheet);
		
		for(XSSFRow r: filteredrow)
		{
			String text="";
			
			for(int c=r.getFirstCellNum(); c<r.getLastCellNum(); c++)
			{
				XSSFCell cell = r.getCell(c);
				switch(cell.getCellType())
				{
				case STRING:
					//System.out.print(cell.getStringCellValue());
					text +=cell.getStringCellValue();
					break;
				case NUMERIC:
					//System.out.print(cell.getNumericCellValue());
					text +=cell.getNumericCellValue();
					break;
				case BLANK: text +="N/A"; break;
				case ERROR: break;
				default:
					break;
				}//System.out.print("|");
				text +="|";
			}//System.out.println(""); 
			resultAL.add(text);
		}
		
		//fis.close();
		is.close();
		}
		
		return resultAL;
		
	}
	
	public ArrayList<XSSFRow> searchTitle(String searchtitle, XSSFSheet sheet) throws Exception
	{
		ArrayList<XSSFRow> filteredrow = new ArrayList<>();
		
		for(int r=sheet.getFirstRowNum();r<=sheet.getLastRowNum(); r++)
		{
			
			XSSFRow row = sheet.getRow(r);
			for(int c=row.getFirstCellNum(); c<row.getLastCellNum(); c++)
			{
				XSSFCell cell = row.getCell(c);
				
				switch(cell.getCellType())
				{
				case STRING:
					if(searchtitle.equals(cell.getStringCellValue()))
						{
						filteredrow.add(row);
						//System.out.print(cell.getStringCellValue());
						}
					break;
				case NUMERIC:
					//filteredrow.add(row);
					//System.out.print(cell.getNumericCellValue());
					break;
				case BLANK: break;
				case ERROR: break;
				default:
					//System.out.print(cell.getStringCellValue());
					break;
				}//System.out.print("|");
			}
			
		}
		//System.out.println("Search complete..");
		//System.out.println("rows \n" + filteredrow);
		
		/*
		for(XSSFRow r: filteredrow)
		{
			for(int c=r.getFirstCellNum(); c<r.getLastCellNum(); c++)
			{
				XSSFCell cell = r.getCell(c);
				switch(cell.getCellType())
				{
				case STRING:
					System.out.print(cell.getStringCellValue());					
					break;
				case NUMERIC:
					System.out.print(cell.getNumericCellValue());
					break;
				case BLANK: break;
				case ERROR: break;
				default:
					break;
				}System.out.print("|");
				
			}System.out.println("");
				
		}*/
		return filteredrow;
	}
}
