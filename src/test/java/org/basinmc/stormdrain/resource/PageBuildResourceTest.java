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
import org.basinmc.stormdrain.resource.PageBuild.Status;
import org.junit.Assert;

/**
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class PageBuildResourceTest extends AbstractResourceParserTest<PageBuild> {

  public PageBuildResourceTest() {
    super("page_build", PageBuild.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doTest(@NonNull PageBuild model) {
    Assert.assertEquals(Status.BUILT, model.getStatus());
    Assert.assertFalse(model.getErrorMessage().isPresent());
    Assert.assertNotNull(model.getPusher());
    Assert.assertEquals("053b99542c83021d6b202d1a1f5ecd5ef7084e55", model.getCommitId());
    Assert.assertTrue(model.getDuration().isPresent());
    Assert.assertEquals(3790, model.getDuration().get().getSeconds()); // TODO: This might be in ms
    Assert.assertEquals(1430869213L, model.getCreationTimestamp().getEpochSecond());
    Assert.assertTrue(model.getModificationTimestamp().isPresent());
    Assert.assertEquals(1430869217L, model.getModificationTimestamp().get().getEpochSecond());
  }
}
