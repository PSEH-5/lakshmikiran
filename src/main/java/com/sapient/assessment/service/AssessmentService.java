/**
 * 
 */
package com.sapient.assessment.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sapient.assessment.bean.Response;
import com.sapient.assessment.constants.Constants;

/**
 * @author indiahiring
 *
 */
@Service
public class AssessmentService {

	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * service to return list of team standings as per league Id
	 * 
	 * @param leagueId
	 * 
	 * @return json response
	 */
	@SuppressWarnings("unchecked")
	public JSONObject standards(Integer leagueId) {
		if (logger.isDebugEnabled()) {
			logger.debug("standards :: Entered standards service");
		}
		JSONObject json = new JSONObject();
		try {
			String url = Constants.URL + leagueId + "&" + Constants.API + Constants.API_KEY;
			logger.info("standards :: url for consuming data is :: " + url);

			RestTemplate template = new RestTemplate();
			List<LinkedHashMap<String, String>> response = template.getForObject(url, List.class);
			if (null == response || response.size() == 0) {
				logger.info("standings :: no data from url");
				json.put(Constants.STATUS_CODE, Constants.STATUS_CODE_FAILURE);
				json.put(Constants.STATUS_MESSAGE, Constants.STATUS_MESSAGE_FAILURE);
				json.put(Constants.DESCRIPTION, "no data from url");

				return json;
			}
			List<Response> list = new ArrayList<>();
			logger.info("standings :: converting the retrieved response to our required response");
			for (LinkedHashMap<String, String> map : response) {
				Response res = new Response();
				if (map.containsKey("country_name"))
					res.setCountry_name(map.get("country_name"));
				if (map.containsKey("league_id"))
					res.setLeague_id(map.get("league_id"));
				if (map.containsKey("league_name"))
					res.setLeague_name(map.get("league_name"));
				if (map.containsKey("team_id"))
					res.setTeam_id(map.get("team_id"));
				if (map.containsKey("team_name"))
					res.setTeam_name(map.get("team_name"));
				if (map.containsKey("overall_league_position"))
					res.setOverall_league_position(map.get("overall_league_position"));

				list.add(res);
			}
			logger.info("standings :: converted response and added to list");
			json.put(Constants.STATUS_CODE, Constants.STATUS_CODE_SUCCESS);
			json.put(Constants.STATUS_MESSAGE, Constants.STATUS_MESSAGE_SUCCESS);
			json.put(Constants.RESPONSE, list);

			if (logger.isDebugEnabled()) {
				logger.debug("standards :: Exiting standards service");
			}
			return json;
		} catch (Exception e) {
			logger.error("standings :: exception while retrieving the url", e);

			json.put(Constants.STATUS_CODE, Constants.STATUS_CODE_EXCEPTION);
			json.put(Constants.STATUS_MESSAGE, Constants.STATUS_MESSAGE_EXCEPTION);
			json.put(Constants.DESCRIPTION, "exception while retrieving the url");
			if (logger.isDebugEnabled()) {
				logger.debug("standards :: Exiting standards service");
			}
			return json;
		}

	}
}
