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
import org.basinmc.stormdrain.resource.Organization;
import org.basinmc.stormdrain.resource.Team;
import org.basinmc.stormdrain.resource.User;

/**
 * Represents an event which notifies its receivers of a change to a team mebership (such as a newly
 * added membership or its deletion).
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class MembershipEvent extends AbstractOrganizationEvent {

  private final Action action;
  private final Team team;
  private final User member;

  @JsonCreator
  public MembershipEvent(
      @NonNull @JsonProperty(value = "action", required = true) Action action,
      @NonNull @JsonProperty(value = "team", required = true) Team team,
      @NonNull @JsonProperty(value = "member", required = true) User member,
      @NonNull @JsonProperty(value = "sender", required = true) User sender,
      @NonNull @JsonProperty(value = "organization", required = true) Organization organization) {
    super(sender, organization);
    this.action = action;
    this.team = team;
    this.member = member;
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
   * Retrieves the team from which a member was added to or removed from.
   *
   * @return a team.
   */
  @NonNull
  public Team getTeam() {
    return this.team;
  }

  /**
   * Retrieves the member which has been added or removed from a team.
   *
   * @return a member.
   */
  @NonNull
  public User getMember() {
    return this.member;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MembershipEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    MembershipEvent that = (MembershipEvent) o;
    return Objects.equals(this.team, that.team);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.team);
  }

  /**
   * Provides a list of valid change types.
   */
  public enum Action {
    ADDED,
    REMOVED
  }
}
