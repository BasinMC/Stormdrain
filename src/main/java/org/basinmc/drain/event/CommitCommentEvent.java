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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Objects;
import org.basinmc.drain.resource.CommitComment;
import org.basinmc.drain.resource.Repository;
import org.basinmc.drain.resource.User;

/**
 * Represents an event which notifies its receiver of a change to an existing commit comment or of
 * the creation of a completely new commit comment.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class CommitCommentEvent extends AbstractRepositoryEvent {

  private final Action action;
  private final CommitComment comment;

  @JsonCreator
  public CommitCommentEvent(
      @NonNull @JsonProperty(value = "action", required = true) Action action,
      @NonNull @JsonProperty(value = "comment", required = true) CommitComment comment,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(repository, sender);
    this.action = action;
    this.comment = comment;
  }

  /**
   * Retrieves the change type.
   *
   * @return an action.
   */
  @NonNull
  public Action getAction() {
    return this.action;
  }

  /**
   * Retrieves the comment to which this change applies.
   *
   * @return a comment.
   */
  @NonNull
  public CommitComment getComment() {
    return this.comment;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CommitCommentEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    CommitCommentEvent that = (CommitCommentEvent) o;
    return this.action == that.action &&
        Objects.equals(this.comment, that.comment);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.action, this.comment);
  }

  /**
   * Provides a list of valid event actions.
   */
  public enum Action {
    CREATED
  }
}
