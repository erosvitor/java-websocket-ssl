package com.ctseducare.websocket;

import com.ctseducare.websocket.server.WSServer;
import com.ctseducare.websocket.sslcertificate.SSLCertificateException;

public class ApplicationStart {

  public static void main(String[] args) {
    try {
      WSServer.getInstance().start();
    } catch (SSLCertificateException e) {
      e.printStackTrace();
    }

  }

}
