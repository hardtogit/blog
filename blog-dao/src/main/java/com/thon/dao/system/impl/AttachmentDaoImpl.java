/**
 * 
 */
package com.thon.dao.system.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.dao.system.AttachmentDao;
import com.thon.entity.system.Attachment;
import org.springframework.stereotype.Repository;

/**
 * @author thon
 * @email thon.ju@meet-future.com
 * @date 2011-11-10 14:18:53
 * @description:
 */
@Repository
public class AttachmentDaoImpl extends BaseDaoImpl<Attachment> implements AttachmentDao{
	
	public AttachmentDaoImpl(){
		super(Attachment.class);
	}

}
