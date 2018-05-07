package com.thon.entity.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thon.commons.persistence.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

/**
 * 词汇
 * @author thon
 * @date Sep 5, 2014 11:41:06 AM
 */
@Entity
@Table(name = "bi_term")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Term extends IdEntity {
    public static final String TYPE_POST_TAG="post_tag";
    public static final String TYPE_CATEGORY="category";

	private Term parent;	// 父级菜单
	private String parentIds; // 所有父级编号
	private String name;	// 标签名
	private String slug;	// 描述
	private String type;	// 类型

	private List<Term> childList = Lists.newArrayList(); // 拥有子词汇
    private List<Post> postList = Lists.newArrayList(); // 拥有的文章

	public Term() {
		super();
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public Term getParent() {
		return parent;
	}

	public void setParent(Term parent) {
		this.parent = parent;
	}

	@Length(min=0, max=255)
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Length(min=1, max=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=1, max=100)
	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	@Length(min=1, max=100)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(cascade = {CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="parent")
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Term> getChildList() {
		return childList;
	}

	public void setChildList(List<Term> childList) {
		this.childList = childList;
	}

    @ManyToMany(mappedBy = "termList", fetch=FetchType.LAZY)
    @Where(clause="del_flag="+DEL_FLAG_NORMAL)
    @OrderBy("id") @Fetch(FetchMode.SUBSELECT)
    @NotFound(action = NotFoundAction.IGNORE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Transient
	public static void sortList(List<Term> list, List<Term> sourcelist, Integer parentId){
		for (int i=0; i<sourcelist.size(); i++){
            Term e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null
					&& e.getParent().getId().equals(parentId)){
				list.add(e);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j=0; j<sourcelist.size(); j++){
                    Term child = sourcelist.get(j);
					if (child.getParent()!=null && child.getParent().getId()!=null
							&& child.getParent().getId().equals(e.getId())){
						sortList(list, sourcelist, e.getId());
						break;
					}
				}
			}
		}
	}

	@Transient
	public static boolean isRoot(Integer id){
		return id != null && id.equals(1);
	}
}