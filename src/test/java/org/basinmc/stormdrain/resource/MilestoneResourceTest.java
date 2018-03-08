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
import org.junit.Assert;

/**
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class MilestoneResourceTest extends AbstractResourceParserTest<Milestone> {

  public MilestoneResourceTest() {
    super("milestone", Milestone.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doTest(@NonNull Milestone model) {
    Assert.assertEquals("2055681", model.getId());
    Assert.assertEquals(3, model.getNumber());
    Assert.assertEquals("I am a milestone", model.getTitle());
    Assert.assertFalse(model.getDescription().isPresent());
    Assert.assertEquals(0, model.getOpenIssues());
    Assert.assertEquals(0, model.getClosedIssues());
    Assert.assertEquals(1475868368L, model.getCreationTimestamp().getEpochSecond());
    Assert.assertFalse(model.getModificationTimestamp().isPresent());
    Assert.assertFalse(model.getDueOn().isPresent());
    Assert.assertFalse(model.getClosedAt().isPresent());
    Assert.assertNotNull(model.getCreator());
    Assert.assertEquals(
        "https://github.com/baxterandthehackers/public-repo/milestones/Test%20milestone%20creation%20webhook%20from%20command%20line2",
        model.getBrowserUrl().toExternalForm());
  }
}
