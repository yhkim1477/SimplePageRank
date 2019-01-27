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

import java.io.IOException;

import org.junit.Test;

/*
 * The test suite for the SimplePageRank using the given resource.
 */
public class SimplePageRankTest {

	@Test
	public void test() {
		SimplePageRank spr = new SimplePageRank();
		try {
			spr.initialize("res/TestLinks.csv");
			spr.iterateEval();
			spr.print();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
