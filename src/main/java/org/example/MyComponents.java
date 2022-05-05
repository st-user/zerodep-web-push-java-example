package org.example;

import com.zerodeplibs.webpush.VAPIDKeyPair;
import com.zerodeplibs.webpush.VAPIDKeyPairs;
import com.zerodeplibs.webpush.key.PrivateKeySources;
import com.zerodeplibs.webpush.key.PublicKeySources;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Bean {

    /**
     * In this example, we read a key pair for VAPID
     * from a PEM formatted file on the file system.
     * <p>
     * You can extract key pairs from various sources:
     * '.der' file(binary content), an octet sequence stored in a database and so on.
     * For more information, please see the javadoc of PrivateKeySources and PublicKeySources.
     */
    @org.springframework.context.annotation.Bean
    public VAPIDKeyPair vaidKeyPair(
        @Value("${private.key.file.path}") String privateKeyFilePath,
        @Value("${public.key.file.path}") String publicKeyFilePath) throws IOException {

        return VAPIDKeyPairs.of(
            PrivateKeySources.ofPEMFile(new File(privateKeyFilePath).toPath()),
            PublicKeySources.ofPEMFile(new File(publicKeyFilePath).toPath())

            /*
             * If you want to make your own VAPIDJWTGenerator,
             * the project for its sub-modules is a good example.
             * For more information, please consult the source codes on https://github.com/st-user/zerodep-web-push-java-ext-jwt
             */

            // (privateKey, publicKey) -> new MyOwnVAPIDJWTGenerator(privateKey)
        );
    }
}
