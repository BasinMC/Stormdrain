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
package org.basinmc.drain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Objects;
import org.basinmc.drain.resource.Deployment;
import org.basinmc.drain.resource.DeploymentStatus;
import org.basinmc.drain.resource.Repository;
import org.basinmc.drain.resource.User;

/**
 * Represents an event which notifies its receiver of the change of the status of a previously
 * queued deployment.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class DeploymentStatusEvent extends AbstractRepositoryEvent {

  private final DeploymentStatus deploymentStatus;
  private final Deployment deployment;

  @JsonCreator
  public DeploymentStatusEvent(
      @NonNull @JsonProperty(value = "deployment_status", required = true) DeploymentStatus deploymentStatus,
      @NonNull @JsonProperty(value = "deployment", required = true) Deployment deployment,
      @NonNull @JsonProperty(value = "repository", required = true) Repository repository,
      @NonNull @JsonProperty(value = "sender", required = true) User sender) {
    super(repository, sender);
    this.deploymentStatus = deploymentStatus;
    this.deployment = deployment;
  }

  /**
   * Retrieves the new status of the deployment in question.
   *
   * @return a status.
   */
  @NonNull
  public DeploymentStatus getDeploymentStatus() {
    return this.deploymentStatus;
  }

  /**
   * Retrieves the deployment which's status has been changed.
   *
   * @return a deployment.
   */
  @NonNull
  public Deployment getDeployment() {
    return this.deployment;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DeploymentStatusEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    DeploymentStatusEvent that = (DeploymentStatusEvent) o;
    return Objects.equals(this.deploymentStatus, that.deploymentStatus) &&
        Objects.equals(this.deployment, that.deployment);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.deploymentStatus, this.deployment);
  }
}
