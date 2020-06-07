package mx.open.dashboard.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.open.dashboard.ds.TranslationMessagesDS;

@Repository
public class TranslationMessagesRepository{
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
    JdbcTemplate jdbcTemplate;

	
	
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
                                rs.getInt("ID_TRANSLATION_MESSAGE"),
                                rs.getInt("ID_USER"),
                                rs.getString("ID"),
                                rs.getString("MESSAGE"),
                                rs.getString("TRANSLATIONS")
                        )
        );
    }
	
	public List<TranslationMessagesDS> getTranslationMessagesDSFromIdList(List<String> ids) {
	    String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
	  
	    return jdbcTemplate.query(
	      String.format("SELECT * FROM TRANSLATION_MESSAGES WHERE TRANSLATIONS IN (%s)", inSql), 
	      ids.toArray(), 
	      (rs, rowNum) -> new TranslationMessagesDS(rs.getInt("ID_TRANSLATION_MESSAGE"), rs.getInt("ID_USER"), 
	        rs.getString("ID"), rs.getString("MESSAGE"), rs.getString("TRANSLATIONS")));
	 
	}


}
