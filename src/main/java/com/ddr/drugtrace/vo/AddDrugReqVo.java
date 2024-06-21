package com.ddr.drugtrace.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class AddDrugReqVo {
    private BigInteger producer;
    private String prouctionDataStr;
}
