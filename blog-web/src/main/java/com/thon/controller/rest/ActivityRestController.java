/**
 * 
 */
package com.thon.controller.rest;

import com.thon.commons.mapper.BeanMapper;
import com.thon.commons.persistence.Page;
import com.thon.controller.util.BaseController;
import com.thon.dto.util.ActivityDTO;
import com.thon.entity.system.Activity;
import com.thon.service.system.ActivityService;
import com.thon.service.system.SystemService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author thon
 * @email thon.ju@gmail.com
 * @date Aug 19, 2014 7:46:21 PM
 * @description
 */
@Controller
@RequestMapping("/api/activity")
public class ActivityRestController extends BaseController{
    private static final Log log = LogFactory.getLog(ActivityRestController.class);

	@Autowired
	private SystemService systemService;
	@Autowired
	private ActivityService activityService;

    /**
     *
     * @param courseId
     * @param userId
     * @param action
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ResponseEntity<HttpEntity> apiList(@RequestParam(value = "cid", required = true) Integer courseId,
            @RequestParam(value = "uid", required = true) Integer userId,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "p", defaultValue = "1") int pageNo,
            @RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize) {

        Page<Activity> page = activityService.findActivityPage(courseId, userId, action, pageNo, pageSize);

        Page<ActivityDTO> pageDTO = new Page<ActivityDTO>();
        pageDTO.setPageNo(page.getPageNo());
        pageDTO.setPageSize(page.getPageSize());
        pageDTO.setTotalCount(page.getTotalCount());
        pageDTO.setResult(BeanMapper.mapList(page.getResult(), ActivityDTO.class));
        return new ResponseEntity(pageDTO, HttpStatus.OK);
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/user/list", method = RequestMethod.GET)
	public ResponseEntity<HttpEntity> apiUserList(@RequestParam(value = "uid", required = true) Integer userId,
			@RequestParam(value = "p", defaultValue = "1") int pageNo, 
			@RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize) {
		
		Page<Activity> page = activityService.findUserActivityPage(userId, pageNo, pageSize);

		Page<ActivityDTO> pageDTO = new Page<ActivityDTO>();
		pageDTO.setPageNo(page.getPageNo());
		pageDTO.setPageSize(page.getPageSize());
		pageDTO.setTotalCount(page.getTotalCount());
		pageDTO.setResult(BeanMapper.mapList(page.getResult(), ActivityDTO.class));
		return new ResponseEntity(pageDTO, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/teach/list", method = RequestMethod.GET)
	public ResponseEntity<HttpEntity> apiTeachList(@RequestParam(value = "uid", required = true) Integer userId,
			@RequestParam(value = "p", defaultValue = "1") int pageNo, 
			@RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize) {
		
		log.debug("query user activity list begin");
		
		Page<Activity> page = activityService.findTeachActivityPage(userId, pageNo, pageSize);

        Page<ActivityDTO> pageDTO = new Page<ActivityDTO>();
        pageDTO.setPageNo(page.getPageNo());
        pageDTO.setPageSize(page.getPageSize());
        pageDTO.setTotalCount(page.getTotalCount());
        pageDTO.setResult(BeanMapper.mapList(page.getResult(), ActivityDTO.class));
		return new ResponseEntity(pageDTO, HttpStatus.OK);
	}

}
