package com.ddr.drugtrace.controller;

import com.ddr.drugtrace.solidity.DrugTrace;
import com.ddr.drugtrace.util.ResponseDto;
import com.ddr.drugtrace.vo.AddDrugReqVo;
import com.ddr.drugtrace.vo.DrugConutReqVo;
import com.ddr.drugtrace.vo.DrugsReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.tuples.generated.Tuple12;

import java.math.BigInteger;

@RestController
@RequestMapping("/drugtrace")
public class DrugTraceController {
    @Autowired
    private DrugTrace drugTrace;
    @Autowired
    private Web3j web3j;
    @PostMapping("/getDrugCount")
    public ResponseDto<BigInteger> getDrugCount(@RequestBody DrugConutReqVo vo) {
        try {
            return ResponseDto.success(drugTrace.getDrugCount(vo.getUserId(), vo.getId()).send());
        } catch (Throwable e) {
            e.printStackTrace();
            return ResponseDto.fail(e.getMessage());
        }
    }

    @PostMapping("/addDrug")
    public ResponseDto<Void> addDrug(@RequestBody AddDrugReqVo vo) {
        try {
            drugTrace.addDrug(vo.getProducer(), vo.getProuctionDataStr()).send();
            return ResponseDto.success(null);
        } catch (Throwable e) {
            e.printStackTrace();
            return ResponseDto.fail(e.getMessage());
        }
    }

    @PostMapping("/drugs")
    public ResponseDto<Tuple12<BigInteger, BigInteger, BigInteger, String, String, String, BigInteger, BigInteger, String, String, BigInteger, BigInteger>> drugs(@RequestBody DrugsReqVo vo) {
        try {
            return ResponseDto.success(drugTrace.drugs(vo.getIndex()).send());
        } catch (Throwable e) {
            e.printStackTrace();
            return ResponseDto.fail(e.getMessage());
        }
    }
}
