package reusableActions;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;


@SuppressWarnings("all")
public class ExcelReader {

    public String path;
    public InputStream fis = null;
    public FileOutputStream fileOut = null;
    public FileWriter fileOut1 = null;

    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    private Column col = null;
    private XSSFCell cell = null;
    private String sheetName;

    public ExcelReader(String path, String shtName) {

        this.path = path;
        try {
            fis = getInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            sheetName = shtName;
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ExcelReader(String path) {

        this.path = path;
        try {
            fis = getInputStream(path);
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // returns the row count in a sheet

    /**
     * @param sheetName
     * @return
     */
    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);// (arg0)getSheetIndex
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheetAt(index);
            int number = sheet.getLastRowNum() + 1;
            return number;
        }

    }

    /*
     * 20000
     *
     * 15000
     */
    // returns the data from a cell
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num = -1;
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                // System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim()
                        .equals(colName.trim()))
                    col_Num = i;
                //row.removeCell(row.getCell(i));
                
            }
            if (col_Num == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(col_Num);

            if (cell == null)
                return "";
         // System.out.println(cell.getCellType());
            if (cell.getCellType() ==  CellType.STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == CellType.NUMERIC
                    || cell.getCellType() == CellType.FORMULA) {
                String cellText = String.valueOf(cell.getNumericCellValue());
              if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR)))
                            .substring(2);
                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/"
                            + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

                    // System.out.println(cellText);

                }

                return cellText;
            } else if (cell.getCellType() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue()); 
        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colName
                    + " does not exist in xls";
        }
    }
    
    

    public String getCellValue(String rowName, String colName) {
        String cellText = null;
        try {
            int index = workbook.getSheetIndex(sheetName);
            int rowNumber = 0;
            int colNumber = 0;
            boolean flag = false;
            if (index == -1)
                return "";
            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                if (row.getCell(j).toString().equalsIgnoreCase(colName)) {
                    colNumber = j;
                    break;
                }
            }
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                if (row.getCell(0).toString().equalsIgnoreCase(rowName)) {
                    rowNumber = i;
                    break;
                }
            }
            row = sheet.getRow(rowNumber);
            if (row == null)
                return "";
            cell = row.getCell(colNumber);
            if (cell == null)
                return "";

            if (cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == CellType.NUMERIC
                    || cell.getCellType() == CellType.FORMULA) {

                cellText = String.valueOf((long) cell.getNumericCellValue());


                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR)))
                            .substring(2);
                    cellText = cal.get(Calendar.MONTH) + 1 + "/"
                            + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;

                    // System.out.println(cellText);

                }

                return cellText;
            } else if (cell.getCellType() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellText;
    }

    // returns the data from a cell
    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);

            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(colNum-1);
            if (cell == null)
                return "";

            if (cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == CellType.NUMERIC
                    || cell.getCellType() == CellType.FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue())
                        .replaceFirst(".0", "");

                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR)))
                            .substring(2);
                    cellText = cal.get(Calendar.MONTH) + 1 + "/"
                            + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;

                    // System.out.println(cellText);

                }

                return cellText;
            } else if (cell.getCellType() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colNum
                    + " does not exist  in xls";
        }
    }

    // returns true if data is set successfully else false
    public boolean setCellData(String sheetName, String colName, int rowNum,
                               String data) {
    	
        try {
//            fis = new FileInputStream(path);
//            workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;

            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                // System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equals(colName))
                    colNum = i;
            }
            if (colNum == -1)
                return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            // cell style
            // CellStyle cs = workbook.createCellStyle();
            // cs.setWrapText(true);
            // cell.setCellStyle(cs);
            cell.setCellValue(data);

            fileOut = new FileOutputStream(new File(path));

            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if data is set successfully else false
    public boolean setCellData(String sheetName, String colName, int rowNum,
                               String data, String url) {
        // System.out.println("setCellData setCellData******************");
        try {
            //fis = getInputStream(path);
            //workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;

            sheet = workbook.getSheetAt(index);
            sheet.getPhysicalNumberOfRows();
            // System.out.println("A");
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                // System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim()
                        .equalsIgnoreCase(colName))
                    colNum = i;
            }

            if (colNum == -1)
                return false;
            sheet.autoSizeColumn(colNum); // ashish
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            cell.setCellValue(data);
            XSSFCreationHelper createHelper = workbook.getCreationHelper();

            // cell style for hyperlinks
            // by default hypelrinks are blue and underlined
            CellStyle hlink_style = workbook.createCellStyle();
            XSSFFont hlink_font = workbook.createFont();
            hlink_font.setUnderline(XSSFFont.U_SINGLE);
            hlink_font.setColor(IndexedColors.BLUE.getIndex());
            hlink_style.setFont(hlink_font);
            // hlink_style.setWrapText(true);

       /*     XSSFHyperlink link = createHelper
                    .createHyperlink(XSSFHyperlink.LINK_FILE);
            link.setAddress(url);
            cell.setHyperlink(link);
            cell.setCellStyle(hlink_style); */
            
           
         
            fis.close();

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if sheet is created successfully else false
    public boolean addSheet(String sheetname) {

        FileOutputStream fileOut;
        try {
            workbook.createSheet(sheetname);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if sheet is removed successfully else false if sheet does
    // not exist
    public boolean removeSheet(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return false;

        FileOutputStream fileOut;
        try {
            workbook.removeSheetAt(index);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if column is created successfully
    public boolean addColumn(String sheetName, String colName) {
        // System.out.println("**************addColumn*********************");

        try {
            fis = getInputStream(path);
            workbook = new XSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

           /* XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(XSSFColor.toXSSFColor(GREY).index);
           // style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
            style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  */
            /*
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(new XSSFColor(new java.awt.Color(128, 0, 128)));
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);*/
            
            
            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(0);
            if (row == null)
                row = sheet.createRow(0);

            // cell = row.getCell();
            // if (cell == null)
            // System.out.println(row.getLastCellNum());
            if (row.getLastCellNum() == -1)
                cell = row.createCell(0);
            else
                cell = row.createCell(row.getLastCellNum());

            cell.setCellValue(colName);
           // cell.setCellStyle(style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    // removes a column and all the contents
    public boolean removeColumn(String sheetName, int colNum) {
        try {
            if (!isSheetExist(sheetName))
                return false;
            fis = getInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            XSSFCellStyle style = workbook.createCellStyle();
            /*style.setFillForegroundColor(XSSFColor.GREY_40_PERCENT.index);
            XSSFCreationHelper createHelper = workbook.getCreationHelper();
            style.setFillPattern(XSSFCellStyle.NO_FILL);*/

            for (int i = 0; i < getRowCount(sheetName); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    cell = row.getCell(colNum);
                    if (cell != null) {
                        cell.setCellStyle(style);
                        row.removeCell(cell);
                    }
                }
            }
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    // find whether sheets exists
    public boolean isSheetExist(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            index = workbook.getSheetIndex(sheetName.toUpperCase());
            if (index == -1)
                return false;
            else
                return true;
        } else
            return true;
    }

    // returns number of columns in a sheet
    public int getColumnCount(String sheetName) {
        // check if sheet exists
        if (!isSheetExist(sheetName))
            return -1;
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);

        if (row == null)
            return -1;

        return row.getLastCellNum();
    }

    // String sheetName, String testCaseName,String keyword ,String URL,String
    // message
    public boolean addHyperLink(String sheetName, String screenShotColName,
                                String testCaseName, int index, String url, String message) {
        // System.out.println("ADDING addHyperLink******************");

        url = url.replace('\\', '/');
        if (!isSheetExist(sheetName))
            return false;

        sheet = workbook.getSheet(sheetName);

        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
                // System.out.println("**caught "+(i+index));
                setCellData(sheetName, screenShotColName, i + index, message,
                        url);
                break;
            }
        }
        return true;
    }

    public int getCellRowNum(String sheetName, String colName, String cellValue) {
        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
                return i;
            }
        }
        return -1;

    }

    public String getRowDataValue(String sheetName, String colName) {

        HashMap map = new HashMap();
        sheet = workbook.getSheet(sheetName);
        XSSFCell cell;

        int numOfCols = 0;

        Iterator<Row> rows = sheet.rowIterator();

        row = (XSSFRow) rows.next();

        numOfCols = row.getLastCellNum();
        String[] headers = new String[numOfCols];
        int iCountColumns = 0;

        while (iCountColumns < numOfCols) {
            headers[iCountColumns] = row.getCell(iCountColumns).getStringCellValue();
            iCountColumns++;
        }

        while (rows.hasNext()) {
            int j = 0;
            row = (XSSFRow) rows.next();
            try {
                while (j < iCountColumns) {

                    cell = (XSSFCell) row.getCell(j);

                    if (headers[j].equalsIgnoreCase(colName)) {
                        if (cell.getCellType() == CellType.STRING) {
                            map.put(headers[j], cell.getStringCellValue());
                            break;
                        } else if (cell.getCellType() == CellType.NUMERIC) {
                            map.put(headers[j], Double.toString(cell.getNumericCellValue()));
                            break;
                        } else {
                            map.put(headers[j], "");
                            break;
                        }
                    }
                    j = j + 1;
                }
            } catch (Exception e) {
            }
            j = 0;
        }

        String rowValue = map.get(colName).toString();

        if (rowValue == "") {
            return null;
        }

        return rowValue;
    }

    private InputStream getInputStream(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                return new FileInputStream(path);
            } catch (FileNotFoundException e) {
            }
        }
        return getClass().getClassLoader().getResourceAsStream(path);
    }
    
    /**
     * Removes previously stored JOB ID's from TestData Sheet
     * @param sheetName
     * @return
     * @throws Throwable
     */
    public String[] remove_JobIDs(String sheetName) throws Throwable {
    	String a[] = new String[10];
    	try {    
    		int b = 0,c=0;
    		int index = workbook.getSheetIndex(sheetName);
    		int col_Num = -1;
    		if (index == -1)   
    			return null;

    		sheet = workbook.getSheetAt(index);
    		row = sheet.getRow(0);
    		// Get column_Names having JOB_ID
    		for (int i = 0; i < row.getLastCellNum(); i++) {
    			// System.out.println(row.getCell(i).getStringCellValue().trim());
    			if (row.getCell(i).getStringCellValue().trim().contains("JobID")){
    				a[b]=row.getCell(i).getStringCellValue().trim();
        			b++;
    			}    				
    		}   
    		// Get column_Number using the column_Name stored in a[]
    		for (int i = 0; i < row.getLastCellNum(); i++) {
    			// System.out.println(row.getCell(i).getStringCellValue().trim());
    			row = sheet.getRow(0);
    			if (row.getCell(i).getStringCellValue().trim().equals(a[c])){
    				col_Num = i;
    				c++;
    				// iterate till the end of the total rows using the column_Number
    				for (int j = 1; j < getRowCount(sheetName); j++) {
    					row = sheet.getRow(j);
    					cell = row.getCell(col_Num);
    					/*if (cell.getCellType()!= Cell.CELL_TYPE_BLANK) {
							row.removeCell(cell);
						}*/					
    					
    					try {
    						if(!cell.getStringCellValue().trim().equals(null)){
        						if (!cell.getStringCellValue().trim().isEmpty()) {
        							//System.out.println(cell.getStringCellValue().trim());
        							row.removeCell(cell);
        							fileOut = new FileOutputStream(new File(path));
        							workbook.write(fileOut);
        							fileOut.close();
        							//cell.setCellValue("");
        						}
        					}
						} catch (Exception e) {
							
						}
					}
    			}    				
    		}   
    		
    		return a;
    	}catch (Exception e) {
    		e.printStackTrace(); 
    		return null;
    	}finally{
    		
    	}

    }
    
    
   
    
    public void readTemplateRunMode(String sheetName) {
        try {
        for(int i=1;i<=getRowCount(sheetName);i++) {
         if(getCellData(sheetName, 2, 1).equals("PublisherTemplate")) {
          String prodName=getCellData(sheetName, 1, i+1);
          String tempName=getCellData(sheetName, 2, 1);
          String tempRunmode=getCellData(sheetName, 2, i+1);
          if(tempRunmode.equals("Y")) {
        	  System.out.println(prodName);
           System.out.println("Upload Publisger Template");
          }
         }
         if(getCellData(sheetName, 4, 1).equals("PhotoshopTemplate")) {
          String prodName=getCellData(sheetName, 1, i+1);
          String tempName=getCellData(sheetName, 4, 1);
          String tempRunmode=getCellData(sheetName, 4, i+1);
          if(tempRunmode.equals("Y")) {
        	  System.out.println(prodName);
           System.out.println("Upload Photoshop Template");
          }
         }
         if(getCellData(sheetName, 6, 1).equals("CorelDrawTemplate"))  {
          String prodName=getCellData(sheetName, 1, i+1);
          String tempName=getCellData(sheetName, 6, 1);
          String tempRunmode=getCellData(sheetName, 6, i+1);
          if(tempRunmode.equals("Y")) {
        	  System.out.println(prodName);
           System.out.println("Upload CorelDraw Template");
          } 
         }
         if(getCellData(sheetName, 8, 1).equals("IllustratorTemplate"))  {
          String prodName=getCellData(sheetName, 1, i+1);
          String tempName=getCellData(sheetName, 8, 1);
          String tempRunmode=getCellData(sheetName, 8, i+1);
          if(tempRunmode.equals("Y")) {
        	  System.out.println(prodName);
           System.out.println("Upload Illustrator Template");
          } 
         } 
         if(getCellData(sheetName, 10, 1).equals("QuarkXPressTemplate"))  {
          String prodName=getCellData(sheetName, 1, i+1);
          String tempName=getCellData(sheetName, 10, 1);
          String tempRunmode=getCellData(sheetName, 10, i+1);
          if(tempRunmode.equals("Y")) {
        	  System.out.println(prodName);
           System.out.println("Upload QuarkXPress Template");
          } 
         }
         if(getCellData(sheetName, 12, 1).equals("FreehandTemplate"))  {
          String prodName=getCellData(sheetName, 1, i+1);
          String tempName=getCellData(sheetName, 12, 1);
          String tempRunmode=getCellData(sheetName, 12, i+1);
          if(tempRunmode.equals("Y")) {
        	  System.out.println(prodName);
           System.out.println("Upload FreeHand Template");
          } 
         }
         if(getCellData(sheetName, 14, 1).equals("InDesignTemplate"))  {
          String prodName=getCellData(sheetName, 1, i+1);
          String tempName=getCellData(sheetName, 14, 1);
          String tempRunmode=getCellData(sheetName, 14, i+1);
          if(tempRunmode.equals("Y")) {
        	  System.out.println(prodName);
           System.out.println("Upload InDesign Template");
          } 
            }
           }
         } catch(Exception e) {
         e.printStackTrace();
        }
       }

}

