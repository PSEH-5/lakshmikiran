/**
 * 
 */
package com.sapient.assessment.controller;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.assessment.constants.Constants;
import com.sapient.assessment.service.AssessmentService;

/**
 * @author indiahiring
 *
 */
@RestController
@RequestMapping("/sapient")
public class AssessmentController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AssessmentService assessmentService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/standings",method = RequestMethod.GET)
	public JSONObject standings(@RequestParam Integer leagueId) {
		if(logger.isDebugEnabled()) {
			logger.debug("standings :: entered standards controller");
		}
		JSONObject response = new JSONObject();
		if(null == leagueId || leagueId <= 0) {
			logger.info("standings :: invalid league id :: "+leagueId);
			response.put(Constants.STATUS_CODE, Constants.STATUS_CODE_FAILURE);
			response.put(Constants.STATUS_MESSAGE, Constants.STATUS_MESSAGE_FAILURE);
			response.put(Constants.DESCRIPTION, "Invalid League Id");
			
			return response;
			
		}
		logger.info("standings :: calling assessment service");
		response = assessmentService.standards(leagueId);
		if(logger.isDebugEnabled()) {
			logger.debug("standings :: exiting standards controller");
		}
		return response;
	}
}
