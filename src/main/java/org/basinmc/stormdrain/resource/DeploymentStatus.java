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

/**
 * Represents the current status of a deployment.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class DeploymentStatus extends AbstractTimestampedResource {

  private final State state;
  private final String description;
  private final URL targetUrl;

  @JsonCreator
  public DeploymentStatus(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @NonNull @JsonProperty(value = "state", required = true) State state,
      @Nullable @JsonProperty(value = "description", required = true) String description,
      @Nullable @JsonProperty("target_url") URL targetUrl,
      @NonNull @JsonProperty(value = "created_at", required = true) Instant createdAt,
      @NonNull @JsonProperty(value = "updated_at", required = true) Instant updatedAt) {
    super(id, createdAt, updatedAt);
    this.state = state;
    this.description = description;
    this.targetUrl = targetUrl;
  }

  /**
   * Retrieves the current state of the referenced deployment.
   *
   * @return a state.
   */
  @NonNull
  public State getState() {
    return this.state;
  }

  /**
   * Retrieves a human readable description for the current deployment state.
   *
   * @return a description or, if none has been given, an empty optional.
   */
  @NonNull
  public Optional<String> getDescription() {
    return Optional.ofNullable(this.description);
  }

  /**
   * Retrieves a target URL where the details of the deployment can be viewed.
   *
   * @return a target url or, if none is given by the deployment backend, an empty optional.
   */
  @NonNull
  public Optional<URL> getTargetUrl() {
    return Optional.ofNullable(this.targetUrl);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DeploymentStatus)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    DeploymentStatus that = (DeploymentStatus) o;
    return this.state == that.state &&
        Objects.equals(this.description, that.description) &&
        Objects.equals(this.targetUrl, that.targetUrl);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.state, this.description, this.targetUrl);
  }

  /**
   * Provides a list of valid deployment states.
   */
  public enum State {
    ERROR,
    FAILURE,
    INACTIVE,
    PENDING,
    SUCCESS
  }
}
