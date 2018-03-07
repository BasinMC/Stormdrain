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
import java.net.URL;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents a review comment.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class ReviewComment extends Comment {

  private final String path;
  private final String diffHunk;
  private final int position;
  private final int originalPosition;

  @JsonCreator
  public ReviewComment(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @NonNull @JsonProperty(value = "body", required = true) String body,
      @NonNull @JsonProperty(value = "path", required = true) String path,
      @NonNull @JsonProperty(value = "diff_hunk", required = true) String diffHunk,
      @JsonProperty("position") int position,
      @JsonProperty("original_position") int originalPosition,
      @NonNull @JsonProperty(value = "user", required = true) User user,
      @Nullable @JsonProperty(value = "html_url", required = true) URL browserUrl,
      @NonNull @JsonProperty(value = "created_at", required = true) Instant createdAt,
      @NonNull @JsonProperty(value = "updated_at", required = true) Instant updatedAt) {
    super(id, body, user, browserUrl, createdAt, updatedAt);
    this.path = path;
    this.diffHunk = diffHunk;
    this.position = position;
    this.originalPosition = originalPosition;
  }

  /**
   * Retrieves the relative file path on which this comment was placed.
   *
   * @return a path.
   */
  @NonNull
  public String getPath() {
    return this.path;
  }

  /**
   * Retrieves the diff on which this comment was placed.
   *
   * @return a diff.
   */
  @NonNull
  public String getDiffHunk() {
    return this.diffHunk;
  }

  /**
   * Retrieves the line at which this comment was placed.
   *
   * @return a line number.
   */
  public int getPosition() {
    return this.position;
  }

  /**
   * Retrieves the original line at which this comment was placed.
   *
   * @return a line number.
   */
  public int getOriginalPosition() {
    return this.originalPosition;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ReviewComment)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ReviewComment that = (ReviewComment) o;
    return this.position == that.position &&
        this.originalPosition == that.originalPosition &&
        Objects.equals(this.path, that.path) &&
        Objects.equals(this.diffHunk, that.diffHunk);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(super.hashCode(), this.path, this.diffHunk, this.position, this.originalPosition);
  }
}
