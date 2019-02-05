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

public class Validator {
	private Validator() {
	}

	/**
	 * Validates whether the given value is a valid string.
	 * 
	 * @param value
	 *            Object value to validate
	 * @return Result of validation
	 */
	public static boolean validateString(Object value) {
		if (value == null || !String.class.isInstance(value))
			return false;
		if (((String) value).isEmpty())
			return false;
		return true;
	}

	/**
	 * Validates whether the given value is a valid string array.
	 * 
	 * @param value
	 *            Object value to validate
	 * @return Result of validation
	 */
	public static boolean validateStringArray(Object value) {
		if (value == null)
			return false;

		try {
			String[] strArray = (String[]) value;
			if (strArray.length <= 0)
				return false;
			else
				return true;
		} catch (Exception e) {
			return false;
		}
	}

}
