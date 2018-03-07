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
import org.basinmc.stormdrain.resource.PullRequest;
import org.basinmc.stormdrain.resource.Repository;
import org.basinmc.stormdrain.resource.ReviewComment;
import org.basinmc.stormdrain.resource.User;

/**
 * Represents an event which notifies its receivers of comments which have been newly created,
 * changed or deleted on a pull request review.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class PullRequestReviewCommentEvent extends AbstractRepositoryEvent {

  private final Action action;
  private final ReviewComment comment;
  private final PullRequest pullRequest;

  @JsonCreator
  public PullRequestReviewCommentEvent(
      @NonNull @JsonProperty(value = "action", required = true) Action action,
      @NonNull @JsonProperty(value = "comment", required = true) ReviewComment comment,
      @NonNull @JsonProperty(value = "pull_request", required = true) PullRequest pullRequest,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(repository, sender);
    this.action = action;
    this.comment = comment;
    this.pullRequest = pullRequest;
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
   * Retrieves the comment which is represented by this event.
   *
   * @return a comment.
   */
  @NonNull
  public ReviewComment getComment() {
    return this.comment;
  }

  /**
   * Retrieves the pull request to which the comment which is referenced by this event has been
   * added.
   *
   * @return a pull request.
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
    if (!(o instanceof PullRequestReviewCommentEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    PullRequestReviewCommentEvent that = (PullRequestReviewCommentEvent) o;
    return this.action == that.action &&
        Objects.equals(this.comment, that.comment) &&
        Objects.equals(this.pullRequest, that.pullRequest);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.action, this.comment, this.pullRequest);
  }

  /**
   * Provides a list of valid change types.
   */
  public enum Action {
    CREATED,
    EDITED,
    DELETED
  }
}
