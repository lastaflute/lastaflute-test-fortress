package org.docksidestage.remote.fortress;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.dbflute.remoteapi.exception.RemoteApiIOException;
import org.docksidestage.remote.fortressman.RemoteFortressManBhv;
import org.docksidestage.remote.fortressman.wx.multipart.RemoteFrMultipartParam;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.web.ruts.multipart.MultipartFormFile;

/**
 * @author jflute
 */
public class RemoteFortressBhvTest extends UnitFortressBasicTestCase {

    @Resource
    private RemoteFortressManBhv fortressBhv;

    public void test_requestWxMultipart_basic() throws IOException {
        // ## Arrange ##
        RemoteFrMultipartParam param = new RemoteFrMultipartParam();
        param.sea = "mystic";
        param.land = 1;
        param.uploadedFile = prepareSimpleTextFormFile();

        // ## Act ##
        // ## Assert ##
        try {
            fortressBhv.requestWxMultipart(param); // see receiver log for now
        } catch (RemoteApiIOException e) {
            final Throwable cause = e.getCause();
            if (cause != null && cause.getMessage() != null && isNotBoot(cause.getMessage())) {
                log("Failed to request myself: param=" + param, e); // continue because of visual check only
            } else {
                throw e;
            }
        }
    }

    private boolean isNotBoot(final String msg) {
        return msg.contains("Error 503 Service Unavailable") || msg.contains("Read timed out") || msg.contains("Connection refused");
    }

    private MultipartFormFile prepareSimpleTextFormFile() throws UnsupportedEncodingException {
        byte[] fileData = "resort".getBytes("UTF-8");
        String fileName = "maihama.txt";
        return new ByteArrayMultipartFormFile(fileData, fileName);
    }

    private static class ByteArrayMultipartFormFile implements MultipartFormFile {

        protected final byte[] fileData;
        protected final String fileName;

        public ByteArrayMultipartFormFile(byte[] fileData, String fileName) {
            this.fileData = fileData;
            this.fileName = fileName;
        }

        @Override
        public byte[] getFileData() throws IOException {
            return fileData;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(fileData);
        }

        @Override
        public String getContentType() {
            return "application/octet-stream";
        }

        @Override
        public int getFileSize() { // unused on MultipartEntityBuilder@addBinaryBody()
            return fileData.length; // #thinking OK? or needed?
        }

        @Override
        public String getFileName() {
            return fileName;
        }

        @Override
        public void destroy() {
            // do nothing, unsupported
        }
    }
}
