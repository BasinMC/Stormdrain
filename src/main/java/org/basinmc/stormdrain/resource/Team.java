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
 * Represents a team within an organization.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Team extends AbstractResource {

  private final String name;
  private final String slug;
  private final Permission permission;

  @JsonCreator
  public Team(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @NonNull @JsonProperty(value = "name", required = true) String name,
      @NonNull @JsonProperty(value = "slug", required = true) String slug,
      @NonNull @JsonProperty(value = "permission", required = true) Permission permission) {
    super(id);
    this.name = name;
    this.slug = slug;
    this.permission = permission;
  }

  /**
   * Retrieves a human readable name for this team.
   *
   * @return a name.
   */
  @NonNull
  public String getName() {
    return this.name;
  }

  /**
   * Retrieves a URL and mention friendly name for this team.
   *
   * @return a name.
   */
  @NonNull
  public String getSlug() {
    return this.slug;
  }

  /**
   * Retrieves the permissions this team has on the repository.
   *
   * @return a permission.
   */
  @NonNull
  public Permission getPermission() {
    return this.permission;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Team)) {
      return false;
    }
    Team team = (Team) o;
    return Objects.equals(this.name, team.name) &&
        Objects.equals(this.slug, team.slug) &&
        this.permission == team.permission;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.slug, this.permission);
  }

  /**
   * Provides a list of valid permissions.
   */
  public enum Permission {
    ADMIN,
    PUSH,
    PULL
  }
}
