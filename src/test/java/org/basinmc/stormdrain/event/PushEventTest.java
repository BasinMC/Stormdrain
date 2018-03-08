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
import org.junit.Assert;

/**
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class PushEventTest extends AbstractEventParserTest<PushEvent> {

  public PushEventTest() {
    super("push", PushEvent.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doTest(@NonNull PushEvent model) {
    Assert.assertEquals("refs/heads/changes", model.getReference());
    Assert.assertEquals("9049f1265b7d61be4a8904a9a27120d2064dab3b", model.getPreviousCommitId());
    Assert.assertEquals("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c", model.getTargetCommitId());
    Assert.assertFalse(model.isCreated());
    Assert.assertFalse(model.isDeleted());
    Assert.assertFalse(model.isForced());
    Assert.assertNotNull(model.getCommits());
    Assert.assertEquals(1, model.getCommits().size());
    Assert.assertNotNull(model.getHeadCommit());
    Assert.assertNotNull(model.getRepository());
    Assert.assertNotNull(model.getSender());
    Assert.assertEquals(
        "https://github.com/baxterthehacker/public-repo/compare/9049f1265b7d...0d1a26e67d8f",
        model.getCompareUrl().toExternalForm());
  }
}
