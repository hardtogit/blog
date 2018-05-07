/**
 * 
 */
package com.thon.controller.rest;


import com.thon.commons.persistence.Page;
import com.thon.controller.util.BaseController;
import com.thon.entity.system.Log;
import com.thon.service.system.LogService;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author thon
 * @date Dec 23, 2013 5:36:22 PM
 */
@Controller
@RequestMapping("/api")
public class LogRestController extends BaseController{
	
	
	
	@Autowired
	private LogService logService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/log/list", method = RequestMethod.GET)
	public ResponseEntity<HttpEntity> apiLogList(@RequestParam(value = "p", defaultValue = "1") int pageNo, 
			@RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize, 
			@RequestParam Map<String, Object> paramMap, 
			Model model) {
		
		Page<Log> page = new Page<Log>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page = logService.find(page, paramMap);
		
		return new ResponseEntity(page, HttpStatus.OK);
	}

}
