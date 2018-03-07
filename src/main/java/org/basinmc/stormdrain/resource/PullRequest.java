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
import java.util.Set;

/**
 * Represents a pull request.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class PullRequest extends Issue {

  private final String mergeCommitId;
  private final Reference head;
  private final Reference base;
  private final URL diffUrl;
  private final URL patchUrl;
  private final Instant mergedAt;

  @JsonCreator
  public PullRequest(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @JsonProperty("number") long number,
      @NonNull @JsonProperty(value = "title", required = true) String title,
      @Nullable @JsonProperty("body") String body,
      @NonNull @JsonProperty(value = "user", required = true) User user,
      @NonNull @JsonProperty(value = "state", required = true) State state,
      @Nullable @JsonProperty("merge_commit_sha") String mergeCommitId,
      @NonNull @JsonProperty(value = "head", required = true) Reference head,
      @NonNull @JsonProperty(value = "base", required = true) Reference base,
      @JsonProperty("locked") boolean locked,
      @JsonProperty("comments") long comments,
      @Nullable @JsonProperty("assignee") User assignee,
      @Nullable @JsonProperty("milestone") Milestone milestone,
      @Nullable @JsonProperty("labels") Set<Label> labels,
      @NonNull @JsonProperty(value = "html_url", required = true) URL htmlUrl,
      @NonNull @JsonProperty(value = "diff_url", required = true) URL diffUrl,
      @NonNull @JsonProperty(value = "patch_url", required = true) URL patchUrl,
      @NonNull @JsonProperty(value = "created_at", required = true) Instant createdAt,
      @NonNull @JsonProperty(value = "updated_at", required = true) Instant updatedAt,
      @Nullable @JsonProperty("closed_at") Instant closedAt,
      @Nullable @JsonProperty("merged_at") Instant mergedAt) {
    super(id, number, title, body, user, state, locked, comments, assignee, milestone, labels,
        htmlUrl, createdAt, updatedAt, closedAt);
    this.mergeCommitId = mergeCommitId;
    this.head = head;
    this.base = base;
    this.diffUrl = diffUrl;
    this.patchUrl = patchUrl;
    this.mergedAt = mergedAt;
  }

  /**
   * Retrieves the hash of the commit which merged this pull request into the repository.
   *
   * @return a commit hash or, if the pull request has not been merged yet, an empty optional.
   */
  @NonNull
  public Optional<String> getMergeCommitId() {
    return Optional.ofNullable(this.mergeCommitId);
  }

  /**
   * Retrieves the pull request's head reference.
   *
   * @return a commit.
   */
  @NonNull
  public Reference getHead() {
    return this.head;
  }

  /**
   * Retrieves the pull request's base reference.
   *
   * @return a commit.
   */
  @NonNull
  public Reference getBase() {
    return this.base;
  }

  /**
   * Retrieves the date and time (in UTC) at which this request was merged (if any).
   *
   * @return a date and time or, if the pull request has not been merged yet, an empty optional.
   */
  @NonNull
  public Optional<Instant> getMergedAt() {
    return Optional.ofNullable(this.mergedAt);
  }

  /**
   * Retrieves the URL from which a unified diff of this pull request can be retrieved.
   *
   * @return a url.
   */
  @NonNull
  public URL getDiffUrl() {
    return this.diffUrl;
  }

  /**
   * Retrieves the URL from which a git patch of this pull request can be retrieved.
   *
   * @return a url.
   */
  @NonNull
  public URL getPatchUrl() {
    return this.patchUrl;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PullRequest)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    PullRequest that = (PullRequest) o;
    return Objects.equals(this.mergedAt, that.mergedAt) &&
        Objects.equals(this.mergeCommitId, that.mergeCommitId) &&
        Objects.equals(this.head, that.head) &&
        Objects.equals(this.base, that.base);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.mergedAt, this.mergeCommitId, this.head, this.base);
  }
}
