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
import org.basinmc.drain.resource.PullRequest;
import org.basinmc.drain.resource.Repository;
import org.basinmc.drain.resource.User;

/**
 * Represents an event which notifies all of its receivers of the creation of a new pull request or
 * a change to an existing request.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class PullRequestEvent extends AbstractRepositoryEvent {

  private final Action action;
  private final long number;
  private final PullRequest pullRequest;

  @JsonCreator
  public PullRequestEvent(
      @NonNull @JsonProperty(value = "action", required = true) Action action,
      @JsonProperty(value = "number", required = true) long number,
      @NonNull @JsonProperty(value = "pull_request", required = true) PullRequest pullRequest,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(repository, sender);
    this.action = action;
    this.number = number;
    this.pullRequest = pullRequest;
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
   * Retrieves the repository unique identifier for this pull request.
   *
   * @return a number.
   */
  public long getNumber() {
    return this.number;
  }

  /**
   * Retrieves the pull request which has been changed by this event.
   */
  @NonNull
  public PullRequest getPullRequest() {
    return this.pullRequest;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PullRequestEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    PullRequestEvent that = (PullRequestEvent) o;
    return this.number == that.number &&
        this.action == that.action &&
        Objects.equals(this.pullRequest, that.pullRequest);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.action, this.number, this.pullRequest);
  }

  /**
   * Provides a list of valid change types.
   */
  public enum Action {
    ASSIGNED,
    UNASSIGNED,
    REVIEW_REQUESTED,
    REVIEW_REQUEST_REMOVED,
    LABELED,
    UNLABELED,
    OPENED,
    EDITED,
    CLOSED,
    REOPENED
  }
}
