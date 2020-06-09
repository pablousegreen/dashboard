package mx.open.dashboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import mx.open.dashboard.ds.TranslationMessagesDS;
import mx.open.dashboard.ds.Translations;
import mx.open.dashboard.repository.TranslationMessagesRepository;

@Service
public class TranslationMessagesBusiness {
	
	@Autowired
	TranslationMessagesRepository translationMessagesRepository;
	
	private String idsList = "";
	
//	public Map<String, TranslationMessagesDS> getTranslations(final List<String> translations) {
//		if (translations == null) {
//			return new HashMap<>();
//		}
//		
//		translations.stream()
//		.filter(Objects::nonNull)
//		.filter(t -> !t.isEmpty())
//		.map(e -> addString(idsList, e))
//		.collect(Collectors.toList());
//		
//		List<TranslationMessagesDS> translationMessagesDSList = translationMessagesRepository.findByActionsIds(idsList);
//		if (translationMessagesDSList == null) {
//			return new HashMap<>();
//		}
//		idsList = "";
//		return translationMessagesDSList.stream()
//				.filter(Objects::nonNull)
//				.filter(t -> t.getId() != null)
//				.collect(Collectors.toMap(TranslationMessagesDS::getId, t -> t));
//	}
	
	public Map<String, TranslationMessagesDS> getTranslationsTranslationMessagesDS(final List<TranslationMessagesDS> translations) {
		if (translations == null) {
			return new HashMap<>();
		}
		
		translations.stream()
		.filter(e->e.getTranslationsJsonNode().toString() != null)
		.map(e -> addString(idsList, e.getTranslationsJsonNode().toString()))
		.collect(Collectors.toList());
		
		List<TranslationMessagesDS> translationMessagesDSList = translationMessagesRepository.findByActionsIds(idsList);
		if (translationMessagesDSList == null) {
			return new HashMap<>();
		}
		
		return translationMessagesDSList.stream()
				.filter(Objects::nonNull)
				.filter(t -> t.getId() != null)
				.collect(Collectors.toMap(TranslationMessagesDS::getId, t -> t));
	}

	private String addString(String idsList, String elem) {
		if(elem == null) {
			return null;
		}
		if(idsList == null || idsList.length() ==0) {
			this.idsList += "\""+elem+"\"";
		}else {
			this.idsList += ","+("\""+elem+"\"");			
		}
		return this.idsList;
	}
	
	public Map<String, TranslationMessagesDS> getTranslationMessagesDSFromIdList(final List<TranslationMessagesDS> translations) {
		if (translations == null) {
			return new HashMap<>();
		}
		//Validate Non Null and notEmpty
		List<String> idsListSerach = translations.stream()
				.filter(Objects::nonNull)
				.filter(e->!e.getId().isEmpty())
				.map(e-> e.getId())
				.collect(Collectors.toList());
		
		//Get  Data from  Repository
		List<TranslationMessagesDS> translationMessagesDSList = translationMessagesRepository.getTranslationMessagesDSFromIdList(idsListSerach);
		if (translationMessagesDSList == null) {
			return new HashMap<>();
		}
		
		//Translate List to Map
		return translationMessagesDSList.stream()
				.filter(Objects::nonNull)
				.filter(t -> t.getId() != null)
				.collect(Collectors.toMap(TranslationMessagesDS::getId, t -> t));
	}
	
	public TranslationMessagesDS getTranslationById(final String id) {
		if (id == null) {
			return new TranslationMessagesDS();
		}
		// Get Data from Repository
		return translationMessagesRepository.getTranslationMessagesDSById(id).orElse(new TranslationMessagesDS());
	}
	
	public TranslationMessagesDS updateTranslationMessages(final TranslationMessagesDS translationMessagesDS) {
		if (translationMessagesDS == null) {
			return new TranslationMessagesDS();
		}
		setTransactions(translationMessagesDS);
		// Get Data from Repository
		 if(translationMessagesRepository.updateTranslationMessages(translationMessagesDS) ==1) {
			 return translationMessagesDS;
		 }
		 return new TranslationMessagesDS();
	}
	
	private void setTransactions(TranslationMessagesDS translationMessagesDS) {
		Translations translationsDTO = new Translations();
		JsonNode esMX = translationMessagesDS.getTranslationsJsonNode().get("es_MX");
		JsonNode esCO = translationMessagesDS.getTranslationsJsonNode().get("es_CO");
		JsonNode enUS = translationMessagesDS.getTranslationsJsonNode().get("en_US");
		translationsDTO.setEsMX(esMX == null?"{}":esMX.asText());
		translationsDTO.setEsCO(esCO == null?"{}":esCO.asText());
		translationsDTO.setEnUS(enUS == null?"{}":enUS.asText());
		translationMessagesDS.setTranslationsDTO(translationsDTO);
	}
}
