/*
 * Copyright 2016 Foundation authors
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
package com.paritytrading.foundation;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.Test;

class ByteBuffersTest {

    @Test
    void gettingAbsoluteBulk() {
        ByteBuffer src = ByteBuffer.wrap(new byte[] { 0x01, 0x02, 0x03, 0x04 });
        byte[]     dst = new byte[2];

        ByteBuffers.get(src, dst, 1);

        assertArrayEquals(new byte[] { 0x02, 0x03 }, dst);
    }

    @Test
    void gettingAbsoluteBulkWithOffsetAndLength() {
        ByteBuffer src = ByteBuffer.wrap(new byte[] { 0x01, 0x02, 0x03, 0x04 });
        byte[]     dst = new byte[4];

        ByteBuffers.get(src, dst, 1, 2, 2);

        assertArrayEquals(new byte[] { 0x00, 0x00, 0x02, 0x03 }, dst);
    }

    @Test
    void puttingAbsoluteBulk() {
        byte[]     src = new byte[] { 0x01, 0x02 };
        ByteBuffer dst = ByteBuffer.wrap(new byte[4]);

        ByteBuffers.put(dst, src, 1);

        assertArrayEquals(new byte[] { 0x00, 0x01, 0x02, 0x00 }, dst.array());
    }

    @Test
    void puttingAbsoluteBulkWithOffsetAndLength() {
        byte[]     src = new byte[] { 0x01, 0x02, 0x03, 0x04 };
        ByteBuffer dst = ByteBuffer.wrap(new byte[4]);

        ByteBuffers.put(dst, src, 1, 2, 2);

        assertArrayEquals(new byte[] { 0x00, 0x03, 0x04, 0x00 }, dst.array());
    }

    @Test
    void gettingRelativeUnsignedByte() {
        ByteBuffer buffer = ByteBuffer.allocate(1);

        buffer.put((byte)0xff);
        buffer.flip();

        assertEquals(255, ByteBuffers.getUnsigned(buffer));
    }

    @Test
    void gettingAbsoluteUnsignedByte() {
        ByteBuffer buffer = ByteBuffer.allocate(1);

        buffer.put((byte)0xff);
        buffer.flip();

        assertEquals(255, ByteBuffers.getUnsigned(buffer, 0));
    }

    @Test
    void puttingRelativeUnsignedByte() {
        ByteBuffer buffer = ByteBuffer.allocate(1);

        ByteBuffers.putUnsigned(buffer, (short)255);
        buffer.flip();

        assertEquals((byte)0xff, buffer.get());
    }

    @Test
    void puttingAbsoluteUnsignedByte() {
        ByteBuffer buffer = ByteBuffer.allocate(1);

        ByteBuffers.putUnsigned(buffer, 0, (short)255);

        assertEquals((byte)0xff, buffer.get());
    }

    @Test
    void gettingRelativeUnsignedShort() {
        ByteBuffer buffer = ByteBuffer.allocate(2);

        buffer.putShort((short)0xffff);
        buffer.flip();

        assertEquals(65535, ByteBuffers.getUnsignedShort(buffer));
    }

    @Test
    void gettingAbsoluteUnsignedShort() {
        ByteBuffer buffer = ByteBuffer.allocate(2);

        buffer.putShort((short)0xffff);
        buffer.flip();

        assertEquals(65535, ByteBuffers.getUnsignedShort(buffer, 0));
    }

    @Test
    void puttingRelativeUnsignedShort() {
        ByteBuffer buffer = ByteBuffer.allocate(2);

        ByteBuffers.putUnsignedShort(buffer, 65535);
        buffer.flip();

        assertEquals((short)0xffff, buffer.getShort());
    }

    @Test
    void puttingAbsoluteUnsignedShort() {
        ByteBuffer buffer = ByteBuffer.allocate(2);

        ByteBuffers.putUnsignedShort(buffer, 0, 65535);

        assertEquals((short)0xffff, buffer.getShort());
    }

    @Test
    void gettingRelativeUnsignedInteger() {
        ByteBuffer buffer = ByteBuffer.allocate(4);

        buffer.putInt(0xffffffff);
        buffer.flip();

        assertEquals(4294967295L, ByteBuffers.getUnsignedInt(buffer));
    }

    @Test
    void gettingAbsoluteUnsignedInteger() {
        ByteBuffer buffer = ByteBuffer.allocate(4);

        buffer.putInt(0xffffffff);
        buffer.flip();

        assertEquals(4294967295L, ByteBuffers.getUnsignedInt(buffer, 0));
    }

    @Test
    void puttingRelativeUnsignedInteger() {
        ByteBuffer buffer = ByteBuffer.allocate(4);

        ByteBuffers.putUnsignedInt(buffer, 4294967295L);
        buffer.flip();

        assertEquals(0xffffffff, buffer.getInt());
    }

    @Test
    void puttingAbsoluteUnsignedInteger() {
        ByteBuffer buffer = ByteBuffer.allocate(4);

        ByteBuffers.putUnsignedInt(buffer, 0, 4294967295L);

        assertEquals(0xffffffff, buffer.getInt());
    }

}
