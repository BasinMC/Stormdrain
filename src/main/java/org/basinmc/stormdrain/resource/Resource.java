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
import java.net.URL;
import java.time.Instant;
import java.util.Optional;

/**
 * A typical GitHub resource which has been assigned a globally unique identification number.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface Resource {

  /**
   * <p>Retrieves the globally unique identifier for this resource within GitHub's system.</p>
   *
   * <p>While typically not used to retrieve a specific resource from the GitHub API, it may still
   * be used to recognize and associate with this particular resource in a safe manner as this ID
   * will always refer to this resource and is never re-allocated.</p>
   *
   * @return a numeric identifier.
   */
  String getId();

  /**
   * Represents a browser accessible resource which provides an HTML or otherwise browser friendly
   * page to view this resource at.
   */
  interface BrowserAccessible {

    /**
     * <p>Retrieves the URL at which this resource is directly accessible on the GitHub website
     * itself.</p>
     *
     * <p>This will typically refer to an HTML page (for instance the user interface of a repository
     * where users can browse the repository contents, read its documentation, etc).</p>
     *
     * @return a url pointing to a browser friendly page for this resource.
     */
    @NonNull
    URL getBrowserUrl();
  }

  /**
   * Represents a resource which identifies its creation and change timestamps respectively.
   */
  interface Timestamped {

    /**
     * Retrieves the time and date (in UTC) at which this particular resource has been initially
     * created.
     *
     * @return a timestamp.
     */
    @NonNull
    Instant getCreationTimestamp();

    /**
     * Retrieves the time and date (in UTC) at which this particular resource has last been
     * changed.
     *
     * @return a timestamp.
     */
    @NonNull
    Optional<Instant> getModificationTimestamp();
  }
}
