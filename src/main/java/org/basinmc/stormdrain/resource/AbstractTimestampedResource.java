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

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import org.basinmc.stormdrain.resource.Resource.Timestamped;
import org.basinmc.stormdrain.utility.ValueUtility;

/**
 * Provides an abstract timestamped resource implementation.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public abstract class AbstractTimestampedResource extends AbstractResource implements Timestamped {


  private final Instant creationTimestamp;
  private final Instant modificationTimestamp;

  protected AbstractTimestampedResource(
      @NonNull String id,
      @NonNull Instant creationTimestamp,
      @Nullable Instant modificationTimestamp) {
    super(id);
    this.creationTimestamp = creationTimestamp;
    this.modificationTimestamp = ValueUtility
        .toOptionalModificationTimestamp(creationTimestamp, modificationTimestamp);
  }

  /**
   * {@inheritDoc}
   */
  @NonNull
  @Override
  public Instant getCreationTimestamp() {
    return this.creationTimestamp;
  }

  /**
   * {@inheritDoc}
   */
  @NonNull
  @Override
  public Optional<Instant> getModificationTimestamp() {
    return Optional.ofNullable(this.modificationTimestamp);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractTimestampedResource)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    AbstractTimestampedResource that = (AbstractTimestampedResource) o;
    return Objects.equals(this.creationTimestamp, that.creationTimestamp) &&
        Objects.equals(this.modificationTimestamp, that.modificationTimestamp);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.creationTimestamp, this.modificationTimestamp);
  }
}
