package ourLib;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PipeResulsetToCSV {

	String lineDelimiter="\n";
	String fieldDelimiter=";";
	
	
	public String pipe(ResultSet rs) throws SQLException, IOException {
		
		StringBuilder strbuilder= new StringBuilder();
		
		Integer columnsNumber=rs.getMetaData().getColumnCount();
		
		for(Integer i=1; i<=columnsNumber; i++) {
			String columnName=rs.getMetaData().getColumnLabel(i).toUpperCase();
			strbuilder.append(columnName);
			strbuilder.append(fieldDelimiter);
		}
		strbuilder.append(lineDelimiter);
		
		while(rs.next()) {
			//maldito resulset, porque tenes que arrancar a contar desde 1 en lugar de 0???????
			for(Integer i=1; i<=columnsNumber; i++) {
				String data=rs.getObject(i).toString();
				strbuilder.append(data);
				strbuilder.append(fieldDelimiter);
			}
			strbuilder.append(lineDelimiter);
		}
		
		return strbuilder.toString();
		
	}
	
	
	
	
}
