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
package org.basinmc.drain.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.basinmc.drain.resource.Resource.Timestamped;

/**
 * Represents a page's build state or result.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class PageBuild implements Timestamped {

  private final Status status;
  private final String errorMessage;
  private final Duration duration;
  private final String commitId;
  private final User pusher;
  private final Instant creationTimestamp;
  private final Instant modificationTimestamp;

  public PageBuild(
      @Nullable Status status,
      @Nullable String errorMessage,
      @Nullable Duration duration,
      @NonNull String commitId,
      @NonNull User pusher,
      @NonNull Instant creationTimestamp,
      @NonNull Instant modificationTimestamp) {
    this.status = status;
    this.errorMessage = errorMessage;
    this.duration = duration;
    this.commitId = commitId;
    this.pusher = pusher;
    this.creationTimestamp = creationTimestamp;
    this.modificationTimestamp = modificationTimestamp;
  }

  @JsonCreator
  protected PageBuild(
      @JsonProperty("status") Status status,
      @Nullable @JsonProperty("error") Map<String, String> error,
      @Nullable @JsonProperty("duration") Duration duration,
      @NonNull @JsonProperty(value = "commit", required = true) String commitId,
      @NonNull @JsonProperty(value = "pusher", required = true) User pusher,
      @NonNull @JsonProperty(value = "created_at", required = true) Instant creationTimestamp,
      @Nullable @JsonProperty("updated_at") Instant modificationTimestamp) {
    this.status = status != null ? status : Status.NONE;
    this.errorMessage = error != null ? error.get("message") : null;
    this.duration = duration;
    this.commitId = commitId;
    this.pusher = pusher;
    this.creationTimestamp = creationTimestamp;
    this.modificationTimestamp = modificationTimestamp;
  }

  /**
   * Retrieves the current state of this page build (or null if none has been assigned yet).
   *
   * @return a state.
   */
  @NonNull
  public Status getStatus() {
    return this.status;
  }

  /**
   * Retrieves an error message for this page build (if the build has failed).
   *
   * @return an error message or, if the build has not been completed yet or has succeded, an empty
   * optional.
   */
  @NonNull
  public Optional<String> getErrorMessage() {
    return Optional.ofNullable(this.errorMessage);
  }

  /**
   * Retrieves the total amount of time this build took to complete (or fail).
   *
   * @return a duration.
   */
  @NonNull
  // TODO: Possibly GitHub tracks this from the start of the build (e.g. not optional)
  public Optional<Duration> getDuration() {
    return Optional.ofNullable(this.duration);
  }

  /**
   * Retrieves the hash of the commit which triggered this page build.
   *
   * @return a commit hash.
   */
  @NonNull
  public String getCommitId() {
    return this.commitId;
  }

  /**
   * Retrieves the user which pushed the commit that triggered this builder.
   *
   * @return an author.
   */
  @NonNull
  public User getPusher() {
    return this.pusher;
  }

  /**
   * {@inheritDoc}
   */
  @NonNull
  @Override
  public Instant getCreationTimestamp() {
    return this.creationTimestamp;
  }

  /**
   * {@inheritDoc}
   */
  @NonNull
  @Override
  public Optional<Instant> getModificationTimestamp() {
    return Optional.ofNullable(this.modificationTimestamp);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PageBuild)) {
      return false;
    }
    PageBuild pageBuild = (PageBuild) o;
    return this.status == pageBuild.status &&
        Objects.equals(this.errorMessage, pageBuild.errorMessage) &&
        Objects.equals(this.duration, pageBuild.duration) &&
        Objects.equals(this.commitId, pageBuild.commitId) &&
        Objects.equals(this.pusher, pageBuild.pusher) &&
        Objects.equals(this.creationTimestamp, pageBuild.creationTimestamp) &&
        Objects.equals(this.modificationTimestamp, pageBuild.modificationTimestamp);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.status, this.errorMessage, this.duration, this.commitId, this.pusher,
        this.creationTimestamp, this.modificationTimestamp);
  }

  /**
   * Provides a list of value build states.
   */
  public enum Status {
    NONE,
    QUEUED,
    BUILDING,
    BUILT,
    ERRORED
  }
}
