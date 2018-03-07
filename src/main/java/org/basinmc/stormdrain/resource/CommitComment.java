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

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.net.URL;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a comment which has been placed on a particular file within a commit.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class CommitComment extends Comment {

  private final String commitId;
  private final Location location;

  public CommitComment(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @NonNull @JsonProperty(value = "commit_id", required = true) String commitId,
      @Nullable @JsonProperty(value = "line", required = true) Integer line,
      @Nullable @JsonProperty("path") String path,
      @NonNull @JsonProperty(value = "user", required = true) User user,
      @NonNull @JsonProperty(value = "body", required = true) String body,
      @NonNull @JsonProperty(value = "html_url", required = true) URL browserUrl,
      @NonNull @JsonProperty(value = "created_at", required = true) Instant createdAt,
      @NonNull @JsonProperty(value = "updated_at", required = true) Instant updatedAt) {
    super(id, body, user, browserUrl, createdAt, updatedAt);
    this.commitId = commitId;

    if ((path != null && line == null) || (path == null && line != null)) {
      throw new IllegalArgumentException(
          "Illegal comment location: File comments must specify line and path");
    }

    this.location = path != null ? new Location(path, line) : null;
  }

  /**
   * Retrieves the sha hash for the commit this comment has been attached to.
   *
   * @return a commit hash.
   */
  @NonNull
  public String getCommitId() {
    return this.commitId;
  }

  /**
   * Retrieves the location at which this comment has been placed (e.g. when the comment has been
   * placed on a specific change).
   *
   * @return a location or, if commit has not been placed on a specific file, an empty optional.
   */
  @NonNull
  public Optional<Location> getLocation() {
    return Optional.ofNullable(this.location);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CommitComment)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    CommitComment comment = (CommitComment) o;
    return Objects.equals(this.commitId, comment.commitId) &&
        Objects.equals(this.location, comment.location);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.commitId, this.location);
  }

  /**
   * Represents the location at which a comment was placed.
   */
  public static class Location {

    private final String path;
    private final int line;

    public Location(@NonNull String path, int line) {
      this.path = path;
      this.line = line;
    }

    @NonNull
    public String getPath() {
      return this.path;
    }

    public int getLine() {
      return this.line;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Location)) {
        return false;
      }
      Location location = (Location) o;
      return this.line == location.line &&
          Objects.equals(this.path, location.path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
      return Objects.hash(this.path, this.line);
    }
  }
}
