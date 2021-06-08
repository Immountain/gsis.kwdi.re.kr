package infomind.com.utils.web;

import org.apache.taglibs.standard.resources.Resources;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.util.Locale;

public class InfoWebIncludeResponseWrapper extends HttpServletResponseWrapper {
    private StringWriter sw = new StringWriter();

    private ByteArrayOutputStream bos = new ByteArrayOutputStream();

    private ServletOutputStream sos = new ServletOutputStream() {
        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
        }

        public void write(int b) throws IOException {
            bos.write(b);
        }
    };

    private boolean isWriterUsed;

    private boolean isStreamUsed;

    private int status = 200;

    public InfoWebIncludeResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public PrintWriter getWriter() {
        if (isStreamUsed)
            throw new IllegalStateException(Resources.getMessage("IMPORT_ILLEGAL_STREAM"));

        isWriterUsed = true;

        return new PrintWriter(sw);
    }

    /**
     * Returns a ServletOutputStream designed to buffer the output.
     */
    public ServletOutputStream getOutputStream() {
        if (isWriterUsed)
            throw new IllegalStateException(Resources.getMessage("IMPORT_ILLEGAL_WRITER"));
        isStreamUsed = true;
        return sos;
    }

    /**
     * Has no effect.
     */
    public void setContentType(String x) {
    }

    /**
     * Has no effect.
     */
    public void setLocale(Locale x) {
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String getString() throws UnsupportedEncodingException {
        if (isWriterUsed)
            return sw.toString();
        else if (isStreamUsed) {
            return bos.toString("utf-8");
        } else
            return "";
    }
}
