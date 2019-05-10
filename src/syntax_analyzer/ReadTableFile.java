package syntax_analyzer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

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
		slrTable = new HashMap<Integer, HashMap<String, String>>();
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
					for (columnindex = 1; columnindex <= totalColumn; columnindex++) {
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
								continue;
							}
							if(columnindex<19) {
								String nonterminal ="";
								nonterminal = columnIndexToNonTerminal(columnindex);
								curRowHash.put(nonterminal,value);
							}else {
								String terminal ="";
								terminal = columnIndexToTerminal(columnindex);
							}
						}
					}
					this.slrTable.put(rowindex,curRowHash);
				}

			}
		}

	}
	public String columnIndexToTerminal(int column) {
		String terminal = "";
		switch(column) {
		case 19:
			terminal = "CODE";
			break;
		case 20:
			terminal = "VDECL";
			break;
		case 21:
			terminal = "FDECL";
			break;
		case 22:
			terminal = "ARG";
			break;
		case 23:
			terminal = "MOREARG";
			break;
		case 24:
			terminal = "BLOCK";
			break;
		case 25:
			terminal = "STMT";
			break;
		case 26:
			terminal = "RHS";
			break;
		case 27:
			terminal = "EXPR";
			break;
		case 28:
			terminal = "TERM";
			break;
		case 29:
			terminal = "FACTOR";
			break;
		case 30:
			terminal = "CON";
			break;
		case 31:
			terminal = "FCALL";
			break;
		}
		return terminal;
	}
	public String columnIndexToNonTerminal(int column) {
		String nonTerminal = "";
		switch(column) {
		case 1:
			nonTerminal = "vtype";
			break;
		case 2:
			nonTerminal = "num";
			break;
		case 3:
			nonTerminal = "literal";
			break;
		case 4:
			nonTerminal = "id";
			break;
		case 5:
			nonTerminal = "if";
			break;
		case 6:
			nonTerminal = "else";
			break;
		case 7:
			nonTerminal = "while";
			break;
		case 8:
			nonTerminal = "addsub";
			break;
		case 9:
			nonTerminal = "multdiv";
			break;
		case 10:
			nonTerminal = "assign";
			break;
		case 11:
			nonTerminal = "comp";
			break;
		case 12:
			nonTerminal = "semi";
			break;
		case 13:
			nonTerminal = "comma";
			break;
		case 14:
			nonTerminal = "lparen";
			break;
		case 15:
			nonTerminal = "rparen";
			break;
		case 16:
			nonTerminal = "lbrace";
			break;
		case 17:
			nonTerminal = "rbrace";
			break;
		case 18:
			nonTerminal = "ENDMARKER";
			break;
		}
		return nonTerminal;
	}
	public void printSTRtable() {
		for(Integer key : this.slrTable.keySet()) {
			System.out.println(key + "rowindex : ");
			for(String k : this.slrTable.get(key).keySet()) {
				System.out.print("("+k+"," +this.slrTable.get(key).get(k)+")");
			}
			System.out.println("");
		}
	}
}
