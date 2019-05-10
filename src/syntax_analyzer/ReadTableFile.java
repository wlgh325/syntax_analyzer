package syntax_analyzer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadTableFile {
	//SLR-TABLE
	public HashMap<Integer, HashMap<String, String>> slrTable;
	
	private String path;
	private int totalRow;
	private int totalColumn;

	// Constructor
	ReadTableFile() {

		this.path = "./SLRTableFile.xls";
		this.totalRow = 0;
		this.totalColumn = 0;
	}

	public void readExcel() throws IOException {
		FileInputStream fis = new FileInputStream(path);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);

		int rowindex = 0;
		int columnindex = 0;
		int num = 0;

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			HSSFSheet sheet = workbook.getSheetAt(i); // sheet 생성

			// 행의 수
			totalRow = sheet.getPhysicalNumberOfRows();
			for (rowindex = 1; rowindex < totalRow; rowindex++) {
				// 행을 읽는다
				HSSFRow row = sheet.getRow(rowindex);
				if (row != null) {
					// 셀의 수
					totalColumn = row.getPhysicalNumberOfCells();
					HashMap<String,String> curRowHash = new HashMap<String,String>();
					for (columnindex = 0; columnindex <= totalColumn; columnindex++) {
						// 셀값을 읽는다
						HSSFCell cell = sheet.getRow(rowindex).getCell((short) columnindex);
						String value = "";
						// 셀이 빈값일경우를 위한 널체크
						if (cell == null) {
							continue;
						} else {
							// 벨류가 뭔지 받아오기
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_FORMULA:
								value = cell.getCellFormula();
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								value = cell.getNumericCellValue() + "";
								break;
							case HSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue() + "";
								break;
							case HSSFCell.CELL_TYPE_BLANK:
								continue;
							case HSSFCell.CELL_TYPE_ERROR:
								value = cell.getErrorCellValue() + "";
								break;
							}
						}
					}
				}

			}
		}

	}
	public String columnIndexToTerminal(int column) {
		String terminal = "";
		switch(column) {
		case 1:
			terminal = "CODE";
			break;
		case 2:
			terminal = "VDECL";
			break;
		case 3:
			terminal = "FDECL";
			break;
		case 4:
			terminal = "ARG";
			break;
		case 5:
			terminal = "MOREARG";
			break;
		case 6:
			terminal = "BLOCK";
			break;
		case 7:
			terminal = "STMT";
			break;
		case 8:
			terminal = "RHS";
			break;
		case 9:
			terminal = "EXPR";
			break;
		case 10:
			terminal = "TERM";
			break;
		case 11:
			terminal = "FACTOR";
			break;
		case 12:
			terminal = "CON";
			break;
		case 13:
			terminal = "FCALL";
			break;
		}
		return terminal;
	}
	public String columnIndexToNonTerminal(int column) {
		String nonTerminal = "";
		switch(column) {
		case 1:
			nonTerminal = "INT";
			break;
		case 2:
			nonTerminal = "CHAR";
			break;
		case 3:
			nonTerminal = "INTEGER";
			break;
		case 4:
			nonTerminal = "LITERAL";
			break;
		case 5:
			nonTerminal = "IDENTIFIEL";
			break;
		case 6:
			nonTerminal = "IF";
			break;
		case 7:
			nonTerminal = "ELSE";
			break;
		case 8:
			nonTerminal = "WHILE";
			break;
		case 9:
			nonTerminal = "ADD_OPERATION";
			break;
		case 10:
			nonTerminal = "SUB_OPERATION";
			break;
		case 11:
			nonTerminal = "MULTI_OPERATION";
			break;
		case 12:
			nonTerminal = "DIV_OPERATION";
			break;
		case 13:
			nonTerminal = "ASSIGNMENT_OPERATION";
			break;
		case 14:
			nonTerminal = "COMPARSION_OPERATION";
			break;
		case 15:
			nonTerminal = "SEMICOLON";
			break;
		case 16:
			nonTerminal = "COMMA";
			break;
		case 17:
			nonTerminal = "LPAREN";
			break;
		case 18:
			nonTerminal = "RPAREN";
			break;
		case 19:
			nonTerminal = "LBRACKET";
			break;
		case 20:
			nonTerminal = "RBRACKET";
			break;
		case 21:
			nonTerminal = "ENDMARKER";
			break;
		}
		return nonTerminal;
	}
}
