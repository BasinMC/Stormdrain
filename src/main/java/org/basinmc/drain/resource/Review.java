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
 * Represents a pull request review.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Review extends AbstractBrowserAccessibleResource {

  private final String body;
  private final Instant submissionTimestamp;
  private final State state;

  @JsonCreator
  public Review(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @NonNull @JsonProperty(value = "state", required = true) State state,
      @NonNull @JsonProperty(value = "body", required = true) String body,
      @NonNull @JsonProperty(value = "submitted_at", required = true) Instant submissionTimestamp,
      @NonNull @JsonProperty(value = "html_url", required = true) URL browserUrl) {
    super(id, browserUrl);
    this.body = body;
    this.submissionTimestamp = submissionTimestamp;
    this.state = state;
  }

  /**
   * Retrieves the human readable contents of this review.
   *
   * @return a description.
   */
  @NonNull
  public String getBody() {
    return this.body;
  }

  /**
   * Retrieves the time and date (in UTC) at which this review has been submitted.
   *
   * @return a time and date.
   */
  @NonNull
  public Instant getSubmissionTimestamp() {
    return this.submissionTimestamp;
  }

  /**
   * Retrieves the state of this review.
   *
   * @return a state.
   */
  @NonNull
  public State getState() {
    return this.state;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Review)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Review review = (Review) o;
    return Objects.equals(this.body, review.body) &&
        Objects.equals(this.submissionTimestamp, review.submissionTimestamp) &&
        this.state == review.state;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.body, this.submissionTimestamp, this.state);
  }

  /**
   * Provides a list of valid review states.
   */
  public enum State {
    PENDING,
    CHANGES_REQUESTED,
    APPROVED,
    DISMISSED,
  }
}
