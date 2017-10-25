/**
 * 
 */
package com.meihua.domain;

import java.io.Serializable;

/**
 * 投票活动详情
 * 
 * @author hzy
 */
public class VoteActionsDetailsResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 投票活动ID
	 */
	private Integer vote_action_id;
	/**
	 * 活动标题
	 */
	private String title;
	/**
	 * 工作总结上传开始时间
	 */
	private String work_summary_upload_start_time;
	/**
	 * 工作总结上传截止时间
	 */
	private String work_summary_upload_end_time;
	/**
	 * 活动状态（未开始，进行中，已完成）
	 */
	private String status;
	/**
	 * 是否已上传工作总结。 0, 未上传 1, 已上传
	 */
	private Integer work_summary_uploaded;
	/**
	 * 工作总结内容
	 */
	private String work_summary_content;

	/**
	 * @return the vote_action_id
	 */
	public Integer getVote_action_id() {
		return vote_action_id;
	}

	/**
	 * @param vote_action_id
	 *            the vote_action_id to set
	 */
	public void setVote_action_id(Integer vote_action_id) {
		this.vote_action_id = vote_action_id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the work_summary_upload_start_time
	 */
	public String getWork_summary_upload_start_time() {
		return work_summary_upload_start_time;
	}

	/**
	 * @param work_summary_upload_start_time
	 *            the work_summary_upload_start_time to set
	 */
	public void setWork_summary_upload_start_time(String work_summary_upload_start_time) {
		this.work_summary_upload_start_time = work_summary_upload_start_time;
	}

	/**
	 * @return the work_summary_upload_end_time
	 */
	public String getWork_summary_upload_end_time() {
		return work_summary_upload_end_time;
	}

	/**
	 * @param work_summary_upload_end_time
	 *            the work_summary_upload_end_time to set
	 */
	public void setWork_summary_upload_end_time(String work_summary_upload_end_time) {
		this.work_summary_upload_end_time = work_summary_upload_end_time;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the work_summary_uploaded
	 */
	public Integer getWork_summary_uploaded() {
		return work_summary_uploaded;
	}

	/**
	 * @param work_summary_uploaded
	 *            the work_summary_uploaded to set
	 */
	public void setWork_summary_uploaded(Integer work_summary_uploaded) {
		this.work_summary_uploaded = work_summary_uploaded;
	}

	/**
	 * @return the work_summary_content
	 */
	public String getWork_summary_content() {
		return work_summary_content;
	}

	/**
	 * @param work_summary_content
	 *            the work_summary_content to set
	 */
	public void setWork_summary_content(String work_summary_content) {
		this.work_summary_content = work_summary_content;
	}
}
