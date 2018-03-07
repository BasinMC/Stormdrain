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
import java.net.URL;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents a comment which has been made on source code, an issue or a pull request.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Comment extends AbstractTimestampedBrowserAccessibleResource {

  private final String body;
  private final User user;

  @JsonCreator
  public Comment(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @NonNull @JsonProperty(value = "body", required = true) String body,
      @NonNull @JsonProperty(value = "user", required = true) User user,
      @NonNull @JsonProperty(value = "html_url", required = true) URL browserUrl,
      @NonNull @JsonProperty(value = "created_at", required = true) Instant createdAt,
      @NonNull @JsonProperty(value = "updated_at", required = true) Instant updatedAt) {
    super(id, browserUrl, createdAt, updatedAt);
    this.body = body;
    this.user = user;
  }

  /**
   * Retrieves the actual text within this comment.
   *
   * @return a comment body.
   */
  @NonNull
  public String getBody() {
    return this.body;
  }

  /**
   * Retrieves the author of this comment.
   *
   * @return an author.
   */
  @NonNull
  public User getUser() {
    return this.user;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Comment)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Comment comment = (Comment) o;
    return Objects.equals(this.body, comment.body) &&
        Objects.equals(this.user, comment.user);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.body, this.user);
  }
}
