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

import edu.umd.cs.findbugs.annotations.NonNull;
import org.basinmc.stormdrain.resource.ResourceOwner.Type;
import org.junit.Assert;

/**
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class ResourceOwnerResourceTest extends AbstractResourceParserTest<ResourceOwner> {

  public ResourceOwnerResourceTest() {
    super("resource_owner", ResourceOwner.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doTest(@NonNull ResourceOwner model) {
    Assert.assertEquals("7649605", model.getId());
    Assert.assertEquals("baxterandthehackers", model.getLogin());
    Assert.assertEquals(Type.ORGANIZATION, model.getType());
    Assert.assertFalse(model.getGravatarId().isPresent());
    Assert.assertFalse(model.isSiteAdmin());
    Assert.assertEquals("https://avatars.githubusercontent.com/u/7649605?v=3",
        model.getAvatarUrl().toExternalForm());
    Assert.assertEquals("https://github.com/baxterandthehackers",
        model.getBrowserUrl().toExternalForm());
  }
}
