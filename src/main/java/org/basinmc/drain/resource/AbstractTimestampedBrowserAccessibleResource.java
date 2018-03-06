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

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.net.URL;
import java.time.Instant;
import java.util.Objects;
import org.basinmc.drain.resource.Resource.BrowserAccessible;

/**
 * Provides an abstract base for timestamped, browser accessible resources.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public abstract class AbstractTimestampedBrowserAccessibleResource extends
    AbstractTimestampedResource implements BrowserAccessible {

  private final URL browserUrl;

  protected AbstractTimestampedBrowserAccessibleResource(
      @NonNull String id,
      @NonNull URL browserUrl,
      @NonNull Instant creationTimestamp,
      @Nullable Instant modificationTimestamp) {
    super(id, creationTimestamp, modificationTimestamp);
    this.browserUrl = browserUrl;
  }

  /**
   * {@inheritDoc}
   */
  @NonNull
  @Override
  public URL getBrowserUrl() {
    return this.browserUrl;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractTimestampedBrowserAccessibleResource)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    AbstractTimestampedBrowserAccessibleResource that = (AbstractTimestampedBrowserAccessibleResource) o;
    return Objects.equals(this.browserUrl, that.browserUrl);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.browserUrl);
  }
}
