package com.ctseducare.websocket.sslcertificate;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class SSLCertificate {

  private static final String KEYSTORE_FILE = "/opt/websocketssl/javawsssl.jks";
  private static final String STORE_PASSWORD = "ctsABC";
  private static final String KEY_PASSWORD = "ctsABC";

  private SSLCertificate() {}

  public static SSLContext define() throws SSLCertificateException {
    File keystoreFile = new File(KEYSTORE_FILE);
    if (!keystoreFile.exists()) {
      throw new SSLCertificateException(String.format("The keystore file %s not found!", KEYSTORE_FILE));
    }

    try (FileInputStream fis = new FileInputStream(keystoreFile)) {
      KeyStore keystore = KeyStore.getInstance("JKS");
      keystore.load(fis, STORE_PASSWORD.toCharArray());

      KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
      kmf.init(keystore, KEY_PASSWORD.toCharArray());
      TrustManagerFactory tmf = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
      tmf.init(keystore);

      SSLContext sslContext = null;
      sslContext = SSLContext.getInstance("TLS");
      sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

      return sslContext;
    } catch (Exception e) {
      throw new SSLCertificateException("SSL Certificate invalid. The password maybe wrong!");
    }
  }

}
