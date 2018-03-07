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
package org.basinmc.stormdrain;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Objects;
import java.util.UUID;
import org.basinmc.stormdrain.event.Event;

/**
 * Represents a full payload with all of its data.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class Payload<E extends Event> {

  private final UUID deliveryId;
  private final PayloadType type;
  private final E event;

  public Payload(@NonNull UUID deliveryId, @NonNull PayloadType type, @NonNull E event) {
    this.deliveryId = deliveryId;
    this.type = type;
    this.event = event;

    if (type.getType() != event.getClass()) {
      throw new IllegalArgumentException(
          "Illegal event type: Expected " + type.getType().getName() + " but got " + event
              .getClass().getName());
    }
  }

  /**
   * Retrieves the delivery identification (e.g. an identifier which only ever refers to a single
   * specific delivery of an event and may thus be used to identify retries).
   *
   * @return a delivery identifier.
   */
  @NonNull
  public UUID getDeliveryId() {
    return this.deliveryId;
  }

  /**
   * Retrieves the type of event which is represented by this payload.
   *
   * @return a payload type.
   */
  @NonNull
  public PayloadType getType() {
    return this.type;
  }

  /**
   * Retrieves the actual event contained within this payload.
   *
   * @return an event.
   */
  @NonNull
  public E getEvent() {
    return this.event;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Payload)) {
      return false;
    }
    Payload<?> payload = (Payload<?>) o;
    return Objects.equals(this.deliveryId, payload.deliveryId) &&
        this.type == payload.type &&
        Objects.equals(this.event, payload.event);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.deliveryId, this.type, this.event);
  }
}
