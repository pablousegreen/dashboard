package mx.open.dashboard.ds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslationMessagesDS {

	private Integer idTranslationMessage;
	
	private Integer idUser;
	
	private String id;

	private String message;
	
	private String translations;

	public TranslationMessagesDS(int idTranslationMessage, int idUser, String id, String message, String translations) {
		this.idTranslationMessage = idTranslationMessage;
		this.idUser = idUser;
		this.id = id;
		this.message = message;
		this.translations = translations;
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

	public String getTranslations() {
		return translations;
	}

	public void setTranslations(String translations) {
		this.translations = translations;
	}

}
