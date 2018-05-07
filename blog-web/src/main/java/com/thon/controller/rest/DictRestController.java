package com.thon.controller.rest;

import com.thon.commons.config.Global;
import com.thon.commons.persistence.Page;
import com.thon.controller.util.BaseController;
import com.thon.entity.system.Dict;
import com.thon.service.system.DictService;
import com.thon.service.system.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 
 * @author thon
 * @date Apr 4, 2014 2:53:23 PM
 */
@Controller
@RequestMapping("/api/dict")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DictRestController extends BaseController{

	@Autowired
	private SystemService systemService;
	@Autowired
	private DictService dictService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<?> list(@RequestParam(value = "status", required = false) Integer status, 
			@RequestParam(value = "p", defaultValue = "1") int pageNo, 
			@RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize,
			Dict dict) {
		
		Page<Dict> page = new Page<Dict>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		page = dictService.find(page, dict);
		
		return new ResponseEntity(page, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(@Valid Dict dict) {
		if(Global.isDemoMode()){
			return new ResponseEntity("", HttpStatus.FORBIDDEN);
		}
		
		dictService.saveDict(dict);;
		return new ResponseEntity("true", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@RequestParam(value="id", required=true) Integer id) {
		if(Global.isDemoMode()){
			return new ResponseEntity("", HttpStatus.FORBIDDEN);
		}
		
		dictService.deleteDict(id);
		return new ResponseEntity("true", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	public ResponseEntity<?> sort(Integer[] ids, Integer[] sorts) {
		if(Global.isDemoMode()){
			return new ResponseEntity("", HttpStatus.FORBIDDEN);
		}
    	int len = ids.length;
    	Dict[] dicts = new Dict[len];
    	for (int i = 0; i < len; i++) {
    		dicts[i] = dictService.getDict(ids[i]);
    		dicts[i].setSort(sorts[i]);
    		dictService.saveDict(dicts[i]);
    	}

    	return new ResponseEntity("true", HttpStatus.OK);
	}
	
}
