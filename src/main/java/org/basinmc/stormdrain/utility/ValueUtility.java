/*
 * Copyright 2018 Johannes Donath <johannesd@torchmind.com>
 * and other copyright owners as documented in the project's IP log.
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
package org.basinmc.stormdrain.utility;

import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Provides utility methods which simplify the interaction with various different value types.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public final class ValueUtility {

  private ValueUtility() {
  }

  /**
   * Reduces optional string properties to their actual value (e.g. interprets empty strings as
   * unset).
   *
   * @param value a string value.
   * @return a string or null when unset.
   */
  @Nullable
  public static String toOptionalString(@Nullable String value) {
    if (value == null || value.trim().isEmpty()) {
      return null;
    }

    return value;
  }
}
