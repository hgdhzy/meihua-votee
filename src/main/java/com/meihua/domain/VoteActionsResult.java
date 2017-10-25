/**
 * 
 */
package com.meihua.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 投票活动列表
 * 
 * @author hzy
 *
 */
public class VoteActionsResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 投票活动ID
	 */
	private Integer pages;
	/**
	 * 活动标题
	 */
	private List<VoteActions> vote_actions;

	/**
	 * @return the pages
	 */
	public Integer getPages() {
		return pages;
	}

	/**
	 * @param pages
	 *            the pages to set
	 */
	public void setPages(Integer pages) {
		this.pages = pages;
	}

	/**
	 * @return the vote_actions
	 */
	public List<VoteActions> getVote_actions() {
		return vote_actions;
	}

	/**
	 * @param vote_actions
	 *            the vote_actions to set
	 */
	public void setVote_actions(List<VoteActions> vote_actions) {
		this.vote_actions = vote_actions;
	}

}
