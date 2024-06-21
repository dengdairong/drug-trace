package com.ddr.drugtrace.solidity.config;

import com.ddr.drugtrace.solidity.DrugTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Configuration
public class ContractConfig {
    @Autowired
    private ContractProp contractProp;

    @Bean
    public Web3j getWeb3j() {
        return Web3j.build(contractProp.getHttpService());
    }

    @Bean
    public ContractGasProvider getContractGasProvider() {
        return new StaticGasProvider(BigInteger.valueOf(20000000000L), BigInteger.valueOf(6721975L));
    }

    @Bean
    public DrugTrace getDrugTrace(Web3j web3j) throws Exception {
        return DrugTrace.load(contractProp.getContractAddress(), getWeb3j(), contractProp.getCredentials(), getContractGasProvider());
    }

    /*public static void main(String[] args) throws Throwable {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();//调用Keys的静态方法创建密钥对
        String privateKey = ecKeyPair.getPrivateKey().toString(16);//获取私钥
        String publicKey = ecKeyPair.getPublicKey().toString(16);//获取公钥
        String address = Keys.getAddress(publicKey);//获取地址值
        System.out.println("Your private key:" + privateKey);
        System.out.println("Your public key:" + publicKey);
        System.out.println("Your address:" + address);
    }*/
    /*public static void main(String[] args) throws Throwable {
        Credentials credentials = Credentials.create("8d7c434caa316a231b008ecb9d8d7d0cff3fe6cce0e004bd2edbccf4e152d042");
        ECKeyPair keyPair = credentials.getEcKeyPair();
        String address = Keys.getAddress(keyPair.getPublicKey());
        String publicKey = Keys.toChecksumAddress(address);
        System.out.println("公钥: " + credentials.getEcKeyPair().getPublicKey());
        System.out.println("地址: " + address);
        System.out.println("地址: " + publicKey);
    }*/
}
