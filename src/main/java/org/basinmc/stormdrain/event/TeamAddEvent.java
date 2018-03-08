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
import java.util.Objects;
import org.basinmc.stormdrain.resource.Repository;
import org.basinmc.stormdrain.resource.Team;
import org.basinmc.stormdrain.resource.User;

/**
 * Represents an event which notifies its receiver of repository additions to existing teams.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class TeamAddEvent extends AbstractRepositoryEvent {

  private final Team team;

  @JsonCreator
  public TeamAddEvent(
      @NonNull @JsonProperty(value = "team", required = true) Team team,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(repository, sender);
    this.team = team;
  }

  /**
   * Retrieves the team which has been added to the repository.
   *
   * @return a team.
   */
  @NonNull
  public Team getTeam() {
    return this.team;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TeamAddEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    TeamAddEvent that = (TeamAddEvent) o;
    return Objects.equals(this.team, that.team);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.team);
  }
}
