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
package org.basinmc.stormdrain.event;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;

/**
 * Provides a base to tests which evaluate whether the event implementations correctly mirror the
 * structure of the documented event hooks.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public abstract class AbstractEventTest<E extends Event> {

  private final Class<E> type;
  private final String eventName;

  public AbstractEventTest(@NonNull Class<E> type, @NonNull String eventName) {
    this.type = type;
    this.eventName = eventName;
  }

  /**
   * Evaluates whether the event is correctly parsed based on its event type.
   *
   * @throws IOException when reading the test file fails or its implementation does not match the
   * expected bounds.
   */
  @Test
  public void testParse() throws IOException {
    try (InputStream inputStream = this.getClass()
        .getResourceAsStream("/" + this.eventName + ".json")) {
      ObjectMapper mapper = new ObjectMapper();

      mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      mapper.findAndRegisterModules();

      this.doTest(mapper.readValue(inputStream, this.type));
    }
  }

  /**
   * Evaluates whether the specified event has been parsed correctly.
   *
   * @param event a parsed event.
   */
  protected abstract void doTest(@NonNull E event);
}
