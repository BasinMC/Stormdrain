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
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a deployment using an arbitrary tool in an arbitrary deployment environment.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Deployment extends AbstractTimestampedResource {

  private final String environment;
  private final String description;
  private final User creator;

  @JsonCreator
  public Deployment(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @NonNull @JsonProperty(value = "environment", required = true) String environment,
      @Nullable @JsonProperty("description") String description,
      @NonNull @JsonProperty(value = "creator", required = true) User creator,
      @NonNull @JsonProperty(value = "created_at", required = true) Instant createdAt,
      @NonNull @JsonProperty(value = "updated_at", required = true) Instant updatedAt) {
    super(id, createdAt, updatedAt);
    this.environment = environment;
    this.description = description;
    this.creator = creator;
  }

  /**
   * Retrieves an arbitrarily defined deployment environment on which this deployment took place.
   *
   * @return an environment.
   */
  @NonNull
  public String getEnvironment() {
    return this.environment;
  }

  /**
   * Retrieves a human readable description for this deployment.
   *
   * @return a description.
   */
  @NonNull
  public Optional<String> getDescription() {
    return Optional.ofNullable(this.description);
  }

  /**
   * Retrieves the user which authored this deployment (typically the author of a commit).
   *
   * @return an author.
   */
  @NonNull
  public User getCreator() {
    return this.creator;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Deployment)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Deployment that = (Deployment) o;
    return Objects.equals(this.environment, that.environment) &&
        Objects.equals(this.description, that.description) &&
        Objects.equals(this.creator, that.creator);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.environment, this.description, this.creator);
  }
}
