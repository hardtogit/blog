package com.thon.controller.rest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thon.commons.persistence.Page;
import com.thon.controller.util.BaseController;
import com.thon.entity.system.Office;
import com.thon.service.system.OfficeService;
import com.thon.service.system.SystemService;
import com.thon.service.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author thon
 * @date Feb 19, 2014 4:16:44 PM
 */
@Controller
@RequestMapping("/api/office")
public class OfficeRestController extends BaseController {

	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<?> list(@RequestParam(value = "p", defaultValue = "1") int pageNo,
								  @RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize,
								  Office office) {

		Page<Office> page = new Page<Office>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);

		page = officeService.findOffices(page, office);

		return new ResponseEntity(page, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(Office office) {

		if (office.getMaster()!=null && office.getMaster().getId() == null) {
			office.setMaster(null);
		}
		officeService.saveOffice(office);
		return new ResponseEntity("true", HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public ResponseEntity<?> change(@RequestParam(value="id", required=true) Integer id) {

		Office office = officeService.getOffice(id);
		if(office != null){
			UserUtils.cacheOffice(office);
		}

		return new ResponseEntity("true", HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@RequestParam(value="id", required=true) Integer id) {

		Office office = officeService.getOffice(id);
		if(office != null){
			officeService.delOffice(office);
		}

		return new ResponseEntity("true", HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequiresUser
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public ResponseEntity<?> tree(@RequestParam(required=false) Integer extId) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Office> list = officeService.listAllOffices();
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
			if (extId == null || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				Map<String, Object> dataMap = Maps.newHashMap();
				dataMap.put("grade", e.getGrade());
				map.put("id", e.getId());
				map.put("parent", e.getParent()!=null?e.getParent().getId():"#");
				map.put("text", e.getName());
				map.put("data", dataMap);
				mapList.add(map);
			}
		}
		return new ResponseEntity(mapList, HttpStatus.OK);
	}
}
