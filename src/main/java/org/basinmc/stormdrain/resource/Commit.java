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
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.basinmc.stormdrain.utility.ValueUtility;

/**
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Commit extends AbstractBrowserAccessibleResource {

  private final String treeId;
  private final boolean distinct;
  private final String message;

  private final Author author;
  private final Author committer;
  private final OffsetDateTime timestamp;

  private final Set<String> addedFiles;
  private final Set<String> removedFiles;
  private final Set<String> modifiedFiles;

  @JsonCreator
  public Commit(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @NonNull @JsonProperty(value = "tree_id", required = true) String treeId,
      @JsonProperty("distinct") boolean distinct,
      @NonNull @JsonProperty(value = "message", required = true) String message,
      @NonNull @JsonProperty(value = "author", required = true) Author author,
      @NonNull @JsonProperty(value = "committer", required = true) Author committer,
      @NonNull @JsonProperty(value = "timestamp", required = true) OffsetDateTime timestamp,
      @NonNull @JsonProperty(value = "added", required = true) Set<String> addedFiles,
      @NonNull @JsonProperty(value = "removed", required = true) Set<String> removedFiles,
      @NonNull @JsonProperty(value = "modified", required = true) Set<String> modifiedFiles,
      @NonNull @JsonProperty(value = "url", required = true) URL browserUrl) {
    super(id, browserUrl);
    this.treeId = treeId;
    this.distinct = distinct;
    this.message = message;
    this.author = author;
    this.committer = !author.equals(committer) ? committer : null;
    this.timestamp = timestamp;
    this.addedFiles = new HashSet<>(addedFiles);
    this.removedFiles = new HashSet<>(removedFiles);
    this.modifiedFiles = new HashSet<>(modifiedFiles);
  }

  /**
   * Retrieves the git tree identifier.
   *
   * @return a tree hash.
   */
  @NonNull
  public String getTreeId() {
    return this.treeId;
  }

  public boolean isDistinct() {
    return this.distinct;
  }

  /**
   * Retrieves the message of this commit.
   *
   * @return a message.
   */
  @NonNull
  public String getMessage() {
    return this.message;
  }

  /**
   * Retrieves the author of this commit (e.g. the person who wrote the code).
   *
   * @return an author.
   */
  @NonNull
  public Author getAuthor() {
    return this.author;
  }

  /**
   * Retrieves the committer of this commit (e.g. the person who submitted the code to the
   * repository).
   *
   * @return an author or, if this value is equal to the commit author, an empty optional.
   */
  @NonNull
  public Optional<Author> getCommitter() {
    return Optional.ofNullable(this.committer);
  }

  /**
   * Retrieves the date and time at which this commit has been created.
   *
   * @return a date and time.
   */
  @NonNull
  public OffsetDateTime getTimestamp() {
    return this.timestamp;
  }

  /**
   * Retrieves the repository relative paths for all files which were added with this commit.
   *
   * @return a set of added files.
   */
  @NonNull
  public Set<String> getAddedFiles() {
    return Collections.unmodifiableSet(this.addedFiles);
  }

  /**
   * Retrieves the repository relative paths for all files which were removed with this commit.
   *
   * @return a set of removed files.
   */
  @NonNull
  public Set<String> getRemovedFiles() {
    return Collections.unmodifiableSet(this.removedFiles);
  }

  /**
   * Retrieves the repository relative paths for all files which were modified with this commit.
   *
   * @return a set of modified files.
   */
  @NonNull
  public Set<String> getModifiedFiles() {
    return Collections.unmodifiableSet(this.modifiedFiles);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Commit)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Commit commit = (Commit) o;
    return this.distinct == commit.distinct &&
        Objects.equals(this.treeId, commit.treeId) &&
        Objects.equals(this.message, commit.message) &&
        Objects.equals(this.author, commit.author) &&
        Objects.equals(this.committer, commit.committer) &&
        Objects.equals(this.timestamp, commit.timestamp) &&
        Objects.equals(this.addedFiles, commit.addedFiles) &&
        Objects.equals(this.removedFiles, commit.removedFiles) &&
        Objects.equals(this.modifiedFiles, commit.modifiedFiles);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(super.hashCode(), this.treeId, this.distinct, this.message, this.author,
            this.committer, this.timestamp,
            this.addedFiles,
            this.removedFiles, this.modifiedFiles);
  }

  /**
   * Represents a commit author.
   */
  public static class Author {

    private final String name;
    private final String email;
    private final String login;

    @JsonCreator
    public Author(
        @NonNull @JsonProperty(value = "name", required = true) String name,
        @NonNull @JsonProperty(value = "email", required = true) String email,
        @Nullable @JsonProperty("username") String login) {
      this.name = name;
      this.email = email;
      this.login = ValueUtility.toOptionalString(login);
    }

    /**
     * Retrieves the display name for this commit author.
     *
     * @return a name.
     */
    @NonNull
    public String getName() {
      return this.name;
    }

    /**
     * Retrieves the display E-Mail address for this commit author (possibly a GitHub proxy mail).
     *
     * @return an email address.
     */
    @NonNull
    public String getEmail() {
      return this.email;
    }

    /**
     * Retrieves the login name of the author.
     *
     * @return a login name or, if none has been associated, an empty optional.
     */
    @NonNull
    // TODO: verify whether this is actually how GitHub handles unassociated stuff
    public Optional<String> getLogin() {
      return Optional.ofNullable(this.login);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Author)) {
        return false;
      }
      Author author = (Author) o;
      return Objects.equals(this.name, author.name) &&
          Objects.equals(this.email, author.email) &&
          Objects.equals(this.login, author.login);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
      return Objects.hash(this.name, this.email, this.login);
    }
  }
}
