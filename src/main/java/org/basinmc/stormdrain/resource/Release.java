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
 * Represents a released version.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Release extends AbstractBrowserAccessibleResource {

  private final String tagName;
  private final String name;
  private final String body;
  private final boolean draft;
  private final boolean prerelease;
  private final User author;
  private final URL tarballUrl;
  private final URL zipballUrl;
  private final Instant creationTimestamp;
  private final Instant publishingTimestamp;

  @JsonCreator
  public Release(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @NonNull @JsonProperty(value = "tag_name", required = true) String tagName,
      @Nullable @JsonProperty("name") String name,
      @Nullable @JsonProperty("body") String body,
      @JsonProperty("draft") boolean draft,
      @JsonProperty("prerelease") boolean prerelease,
      @NonNull @JsonProperty(value = "author", required = true) User author,
      @NonNull @JsonProperty(value = "tarball_url", required = true) URL tarballUrl,
      @NonNull @JsonProperty(value = "zipball_url", required = true) URL zipballUrl,
      @Nullable @JsonProperty(value = "html_url", required = true) URL htmlUrl,
      @NonNull @JsonProperty(value = "created_at", required = true) Instant creationTimestamp,
      @Nullable @JsonProperty(value = "published_at", required = true) Instant publishingTimestamp) {
    super(id, htmlUrl);
    this.tagName = tagName;
    this.name = name;
    this.body = body;
    this.draft = draft;
    this.prerelease = prerelease;
    this.author = author;
    this.tarballUrl = tarballUrl;
    this.zipballUrl = zipballUrl;
    this.creationTimestamp = creationTimestamp;
    this.publishingTimestamp = publishingTimestamp;
  }

  /**
   * Retrieves the name of the tag which is represented by this release.
   *
   * @return a tag name.
   */
  @NonNull
  public String getTagName() {
    return this.tagName;
  }

  /**
   * Retrieves a human readable name for this release.
   *
   * @return a name or, if none has been set, an empty optional.
   */
  @NonNull
  public Optional<String> getName() {
    return Optional.ofNullable(this.name);
  }

  /**
   * Retrieves a human readable description for this release.
   *
   * @return a description or, if none has been set, an empty optional.
   */
  @NonNull
  public Optional<String> getBody() {
    return Optional.ofNullable(this.body);
  }

  /**
   * Evaluates whether this release is a draft (e.g. has not been released yet).
   *
   * @return true if draft, false otherwise.
   */
  public boolean isDraft() {
    return this.draft;
  }

  /**
   * Evaluates whether this release is a preview (e.g. unstable).
   *
   * @return true if pre-release, false otherwise.
   */
  public boolean isPrerelease() {
    return this.prerelease;
  }

  /**
   * Retrieves the user which created this release.
   *
   * @return an author.
   */
  @NonNull
  public User getAuthor() {
    return this.author;
  }

  /**
   * Retrieves a URL for the tarball (e.g. a tar archive which contains the code from which this
   * release was composed according to the tag).
   *
   * @return a URL.
   */
  @NonNull
  public URL getTarballUrl() {
    return this.tarballUrl;
  }

  /**
   * Retrieves a URL for the zipball (e.g. a zip archive which contains the code from which this
   * release was composed according to the tag).
   *
   * @return a URL.
   */
  @NonNull
  public URL getZipballUrl() {
    return this.zipballUrl;
  }

  /**
   * Retrieves the date and time (in UTC) at which this release has been initially created.
   *
   * @return a creation timestamp.
   */
  @NonNull
  public Instant getCreationTimestamp() {
    return this.creationTimestamp;
  }

  /**
   * Retrieves the date and time (in UTC) at which this release has been published.
   *
   * @return a publishing timestamp or, if the release is still a draft, an empty optional.
   */
  @NonNull
  public Optional<Instant> getPublishingTimestamp() {
    return Optional.ofNullable(this.publishingTimestamp);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Release)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Release release = (Release) o;
    return this.draft == release.draft &&
        this.prerelease == release.prerelease &&
        Objects.equals(this.tagName, release.tagName) &&
        Objects.equals(this.name, release.name) &&
        Objects.equals(this.body, release.body) &&
        Objects.equals(this.author, release.author) &&
        Objects.equals(this.tarballUrl, release.tarballUrl) &&
        Objects.equals(this.zipballUrl, release.zipballUrl) &&
        Objects.equals(this.creationTimestamp, release.creationTimestamp) &&
        Objects.equals(this.publishingTimestamp, release.publishingTimestamp);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(super.hashCode(), this.tagName, this.name, this.body, this.draft, this.prerelease,
            this.author, this.tarballUrl,
            this.zipballUrl, this.creationTimestamp, this.publishingTimestamp);
  }

  /**
   * Provides a list of valid asset states.
   */
  public enum State {
    NEW,
    UPLOADED
  }

  /**
   * Represents a downloadable asset within a release.
   */
  public static class Asset extends AbstractTimestampedBrowserAccessibleResource {

    private final State state;
    private final String contentType;
    private final String name;
    private final String label;
    private final User uploader;
    private final long size;
    private final long downloadCount;

    @JsonCreator
    public Asset(
        @NonNull @JsonProperty(value = "id", required = true) String id,
        @NonNull @JsonProperty(value = "state", required = true) State state,
        @NonNull @JsonProperty(value = "content_type", required = true) String contentType,
        @NonNull @JsonProperty(value = "name", required = true) String name,
        @Nullable @JsonProperty("label") String label,
        @NonNull @JsonProperty(value = "uploader", required = true) User uploader,
        @JsonProperty("size") long size,
        @JsonProperty("download_count") long downloadCount,
        @NonNull @JsonProperty(value = "browser_download_url", required = true) URL browserUrl,
        @NonNull @JsonProperty(value = "created_at", required = true) Instant createdAt,
        @NonNull @JsonProperty(value = "updated_at", required = true) Instant updatedAt) {
      super(id, browserUrl, createdAt, updatedAt);
      this.state = state;
      this.contentType = contentType;
      this.name = name;
      this.label = label;
      this.uploader = uploader;
      this.size = size;
      this.downloadCount = downloadCount;
    }

    /**
     * Retrieves the current state of the asset.
     *
     * @return a state.
     */
    @NonNull
    public State getState() {
      return this.state;
    }

    /**
     * Retrieves the mime content type for this asset (application/octet-stream if unknown to
     * GitHub).
     *
     * @return a content type.
     */
    @NonNull
    public String getContentType() {
      return this.contentType;
    }

    /**
     * Retrieves the name for this asset (typically a filename).
     *
     * @return a name.
     */
    @NonNull
    public String getName() {
      return this.name;
    }

    /**
     * Retrieves a label for this asset.
     *
     * @return a label.
     */
    @Nullable
    public String getLabel() {
      return this.label;
    }

    /**
     * Retrieves the user which uploaded this asset.
     *
     * @return an uploader.
     */
    @NonNull
    public User getUploader() {
      return this.uploader;
    }

    /**
     * Retrieves the total size (in bytes) of this asset.
     *
     * @return a size.
     */
    public long getSize() {
      return this.size;
    }

    /**
     * Retrieves the total amount of times this asset has been downloaded.
     *
     * @return a download count.
     */
    public long getDownloadCount() {
      return this.downloadCount;
    }
  }
}
