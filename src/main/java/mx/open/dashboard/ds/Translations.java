package mx.open.dashboard.ds;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Translations {

	@JsonProperty("esMX")
	private String esMX;
	@JsonProperty("esCO")
	private String esCO;
	@JsonProperty("enUS")
	private String enUS;

	public String getEsMX() {
		return esMX;
	}
	public void setEsMX(String esMX) {
		this.esMX = esMX;
	}
	public String getEsCO() {
		return esCO;
	}
	public void setEsCO(String esCO) {
		this.esCO = esCO;
	}
	public String getEnUS() {
		return enUS;
	}
	public void setEnUS(String enUS) {
		this.enUS = enUS;
	}
	
	@Override
	public String toString() {
		return " { \"es_MX\" : " + "\""+esMX+"\"" + ", \"es_CO\" : " + "\""+esCO+"\"" + ", \"en_US\" :" +"\""+enUS +"\"}";
	}

}

