package com.ddr.drugtrace.solidity.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Keys;
import org.web3j.protocol.http.HttpService;

import java.util.Objects;

@ConfigurationProperties(
        prefix = "contract"
)
@EnableAutoConfiguration
@Data
@Component
public class ContractProp {
    private String url;
    private String privateKey;
    // 钱包地址
    private volatile String address;
    // 合约地址
    private volatile String contractAddress;
    private volatile HttpService httpService;
    private volatile Credentials credentials;

    public String getAddress() {
        if (!StringUtils.hasText(address)) {
            address = Keys.toChecksumAddress(Keys.getAddress(getCredentials().getEcKeyPair().getPublicKey()));
        }
        return address;
    }

    public HttpService getHttpService() {
        if (Objects.isNull(httpService)) {
            synchronized (this) {
                if (Objects.nonNull(httpService)) {
                    return httpService;
                }
                Assert.hasText(url, "[SmartContract]url is null");
                httpService = new HttpService(url);
            }
        }
        return httpService;
    }

    public Credentials getCredentials() {
        if (Objects.isNull(credentials)) {
            synchronized (this) {
                if (Objects.nonNull(credentials)) {
                    return credentials;
                }
                Assert.hasText(privateKey, "[SmartContract]privateKey is null");
                credentials = Credentials.create(privateKey);
            }
        }
        return credentials;
    }
}
