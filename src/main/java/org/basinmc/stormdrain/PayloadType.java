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
package org.basinmc.stormdrain;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.basinmc.stormdrain.event.CommitCommentEvent;
import org.basinmc.stormdrain.event.CreateEvent;
import org.basinmc.stormdrain.event.DeleteEvent;
import org.basinmc.stormdrain.event.DeploymentEvent;
import org.basinmc.stormdrain.event.DeploymentStatusEvent;
import org.basinmc.stormdrain.event.Event;
import org.basinmc.stormdrain.event.ForkEvent;
import org.basinmc.stormdrain.event.GollumEvent;
import org.basinmc.stormdrain.event.IssueCommentEvent;
import org.basinmc.stormdrain.event.IssuesEvent;
import org.basinmc.stormdrain.event.LabelEvent;
import org.basinmc.stormdrain.event.MemberEvent;
import org.basinmc.stormdrain.event.MembershipEvent;
import org.basinmc.stormdrain.event.MilestoneEvent;
import org.basinmc.stormdrain.event.OrganizationBlockEvent;
import org.basinmc.stormdrain.event.OrganizationEvent;
import org.basinmc.stormdrain.event.PageBuildEvent;
import org.basinmc.stormdrain.event.PublicEvent;
import org.basinmc.stormdrain.event.PullRequestEvent;
import org.basinmc.stormdrain.event.PullRequestReviewCommentEvent;
import org.basinmc.stormdrain.event.PullRequestReviewEvent;
import org.basinmc.stormdrain.event.PushEvent;
import org.basinmc.stormdrain.event.ReleaseEvent;
import org.basinmc.stormdrain.event.RepositoryEvent;
import org.basinmc.stormdrain.event.TeamAddEvent;
import org.basinmc.stormdrain.event.TeamEvent;
import org.basinmc.stormdrain.event.WatchEvent;

