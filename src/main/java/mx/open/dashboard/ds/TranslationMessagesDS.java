package mx.open.dashboard.ds;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslationMessagesDS {
	
	@JsonProperty("idTranslationMessage")
	private Integer idTranslationMessage;
	
	@JsonProperty("idUser")
	private Integer idUser;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("translations")
	private JsonNode translationsJsonNode;
	
	@JsonProperty("translationsDTO")
	private Translations translationsDTO;
	
	public TranslationMessagesDS() {

	}

	public TranslationMessagesDS(int idTranslationMessage, int idUser, String id, String message, JsonNode translationsJsonNode) {
		this.idTranslationMessage = idTranslationMessage;
		this.idUser = idUser;
		this.id = id;
		this.message = message;
		this.translationsJsonNode = translationsJsonNode;
	}

	public Integer getIdTranslationMessage() {
		return idTranslationMessage;
	}

	public void setIdTranslationMessage(Integer idTranslationMessage) {
		this.idTranslationMessage = idTranslationMessage;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JsonNode getTranslationsJsonNode() {
		return translationsJsonNode;
	}

	public void setTranslationsJsonNode(JsonNode translationsJsonNode) {
		this.translationsJsonNode = translationsJsonNode;
	}
	
	public Translations getTranslationsDTO() {
		return translationsDTO;
	}


	public void setTranslationsDTO(Translations translationsDTO) {
		this.translationsDTO = translationsDTO;
	}

}
