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
import java.net.URL;

/**
 * Represents a user account.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class User extends ResourceOwner {

  public User(
      @NonNull @JsonProperty(value = "id", required = true) String id,
      @NonNull @JsonProperty(value = "login", required = true) String login,
      @JsonProperty("site_admin") boolean siteAdmin,
      @NonNull @JsonProperty(value = "html_url", required = true) URL browserUrl,
      @Nullable @JsonProperty("gravatarId") String gravatarId,
      @NonNull @JsonProperty(value = "avatar_url", required = true) URL avatarUrl) {
    super(id, login, Type.USER, siteAdmin, browserUrl, gravatarId, avatarUrl);
  }
}
