/**
 * Copyright 2019 Yong-Hyun Kim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yhkim.algorithm.pagerank;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SimplePageRank implements Rank<PageRankValue> {
	private final Map<String, PageRankValue> mapPageRanks;
	private final int iteration;

	/**
	 * The constructor uses a predefined iteration settings.
	 */
	public SimplePageRank() {
		this(Configurations.RANK_ITERATION);
	}

	/**
	 * Default constructor.
	 *
	 * @param iteration Number of algorithm evaluations.
	 */
	public SimplePageRank(int iteration) {
		mapPageRanks = new HashMap<>();
		this.iteration = iteration;
	}

	/**
	 * Initialization via the given text file path.
	 *
	 * @param path Text file path.
	 * @throws IOException
	 */
	public void initialize(String path) throws IOException {
		List<String[]> parsedLines = ParserUtil.parseTextFile(path);
		if (parsedLines == null) throw new IOException();
		
		for (String[] line : parsedLines) {
			addPageRankValue(line);
		}
	}

	/**
	 * Initialization via the given csv text.
	 *
	 * @param lines CSV lines.
	 */
	public void initialize(String[] lines) {
		for (String line : lines)
			addPageRankValue(ParserUtil.parseCSV(line));
	}

	/**
	 * Initialize PageRank map structure via the given csv text.
	 *
	 * @param csv CSV lines.
	 */
	private void addPageRankValue(String[] csv) {
		if (csv == null || csv.length == 0) throw new IllegalArgumentException();

		String key = csv[Configurations.IDX_KEY_LINK];
		if (!ValidatorUtil.validateString(key)) throw new NullPointerException();
		
		PageRankValue prValue;
		if (mapPageRanks.containsKey(key)) {
			prValue = mapPageRanks.get(key);
		} else {
			prValue = new PageRankValue();
		}

		for (int i = (Configurations.IDX_KEY_LINK + 1); i < csv.length; i++) {
			prValue.addOutLink(csv[i]);
			if (!mapPageRanks.containsKey(csv[i])) {
				mapPageRanks.put(csv[i], new PageRankValue());
			}
		}
		mapPageRanks.put(key, prValue);
	}

	/**
	 * Evaluate iteratively
	 */
	public void iterateEval() {
		int curIter = 0;
		while (curIter++ < iteration) evaluate();
	}
	
	/**
	 * @see com.yhkim.algorithm.pagerank.Rank#evaluate()
	 */
	@Override
	public void evaluate() {
		// Set the outLinkScore for the entire link
		mapPageRanks.keySet().forEach(k -> {
			PageRankValue prValue = mapPageRanks.get(k);
			Iterator<String> outLinks = prValue.getOutLinks();

			while (outLinks.hasNext()) {
				PageRankValue prOutLink = mapPageRanks.get(outLinks.next());

				if (prOutLink != null) {
					// Divides the source link's page rank score by the number of out links
					double score = prValue.getScore() / prValue.getOutLinkCount();
					prOutLink.addOutLinkScore(score);
				}
			}
		});
		
		// After setting outLinkScore, evaluate each page rank score using the number of entire link
		mapPageRanks.keySet().forEach(k -> mapPageRanks.get(k).evaluate(Configurations.DAMPING_FACTOR, mapPageRanks.size()));
	}
	
	/**
	 * @see com.yhkim.algorithm.pagerank.Rank#getResults()
	 */
	@Override
	public Map<String, PageRankValue> getResults() {
		return mapPageRanks;
	}

	/**
	 * Returns the number of entire link.
	 * 
	 * @return The number of entire link.
	 */
	public long getTotalLinkCount() {
		return mapPageRanks.size();
	}

	/**
	 * Print all links and scores
	 */
	public void print() {
		mapPageRanks.entrySet().stream().sorted((a, b) -> (a.getValue().getScore() > b.getValue().getScore()) ? -1 : 1)
				.map(e -> e.getKey() + " - " + e.getValue().getScore()).forEach(System.out::println);
	}

}
