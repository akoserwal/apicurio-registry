/*
 * Copyright 2020 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.registry.utils.kafka;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public abstract class SelfSerde<T> implements Serde<T>, Serializer<T>, Deserializer<T> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) { }

    @Override
    public void close() { }

    @Override
    public Serializer<T> serializer() {
        return this;
    }

    @Override
    public Deserializer<T> deserializer() {
        return this;
    }

    /**
     * A {@link SelfSerde} implementation that serializes {@link Void} type
     * which has the only possible value {@code null}.
     */
    public static final SelfSerde<Void> VOID = new SelfSerde<Void>() {
        @Override
        public Void deserialize(String topic, byte[] data) {
            assert data == null;
            return null;
        }

        @Override
        public byte[] serialize(String topic, Void data) {
            return null;
        }
    };

}