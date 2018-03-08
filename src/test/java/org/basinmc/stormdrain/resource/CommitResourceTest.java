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
public class CommitResourceTest extends AbstractResourceParserTest<Commit> {

  public CommitResourceTest() {
    super("commit", Commit.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doTest(@NonNull Commit model) {
    Assert.assertEquals("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c", model.getId());
    Assert.assertEquals("f9d2a07e9488b91af2641b26b9407fe22a451433", model.getTreeId());
    Assert.assertTrue(model.isDistinct());
    Assert.assertEquals("Update README.md", model.getMessage());
    Assert.assertNotNull(model.getAuthor());
    Assert.assertFalse(model.getCommitter().isPresent());
    Assert.assertTrue(model.getAddedFiles().isEmpty());
    Assert.assertEquals(1, model.getModifiedFiles().size());
    Assert.assertTrue(model.getModifiedFiles().contains("README.md"));
    Assert.assertTrue(model.getRemovedFiles().isEmpty());
    Assert.assertEquals(
        "https://github.com/baxterthehacker/public-repo/commit/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c",
        model.getBrowserUrl().toExternalForm());
    Assert.assertEquals(1430869215L, model.getTimestamp().toEpochSecond());
  }
}
