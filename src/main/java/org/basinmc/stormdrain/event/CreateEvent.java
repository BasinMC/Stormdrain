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
import org.basinmc.stormdrain.resource.Repository;
import org.basinmc.stormdrain.resource.User;

/**
 * Represents an event which notifies its receiver of the creation of a new reference (a branch or
 * tag).
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class CreateEvent extends AbstractReferenceEvent {

  private final String defaultBranch;

  @JsonCreator
  public CreateEvent(
      @NonNull @JsonProperty(value = "ref", required = true) String reference,
      @NonNull @JsonProperty(value = "ref_type", required = true) ReferenceType referenceType,
      @NonNull @JsonProperty(value = "master_branch", required = true) String defaultBranch,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(reference, referenceType, repository, sender);
    this.defaultBranch = defaultBranch;
  }

  /**
   * Retrieves the currently configured default branch for the affected repository.
   *
   * @return a default branch.
   */
  @NonNull
  public String getDefaultBranch() {
    return this.defaultBranch;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CreateEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    CreateEvent that = (CreateEvent) o;
    return Objects.equals(this.defaultBranch, that.defaultBranch);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.defaultBranch);
  }
}
