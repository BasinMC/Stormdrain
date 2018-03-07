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

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Objects;
import org.basinmc.drain.resource.PageBuild;
import org.basinmc.drain.resource.Repository;
import org.basinmc.drain.resource.User;

/**
 * Represents an event which notifies its receivers of a change within the state of an ongoing page
 * build process (such as a newly queued build, its switch from queued to building or ultimately its
 * result).
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class PageBuildEvent extends AbstractRepositoryEvent {

  private final PageBuild build;

  public PageBuildEvent(
      @NonNull @JsonProperty(value = "build", required = true) PageBuild build,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(repository, sender);
    this.build = build;
  }

  /**
   * Retrieves the page build which this event represents.
   *
   * @return a build.
   */
  @NonNull
  public PageBuild getBuild() {
    return this.build;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PageBuildEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    PageBuildEvent that = (PageBuildEvent) o;
    return Objects.equals(this.build, that.build);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.build);
  }
}
