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
package org.basinmc.stormdrain.event;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.basinmc.stormdrain.event.OrganizationBlockEvent.Action;
import org.junit.Assert;

/**
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class OrganizationBlockEventTest extends AbstractEventParserTest<OrganizationBlockEvent> {

  public OrganizationBlockEventTest() {
    super("org_block", OrganizationBlockEvent.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doTest(@NonNull OrganizationBlockEvent model) {
    Assert.assertEquals(Action.BLOCKED, model.getAction());
    Assert.assertNotNull(model.getBlockedUser());
    Assert.assertNotNull(model.getOrganization());
    Assert.assertNotNull(model.getSender());
  }
}