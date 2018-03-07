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
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an issue or pull request which has been created by a user on the repository (typically
 * when they do not have direct push access or access to the repository branches has been
 * restricted).
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Issue extends AbstractTimestampedBrowserAccessibleResource {

  private final long number;
  private final String title;
  private final String body;
  private final User user;
  private final State state;

  private final boolean locked;
  private final long comments;
  private final User assignee;
  private final Milestone milestone;
  private final Set<Label> labels;
  private final Instant closedAt;

  @JsonCreator
  public Issue(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @JsonProperty("number") long number,
      @NonNull @JsonProperty(value = "title", required = true) String title,
      @NonNull @JsonProperty(value = "body", required = true) String body,
      @NonNull @JsonProperty(value = "user", required = true) User user,
      @NonNull @JsonProperty(value = "state", required = true) State state,
      @JsonProperty("locked") boolean locked,
      @JsonProperty("comments") long comments,
      @Nullable @JsonProperty("assignee") User assignee,
      @Nullable @JsonProperty("milestone") Milestone milestone,
      @Nullable @JsonProperty("labels") Set<Label> labels,
      @Nullable @JsonProperty(value = "html_url", required = true) URL browserUrl,
      @NonNull @JsonProperty(value = "created_at", required = true) Instant createdAt,
      @NonNull @JsonProperty(value = "updated_at", required = true) Instant updatedAt,
      @Nullable @JsonProperty("closed_at") Instant closedAt) {
    super(id, browserUrl, createdAt, updatedAt);
    this.number = number;
    this.title = title;
    this.body = body;
    this.user = user;
    this.state = state;
    this.locked = locked;
    this.comments = comments;
    this.assignee = assignee;
    this.milestone = milestone;
    this.labels = labels == null ? null : new HashSet<>(labels);
    this.closedAt = closedAt;
  }

  /**
   * Retrieves a repository unique numeric identifier for this issue or pull request.
   *
   * @return a numeric identifier.
   */
  public long getNumber() {
    return this.number;
  }

  /**
   * Retrieves a human readable title for this issue or pull request.
   *
   * @return a title.
   */
  @NonNull
  public String getTitle() {
    return this.title;
  }

  /**
   * Retrieves the actual description of this issue or pull request which explains the problems or
   * proposed changes.
   *
   * @return a body.
   */
  @NonNull
  public String getBody() {
    return this.body;
  }

  /**
   * Retrieves the user which created this issue or pull request.
   *
   * @return an author.
   */
  @NonNull
  public User getUser() {
    return this.user;
  }

  /**
   * Retrieves the current state of this issue.
   *
   * @return an issue state.
   */
  @NonNull
  public State getState() {
    return this.state;
  }

  /**
   * Evaluates whether this issue or pull request been locked temporarily to prevent any further
   * changes.
   *
   * @return true if locked, false otherwise.
   */
  public boolean isLocked() {
    return this.locked;
  }

  /**
   * Retrieves the total amount of comments on this issue.
   *
   * @return an amount of comments.
   */
  public long getComments() {
    return this.comments;
  }

  /**
   * Retrieves the user to which this issue has been assigned (if any).
   *
   * @return a user.
   */
  @Nullable
  public User getAssignee() {
    return this.assignee;
  }

  /**
   * Retrieves the milestone to which this issue has been assigned (if any).
   *
   * @return a milestone.
   */
  @Nullable
  public Milestone getMilestone() {
    return this.milestone;
  }

  /**
   * Retrieves a set of labels which have been applied to this issue.
   *
   * @return a set of labels.
   */
  @NonNull
  public Set<Label> getLabels() {
    return Collections.unmodifiableSet(this.labels);
  }

  /**
   * Retrieves the time and date (in UTC) at which this issue has been closed (e.g. accepted or
   * denied).
   *
   * @return a date and time.
   */
  @Nullable
  public Instant getClosedAt() {
    return this.closedAt;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Issue)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Issue issue = (Issue) o;
    return this.number == issue.number &&
        this.locked == issue.locked &&
        this.comments == issue.comments &&
        Objects.equals(this.title, issue.title) &&
        Objects.equals(this.body, issue.body) &&
        Objects.equals(this.user, issue.user) &&
        this.state == issue.state &&
        Objects.equals(this.assignee, issue.assignee) &&
        Objects.equals(this.milestone, issue.milestone) &&
        Objects.equals(this.labels, issue.labels) &&
        Objects.equals(this.closedAt, issue.closedAt);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(super.hashCode(), this.number, this.title, this.body, this.user, this.state,
            this.locked, this.comments, this.assignee,
            this.milestone, this.labels, this.closedAt);
  }

  /**
   * Provides a list of valid issue, milestone and pull request states.
   */
  public enum State {
    OPEN,
    CLOSED
  }

  /**
   * Represents an issue label.
   */
  public static class Label {

    private final String name;
    private final int color;

    public Label(@NonNull String name, int color) {
      this.name = name;
      this.color = color;
    }

    @JsonCreator
    public Label(
        @NonNull @JsonProperty(value = "name", required = true) String name,
        @NonNull @JsonProperty(value = "color", required = true) String color) {
      this(name, (int) (Long.parseUnsignedLong(color, 16) & 0xFFFFFFFFL));
    }

    @NonNull
    public String getName() {
      return this.name;
    }

    public int getColor() {
      return this.color;
    }

    @NonNull
    public String getColorHex() {
      return Long.toHexString(this.color & 0xFFFFFFFFL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Label)) {
        return false;
      }
      Label label = (Label) o;
      return this.color == label.color &&
          Objects.equals(this.name, label.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
      return Objects.hash(this.name, this.color);
    }
  }
}
