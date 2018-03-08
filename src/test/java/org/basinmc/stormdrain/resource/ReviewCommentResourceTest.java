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
public class ReviewCommentResourceTest extends AbstractResourceParserTest<ReviewComment> {

  public ReviewCommentResourceTest() {
    super("review_comment", ReviewComment.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doTest(@NonNull ReviewComment model) {
    Assert.assertEquals("29724692", model.getId());
    Assert.assertEquals("README.md", model.getPath());
    Assert.assertEquals("@@ -1 +1 @@\n-# public-repo", model.getDiffHunk());
    Assert.assertEquals(1, model.getPosition());
    Assert.assertEquals(1, model.getOriginalPosition());
    Assert.assertEquals("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c", model.getCommitId());
    Assert.assertEquals("0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c", model.getOriginalCommitId());
    Assert.assertNotNull(model.getUser());
    Assert.assertTrue(model.getBody().isPresent());
    Assert.assertEquals("Maybe you should use more emojji on this line.", model.getBody().get());
    Assert.assertEquals("https://github.com/baxterthehacker/public-repo/pull/1#discussion_r29724692", model.getBrowserUrl().toExternalForm());
    Assert.assertEquals(1430869227L, model.getCreationTimestamp().getEpochSecond());
    Assert.assertFalse(model.getModificationTimestamp().isPresent());
  }
}
