package mx.open.dashboard.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.open.dashboard.ds.TranslationMessagesDS;

@Repository
public class TranslationMessagesRepository{
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
    JdbcTemplate jdbcTemplate;

	private static final Logger logger = LoggerFactory.getLogger(TranslationMessagesRepository.class);
	
	private static final String TRANSLATIONS = "TRANSLATIONS";
	
	public List<TranslationMessagesDS> findByActionsIds(String ids) {
		if(ids == null) {
			return new ArrayList<>();
		}
		
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ids", ids );
        String query = "SELECT * " + 
                "FROM TRANSLATION_MESSAGES " +
                " WHERE TRANSLATIONS IN (:ids) ";
        return namedParameterJdbcTemplate.query(
        		query,
                mapSqlParameterSource,
                (rs, rowNum) ->
                        new TranslationMessagesDS(
//                                rs.getInt("ID_TRANSLATION_MESSAGE"),
//                                rs.getInt("ID_USER"),
//                                rs.getString("ID"),
//                                rs.getString("MESSAGE"),
//                                rs.getString("TRANSLATIONS")
                        )
        );
    }
	
	public List<TranslationMessagesDS> getTranslationMessagesDSFromIdList(List<String> ids) {
	    String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
	  
	    return jdbcTemplate.query(
	      String.format("SELECT * FROM TRANSLATION_MESSAGES WHERE ID IN (%s)", inSql), 
	      ids.toArray(), 
	      (rs, rowNum) -> new TranslationMessagesDS(rs.getInt("ID_TRANSLATION_MESSAGE"), rs.getInt("ID_USER"), 
	        rs.getString("ID"), rs.getString("MESSAGE"), this.parseJson(rs.getString(TRANSLATIONS)==null?"{}":rs.getString(TRANSLATIONS) )));

	 
	}
	
	public Optional<TranslationMessagesDS> getTranslationMessagesDSById(final String id) {
		String query = "SELECT * FROM TRANSLATION_MESSAGES WHERE ID IN (%s)";
		return jdbcTemplate
				.queryForObject(String.format(query, id),
						(rs, rowNum) -> Optional.of(new TranslationMessagesDS(rs.getInt("ID_TRANSLATION_MESSAGE"),
								rs.getInt("ID_USER"), rs.getString("ID"), rs.getString("MESSAGE"),
								this.parseJson(rs.getString(TRANSLATIONS)))));

	}
	
	public int updateTranslationMessages(TranslationMessagesDS translationMessagesDS) {
        return jdbcTemplate.update(
                "update TRANSLATION_MESSAGES set TRANSLATIONS = ? where ID = ?",
                translationMessagesDS.getTranslationsDTO().toString(), translationMessagesDS.getId());
    }
	
	public JsonNode parseJson(final String input) {
	      final ObjectMapper mapper = new ObjectMapper();
	      JsonNode output = null;
	      if(input == null) {
	    	  return null;
	      }
	      try {
	         output = mapper.readTree(input);
	      } catch (final JsonProcessingException e) {
	    	  logger.info("Impossible to parse input: {} {} {}", input, e, "JsonProcessingException");
	      } catch (@SuppressWarnings("hiding") final IOException e) {
	    	  logger.info("Impossible to parse input: {} {} {}", input, e,  "IOException");
	      }
	      return output;
	   }


}
