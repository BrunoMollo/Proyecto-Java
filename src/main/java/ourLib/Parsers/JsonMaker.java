package ourLib.Parsers;


import java.util.List;


/**
* <p>Clase que provee metodos para transformar objetos a Strings en formato JSON.</p>
*/
public class JsonMaker {
	
	private StringBuffer data;
	
	 public static <T extends Jsonable> String getJsonArray(List<T> arr) {
		 StringBuffer strArr= new StringBuffer(256);
		 strArr.append("[");
			for(int i=0; i<arr.size(); i++) {
				strArr.append(arr.get(i).toJson());
				if(i<arr.size()-1) {
					strArr.append(", ");
				}
			}
			strArr.append("]");
		return strArr.toString();
	}

	
	
	public JsonMaker() {
		this.data= new StringBuffer(256);
	}
	
	public void set(String atribute,  String value) {
		if(data.length()!=0) {
			data.append(", ");
		}
		data.append("\""+atribute+"\": \""+value+"\"");
	}
	
	public void set(String atribute,  Number value) {
		if(data.length()!=0) {
			data.append(", ");
		}
		data.append("\""+atribute+"\":"+value);
	}
	
	public String getJSONObject(){
		return "{"+data.toString()+"}";
	}
	
	
}
