package ourLib;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PipeResulsetToCSV {

	String lineDelimiter="\n";
	String fieldDelimiter=";";
	
	
	public void pipe(ResultSet rs, BufferedWriter file) throws SQLException, IOException {
		
		Integer columnsNumber=rs.getMetaData().getColumnCount();
		
		for(Integer i=1; i<=columnsNumber; i++) {
			String columnName=rs.getMetaData().getColumnLabel(i).toUpperCase();
			file.append(columnName);
			file.append(fieldDelimiter);
		}
		file.append(lineDelimiter);
		
		while(rs.next()) {
			//maldito resulset, porque tenes que arrancar a contar desde 1 en lugar de 0???????
			for(Integer i=1; i<=columnsNumber; i++) {
				String data=rs.getObject(i).toString();
				file.append(data);
				file.append(fieldDelimiter);
			}
			file.append(lineDelimiter);
		}
		
	}
	
	
	
	
}
