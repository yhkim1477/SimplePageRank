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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Util {
	private Util() {
	}

	/**
	 * Parse a text file from the given file path.
	 * 
	 * @param path
	 *            File path.
	 * @return A list of the parsed strings.
	 */
	public static List<String[]> parseTextFile(String path) {
		if (path == null || path.isEmpty())
			return null;

		List<String[]> lines = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			stream.forEach(str -> {
				if (Validator.validateString(str)) {
					String[] csv = parseCSV(str);
					if (Validator.validateStringArray(csv))
						lines.add(csv);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	/**
	 * Parse from csv string to string array.
	 * 
	 * @param csv
	 *            A string in csv format.
	 * @return Parsed string array.
	 */
	public static String[] parseCSV(String csv) {
		if (csv == null || csv.isEmpty())
			return null;
		return csv.split(",");
	}
}
