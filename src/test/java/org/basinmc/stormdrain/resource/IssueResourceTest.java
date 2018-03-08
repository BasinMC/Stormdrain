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
import org.basinmc.stormdrain.resource.Issue.State;
import org.junit.Assert;

/**
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class IssueResourceTest extends AbstractResourceParserTest<Issue> {

  public IssueResourceTest() {
    super("issue", Issue.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doTest(@NonNull Issue model) {
    Assert.assertEquals("73464126", model.getId());
    Assert.assertEquals(2, model.getNumber());
    Assert.assertEquals("Spelling error in the README file", model.getTitle());
    Assert.assertNotNull(model.getUser());
    Assert.assertEquals(1, model.getLabels().size());
    Assert.assertEquals(State.OPEN, model.getState());
    Assert.assertFalse(model.isLocked());
    Assert.assertFalse(model.getAssignee().isPresent());
    Assert.assertFalse(model.getMilestone().isPresent());
    Assert.assertEquals(0, model.getComments());
    Assert.assertFalse(model.getClosedAt().isPresent());
    Assert.assertTrue(model.getBody().isPresent());
    Assert.assertEquals("It looks like you accidently spelled 'commit' with two 't's.", model.getBody().get());
    Assert.assertEquals("https://github.com/baxterthehacker/public-repo/issues/2",
        model.getBrowserUrl().toExternalForm());
    Assert.assertEquals(1430869228L, model.getCreationTimestamp().getEpochSecond());
    Assert.assertFalse(model.getModificationTimestamp().isPresent());
  }
}
