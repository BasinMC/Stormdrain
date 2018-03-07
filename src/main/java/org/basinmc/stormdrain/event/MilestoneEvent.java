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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Objects;
import org.basinmc.stormdrain.resource.Milestone;
import org.basinmc.stormdrain.resource.Repository;
import org.basinmc.stormdrain.resource.User;

/**
 * Represents an event which notifies its receivers of a change to milestones (such as their
 * creation, change or deletion).
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class MilestoneEvent extends AbstractRepositoryEvent {

  private final Action action;
  private final Milestone milestone;

  @JsonCreator
  public MilestoneEvent(
      @NonNull @JsonProperty(value = "action", required = true) Action action,
      @NonNull @JsonProperty(value = "milestone", required = true) Milestone milestone,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(repository, sender);
    this.action = action;
    this.milestone = milestone;
  }

  /**
   * Retrieves the type of change which is represented by this event.
   *
   * @return a change type.
   */
  @NonNull
  public Action getAction() {
    return this.action;
  }

  /**
   * Retrieves the milestone which has been changed in its new state.
   *
   * @return a milestone.
   */
  @NonNull
  public Milestone getMilestone() {
    return this.milestone;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MilestoneEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    MilestoneEvent that = (MilestoneEvent) o;
    return this.action == that.action &&
        Objects.equals(this.milestone, that.milestone);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.action, this.milestone);
  }

  /**
   * Provides a list of valid change types.
   */
  public enum Action {
    CREATED,
    CLOSED,
    OPENED,
    EDITED,
    DELETED
  }
}
