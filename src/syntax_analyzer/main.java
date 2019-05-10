package syntax_analyzer;

import java.io.IOException;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadTableFile file = new ReadTableFile();
		try {
			file.readExcel();
			file.printSTRtable();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
