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

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import org.basinmc.stormdrain.utility.ValueUtility;

/**
 * Represents a resource owner (e.g. a user or organization).
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class ResourceOwner extends AbstractBrowserAccessibleResource {

  private final String login;
  private final Type type;
  private final boolean siteAdmin;
  private final String gravatarId;
  private final URL avatarUrl;

  public ResourceOwner(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @NonNull @JsonProperty(value = "login", required = true) String login,
      @NonNull @JsonProperty(value = "type", required = true) Type type,
      @JsonProperty("site_admin") boolean siteAdmin,
      @Nullable @JsonProperty("gravatarId") String gravatarId,
      @NonNull @JsonProperty(value = "avatar_url", required = true) URL avatarUrl)
      throws MalformedURLException {
    // This sucks a bit but resource owners don't come with an HTML url by default
    this(id, login, type, siteAdmin, new URL("https://github.com" + login),
        ValueUtility.toOptionalString(gravatarId), avatarUrl);
  }

  protected ResourceOwner(
      @NonNull String id,
      @NonNull String login,
      @NonNull Type type,
      boolean siteAdmin,
      @NonNull URL browserUrl,
      @Nullable String gravatarId,
      @NonNull URL avatarUrl) {
    super(id, browserUrl);
    this.login = login;
    this.type = type;
    this.siteAdmin = siteAdmin;
    this.gravatarId = gravatarId;
    this.avatarUrl = avatarUrl;
  }

  /**
   * Retrieves the login name (e.g. the url friendly name) of this owner.
   *
   * @return a login name.
   */
  @NonNull
  public String getLogin() {
    return this.login;
  }

  /**
   * Retrieves the type of this resource owner (e.g. whether they are a user or organization).
   *
   * @return a type.
   */
  @NonNull
  public Type getType() {
    return this.type;
  }

  /**
   * Evaluates whether this resource owner is a GitHub administrator (this will always be false for
   * organizations).
   *
   * @return true if administrator, false otherwise.
   */
  public boolean isSiteAdmin() {
    return this.siteAdmin;
  }

  /**
   * Retrieves the Gravatar identifier from which the owner's avatar is retrieved.
   *
   * @return a Gravatar identifier or, if a vanilla GitHub avatar is used, an empty optional.
   */
  @NonNull
  public Optional<String> getGravatarId() {
    return Optional.ofNullable(this.gravatarId);
  }

  /**
   * Retrieves the URL at which the avatar image is available.
   *
   * @return an avatar url.
   */
  @NonNull
  public URL getAvatarUrl() {
    return this.avatarUrl;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ResourceOwner)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ResourceOwner that = (ResourceOwner) o;
    return Objects.equals(this.login, that.login) &&
        this.type == that.type &&
        Objects.equals(this.gravatarId, that.gravatarId) &&
        Objects.equals(this.avatarUrl, that.avatarUrl);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.login, this.type, this.gravatarId, this.avatarUrl);
  }

  /**
   * Provides a list of valid user types.
   */
  public enum Type {
    USER,
    ORGANIZATION
  }
}
