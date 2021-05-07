package com.ctseducare.websocket.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;
import org.java_websocket.server.WebSocketServer;

import com.ctseducare.websocket.sslcertificate.SSLCertificate;
import com.ctseducare.websocket.sslcertificate.SSLCertificateException;

public class WSServer extends WebSocketServer {

  private static final String HOST = "localhost";
  private static final int PORT = 8877;
  private static final boolean USE_SSL = true;

  private static WSServer server = null;

  public static WSServer getInstance() throws SSLCertificateException {
    if (server == null) {
      server = new WSServer();
    }
    return server;
  }

  private WSServer() throws SSLCertificateException {
    super(new InetSocketAddress(HOST, PORT));
    if (USE_SSL) {
      super.setWebSocketFactory(new DefaultSSLWebSocketServerFactory(SSLCertificate.define()));
    }
  }

  @Override
  public void onOpen(WebSocket conn, ClientHandshake handshake) {
    conn.send("Welcome to the Websocket Server!");
    broadcast("New connection: " + conn.getRemoteSocketAddress()); //This method sends a message to all clients connected
    System.out.printf("New connection from %s\n", conn.getRemoteSocketAddress());
  }

  @Override
  public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    System.out.printf("Connection %s closed\n", conn.getRemoteSocketAddress());
  }

  @Override
  public void onMessage(WebSocket conn, String message) {
    System.out.printf("Message %s received from %s\n", message, conn.getRemoteSocketAddress());
  }

  @Override
  public void onMessage(WebSocket conn, ByteBuffer message ) {
  }

  @Override
  public void onError(WebSocket conn, Exception ex) {
    System.out.printf("Error %s from %s\n", ex.getMessage(), conn.getRemoteSocketAddress());
  }

  @Override
  public void onStart() {
    System.out.println("Websocket Server started successfully!");
  }

  @Override
  public void start() {
    server.run();
  }
  
  @Override
  public void stop() throws InterruptedException {
    super.stop();
  }

}