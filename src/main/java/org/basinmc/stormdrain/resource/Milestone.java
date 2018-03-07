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
package org.basinmc.stormdrain.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.net.URL;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import org.basinmc.stormdrain.resource.Issue.State;
import org.basinmc.stormdrain.utility.ValueUtility;

/**
 * Represents a milestone which contains one or more issues.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Milestone extends AbstractTimestampedBrowserAccessibleResource {

  private final int number;
  private final Issue.State state;
  private final String title;
  private final String description;
  private final User creator;
  private final long openIssues;
  private final long closedIssues;

  private final Instant closedAt;
  private final Instant dueOn;

  @JsonCreator
  public Milestone(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @JsonProperty(value = "number", required = true) int number,
      @NonNull @JsonProperty(value = "state", required = true) State state,
      @NonNull @JsonProperty(value = "title", required = true) String title,
      @Nullable @JsonProperty("description") String description,
      @NonNull @JsonProperty(value = "creator", required = true) User creator,
      @JsonProperty("open_issues") long openIssues,
      @JsonProperty("closed_issues") long closedIssues,
      @NonNull @JsonProperty(value = "html_url", required = true) URL browserUrl,
      @NonNull @JsonProperty(value = "created_at", required = true) Instant createdAt,
      @NonNull @JsonProperty(value = "updated_at", required = true) Instant updatedAt,
      @Nullable @JsonProperty("closed_at") Instant closedAt,
      @Nullable @JsonProperty("due_on") Instant dueOn) {
    super(id, browserUrl, createdAt, updatedAt);
    this.number = number;
    this.state = state;
    this.title = title;
    this.description = ValueUtility.toOptionalString(description);
    this.creator = creator;
    this.openIssues = openIssues;
    this.closedIssues = closedIssues;
    this.closedAt = closedAt;
    this.dueOn = dueOn;
  }

  /**
   * Retrieves a repository unique numeric identifier for this milestone.
   *
   * @return a numeric identifier.
   */
  public int getNumber() {
    return this.number;
  }

  /**
   * Retrieves the current state of this milestone.
   *
   * @return a state.
   */
  @NonNull
  public State getState() {
    return this.state;
  }

  /**
   * Retrieves a human readable title for this milestone.
   *
   * @return a title.
   */
  @NonNull
  public String getTitle() {
    return this.title;
  }

  /**
   * Retrieves a human readable description for this milestone (if any).
   *
   * @return a description.
   */
  @NonNull
  public Optional<String> getDescription() {
    return Optional.ofNullable(this.description);
  }

  /**
   * Retrieves the user which created this particular milestone.
   *
   * @return an author.
   */
  @NonNull
  public User getCreator() {
    return this.creator;
  }

  /**
   * Retrieves the total amount of open issues within this milestone.
   *
   * @return an amount of issues.
   */
  public long getOpenIssues() {
    return this.openIssues;
  }

  /**
   * Retrieves the total amount of closed issues within this milestone.
   *
   * @return an amount of issues.
   */
  public long getClosedIssues() {
    return this.closedIssues;
  }

  /**
   * Retrieves the date and time (in UTC) at which this issue has been closed.
   *
   * @return a date and time or, when the milestone is still open, an empty optional.
   */
  @NonNull
  public Optional<Instant> getClosedAt() {
    return Optional.ofNullable(this.closedAt);
  }

  /**
   * Retrieves the completion percentage for this milestone (ranging from zero to one where zero
   * indicates no progress towards the goal and one indicates completion).
   *
   * @return a percentage.
   */
  public double getCompletionPercentage() {
    return this.closedIssues / (double) (this.openIssues + this.closedIssues);
  }

  /**
   * Retrieves the date and time (in UTC) at which this milestone is expected to be completed.
   *
   * @return a date and time or, if no due date was set, an empty optional.
   */
  @NonNull
  public Optional<Instant> getDueOn() {
    return Optional.ofNullable(this.dueOn);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Milestone)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Milestone milestone = (Milestone) o;
    return this.number == milestone.number &&
        this.openIssues == milestone.openIssues &&
        this.closedIssues == milestone.closedIssues &&
        this.state == milestone.state &&
        Objects.equals(this.title, milestone.title) &&
        Objects.equals(this.description, milestone.description) &&
        Objects.equals(this.creator, milestone.creator) &&
        Objects.equals(this.closedAt, milestone.closedAt) &&
        Objects.equals(this.dueOn, milestone.dueOn);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(super.hashCode(), this.number, this.state, this.title, this.description, this.creator,
            this.openIssues,
            this.closedIssues,
            this.closedAt, this.dueOn);
  }
}
