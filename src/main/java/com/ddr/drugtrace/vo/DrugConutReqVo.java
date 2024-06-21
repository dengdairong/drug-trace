package com.ddr.drugtrace.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class DrugConutReqVo {
    private BigInteger userId;
    private BigInteger id;
}
