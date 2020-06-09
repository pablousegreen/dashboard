package mx.open.dashboard.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.open.dashboard.ds.TranslationMessagesDS;
import mx.open.dashboard.service.TranslationMessagesBusiness;

@RestController
public class TranslationMessagesController {
	
	@Autowired
	TranslationMessagesBusiness translationMessagesBusiness;
	
	private static final Logger logger = LoggerFactory.getLogger(TranslationMessagesController.class);
	
	@ResponseBody
    @RequestMapping(value = TranslationMessagesRequestPages.TRANSLATION_MESSAGES, method = RequestMethod.POST, produces = "application/json", consumes="application/json") 
	//	public Map<String, TranslationMessagesDS> getTranslations(@RequestBody final List<String> translations) {
	public Map<String, TranslationMessagesDS> getTranslationsBy(@RequestBody final List<TranslationMessagesDS> translations) {
//       return translationMessagesBusiness.getTranslations(translations);
//		return translationMessagesBusiness.getTranslationsTranslationMessagesDS(translations);
		logger.info("Gte By translations : {}", translations);
		return translationMessagesBusiness.getTranslationMessagesDSFromIdList(translations);
    }
	
	@ResponseBody
    @RequestMapping(value = "/translation-messages/{id}", method = RequestMethod.GET, produces = "application/json") 
//	@GetMapping(value = "/translation-messages/{id}")
	public TranslationMessagesDS getTranslation(@PathVariable final String id) {
		logger.info("Gte By Id : {}", id);
		return translationMessagesBusiness.getTranslationById(id);
    }
	
	@ResponseBody
    @RequestMapping(value = "/translation-messages", method = RequestMethod.PUT, produces = "application/json", consumes="application/json") 
	public TranslationMessagesDS updateTranslationMessages(@RequestBody final TranslationMessagesDS translationMessagesDS) {
		logger.info("PUT translationMessage : {}", translationMessagesDS);
		return translationMessagesBusiness.updateTranslationMessages(translationMessagesDS);
    }
	
}
