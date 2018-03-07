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
package org.basinmc.drain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Objects;
import org.basinmc.drain.resource.Issue.Label;
import org.basinmc.drain.resource.Repository;
import org.basinmc.drain.resource.User;

/**
 * Represents an event which notifies its receivers of the creation of or a change to an issue or
 * pull request label.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class LabelEvent extends AbstractRepositoryEvent {

  private final Action action;
  private final Label label;

  @JsonCreator
  public LabelEvent(
      @NonNull @JsonProperty(value = "action", required = true) Action action,
      @NonNull @JsonProperty(value = "label", required = true) Label label,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(repository, sender);
    this.action = action;
    this.label = label;
  }

  /**
   * Retrieves the type of change which this event represents.
   *
   * @return a change type.
   */
  @NonNull
  public Action getAction() {
    return this.action;
  }

  /**
   * Retrieves the label which has been affected by this change.
   *
   * @return a label.
   */
  @NonNull
  public Label getLabel() {
    return this.label;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof LabelEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    LabelEvent that = (LabelEvent) o;
    return this.action == that.action &&
        Objects.equals(this.label, that.label);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.action, this.label);
  }

  public enum Action {
    CREATED,
    EDITED,
    DELETED
  }
}
