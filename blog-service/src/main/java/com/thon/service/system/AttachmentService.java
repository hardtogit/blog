package com.thon.service.system;

import com.thon.commons.persistence.Page;
import com.thon.dao.system.AttachmentDao;
import com.thon.entity.system.Attachment;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author THON
 * @email thon.ju@meet-future.com
 * @date 2011-11-24 上午10:41:06
 * @description:
 */
@Service
@Transactional
public class AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;
	
	public AttachmentService() {
	}
	
	public Attachment getAttachment(int attachmentId) {
		return attachmentDao.get(attachmentId);
	}

	public void saveAttachment(Attachment attachment) {
		attachmentDao.save(attachment);
	}

	public void deleteAttachment(Attachment attachment) {
		attachmentDao.delete(attachment);
	}

    public Page<Attachment> findPage(int pageNo, int pageSize, int createBy){
        Page<Attachment> page = new Page<Attachment>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        DetachedCriteria dc = attachmentDao.createDetachedCriteria();
        dc.add(Restrictions.eq("createBy", createBy));
        dc.add(Restrictions.like("contentType","image/%"));

        dc.addOrder(Order.desc("createTime"));

        return attachmentDao.find(page, dc);
    }

}
