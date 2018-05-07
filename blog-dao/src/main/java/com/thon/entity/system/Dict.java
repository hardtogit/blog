package com.thon.entity.system;

import com.thon.commons.persistence.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 字典Entity
 * @author thon
 * @date Jan 2, 2014 11:41:06 AM
 */
@Entity
@Table(name = "sys_dict")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dict extends IdEntity {
	
	public static final String CHECK_TYPE = "check_type";
	public static final String PROCESS_TYPE = "process_type";
	public static final String PROJECT_INFO_TYPE = "project_info_type";
	public static final String DEL_FLAG_TYPE = "del_flag_type";
	public static final String PROCESS_CATEGORY_SG = "SG";
	public static final String PROCESS_CATEGORY_ZT = "ZT";
	public static final String PROCESS_CATEGORY_BM = "BM";
    public static final String CHAT_OBJECT = "chat_object";
    public static final String CHAT_CONTENT = "chat_content";
    public static final String CHAT_TYPE = "chat_type";
    public static final String DECISION_DELAY_TYPE = "decision_delay_type";

	private String label;	// 标签名
	private String value;	// 数据值
	private String type;	// type
	private String description;// 描述
	private String remarks;// 描述
	private Integer sort;	// 排序

	public Dict() {
		super();
	}
	
	public Dict(int id) {
		this.id = id;
	}
	
	@Length(min=1, max=100)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Length(min=1, max=100)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}