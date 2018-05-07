package com.thon.task;

import java.util.Map;

/**
 * @author THON
 * @email thon.ju@meet-future.com
 * @date 2011-11-27 上午09:38:24
 * @description:
 */
public interface Task {

	void doSyncTask(Map<Object, Object> context);
	
	void doAsyncTask(Map<Object, Object> context);
	
}
