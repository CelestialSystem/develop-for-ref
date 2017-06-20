package model;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import com.google.gson.*;

/**
 * Generates and wraps policies and signatures.
 * See https://www.filestack.com/docs/security for more information on how this should be used.
 */
public class Security {
    private String encodedPolicy;
    private String signature;

    /**
     * Creates an instance from a {@link Policy Policy} object and app secret.
     * Use this to create new policy and signature pairs.
     * Don't include your app secret in client apps.
     * Use the {@link #fromExisting(String, String) fromExisting} method instead in that case.
     *
     * @param policy Configured {@link Policy Policy} object
     * @param appSecret Secret taken from developer portal
     */
    public static Security createNew(Policy policy, String appSecret) {
        Gson gson = new Gson();
        HashFunction hashFunction = Hashing.hmacSha256(appSecret.getBytes(Charsets.UTF_8));

        String jsonPolicy = gson.toJson(policy);
        String encodedPolicy = BaseEncoding.base64Url().encode(jsonPolicy.getBytes(Charsets.UTF_8));
        String signature = hashFunction.hashString(encodedPolicy, Charsets.UTF_8).toString();

        return new Security(encodedPolicy, signature);
    }

    /**
     * Creates a Security object from an existing policy and signature pair.
     * Useful for client side apps or cases where you don't want to include your app secret.
     *
     * @param encodedPolicy Base64URL encoded policy
     * @param signature HMAC SHA-256 signature of policy
     */
    public static Security fromExisting(String encodedPolicy, String signature) {
        return new Security(encodedPolicy, signature);
    }

    private Security(String encodedPolicy, String signature) {
        this.encodedPolicy = encodedPolicy;
        this.signature = signature;
    }

    public String getEncodedPolicy() {
        return encodedPolicy;
    }

    public String getSignature() {
        return signature;
    }
}
