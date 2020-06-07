package mx.open.dashboard.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity(name="TRANSLATION_MESSAGES")
@Table(name="TRANSLATION_MESSAGES")
public class TranslationMessages implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_TRANSLATION_MESSAGE", nullable = false)
	private Integer idTranslationMessage;
	
	@Column(name = "ID_USER", nullable = true)
	private Integer idUser;
	
	@Column(name = "ID", nullable = true)
	private String id;
	
	@Column(name = "MESSAGE", nullable = true)
	private String message;
	
	@Column(name = "TRANSLATIONS", nullable = true)
	private String translations;

}
