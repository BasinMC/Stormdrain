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
import org.basinmc.stormdrain.resource.PullRequest;
import org.basinmc.stormdrain.resource.Repository;
import org.basinmc.stormdrain.resource.Review;
import org.basinmc.stormdrain.resource.User;

/**
 * Represents an event which notifies its receivers of newly added, changed or dismissed pull
 * request reviews.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class PullRequestReviewEvent extends AbstractRepositoryEvent {

  private final Action action;
  private final Review review;
  private final PullRequest pullRequest;

  @JsonCreator
  public PullRequestReviewEvent(
      @NonNull @JsonProperty(value = "action", required = true) Action action,
      @NonNull @JsonProperty(value = "review", required = true) Review review,
      @NonNull @JsonProperty(value = "pull_request", required = true) PullRequest pullRequest,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(repository, sender);
    this.action = action;
    this.review = review;
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
   * Retrieves the review which is represented by this event.
   *
   * @return a review.
   */
  @NonNull
  public Review getReview() {
    return this.review;
  }

  /**
   * Retrieves the pull request to which the review referenced by this event has been added.
   *
   * @return a pull request.
   */
  @NonNull
  public PullRequest getPullRequest() {
    return this.pullRequest;
  }

  /**
   * Provides a list of valid change types.
   */
  public enum Action {
    SUBMITTED,
    EDITED,
    DISMISSED
  }
}
