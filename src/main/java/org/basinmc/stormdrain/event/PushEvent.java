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
package org.basinmc.stormdrain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.basinmc.stormdrain.resource.Commit;
import org.basinmc.stormdrain.resource.Repository;
import org.basinmc.stormdrain.resource.User;

/**
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class PushEvent extends AbstractRepositoryEvent {

  private final String reference;
  private final String previousCommitId;
  private final String targetCommitId;
  private final URL compareUrl;

  private final boolean created;
  private final boolean deleted;
  private final boolean forced;

  private final List<Commit> commits;
  private final Commit headCommit;

  @JsonCreator
  public PushEvent(
      @NonNull @JsonProperty(value = "ref", required = true) String reference,
      @NonNull @JsonProperty(value = "before", required = true) String previousCommitId,
      @NonNull @JsonProperty(value = "after", required = true) String targetCommitId,
      @NonNull @JsonProperty(value = "compare", required = true) URL compareUrl,
      @JsonProperty(value = "created", required = true) boolean created,
      @JsonProperty(value = "deleted", required = true) boolean deleted,
      @JsonProperty(value = "forced", required = true) boolean forced,
      @NonNull @JsonProperty(value = "commits", required = true) List<Commit> commits,
      @NonNull @JsonProperty(value = "head_commit", required = true) Commit headCommit,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(repository, sender);
    this.reference = reference;
    this.previousCommitId = previousCommitId;
    this.targetCommitId = targetCommitId;
    this.compareUrl = compareUrl;
    this.created = created;
    this.deleted = deleted;
    this.forced = forced;
    this.commits = new ArrayList<>(commits);
    this.headCommit = headCommit;
  }

  /**
   * Retrieves the reference to which the sender has pushed.
   *
   * @return a reference.
   */
  @NonNull
  public String getReference() {
    return this.reference;
  }

  /**
   * Retrieves the commit identifier of the last change before this push.
   *
   * @return a commit identifier.
   */
  @NonNull
  public String getPreviousCommitId() {
    return this.previousCommitId;
  }

  /**
   * Retrieves the commit identifier of the topmost change created by this push (e.g. the new
   * head).
   *
   * @return a commit identifier.
   */
  @NonNull
  public String getTargetCommitId() {
    return this.targetCommitId;
  }

  /**
   * Retrieves the URL for the site which lets users compare the source against the previous state
   * (e.g. the state before the change was applied).
   *
   * @return a url.
   */
  @NonNull
  public URL getCompareUrl() {
    return this.compareUrl;
  }

  // TODO: Document
  public boolean isCreated() {
    return this.created;
  }

  // TODO: Document
  public boolean isDeleted() {
    return this.deleted;
  }

  /**
   * Evaluates whether this push was a force push (e.g. may override existing changes).
   *
   * @return true if forced, false otherwise.
   */
  public boolean isForced() {
    return this.forced;
  }

  /**
   * Retrieves the list of commits within this push (limited to a maximum of 20 commits).
   *
   * @return a list of commits.
   */
  public List<Commit> getCommits() {
    return Collections.unmodifiableList(this.commits);
  }

  /**
   * Retrieves the new head commit.
   *
   * @return a commit.
   */
  @NonNull
  public Commit getHeadCommit() {
    return this.headCommit;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PushEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    PushEvent pushEvent = (PushEvent) o;
    return this.created == pushEvent.created &&
        this.deleted == pushEvent.deleted &&
        this.forced == pushEvent.forced &&
        Objects.equals(this.reference, pushEvent.reference) &&
        Objects.equals(this.previousCommitId, pushEvent.previousCommitId) &&
        Objects.equals(this.targetCommitId, pushEvent.targetCommitId) &&
        Objects.equals(this.compareUrl, pushEvent.compareUrl) &&
        Objects.equals(this.commits, pushEvent.commits) &&
        Objects.equals(this.headCommit, pushEvent.headCommit);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(super.hashCode(), this.reference, this.previousCommitId, this.targetCommitId,
            this.compareUrl,
            this.created,
            this.deleted, this.forced, this.commits, this.headCommit);
  }
}
