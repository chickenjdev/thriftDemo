package com.mycompany.training.jetty;

import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ResponseOutput {
    public ResponseOutput(HttpServletResponse response, ServletOutputStream out, ByteBuffer content, AsyncContext async) {
        response.setHeader("Content-Type", "application/json");
        out.setWriteListener(new WriteListener() {
            @Override
            public void onWritePossible() throws IOException {
                while (out.isReady()) {
                    if (!content.hasRemaining()) {
                        response.setStatus(200);
                        async.complete();
                        return;
                    }
                    out.write(content.get());
                }
            }

            @Override
            public void onError(Throwable t) {
                async.complete();
            }
        });
    }
}