/**
 * Provides a list of recognized event types which notify the receiver of certain changes to a
 * repository or organization state.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public enum PayloadType {
  /**
   * Any time a commit is commented on.
   */
  COMMIT_COMMENT(CommitCommentEvent.class),

  /**
   * Any time a branch or tag is created.
   */
  CREATE(CreateEvent.class),

  /**
   * Any time a branch or tag is deleted.
   */
  DELETE(DeleteEvent.class),

  /**
   * Any time a new deployment is created for a repository through the API.
   */
  DEPLOYMENT(DeploymentEvent.class),

  /**
   * Any time a deployment's status changes.
   */
  DEPLOYMENT_STATUS(DeploymentStatusEvent.class),

  /**
   * Any time a repository is forked.
   */
  FORK(ForkEvent.class),

  /**
   * Any time a wiki page is changed.
   */
  GOLLUM(GollumEvent.class),

  /**
   * Any time a GitHub App is installed.
   */
  // TODO
  // INSTALLATION,

  /**
   * Any time a repository is added or removed from an app installation.
   */
  // TODO
  // INSTALLATION_REPOSITORIES,

  /**
   * Any time somebody comments on an issue.
   */
  ISSUE_COMMENT(IssueCommentEvent.class),

  /**
   * Any time an issue is assigned, unassigned, labeled, unlabeled, opened, edited, milestoned,
   * demilestoned, closed or reopened.
   */
  ISSUES(IssuesEvent.class),

  /**
   * Any time a label is created, edited or deleted.
   */
  LABEL(LabelEvent.class),

  /**
   * Any time a user purchased, cancels or changes their GitHub Marketplace plan.
   */
  // TODO
  // MARKETPLACE_PURCHASE,

  /**
   * Any time a user is added or removed as a collaborator to a repository, or has their permissions
   * modified.
   */
  MEMBER(MemberEvent.class),

  /**
   * Any time a user is added or removed from a team.
   */
  MEMBERSHIP(MembershipEvent.class),

  /**
   * Any  time a milestone is created, closed, opened edited, or deleted.
   */
  MILESTONE(MilestoneEvent.class),

  /**
   * Any time a user is added, removed, or invited to an organization.
   */
  ORGANIZATION(OrganizationEvent.class),

  /**
   * Any time an organization blocks or unblocks a user.
   */
  ORG_BLOCK(OrganizationBlockEvent.class),

  /**
   * Any time a pages site is built or results in a failed build.
   */
  PAGE_BUILD(PageBuildEvent.class),

  /**
   * Any time a project card is created, edited, moved, converted to an issue, or deleted.
   */
  // TODO
  // PROJECT_CARD,

  /**
   * Any time a project column is created, edited, moved, or deleted.
   */
  // TODO
  // PROJECT_COLUMN,

  /**
   * Any time a project is created, edited, closed, reopened, or deleted.
   */
  // TODO
  // PROJECT,

  /**
   * Any time a repository changes from private to public.
   */
  PUBLIC(PublicEvent.class),

  /**
   * Any time a commit on a pull request's diff is created, edited, or deleted.
   */
  PULL_REQUEST_REVIEW_COMMENT(PullRequestReviewCommentEvent.class),

  /**
   * Any time a pull request review is submitted, edited, or dismissed.
   */
  PULL_REQUEST_REVIEW(PullRequestReviewEvent.class),

  /**
   * Any time a pull request is assigned, unassigned, labeled, unlabeled, opened, edited, closed,
   * reopened, synchronized (e.g. updated due to a new push), a review is requested, or a review
   * request is removed.
   */
  PULL_REQUEST(PullRequestEvent.class),

  /**
   * Any git push, including editing tags or branches.
   */
  PUSH(PushEvent.class),

  /**
   * Any time a repository is created, deleted, archived, unarchived, made public, or made private.
   */
  REPOSITORY(RepositoryEvent.class),

  /**
   * Any time a release is published.
   */
  RELEASE(ReleaseEvent.class),

  /**
   * Any time a repository receives a status update via the API.
   */
  // TODO
  // STATUS(StatusEvent.class),

  /**
   * Any time a team is created, deleted, modified, or added to or removed from a repository.
   */
  TEAM(TeamEvent.class),

  /**
   * Any time a team is added or modified on a repository.
   */
  TEAM_ADD(TeamAddEvent.class),

  /**
   * Any time a user stars a repository.
   */
  WATCH(WatchEvent.class);

  private final Class<? extends Event> type;
  private final ObjectReader reader;

  PayloadType(@NonNull Class<? extends Event> type) {
    this.type = type;

    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.findAndRegisterModules();
    this.reader = mapper.readerFor(type);
  }

  /**
   * Retrieves the object type which represents a parsed version of this particular event type.
   *
   * @return an event type.
   */
  @NonNull
  public Class<? extends Event> getType() {
    return this.type;
  }

  /**
   * Deoodes an event payload from the supplied payload string.
   *
   * @param payload a payload string.
   * @param <E> an event type.
   * @return an event payload.
   * @throws IOException when the data is malformed.
   */
  @NonNull
  @SuppressWarnings("unchecked")
  public <E extends Event> E read(@NonNull String payload) throws IOException {
    return (E) this.reader.readValue(payload);
  }

  /**
   * Decodes an event payload from the supplied input stream.
   *
   * @param inputStream an input stream.
   * @param <E> an event type.
   * @return an event payload.
   * @throws IOException when reading from the stream fails or the data is malformed.
   */
  @NonNull
  @SuppressWarnings("unchecked")
  public <E extends Event> E read(@NonNull InputStream inputStream) throws IOException {
    return (E) this.reader.readValue(inputStream);
  }

  /**
   * Decodes an event payload from the supplied reader.
   *
   * @param reader a reader.
   * @param <E> an event type.
   * @return an event payload.
   * @throws IOException when reading from the stream fails or the data is malformed.
   */
  @NonNull
  @SuppressWarnings("unchecked")
  public <E extends Event> E read(@NonNull Reader reader) throws IOException {
    return (E) this.reader.readValue(reader);
  }
}
