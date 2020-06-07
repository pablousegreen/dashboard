package mx.open.dashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@ResponseBody
    @RequestMapping(value = TranslationMessagesRequestPages.TRANSLATION_MESSAGES, method = RequestMethod.POST, produces = "application/json", consumes="application/json") 
//	@PostMapping(TranslationMessagesRequestPages.TRANSLATION_MESSAGES)
//	@PostMapping("/elRey")
	//public Map<String, TranslationMessagesDS> getTranslations(@RequestBody final List<String> translations) {
	public Map<String, TranslationMessagesDS> getTranslations(@RequestBody final List<TranslationMessagesDS> translations) {
//       return translationMessagesBusiness.getTranslations(translations);
//		return translationMessagesBusiness.getTranslationsTranslationMessagesDS(translations);
		return translationMessagesBusiness.getTranslationMessagesDSFromIdList(translations);
    }
	
}
