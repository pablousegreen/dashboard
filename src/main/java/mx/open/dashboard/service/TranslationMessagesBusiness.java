package mx.open.dashboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.open.dashboard.ds.TranslationMessagesDS;
import mx.open.dashboard.repository.TranslationMessagesRepository;

@Service
public class TranslationMessagesBusiness {
//	@Autowired
//	TranslationMessagesDAO translationMessagesDAO;
	
	@Autowired
	TranslationMessagesRepository translationMessagesRepository;
	
	private String idsList = "";
	
	public Map<String, TranslationMessagesDS> getTranslations(final List<String> translations) {
		if (translations == null) {
			return new HashMap<>();
		}
		
		translations.stream()
		.filter(Objects::nonNull)
		.filter(t -> !t.isEmpty())
		.map(e -> addString(idsList, e))
		.collect(Collectors.toList());
		
		List<TranslationMessagesDS> translationMessagesDSList = translationMessagesRepository.findByActionsIds(idsList);
		if (translationMessagesDSList == null) {
			return new HashMap<>();
		}
		idsList = "";
		return translationMessagesDSList.stream()
				.filter(Objects::nonNull)
				.filter(t -> t.getId() != null)
				.collect(Collectors.toMap(TranslationMessagesDS::getId, t -> t));
	}
	
	public Map<String, TranslationMessagesDS> getTranslationsTranslationMessagesDS(final List<TranslationMessagesDS> translations) {
		if (translations == null) {
			return new HashMap<>();
		}
		
		translations.stream()
		.filter(Objects::nonNull)
		.filter(t -> !t.getTranslations().isEmpty())
		.map(e -> addString(idsList, e.getTranslations()))
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
		
		List<String> idsListSerach = translations.stream()
		.filter(Objects::nonNull)
		.filter(t -> !t.getTranslations().isEmpty())
		.map(TranslationMessagesDS::getTranslations)
		.collect(Collectors.toList());
		
		List<TranslationMessagesDS> translationMessagesDSList = translationMessagesRepository.getTranslationMessagesDSFromIdList(idsListSerach);
		if (translationMessagesDSList == null) {
			return new HashMap<>();
		}
		return translationMessagesDSList.stream()
				.filter(Objects::nonNull)
				.filter(t -> t.getId() != null)
				.collect(Collectors.toMap(TranslationMessagesDS::getId, t -> t));
	}
}
