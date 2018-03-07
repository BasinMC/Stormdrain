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
import org.basinmc.stormdrain.resource.Issue;
import org.basinmc.stormdrain.resource.Repository;
import org.basinmc.stormdrain.resource.User;

/**
 * Represents an event which notifies its receiver of a change to an existing issue or the creation
 * of a new issue.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class IssuesEvent extends AbstractRepositoryEvent {

  private final Action action;
  private final Issue issue;

  @JsonCreator
  public IssuesEvent(
      @NonNull @JsonProperty(value = "action", required = true) Action action,
      @NonNull @JsonProperty(value = "issue", required = true) Issue issue,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(repository, sender);
    this.action = action;
    this.issue = issue;
  }

  /**
   * Retrieves the change type which this event notifies its receiver of.
   *
   * @return a change type.
   */
  @NonNull
  public Action getAction() {
    return this.action;
  }

  /**
   * Retrieves the issue which has been created or changed.
   *
   * @return an event.
   */
  @NonNull
  public Issue getIssue() {
    return this.issue;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IssuesEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    IssuesEvent that = (IssuesEvent) o;
    return this.action == that.action &&
        Objects.equals(this.issue, that.issue);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.action, this.issue);
  }

  /**
   * provides a list of valid change types.
   */
  public enum Action {
    ASSIGNED,
    UNASSIGNED,
    LABELED,
    UNLABELED,
    OPENED,
    EDITED,
    MILESTONED,
    DEMILESTONED,
    CLOSED,
    REOPENED
  }
}
