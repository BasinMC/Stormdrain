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
import java.util.Optional;
import org.basinmc.stormdrain.resource.Invitation;
import org.basinmc.stormdrain.resource.Membership;
import org.basinmc.stormdrain.resource.Organization;
import org.basinmc.stormdrain.resource.User;

/**
 * Represents an event which notifies its receivers of changes to organization memberships (e.g. new
 * invitations, accepted invitations and removals).
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class OrganizationEvent extends AbstractOrganizationEvent {

  private final Action action;
  private final Invitation invitation;
  private final Membership membership;

  @JsonCreator
  public OrganizationEvent(
      @NonNull @JsonProperty(value = "action", required = true) Action action,
      @NonNull @JsonProperty(value = "invitation", required = true) Invitation invitation,
      @Nullable @JsonProperty("membership") Membership membership,
      @NonNull @JsonProperty(value = "sender", required = true) User sender,
      @NonNull @JsonProperty(value = "organization", required = true) Organization organization) {
    super(sender, organization);
    this.action = action;
    this.invitation = invitation;
    this.membership = membership;
  }

  /**
   * Retrieves teh type of change which is represented by this event.
   *
   * @return a change type.
   */
  @NonNull
  public Action getAction() {
    return this.action;
  }

  /**
   * Retrieves the invitation which is being represented by this event.
   *
   * @return an invitation.
   */
  @NonNull
  public Invitation getInvitation() {
    return this.invitation;
  }

  /**
   * Retrieves the membership which is being represented by this event (if a member has accepted an
   * invitation or has been removed from the organization).
   *
   * @return a membership.
   */
  @NonNull
  public Optional<Membership> getMembership() {
    return Optional.ofNullable(this.membership);
  }

  /**
   * Provides a list of valid change types.
   */
  public enum Action {
    MEMBER_ADDED,
    MEMBER_REMOVED,
    MEMBER_INVITED
  }
}
