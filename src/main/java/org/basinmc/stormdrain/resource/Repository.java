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

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.net.URL;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a repository which is owned by a user or organization.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Repository extends AbstractTimestampedBrowserAccessibleResource {

  private final String name;
  private final String fullName;
  private final ResourceOwner owner;
  private final String description;
  private final boolean privateAccess;
  private final boolean fork;

  private final String gitUrl;
  private final String sshUrl;
  private final String cloneUrl;
  private final String svnUrl;
  private final String mirrorUrl;
  private final URL homepageUrl;
  private final String defaultBranch;

  private final Instant pushedAt;
  private final long size;
  private final String primaryLanguage;

  private final int stargazersCount;
  private final int watchersCount;
  private final int forksCount;
  private final int openIssuesCount;

  private final boolean hasIssues;
  private final boolean hasDownloads;
  private final boolean hasWiki;
  private final boolean hasPages;

  public Repository(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @NonNull @JsonProperty(value = "name", required = true) String name,
      @NonNull @JsonProperty(value = "full_name", required = true) String fullName,
      @Nullable @JsonProperty("description") String description,
      @NonNull @JsonProperty(value = "owner", required = true) ResourceOwner owner,
      @JsonProperty("is_private") boolean privateAccess,
      @JsonProperty("fork") boolean fork,
      @NonNull @JsonProperty(value = "git_url", required = true) String gitUrl,
      @NonNull @JsonProperty(value = "ssh_url", required = true) String sshUrl,
      @NonNull @JsonProperty(value = "clone_url", required = true) String cloneUrl,
      @NonNull @JsonProperty(value = "svn_url", required = true) String svnUrl,
      @Nullable @JsonProperty("mirror_url") String mirrorUrl,
      @Nullable @JsonProperty("homepage") URL homepageUrl,
      @NonNull @JsonProperty(value = "default_branch", required = true) String defaultBranch,
      @JsonProperty(value = "size", required = true) long size,
      @Nullable @JsonProperty("language") String primaryLanguage,
      @JsonProperty(value = "stargazers_count", required = true) int stargazersCount,
      @JsonProperty(value = "watchers_count", required = true) int watchersCount,
      @JsonProperty(value = "forks_count", required = true) int forksCount,
      @JsonProperty("open_issues_count") int openIssuesCount,
      @JsonProperty("has_issues") boolean hasIssues,
      @JsonProperty("has_downloads") boolean hasDownloads,
      @JsonProperty("has_wiki") boolean hasWiki,
      @JsonProperty("has_pages") boolean hasPages,
      @NonNull @JsonProperty(value = "html_url", required = true) URL browserUrl,
      @NonNull @JsonProperty(value = "created_at", required = true) Instant createdAt,
      @NonNull @JsonProperty(value = "updated_at", required = true) Instant updatedAt,
      @Nullable @JsonProperty("pushed_at") Instant pushedAt) {
    super(id, browserUrl, createdAt, updatedAt);
    this.name = name;
    this.fullName = fullName;
    this.owner = owner;
    this.privateAccess = privateAccess;
    this.description = description;
    this.fork = fork;
    this.gitUrl = gitUrl;
    this.sshUrl = sshUrl;
    this.cloneUrl = cloneUrl;
    this.svnUrl = svnUrl;
    this.mirrorUrl = mirrorUrl;
    this.homepageUrl = homepageUrl;
    this.defaultBranch = defaultBranch;
    this.pushedAt = pushedAt;
    this.size = size;
    this.primaryLanguage = primaryLanguage;
    this.stargazersCount = stargazersCount;
    this.watchersCount = watchersCount;
    this.forksCount = forksCount;
    this.openIssuesCount = openIssuesCount;
    this.hasIssues = hasIssues;
    this.hasDownloads = hasDownloads;
    this.hasWiki = hasWiki;
    this.hasPages = hasPages;
  }

  /**
   * Retrieves the simple name of this repository (e.g. the name without its owner).
   *
   * @return a name.
   */
  @NonNull
  public String getName() {
    return this.name;
  }

  /**
   * Retrieves the fully qualified name of this repository (e.g. the name of this repository in
   * addition to its owner's name).
   *
   * @return a name.
   */
  @NonNull
  public String getFullName() {
    return this.fullName;
  }

  /**
   * Retrieves the user or organization to which this repository belongs.
   *
   * @return an owner.
   */
  @NonNull
  public ResourceOwner getOwner() {
    return this.owner;
  }

  /**
   * Retrieves a human readable description for this repository.
   *
   * @return a description or, if none has been set, an empty optional.
   */
  @NonNull
  public Optional<String> getDescription() {
    return Optional.ofNullable(this.description);
  }

  /**
   * Evaluates whether this repository is marked private and thus only accessible to a certain set
   * of users.
   *
   * @return true if private, false otherwise.
   */
  public boolean isPrivateAccess() {
    return this.privateAccess;
  }

  /**
   * Evaluates whether this repository is a fork of another repository.
   *
   * @return true if forked, false otherwise.
   */
  public boolean isFork() {
    return this.fork;
  }

  /**
   * Retrieves the URL from which this repository can be cloned (using the git protocol).
   *
   * @return a clone URL.
   */
  @NonNull
  public String getGitUrl() {
    return this.gitUrl;
  }

  /**
   * Retrieves the URL from which this repository can be cloned (using the ssh protocol).
   *
   * @return a clone URL.
   */
  @NonNull
  public String getSshUrl() {
    return this.sshUrl;
  }

  /**
   * Retrieves the URL from which this repository can be cloned (using the https protocol).
   *
   * @return a clone URL.
   */
  @NonNull
  public String getCloneUrl() {
    return this.cloneUrl;
  }

  /**
   * Retrieves the URL from which this repository can be cloned (using svn compatible mode).
   *
   * @return a clone URL.
   */
  @NonNull
  public String getSvnUrl() {
    return this.svnUrl;
  }

  /**
   * Retrieves the URL from which this repository is mirrored.
   *
   * @return a mirror source URL or, if this repository is not a mirror, an empty optional.
   */
  @NonNull
  public Optional<String> getMirrorUrl() {
    return Optional.ofNullable(this.mirrorUrl);
  }

  /**
   * Retrieves the homepage URL for this particular repository.
   *
   * @return a homepage URL or, if none has been set yet, an empty optional.
   */
  @NonNull
  public Optional<URL> getHomepageUrl() {
    return Optional.ofNullable(this.homepageUrl);
  }

  /**
   * Retrieves the default branch for this repository (typically "master").
   *
   * @return a branch.
   */
  @NonNull
  public String getDefaultBranch() {
    return this.defaultBranch;
  }

  /**
   * Retrieves the last date and time (in UTC) at which a set of commits were pushed to this
   * repository.
   *
   * @return a date and time or, if nothing has been pushed to the repository yet, an empty
   * optional.
   */
  @NonNull
  public Optional<Instant> getPushedAt() {
    return Optional.ofNullable(this.pushedAt);
  }

  /**
   * Retrieves the total size (in bytes) of this repository.
   *
   * @return a size.
   */
  public long getSize() {
    return this.size;
  }

  /**
   * Retrieves the primary programming language for this repository.
   *
   * @return a primary language or, if no code has been pushed yet or if the language is unknown, an
   * empty optional.
   */
  @NonNull
  public Optional<String> getPrimaryLanguage() {
    return Optional.ofNullable(this.primaryLanguage);
  }

  /**
   * Retrieves the total amount of users which starred this repository.
   *
   * @return an amount.
   */
  public int getStargazersCount() {
    return this.stargazersCount;
  }

  /**
   * Retrieves the total amount of users which are watching this repository.
   *
   * @return an amount.
   */
  public int getWatchersCount() {
    return this.watchersCount;
  }

  /**
   * Retrieves the total amount of forks which have been created for this repository.
   *
   * @return an amount.
   */
  public int getForksCount() {
    return this.forksCount;
  }

  /**
   * Retrieves the total amount of open issues within this repository.
   *
   * @return an amount.
   */
  public int getOpenIssuesCount() {
    return this.openIssuesCount;
  }

  /**
   * Evaluates whether the issue tracker is enabled for this repository.
   *
   * @return true if enabled, false otherwise.
   */
  public boolean hasIssues() {
    return this.hasIssues;
  }

  /**
   * Evaluates whether this repository has one or more downloads.
   *
   * @return true if downloads exist, false otherwise.
   */
  public boolean hasDownloads() {
    return this.hasDownloads;
  }

  /**
   * Evaluates whether the wiki is enabled for this repository.
   *
   * @return true if enabled, false otherwise.
   */
  public boolean hasWiki() {
    return this.hasWiki;
  }

  /**
   * Evaluates whether this repository has a deployed GitHub pages branch.
   *
   * @return true if enabled, false otherwise.
   */
  public boolean hasPages() {
    return this.hasPages;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Repository)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Repository that = (Repository) o;
    return this.privateAccess == that.privateAccess &&
        this.fork == that.fork &&
        this.size == that.size &&
        this.stargazersCount == that.stargazersCount &&
        this.watchersCount == that.watchersCount &&
        this.forksCount == that.forksCount &&
        this.openIssuesCount == that.openIssuesCount &&
        this.hasIssues == that.hasIssues &&
        this.hasDownloads == that.hasDownloads &&
        this.hasWiki == that.hasWiki &&
        this.hasPages == that.hasPages &&
        Objects.equals(this.name, that.name) &&
        Objects.equals(this.fullName, that.fullName) &&
        Objects.equals(this.owner, that.owner) &&
        Objects.equals(this.description, that.description) &&
        Objects.equals(this.gitUrl, that.gitUrl) &&
        Objects.equals(this.sshUrl, that.sshUrl) &&
        Objects.equals(this.cloneUrl, that.cloneUrl) &&
        Objects.equals(this.svnUrl, that.svnUrl) &&
        Objects.equals(this.mirrorUrl, that.mirrorUrl) &&
        Objects.equals(this.homepageUrl, that.homepageUrl) &&
        Objects.equals(this.defaultBranch, that.defaultBranch) &&
        Objects.equals(this.pushedAt, that.pushedAt) &&
        Objects.equals(this.primaryLanguage, that.primaryLanguage);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(super.hashCode(), this.name, this.fullName, this.owner, this.description,
            this.privateAccess, this.fork,
            this.gitUrl,
            this.sshUrl, this.cloneUrl, this.svnUrl, this.mirrorUrl, this.homepageUrl,
            this.defaultBranch, this.pushedAt, this.size,
            this.primaryLanguage,
            this.stargazersCount, this.watchersCount, this.forksCount, this.openIssuesCount,
            this.hasIssues,
            this.hasDownloads,
            this.hasWiki, this.hasPages);
  }
}
