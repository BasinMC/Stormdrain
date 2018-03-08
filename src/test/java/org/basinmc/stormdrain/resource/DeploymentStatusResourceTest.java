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
import org.basinmc.stormdrain.resource.DeploymentStatus.State;
import org.junit.Assert;

/**
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class DeploymentStatusResourceTest extends AbstractResourceParserTest<DeploymentStatus> {

  public DeploymentStatusResourceTest() {
    super("deployment_status", DeploymentStatus.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doTest(@NonNull DeploymentStatus model) {
    Assert.assertEquals("1115122", model.getId());
    Assert.assertEquals(State.SUCCESS, model.getState());
    Assert.assertFalse(model.getDescription().isPresent());
    Assert.assertFalse(model.getTargetUrl().isPresent());
    Assert.assertEquals(1430869239L, model.getCreationTimestamp().getEpochSecond());
    Assert.assertFalse(model.getModificationTimestamp().isPresent());
  }
}
