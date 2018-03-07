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
import java.util.Objects;

/**
 * Represents a reference to a repository branch.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Reference {

  private final String label;
  private final String reference;
  private final User user;
  private final Repository repository;

  @JsonCreator
  public Reference(
      @NonNull @JsonProperty(value = "label", required = true) String label,
      @NonNull @JsonProperty(value = "ref", required = true) String reference,
      @NonNull @JsonProperty(value = "user", required = true) User user,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository) {
    this.label = label;
    this.reference = reference;
    this.user = user;
    this.repository = repository;
  }

  /**
   * Retrieves a human readable label for this reference.
   *
   * @return a label.
   */
  @NonNull
  public String getLabel() {
    return this.label;
  }

  /**
   * Retrieves the actual reference name.
   *
   * @return a reference name.
   */
  @NonNull
  public String getReference() {
    return this.reference;
  }

  /**
   * Retrieves the user who created this reference.
   *
   * @return a reference.
   */
  @NonNull
  public User getUser() {
    return this.user;
  }

  /**
   * Retrieves the repository in which this reference was created.
   *
   * @return a repository.
   */
  @NonNull
  public Repository getRepository() {
    return this.repository;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Reference)) {
      return false;
    }
    Reference reference1 = (Reference) o;
    return Objects.equals(this.label, reference1.label) &&
        Objects.equals(this.reference, reference1.reference) &&
        Objects.equals(this.user, reference1.user) &&
        Objects.equals(this.repository, reference1.repository);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.label, this.reference, this.user, this.repository);
  }
}
