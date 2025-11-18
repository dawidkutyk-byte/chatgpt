/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ExceptionLogger
 *  org.apache.http.HttpConnectionFactory
 *  org.apache.http.HttpServerConnection
 *  org.apache.http.config.SocketConfig
 *  org.apache.http.impl.bootstrap.Worker
 *  org.apache.http.protocol.HttpService
 */
package org.apache.http.impl.bootstrap;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.ExceptionLogger;
import org.apache.http.HttpConnectionFactory;
import org.apache.http.HttpServerConnection;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.bootstrap.Worker;
import org.apache.http.protocol.HttpService;

class RequestListener
implements Runnable {
    private final ServerSocket serversocket;
    private final HttpConnectionFactory<? extends HttpServerConnection> connectionFactory;
    private final ExceptionLogger exceptionLogger;
    private final AtomicBoolean terminated;
    private final HttpService httpService;
    private final SocketConfig socketConfig;
    private final ExecutorService executorService;

    public boolean isTerminated() {
        return this.terminated.get();
    }

    public void terminate() throws IOException {
        if (!this.terminated.compareAndSet(false, true)) return;
        this.serversocket.close();
    }

    public RequestListener(SocketConfig socketConfig, ServerSocket serversocket, HttpService httpService, HttpConnectionFactory<? extends HttpServerConnection> connectionFactory, ExceptionLogger exceptionLogger, ExecutorService executorService) {
        this.socketConfig = socketConfig;
        this.serversocket = serversocket;
        this.connectionFactory = connectionFactory;
        this.httpService = httpService;
        this.exceptionLogger = exceptionLogger;
        this.executorService = executorService;
        this.terminated = new AtomicBoolean(false);
    }

    @Override
    public void run() {
        try {
            while (!this.isTerminated()) {
                if (Thread.interrupted()) return;
                Socket socket = this.serversocket.accept();
                socket.setSoTimeout(this.socketConfig.getSoTimeout());
                socket.setKeepAlive(this.socketConfig.isSoKeepAlive());
                socket.setTcpNoDelay(this.socketConfig.isTcpNoDelay());
                if (this.socketConfig.getRcvBufSize() > 0) {
                    socket.setReceiveBufferSize(this.socketConfig.getRcvBufSize());
                }
                if (this.socketConfig.getSndBufSize() > 0) {
                    socket.setSendBufferSize(this.socketConfig.getSndBufSize());
                }
                if (this.socketConfig.getSoLinger() >= 0) {
                    socket.setSoLinger(true, this.socketConfig.getSoLinger());
                }
                HttpServerConnection conn = (HttpServerConnection)this.connectionFactory.createConnection(socket);
                Worker worker = new Worker(this.httpService, conn, this.exceptionLogger);
                this.executorService.execute((Runnable)worker);
            }
            return;
        }
        catch (Exception ex) {
            this.exceptionLogger.log(ex);
        }
    }
}
