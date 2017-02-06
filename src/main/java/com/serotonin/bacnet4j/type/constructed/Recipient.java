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
package com.serotonin.bacnet4j.type.constructed;

import java.util.ArrayList;
import java.util.List;

import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.type.Encodable;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.util.sero.ByteQueue;

public class Recipient extends BaseType {
    private static final long serialVersionUID = -2993858722446507060L;

    private final Choice choice;

    private static List<Class<? extends Encodable>> classes;
    static {
        classes = new ArrayList<>();
        classes.add(ObjectIdentifier.class);
        classes.add(Address.class);
    }

    public Recipient(final ObjectIdentifier device) {
        choice = new Choice(0, device);
    }

    public Recipient(final Address address) {
        choice = new Choice(1, address);
    }

    public boolean isDevice() {
        return choice.getContextId() == 0;
    }

    public ObjectIdentifier getDevice() {
        return (ObjectIdentifier) choice.getDatum();
    }

    public boolean isAddress() {
        return choice.getContextId() == 1;
    }

    public Address getAddress() {
        return (Address) choice.getDatum();
    }

    @Override
    public void write(final ByteQueue queue) {
        write(queue, choice);
    }

    public Recipient(final ByteQueue queue) throws BACnetException {
        choice = new Choice(queue, classes);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (choice == null ? 0 : choice.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Recipient other = (Recipient) obj;
        if (choice == null) {
            if (other.choice != null)
                return false;
        } else if (!choice.equals(other.choice))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Recipient [choice=" + choice + "]";
    }
}
