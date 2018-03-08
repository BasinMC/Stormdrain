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
import org.basinmc.stormdrain.resource.Release.Asset;
import org.basinmc.stormdrain.resource.Release.State;
import org.junit.Assert;

/**
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class ReleaseAssetResourceTest extends AbstractResourceParserTest<Asset> {

  public ReleaseAssetResourceTest() {
    super("release_asset", Asset.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doTest(@NonNull Asset model) {
    Assert.assertEquals("1", model.getId());
    Assert.assertEquals("example.zip", model.getName());
    Assert.assertTrue(model.getLabel().isPresent());
    Assert.assertEquals("short description", model.getLabel().get());
    Assert.assertEquals(State.UPLOADED, model.getState());
    Assert.assertEquals("application/zip", model.getContentType());
    Assert.assertEquals(1024, model.getSize());
    Assert.assertEquals(42, model.getDownloadCount());
    Assert.assertNotNull(model.getUploader());
    Assert
        .assertEquals("https://github.com/octocat/Hello-World/releases/download/v1.0.0/example.zip",
            model.getBrowserUrl().toExternalForm());
    Assert.assertEquals(1361993732L, model.getCreationTimestamp().getEpochSecond());
    Assert.assertFalse(model.getModificationTimestamp().isPresent());
  }
}
