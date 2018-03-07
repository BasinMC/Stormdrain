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
package org.basinmc.drain.event;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Objects;
import org.basinmc.drain.resource.Repository;
import org.basinmc.drain.resource.User;

/**
 * Provides a base for events which regard references.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public abstract class AbstractReferenceEvent extends AbstractRepositoryEvent {

  private final String reference;
  private final ReferenceType referenceType;

  public AbstractReferenceEvent(@NonNull String reference, @NonNull ReferenceType referenceType,
      @NonNull Repository repository, @NonNull User sender) {
    super(repository, sender);
    this.reference = reference;
    this.referenceType = referenceType;
  }

  /**
   * Retrieves the reference which this event refers to.
   *
   * @return a reference name.
   */
  @NonNull
  public String getReference() {
    return this.reference;
  }

  /**
   * Retrieves the type of reference the event refers to.
   *
   * @return a reference type.
   */
  @NonNull
  public ReferenceType getReferenceType() {
    return this.referenceType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractReferenceEvent)) {
      return false;
    }
    AbstractReferenceEvent that = (AbstractReferenceEvent) o;
    return Objects.equals(this.reference, that.reference) &&
        this.referenceType == that.referenceType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.reference, this.referenceType);
  }

  /**
   * Provides a list of valid reference types.
   */
  public enum ReferenceType {
    REPOSITORY,
    BRANCH,
    TAG
  }
}
