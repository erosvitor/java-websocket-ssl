# JavaWebsocketSSL

## Descrição
O **JavaWebsocketSSL** é um projeto exemplo que mostra como implementar um servidor websocket SSL com certificado auto-assinado.

## Tecnologias utilizadas
* Java 11
* Apache Maven 3.6.3
* TooTallNate/Java-WebSocket 1.5.2
* SLF4J 1.7.30
* IDE Eclipse

## Certificado auto-assinado
Para gerar um certificado auto-assinado para o servidor Websocket SSL, deve-se utilizar a ferramenta 'keytool' presente no pacote JDK.

*Sintaxe da ferramenta 'keytool'*

```
$ JAVA_HOME/bin/keytool \
    -genkeypair \
    -alias <nome-alias> \
    -keyalg RSA \
    -keypass <senha> \
    -keystore <path>/<nome-arquivo>.jks \
    -storepass <senha> \
    -dname "cn=<nome-empresa>, ou=<nome-departamento>, o=<nome-empresa>, L=<nome-cidade>, ST=<sigla-estado>, c=<sigla-pais>"
```
*Exemplo de como gerar um certificado auto-assinado*

```
$ JAVA_HOME/bin/keytool \
    -genkeypair \
    -alias javawsssl \
    -keyalg RSA \
    -keypass ctsABC \
    -keystore javawsssl.jks \
    -storepass ctsABC \
    -dname "cn=CTSEducare, ou=Engenharia, o=CTSEducare, L=Curitiba, ST=PR, c=BR"
```

O comando acima gera um arquivo (certificado) com o nome 'javawsssl.jks' no local onde o comando for executado. O certificado terá como senhas 'ctsABC'. Estas informações são utilizadas pela classe **SSLCertificate.java**. Caso deseje alterar o nome do arquivo ou as senhas, será necessário ajustar a classe correspondente.

Após ter gerado o certificado, o mesmo deve ser armazenado no diretório **/opt/websocketssl**. Caso deseje armazená-lo em outro local, deve-se ajustar a classe **SSLCertificate.java**.

## Documentação

### Diagrama de classes

![](references/diagrama-classes.png) 

## Histórico de lançamentos

* 1.0.0 (2021-05-06)
    * Primeira versão
