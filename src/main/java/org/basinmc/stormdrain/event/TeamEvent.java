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
import edu.umd.cs.findbugs.annotations.Nullable;
import java.util.Objects;
import java.util.Optional;
import org.basinmc.stormdrain.resource.Organization;
import org.basinmc.stormdrain.resource.Repository;
import org.basinmc.stormdrain.resource.Team;
import org.basinmc.stormdrain.resource.User;

/**
 * Represents an event which notifies its receivers of the creation or modification of teams as well
 * as their assignment to or a removal from a repository.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class TeamEvent extends AbstractOrganizationEvent {

  private final Action action;
  private final Team team;
  private final Repository repository;

  @JsonCreator
  public TeamEvent(
      @NonNull @JsonProperty(value = "action", required = true) Action action,
      @NonNull @JsonProperty(value = "team", required = true) Team team,
      @Nullable @JsonProperty("repository") Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender,
      @NonNull @JsonProperty(value = "organization", required = true) Organization organization) {
    super(sender, organization);
    this.action = action;
    this.team = team;
    this.repository = repository;
  }

  /**
   * Retrieves the type of change which is represented by this event.
   *
   * @return a change type.
   */
  @NonNull
  public Action getAction() {
    return this.action;
  }

  /**
   * Retrieves the team which is affected by the change represented by this event.
   *
   * @return a team.
   */
  @NonNull
  public Team getTeam() {
    return this.team;
  }

  /**
   * Retrieves the repository this team was added to or removed from.
   *
   * @return a repository or, if the change represented by this event is not an addition to or
   * removal from a repository, an empty optional.
   */
  @NonNull
  public Optional<Repository> getRepository() {
    return Optional.ofNullable(this.repository);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TeamEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    TeamEvent teamEvent = (TeamEvent) o;
    return this.action == teamEvent.action &&
        Objects.equals(this.team, teamEvent.team) &&
        Objects.equals(this.repository, teamEvent.repository);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.action, this.team, this.repository);
  }

  /**
   * Provides a list of valid change types.
   */
  public enum Action {
    CREATED,
    DELETED,
    EDITED,
    ADDED_TO_REPOSITORY,
    REMOVED_FROM_REPOSITORY
  }
}
