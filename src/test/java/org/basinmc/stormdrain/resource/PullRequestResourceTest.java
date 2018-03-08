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
public class PullRequestResourceTest extends AbstractResourceParserTest<PullRequest> {

  public PullRequestResourceTest() {
    super("pull_request", PullRequest.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doTest(@NonNull PullRequest model) {
    Assert.assertEquals("34778301", model.getId());
    Assert.assertEquals(1, model.getNumber());
    Assert.assertEquals(State.OPEN, model.getState());
    Assert.assertFalse(model.isLocked());
    Assert.assertEquals("Update the README with new information", model.getTitle());
    Assert.assertTrue(model.getBody().isPresent());
    Assert.assertEquals("This is a pretty simple change that we need to pull into master.",
        model.getBody().get());
    Assert.assertNotNull(model.getUser());
    Assert.assertFalse(model.getMergeCommitId().isPresent());
    Assert.assertFalse(model.getAssignee().isPresent());
    Assert.assertFalse(model.getMilestone().isPresent());
    Assert.assertNotNull(model.getHead());
    Assert.assertNotNull(model.getBase());
    Assert.assertEquals(0, model.getComments());
    Assert.assertEquals("https://github.com/baxterthehacker/public-repo/pull/1",
        model.getBrowserUrl().toExternalForm());
    Assert.assertEquals("https://github.com/baxterthehacker/public-repo/pull/1.diff",
        model.getDiffUrl().toExternalForm());
    Assert.assertEquals("https://github.com/baxterthehacker/public-repo/pull/1.patch",
        model.getPatchUrl().toExternalForm());
    Assert.assertEquals(1430869227, model.getCreationTimestamp().getEpochSecond());
    Assert.assertFalse(model.getModificationTimestamp().isPresent());
    Assert.assertFalse(model.getClosedAt().isPresent());
    Assert.assertFalse(model.getMergedAt().isPresent());
  }
}
