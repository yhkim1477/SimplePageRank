/*
 *    Copyright 2019 Yong-Hyun Kim
 *    
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *    
 *        http://www.apache.org/licenses/LICENSE-2.0
 *    
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.yhkim.algorithm.pagerank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PageRankValue {

	private double pageRankScore;
	private double outLinkScore;
	private List<String> outLinks;

	/**
	 * Default constructor. This constructor initialize the scores and list object.
	 * The pageRankScore is initialized to 1 according to the default policy of the
	 * known pagerank algorithm.
	 */
	public PageRankValue() {
		pageRankScore = 1D;
		outLinkScore = 0D;
		outLinks = new ArrayList<>();
	}

	/**
	 * Add new out link score of the source page.
	 * 
	 * @param score
	 *            Out link score.
	 */
	public void addOutLinkScore(double score) {
		this.outLinkScore += score;
	}

	/**
	 * Add new out link of the source page.
	 * 
	 * @param link
	 *            Out link address.
	 */
	public void addOutLink(String link) {
		if (Validator.validateString(link))
			outLinks.add(link);
	}

	/**
	 * Returns current pagerank score. It may not be the final score.
	 * 
	 * @return Pagerank score.
	 */
	public double getScore() {
		return pageRankScore;
	}

	/**
	 * Returns current out link score.
	 * 
	 * @return Out link score.
	 */
	public double getOutLinkScore() {
		return outLinkScore;
	}

	/**
	 * Returns a list of out links as an iterator.
	 * 
	 * @return A list of out links.
	 */
	public Iterator<String> getOutLinks() {
		return outLinks.iterator();
	}

	/**
	 * Returns the number of the out links.
	 * 
	 * @return the number of the out links.
	 */
	public long getOutLinkCount() {
		return outLinks.size();
	}

	/**
	 * Evaluate the pagerank algorithm using the given damping factor and the total
	 * number of links.
	 * 
	 * @param d
	 *            Damping factor.
	 * @param n
	 *            The total number of links.
	 */
	public void evaluate(double d, long n) {
		pageRankScore = (1 - d) / n + d * outLinkScore;
		outLinkScore = 0D;
	}

}
