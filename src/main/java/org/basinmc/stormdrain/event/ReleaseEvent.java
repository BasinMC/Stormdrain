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
package org.basinmc.stormdrain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Objects;
import org.basinmc.stormdrain.resource.Release;
import org.basinmc.stormdrain.resource.Repository;
import org.basinmc.stormdrain.resource.User;

/**
 * Represents an event which notifies its receivers of the creation of new releases.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class ReleaseEvent extends AbstractRepositoryEvent {

  private final Release release;

  @JsonCreator
  public ReleaseEvent(
      @NonNull @JsonProperty(value = "release", required = true) Release release,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(repository, sender);
    this.release = release;
  }

  /**
   * Retrieves the release which is represented by this event.
   *
   * @return a release.
   */
  @NonNull
  public Release getRelease() {
    return this.release;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ReleaseEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ReleaseEvent that = (ReleaseEvent) o;
    return Objects.equals(this.release, that.release);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.release);
  }
}
