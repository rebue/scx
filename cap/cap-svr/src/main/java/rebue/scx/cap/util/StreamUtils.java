package rebue.scx.cap.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

public abstract class StreamUtils {
    public static final int BUFFER_SIZE = 4096;
    private static final byte[] EMPTY_CONTENT = new byte[0];

    public StreamUtils() {
    }

    public static byte[] copyToByteArray(final InputStream in) throws IOException {
        if (in == null) {
            return new byte[0];
        } else {
            final ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
            copy(in, out);
            return out.toByteArray();
        }
    }

    public static String copyToString(final InputStream in, final Charset charset) throws IOException {
        if (in == null) {
            return "";
        } else {
            final StringBuilder out = new StringBuilder();
            final InputStreamReader reader = new InputStreamReader(in, charset);
            final char[] buffer = new char[4096];
            final boolean var5 = true;

            int bytesRead;
            while((bytesRead = reader.read(buffer)) != -1) {
                out.append(buffer, 0, bytesRead);
            }

            return out.toString();
        }
    }

    public static void copy(final byte[] in, final OutputStream out) throws IOException {
        out.write(in);
    }

    public static void copy(final String in, final Charset charset, final OutputStream out) throws IOException {
        final Writer writer = new OutputStreamWriter(out, charset);
        writer.write(in);
        writer.flush();
    }

    public static int copy(final InputStream in, final OutputStream out) throws IOException {
        int byteCount = 0;
        final byte[] buffer = new byte[4096];

        int bytesRead;
        for(final boolean var4 = true; (bytesRead = in.read(buffer)) != -1; byteCount += bytesRead) {
            out.write(buffer, 0, bytesRead);
        }

        out.flush();
        return byteCount;
    }

    public static long copyRange(final InputStream in, final OutputStream out, final long start, final long end) throws IOException {
        final long skipped = in.skip(start);
        if (skipped < start) {
            throw new IOException("Skipped only " + skipped + " bytes out of " + start + " required");
        } else {
            long bytesToCopy = end - start + 1L;
            final byte[] buffer = new byte[4096];

            while(bytesToCopy > 0L) {
                final int bytesRead = in.read(buffer);
                if (bytesRead == -1) {
                    break;
                }

                if (bytesRead <= bytesToCopy) {
                    out.write(buffer, 0, bytesRead);
                    bytesToCopy -= bytesRead;
                } else {
                    out.write(buffer, 0, (int)bytesToCopy);
                    bytesToCopy = 0L;
                }
            }

            return end - start + 1L - bytesToCopy;
        }
    }

    public static int drain(final InputStream in) throws IOException {
        final byte[] buffer = new byte[4096];
        int byteCount;
        int bytesRead;
        for(byteCount = 0; (bytesRead = in.read(buffer)) != -1; byteCount += bytesRead) {
        }

        return byteCount;
    }

    public static InputStream emptyInput() {
        return new ByteArrayInputStream(EMPTY_CONTENT);
    }

    public static InputStream nonClosing(final InputStream in) {
        return new StreamUtils.NonClosingInputStream(in);
    }

    public static OutputStream nonClosing(final OutputStream out) {
        return new StreamUtils.NonClosingOutputStream(out);
    }

    private static class NonClosingOutputStream extends FilterOutputStream {
        public NonClosingOutputStream(final OutputStream out) {
            super(out);
        }

        @Override
        public void write(final byte[] b, final int off, final int let) throws IOException {
            out.write(b, off, let);
        }

        @Override
        public void close() throws IOException {
        }
    }

    private static class NonClosingInputStream extends FilterInputStream {
        public NonClosingInputStream(final InputStream in) {
            super(in);
        }

        @Override
        public void close() throws IOException {
        }
    }
}

