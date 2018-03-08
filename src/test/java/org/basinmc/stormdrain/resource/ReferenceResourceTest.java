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
public class ReferenceResourceTest extends AbstractResourceParserTest<Reference> {

  public ReferenceResourceTest() {
    super("reference", Reference.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doTest(@NonNull Reference model) {
    Assert.assertEquals("baxterthehacker:master", model.getLabel());
    Assert.assertEquals("master", model.getReference());
    Assert.assertEquals("9049f1265b7d61be4a8904a9a27120d2064dab3b", model.getCommitId());
    Assert.assertNotNull(model.getUser());
    Assert.assertNotNull(model.getRepository());
  }
}
