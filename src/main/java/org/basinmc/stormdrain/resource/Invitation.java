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
import java.util.Objects;
import java.util.Optional;

/**
 * Represents an invitation to an organization or repository.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Invitation extends AbstractResource {

  private final String login;
  private final String email;
  private final Role role;

  @JsonCreator
  public Invitation(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @Nullable @JsonProperty("login") String login,
      @Nullable @JsonProperty("email") String email,
      @NonNull @JsonProperty(value = "role", required = true) Role role) {
    super(id);

    if (login == null && email == null) {
      throw new IllegalArgumentException("Illegal invitation: Either login or email required");
    }

    this.login = login;
    this.email = email;
    this.role = role;
  }

  /**
   * Retrieves the invited user's login name (if any).
   *
   * @return a login name or, when an email is used, an empty optional.
   */
  @NonNull
  public Optional<String> getLogin() {
    return Optional.ofNullable(this.login);
  }

  /**
   * Retrieves the invited user's E-Mail address (if any).
   *
   * @return an E-Mail address or, when a login is used, an empty optional.
   */
  @NonNull
  public Optional<String> getEmail() {
    return Optional.ofNullable(this.email);
  }

  /**
   * Retrieves the proposed organization role for the invited member.
   *
   * @return a role.
   */
  @NonNull
  public Role getRole() {
    return this.role;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Invitation)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Invitation that = (Invitation) o;
    return Objects.equals(this.login, that.login) &&
        Objects.equals(this.email, that.email) &&
        this.role == that.role;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.login, this.email, this.role);
  }

  /**
   * Provides a list of valid organization roles.
   */
  public enum Role {
    DIRECT_MEMBER,
    ADMIN,
    BILLING_MANAGER,
    HIRING_MANAGER,
    REINSTATE
  }
}
