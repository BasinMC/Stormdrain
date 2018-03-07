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
import java.util.Objects;

/**
 * Represent's a user's membership within a team.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Membership {

  private final Role role;
  private final User user;

  @JsonCreator
  public Membership(
      @NonNull @JsonProperty(value = "role", required = true) Role role,
      @NonNull @JsonProperty(value = "user", required = true) User user) {
    this.role = role;
    this.user = user;
  }

  /**
   * Retrieves the role of this member.
   *
   * @return a role.
   */
  @NonNull
  public Role getRole() {
    return this.role;
  }

  /**
   * Retrieves the user which this membership targets.
   *
   * @return a user.
   */
  @NonNull
  public User getUser() {
    return this.user;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Membership)) {
      return false;
    }
    Membership that = (Membership) o;
    return this.role == that.role &&
        Objects.equals(this.user, that.user);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.role, this.user);
  }

  /**
   * Provides a valid list of member roles.
   */
  public enum Role {
    MEMBER,
    MAINTAINER
  }
}
