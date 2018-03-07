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

import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Objects;
import org.basinmc.stormdrain.resource.User;

/**
 * Provides a base for user triggered events.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public abstract class AbstractUserTriggeredEvent implements Event {

  private final User sender;

  public AbstractUserTriggeredEvent(@NonNull User sender) {
    this.sender = sender;
  }

  @NonNull
  public User getSender() {
    return this.sender;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractUserTriggeredEvent)) {
      return false;
    }
    AbstractUserTriggeredEvent that = (AbstractUserTriggeredEvent) o;
    return Objects.equals(this.sender, that.sender);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.sender);
  }
}
