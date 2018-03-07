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
import java.util.Objects;
import java.util.Optional;
import org.basinmc.stormdrain.resource.Resource.BrowserAccessible;

/**
 * Represents a Gollum wiki page and its respective changes.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class GollumPage implements BrowserAccessible {

  private final String pageName;
  private final String title;
  private final String summary;
  private final Action action;
  private final String commitId;
  private final URL browserUrl;

  @JsonCreator
  public GollumPage(
      @NonNull @JsonProperty(value = "page_name", required = true) String pageName,
      @NonNull @JsonProperty(value = "title", required = true) String title,
      @Nullable @JsonProperty("summary") String summary,
      @NonNull @JsonProperty(value = "action", required = true) Action action,
      @NonNull @JsonProperty(value = "sha", required = true) String commitId,
      @NonNull @JsonProperty(value = "html_url", required = true) URL browserUrl) {
    this.pageName = pageName;
    this.title = title;
    this.summary = summary;
    this.action = action;
    this.commitId = commitId;
    this.browserUrl = browserUrl;
  }

  /**
   * Retrieves a URL friendly name for this page.
   *
   * @return a name.
   */
  @NonNull
  public String getPageName() {
    return this.pageName;
  }

  /**
   * Retrieves a human readable name for this page.
   *
   * @return a name.
   */
  @NonNull
  public String getTitle() {
    return this.title;
  }

  /**
   * Retrieves a short summary for this page.
   *
   * @return a summary or, if no summary was given, an empty optional.
   */
  @NonNull
  public Optional<String> getSummary() {
    return Optional.ofNullable(this.summary);
  }

  /**
   * Retrieves the action which has been applied for this page.
   *
   * @return an action.
   */
  @NonNull
  public Action getAction() {
    return this.action;
  }

  /**
   * Retrieves the commit hash from which this page change originated.
   *
   * @return a commit hash.
   */
  @NonNull
  public String getCommitId() {
    return this.commitId;
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
    if (!(o instanceof GollumPage)) {
      return false;
    }
    GollumPage that = (GollumPage) o;
    return Objects.equals(this.pageName, that.pageName) &&
        Objects.equals(this.title, that.title) &&
        Objects.equals(this.summary, that.summary) &&
        this.action == that.action &&
        Objects.equals(this.commitId, that.commitId) &&
        Objects.equals(this.browserUrl, that.browserUrl);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(this.pageName, this.title, this.summary, this.action, this.commitId, this.browserUrl);
  }

  public enum Action {
    CREATED,
    EDITED
  }
}
