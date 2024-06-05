package com.imranmabar.blog;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blog_post")
public class BlogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String postTitle;

	private String postBody;

	private String postImage;

	private String category;

	private Timestamp create_at;

	private Timestamp modify_at;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostBody() {
		return postBody;
	}

	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}

	public String getPostImage() {
		return postImage;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Timestamp getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}

	public Timestamp getModify_at() {
		return modify_at;
	}

	public void setModify_at(Timestamp modify_at) {
		this.modify_at = modify_at;
	}

	@Override
	public String toString() {
		return "BlogEntity [id=" + id + ", postTitle=" + postTitle + ", postBody=" + postBody + ", postImage="
				+ postImage + ", category=" + category + ", create_at=" + create_at + ", modify_at=" + modify_at + "]";
	}

}
