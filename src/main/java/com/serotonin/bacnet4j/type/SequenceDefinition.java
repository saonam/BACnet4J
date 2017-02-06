/*
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2015 Infinite Automation Software. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * When signing a commercial license with Infinite Automation Software,
 * the following extension to GPL is made. A special exception to the GPL is
 * included to allow you to distribute a combined work that includes BAcnet4J
 * without being obliged to provide the source code for any proprietary components.
 *
 * See www.infiniteautomation.com for commercial license options.
 *
 * @author Matthew Lohbihler
 */
package com.serotonin.bacnet4j.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SequenceDefinition implements Serializable {
    private static final long serialVersionUID = 6464244006575549887L;

    private final List<ElementSpecification> elements;

    public SequenceDefinition(final ElementSpecification... specs) {
        elements = new ArrayList<>();
        for (final ElementSpecification spec : specs)
            elements.add(spec);
    }

    public SequenceDefinition(final List<ElementSpecification> elements) {
        this.elements = elements;
    }

    public List<ElementSpecification> getElements() {
        return elements;
    }

    public static class ElementSpecification {
        private final String id;
        private final Class<? extends Encodable> clazz;
        private final int contextId;
        private final boolean sequenceOf;
        private final boolean optional;

        public ElementSpecification(final String id, final Class<? extends Encodable> clazz, final boolean sequenceOf,
                final boolean optional) {
            this.id = id;
            this.clazz = clazz;
            this.contextId = -1;
            this.sequenceOf = sequenceOf;
            this.optional = optional;
        }

        public ElementSpecification(final String id, final Class<? extends Encodable> clazz, final int contextId,
                final boolean sequenceOf, final boolean optional) {
            this.id = id;
            this.clazz = clazz;
            this.contextId = contextId;
            this.sequenceOf = sequenceOf;
            this.optional = optional;
        }

        public String getId() {
            return id;
        }

        public Class<? extends Encodable> getClazz() {
            return clazz;
        }

        public int getContextId() {
            return contextId;
        }

        public boolean isOptional() {
            return optional;
        }

        public boolean isSequenceOf() {
            return sequenceOf;
        }

        public boolean hasContextId() {
            return contextId != -1;
        }
    }
}
