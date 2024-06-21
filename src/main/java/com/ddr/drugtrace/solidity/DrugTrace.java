package com.ddr.drugtrace.solidity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple12;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.11.3.
 */
@SuppressWarnings("rawtypes")
public class DrugTrace extends Contract {
    public static final String BINARY = "60806040526001805534801561001457600080fd5b50612358806100246000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c8063891cde9911610071578063891cde9914610176578063897b3c74146101a65780639af82521146101d6578063d6febde814610206578063d70518c714610222578063fca5e61e1461023e576100a9565b8063154cce35146100ae5780632df74c27146100de578063327af74c146100fa57806344bd7bf61461011657806349eb063d14610146575b600080fd5b6100c860048036038101906100c39190611753565b610279565b6040516100d5919061178f565b60405180910390f35b6100f860048036038101906100f391906118f0565b61031e565b005b610114600480360381019061010f919061197b565b6103f2565b005b610130600480360381019061012b9190611753565b6104c5565b60405161013d919061178f565b60405180910390f35b610160600480360381019061015b91906119ea565b6104e9565b60405161016d9190611cad565b60405180910390f35b610190600480360381019061018b9190611ccf565b6109ee565b60405161019d919061178f565b60405180910390f35b6101c060048036038101906101bb9190611d0f565b610ab6565b6040516101cd9190611cad565b60405180910390f35b6101f060048036038101906101eb9190611ccf565b611011565b6040516101fd919061178f565b60405180910390f35b610220600480360381019061021b9190611ccf565b611042565b005b61023c60048036038101906102379190611d76565b611199565b005b61025860048036038101906102539190611753565b611390565b6040516102709c9b9a99989796959493929190611e1c565b60405180910390f35b6000806000905060005b6003805490508110156103145760008414806102ed57508360006001600384815481106102b3576102b2611ef9565b5b90600052602060002001546102c89190611f57565b815481106102d9576102d8611ef9565b5b90600052602060002090600c020160000154145b156103015781806102fd90611f8b565b9250505b808061030c90611f8b565b915050610283565b5080915050919050565b60008060018561032e9190611f57565b8154811061033f5761033e611ef9565b5b90600052602060002090600c020190504281600701819055508281600801908161036991906121df565b508181600901908161037b91906121df565b506040518060400160405280600981526020017fe7bb8fe99480e5958600000000000000000000000000000000000000000000008152508160040190816103c291906121df565b50600384908060018154018082558091505060019003906000526020600020016000909190919091505550505050565b6000806001856104029190611f57565b8154811061041357610412611ef9565b5b90600052602060002090600c020190508281600601819055506040518060400160405280601481526020017fe7949fe4baa7e595862d3ee7bb8fe99480e5958600000000000000000000000081525081600401908161047291906121df565b508181600501908161048491906121df565b506002600084815260200190815260200160002084908060018154018082558091505060019003906000526020600020016000909190919091505550505050565b600381815481106104d557600080fd5b906000526020600020016000915090505481565b60606000836001866104fb9190611f57565b61050591906122b1565b9050600081858761051691906122b1565b6105209190611f57565b9050600380549050858761053491906122b1565b111561054e578160038054905061054b9190611f57565b90505b60008167ffffffffffffffff81111561056a576105696117c5565b5b6040519080825280602002602001820160405280156105a357816020015b6105906116a8565b8152602001906001900390816105885790505b5090506000808490505b600380549050811080156105c057508382105b156109df57600087148061062257508660006001600384815481106105e8576105e7611ef9565b5b90600052602060002001546105fd9190611f57565b8154811061060e5761060d611ef9565b5b90600052602060002090600c020160000154145b156109cc57600060016003838154811061063f5761063e611ef9565b5b90600052602060002001546106549190611f57565b8154811061066557610664611ef9565b5b90600052602060002090600c0201604051806101800160405290816000820154815260200160018201548152602001600282015481526020016003820180546106ad90612002565b80601f01602080910402602001604051908101604052809291908181526020018280546106d990612002565b80156107265780601f106106fb57610100808354040283529160200191610726565b820191906000526020600020905b81548152906001019060200180831161070957829003601f168201915b5050505050815260200160048201805461073f90612002565b80601f016020809104026020016040519081016040528092919081815260200182805461076b90612002565b80156107b85780601f1061078d576101008083540402835291602001916107b8565b820191906000526020600020905b81548152906001019060200180831161079b57829003601f168201915b505050505081526020016005820180546107d190612002565b80601f01602080910402602001604051908101604052809291908181526020018280546107fd90612002565b801561084a5780601f1061081f5761010080835404028352916020019161084a565b820191906000526020600020905b81548152906001019060200180831161082d57829003601f168201915b50505050508152602001600682015481526020016007820154815260200160088201805461087790612002565b80601f01602080910402602001604051908101604052809291908181526020018280546108a390612002565b80156108f05780601f106108c5576101008083540402835291602001916108f0565b820191906000526020600020905b8154815290600101906020018083116108d357829003601f168201915b5050505050815260200160098201805461090990612002565b80601f016020809104026020016040519081016040528092919081815260200182805461093590612002565b80156109825780601f1061095757610100808354040283529160200191610982565b820191906000526020600020905b81548152906001019060200180831161096557829003601f168201915b50505050508152602001600a8201548152602001600b820154815250508383815181106109b2576109b1611ef9565b5b602002602001018190525081806109c890611f8b565b9250505b80806109d790611f8b565b9150506105ad565b82955050505050509392505050565b6000806000905060005b6002600086815260200190815260200160002080549050811015610aab576000841480610a8457508360006001600260008981526020019081526020016000208481548110610a4a57610a49611ef9565b5b9060005260206000200154610a5f9190611f57565b81548110610a7057610a6f611ef9565b5b90600052602060002090600c020160000154145b15610a98578180610a9490611f8b565b9250505b8080610aa390611f8b565b9150506109f8565b508091505092915050565b6060600084600187610ac89190611f57565b610ad291906122b1565b90506000818688610ae391906122b1565b610aed9190611f57565b905060026000868152602001908152602001600020805490508688610b1291906122b1565b1115610b3d57816002600087815260200190815260200160002080549050610b3a9190611f57565b90505b60008167ffffffffffffffff811115610b5957610b586117c5565b5b604051908082528060200260200182016040528015610b9257816020015b610b7f6116a8565b815260200190600190039081610b775790505b5090506000808490505b600260008981526020019081526020016000208054905081108015610bc057508382105b15611001576000871480610c3357508660006001600260008c81526020019081526020016000208481548110610bf957610bf8611ef9565b5b9060005260206000200154610c0e9190611f57565b81548110610c1f57610c1e611ef9565b5b90600052602060002090600c020160000154145b15610fee5760006001600260008b81526020019081526020016000208381548110610c6157610c60611ef9565b5b9060005260206000200154610c769190611f57565b81548110610c8757610c86611ef9565b5b90600052602060002090600c020160405180610180016040529081600082015481526020016001820154815260200160028201548152602001600382018054610ccf90612002565b80601f0160208091040260200160405190810160405280929190818152602001828054610cfb90612002565b8015610d485780601f10610d1d57610100808354040283529160200191610d48565b820191906000526020600020905b815481529060010190602001808311610d2b57829003601f168201915b50505050508152602001600482018054610d6190612002565b80601f0160208091040260200160405190810160405280929190818152602001828054610d8d90612002565b8015610dda5780601f10610daf57610100808354040283529160200191610dda565b820191906000526020600020905b815481529060010190602001808311610dbd57829003601f168201915b50505050508152602001600582018054610df390612002565b80601f0160208091040260200160405190810160405280929190818152602001828054610e1f90612002565b8015610e6c5780601f10610e4157610100808354040283529160200191610e6c565b820191906000526020600020905b815481529060010190602001808311610e4f57829003601f168201915b505050505081526020016006820154815260200160078201548152602001600882018054610e9990612002565b80601f0160208091040260200160405190810160405280929190818152602001828054610ec590612002565b8015610f125780601f10610ee757610100808354040283529160200191610f12565b820191906000526020600020905b815481529060010190602001808311610ef557829003601f168201915b50505050508152602001600982018054610f2b90612002565b80601f0160208091040260200160405190810160405280929190818152602001828054610f5790612002565b8015610fa45780601f10610f7957610100808354040283529160200191610fa4565b820191906000526020600020905b815481529060010190602001808311610f8757829003601f168201915b50505050508152602001600a8201548152602001600b82015481525050838381518110610fd457610fd3611ef9565b5b60200260200101819052508180610fea90611f8b565b9250505b8080610ff990611f8b565b915050610b9c565b8295505050505050949350505050565b6002602052816000526040600020818154811061102d57600080fd5b90600052602060002001600091509150505481565b6000806001846110529190611f57565b8154811061106357611062611ef9565b5b90600052602060002090600c020190508181600a01819055504281600b01819055506040518060400160405280600981526020017fe5b7b2e594aee587ba00000000000000000000000000000000000000000000008152508160040190816110cb91906121df565b5060005b6003805490508110156111935783600382815481106110f1576110f0611ef9565b5b90600052602060002001540361118057600360016003805490506111159190611f57565b8154811061112657611125611ef9565b5b90600052602060002001546003828154811061114557611144611ef9565b5b90600052602060002001819055506003805480611165576111646122f3565b5b60019003818190600052602060002001600090559055611193565b808061118b90611f8b565b9150506110cf565b50505050565b600060405180610180016040528060015481526020018481526020014281526020018381526020016040518060400160405280600981526020017fe7949fe4baa7e5958600000000000000000000000000000000000000000000008152508152602001604051806020016040528060008152508152602001600081526020016000815260200160405180602001604052806000815250815260200160405180602001604052806000815250815260200160008152602001600081525090806001815401808255809150506001900390600052602060002090600c020160009091909190915060008201518160000155602082015181600101556040820151816002015560608201518160030190816112b191906121df565b5060808201518160040190816112c791906121df565b5060a08201518160050190816112dd91906121df565b5060c0820151816006015560e0820151816007015561010082015181600801908161130891906121df565b5061012082015181600901908161131f91906121df565b5061014082015181600a015561016082015181600b015550506002600083815260200190815260200160002060015490806001815401808255809150506001900390600052602060002001600090919091909150556001600081548092919061138790611f8b565b91905055505050565b600081815481106113a057600080fd5b90600052602060002090600c02016000915090508060000154908060010154908060020154908060030180546113d590612002565b80601f016020809104026020016040519081016040528092919081815260200182805461140190612002565b801561144e5780601f106114235761010080835404028352916020019161144e565b820191906000526020600020905b81548152906001019060200180831161143157829003601f168201915b50505050509080600401805461146390612002565b80601f016020809104026020016040519081016040528092919081815260200182805461148f90612002565b80156114dc5780601f106114b1576101008083540402835291602001916114dc565b820191906000526020600020905b8154815290600101906020018083116114bf57829003601f168201915b5050505050908060050180546114f190612002565b80601f016020809104026020016040519081016040528092919081815260200182805461151d90612002565b801561156a5780601f1061153f5761010080835404028352916020019161156a565b820191906000526020600020905b81548152906001019060200180831161154d57829003601f168201915b50505050509080600601549080600701549080600801805461158b90612002565b80601f01602080910402602001604051908101604052809291908181526020018280546115b790612002565b80156116045780601f106115d957610100808354040283529160200191611604565b820191906000526020600020905b8154815290600101906020018083116115e757829003601f168201915b50505050509080600901805461161990612002565b80601f016020809104026020016040519081016040528092919081815260200182805461164590612002565b80156116925780601f1061166757610100808354040283529160200191611692565b820191906000526020600020905b81548152906001019060200180831161167557829003601f168201915b50505050509080600a01549080600b015490508c565b6040518061018001604052806000815260200160008152602001600081526020016060815260200160608152602001606081526020016000815260200160008152602001606081526020016060815260200160008152602001600081525090565b6000604051905090565b600080fd5b600080fd5b6000819050919050565b6117308161171d565b811461173b57600080fd5b50565b60008135905061174d81611727565b92915050565b60006020828403121561176957611768611713565b5b60006117778482850161173e565b91505092915050565b6117898161171d565b82525050565b60006020820190506117a46000830184611780565b92915050565b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6117fd826117b4565b810181811067ffffffffffffffff8211171561181c5761181b6117c5565b5b80604052505050565b600061182f611709565b905061183b82826117f4565b919050565b600067ffffffffffffffff82111561185b5761185a6117c5565b5b611864826117b4565b9050602081019050919050565b82818337600083830152505050565b600061189361188e84611840565b611825565b9050828152602081018484840111156118af576118ae6117af565b5b6118ba848285611871565b509392505050565b600082601f8301126118d7576118d66117aa565b5b81356118e7848260208601611880565b91505092915050565b60008060006060848603121561190957611908611713565b5b60006119178682870161173e565b935050602084013567ffffffffffffffff81111561193857611937611718565b5b611944868287016118c2565b925050604084013567ffffffffffffffff81111561196557611964611718565b5b611971868287016118c2565b9150509250925092565b60008060006060848603121561199457611993611713565b5b60006119a28682870161173e565b93505060206119b38682870161173e565b925050604084013567ffffffffffffffff8111156119d4576119d3611718565b5b6119e0868287016118c2565b9150509250925092565b600080600060608486031215611a0357611a02611713565b5b6000611a118682870161173e565b9350506020611a228682870161173e565b9250506040611a338682870161173e565b9150509250925092565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b611a728161171d565b82525050565b600081519050919050565b600082825260208201905092915050565b60005b83811015611ab2578082015181840152602081019050611a97565b60008484015250505050565b6000611ac982611a78565b611ad38185611a83565b9350611ae3818560208601611a94565b611aec816117b4565b840191505092915050565b600061018083016000830151611b106000860182611a69565b506020830151611b236020860182611a69565b506040830151611b366040860182611a69565b5060608301518482036060860152611b4e8282611abe565b91505060808301518482036080860152611b688282611abe565b91505060a083015184820360a0860152611b828282611abe565b91505060c0830151611b9760c0860182611a69565b5060e0830151611baa60e0860182611a69565b50610100830151848203610100860152611bc48282611abe565b915050610120830151848203610120860152611be08282611abe565b915050610140830151611bf7610140860182611a69565b50610160830151611c0c610160860182611a69565b508091505092915050565b6000611c238383611af7565b905092915050565b6000602082019050919050565b6000611c4382611a3d565b611c4d8185611a48565b935083602082028501611c5f85611a59565b8060005b85811015611c9b5784840389528151611c7c8582611c17565b9450611c8783611c2b565b925060208a01995050600181019050611c63565b50829750879550505050505092915050565b60006020820190508181036000830152611cc78184611c38565b905092915050565b60008060408385031215611ce657611ce5611713565b5b6000611cf48582860161173e565b9250506020611d058582860161173e565b9150509250929050565b60008060008060808587031215611d2957611d28611713565b5b6000611d378782880161173e565b9450506020611d488782880161173e565b9350506040611d598782880161173e565b9250506060611d6a8782880161173e565b91505092959194509250565b60008060408385031215611d8d57611d8c611713565b5b6000611d9b8582860161173e565b925050602083013567ffffffffffffffff811115611dbc57611dbb611718565b5b611dc8858286016118c2565b9150509250929050565b600082825260208201905092915050565b6000611dee82611a78565b611df88185611dd2565b9350611e08818560208601611a94565b611e11816117b4565b840191505092915050565b600061018082019050611e32600083018f611780565b611e3f602083018e611780565b611e4c604083018d611780565b8181036060830152611e5e818c611de3565b90508181036080830152611e72818b611de3565b905081810360a0830152611e86818a611de3565b9050611e9560c0830189611780565b611ea260e0830188611780565b818103610100830152611eb58187611de3565b9050818103610120830152611eca8186611de3565b9050611eda610140830185611780565b611ee8610160830184611780565b9d9c50505050505050505050505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000611f628261171d565b9150611f6d8361171d565b9250828203905081811115611f8557611f84611f28565b5b92915050565b6000611f968261171d565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203611fc857611fc7611f28565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061201a57607f821691505b60208210810361202d5761202c611fd3565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b6000600883026120957fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82612058565b61209f8683612058565b95508019841693508086168417925050509392505050565b6000819050919050565b60006120dc6120d76120d28461171d565b6120b7565b61171d565b9050919050565b6000819050919050565b6120f6836120c1565b61210a612102826120e3565b848454612065565b825550505050565b600090565b61211f612112565b61212a8184846120ed565b505050565b5b8181101561214e57612143600082612117565b600181019050612130565b5050565b601f8211156121935761216481612033565b61216d84612048565b8101602085101561217c578190505b61219061218885612048565b83018261212f565b50505b505050565b600082821c905092915050565b60006121b660001984600802612198565b1980831691505092915050565b60006121cf83836121a5565b9150826002028217905092915050565b6121e882611a78565b67ffffffffffffffff811115612201576122006117c5565b5b61220b8254612002565b612216828285612152565b600060209050601f8311600181146122495760008415612237578287015190505b61224185826121c3565b8655506122a9565b601f19841661225786612033565b60005b8281101561227f5784890151825560018201915060208501945060208101905061225a565b8683101561229c5784890151612298601f8916826121a5565b8355505b6001600288020188555050505b505050505050565b60006122bc8261171d565b91506122c78361171d565b92508282026122d58161171d565b915082820484148315176122ec576122eb611f28565b5b5092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fdfea26469706673582212202c2139efdb30703604eb00da19828f4527f074106816cfa862597bae6d8c4ffa64736f6c63430008140033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACCEPT = "accept";

    public static final String FUNC_ADDDRUG = "addDrug";

    public static final String FUNC_BUY = "buy";

    public static final String FUNC_SALE = "sale";

    public static final String FUNC_DRUGS = "drugs";

    public static final String FUNC_GETDRUGCOUNT = "getDrugCount";

    public static final String FUNC_GETDRUGS = "getDrugs";

    public static final String FUNC_GETPAYABLEDRUGCOUNT = "getPayableDrugCount";

    public static final String FUNC_GETPAYABLEDRUGS = "getPayableDrugs";

    public static final String FUNC_PAYABLEDRUGIDLIST = "payableDrugIdList";

    public static final String FUNC_USERIDTODRUGIDLIST = "userIdToDrugIdList";

    @Deprecated
    protected DrugTrace(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DrugTrace(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DrugTrace(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DrugTrace(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> accept(BigInteger _id, String _drugStorageConditions, String _drugStorageLocation) {
        final Function function = new Function(
                FUNC_ACCEPT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id),
                        new org.web3j.abi.datatypes.Utf8String(_drugStorageConditions),
                        new org.web3j.abi.datatypes.Utf8String(_drugStorageLocation)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addDrug(BigInteger _producer, String _prouctionDataStr) {
        final Function function = new Function(
                FUNC_ADDDRUG,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_producer),
                        new org.web3j.abi.datatypes.Utf8String(_prouctionDataStr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> buy(BigInteger _id, BigInteger _buyer) {
        final Function function = new Function(
                FUNC_BUY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id),
                        new org.web3j.abi.datatypes.generated.Uint256(_buyer)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> sale(BigInteger _id, BigInteger _dealer, String _transportationStr) {
        final Function function = new Function(
                FUNC_SALE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id),
                        new org.web3j.abi.datatypes.generated.Uint256(_dealer),
                        new org.web3j.abi.datatypes.Utf8String(_transportationStr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple12<BigInteger, BigInteger, BigInteger, String, String, String, BigInteger, BigInteger, String, String, BigInteger, BigInteger>> drugs(BigInteger param0) {
        final Function function = new Function(FUNC_DRUGS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple12<BigInteger, BigInteger, BigInteger, String, String, String, BigInteger, BigInteger, String, String, BigInteger, BigInteger>>(function,
                new Callable<Tuple12<BigInteger, BigInteger, BigInteger, String, String, String, BigInteger, BigInteger, String, String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple12<BigInteger, BigInteger, BigInteger, String, String, String, BigInteger, BigInteger, String, String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple12<BigInteger, BigInteger, BigInteger, String, String, String, BigInteger, BigInteger, String, String, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (String) results.get(3).getValue(),
                                (String) results.get(4).getValue(),
                                (String) results.get(5).getValue(),
                                (BigInteger) results.get(6).getValue(),
                                (BigInteger) results.get(7).getValue(),
                                (String) results.get(8).getValue(),
                                (String) results.get(9).getValue(),
                                (BigInteger) results.get(10).getValue(),
                                (BigInteger) results.get(11).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getDrugCount(BigInteger _userId, BigInteger _id) {
        final Function function = new Function(FUNC_GETDRUGCOUNT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_userId),
                        new org.web3j.abi.datatypes.generated.Uint256(_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getDrugs(BigInteger _page, BigInteger _pageSize, BigInteger _userId, BigInteger _id) {
        final Function function = new Function(FUNC_GETDRUGS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_page),
                        new org.web3j.abi.datatypes.generated.Uint256(_pageSize),
                        new org.web3j.abi.datatypes.generated.Uint256(_userId),
                        new org.web3j.abi.datatypes.generated.Uint256(_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Drug>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getPayableDrugCount(BigInteger _id) {
        final Function function = new Function(FUNC_GETPAYABLEDRUGCOUNT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getPayableDrugs(BigInteger _page, BigInteger _pageSize, BigInteger _id) {
        final Function function = new Function(FUNC_GETPAYABLEDRUGS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_page),
                        new org.web3j.abi.datatypes.generated.Uint256(_pageSize),
                        new org.web3j.abi.datatypes.generated.Uint256(_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Drug>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> payableDrugIdList(BigInteger param0) {
        final Function function = new Function(FUNC_PAYABLEDRUGIDLIST,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> userIdToDrugIdList(BigInteger param0, BigInteger param1) {
        final Function function = new Function(FUNC_USERIDTODRUGIDLIST,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0),
                        new org.web3j.abi.datatypes.generated.Uint256(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static DrugTrace load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DrugTrace(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DrugTrace load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DrugTrace(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DrugTrace load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DrugTrace(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DrugTrace load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DrugTrace(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DrugTrace> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DrugTrace.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DrugTrace> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DrugTrace.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<DrugTrace> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DrugTrace.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DrugTrace> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DrugTrace.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class Drug extends DynamicStruct {
        public BigInteger id;

        public BigInteger producer;

        public BigInteger productionDate;

        public String productionDateStr;

        public String flow;

        public String transportationStr;

        public BigInteger dealer;

        public BigInteger drugAcceptanceTime;

        public String drugStorageConditions;

        public String drugStorageLocation;

        public BigInteger buyer;

        public BigInteger buyTime;

        public Drug(BigInteger id, BigInteger producer, BigInteger productionDate, String productionDateStr, String flow, String transportationStr, BigInteger dealer, BigInteger drugAcceptanceTime, String drugStorageConditions, String drugStorageLocation, BigInteger buyer, BigInteger buyTime) {
            super(new org.web3j.abi.datatypes.generated.Uint256(id),
                    new org.web3j.abi.datatypes.generated.Uint256(producer),
                    new org.web3j.abi.datatypes.generated.Uint256(productionDate),
                    new org.web3j.abi.datatypes.Utf8String(productionDateStr),
                    new org.web3j.abi.datatypes.Utf8String(flow),
                    new org.web3j.abi.datatypes.Utf8String(transportationStr),
                    new org.web3j.abi.datatypes.generated.Uint256(dealer),
                    new org.web3j.abi.datatypes.generated.Uint256(drugAcceptanceTime),
                    new org.web3j.abi.datatypes.Utf8String(drugStorageConditions),
                    new org.web3j.abi.datatypes.Utf8String(drugStorageLocation),
                    new org.web3j.abi.datatypes.generated.Uint256(buyer),
                    new org.web3j.abi.datatypes.generated.Uint256(buyTime));
            this.id = id;
            this.producer = producer;
            this.productionDate = productionDate;
            this.productionDateStr = productionDateStr;
            this.flow = flow;
            this.transportationStr = transportationStr;
            this.dealer = dealer;
            this.drugAcceptanceTime = drugAcceptanceTime;
            this.drugStorageConditions = drugStorageConditions;
            this.drugStorageLocation = drugStorageLocation;
            this.buyer = buyer;
            this.buyTime = buyTime;
        }

        public Drug(Uint256 id, Uint256 producer, Uint256 productionDate, Utf8String productionDateStr, Utf8String flow, Utf8String transportationStr, Uint256 dealer, Uint256 drugAcceptanceTime, Utf8String drugStorageConditions, Utf8String drugStorageLocation, Uint256 buyer, Uint256 buyTime) {
            super(id, producer, productionDate, productionDateStr, flow, transportationStr, dealer, drugAcceptanceTime, drugStorageConditions, drugStorageLocation, buyer, buyTime);
            this.id = id.getValue();
            this.producer = producer.getValue();
            this.productionDate = productionDate.getValue();
            this.productionDateStr = productionDateStr.getValue();
            this.flow = flow.getValue();
            this.transportationStr = transportationStr.getValue();
            this.dealer = dealer.getValue();
            this.drugAcceptanceTime = drugAcceptanceTime.getValue();
            this.drugStorageConditions = drugStorageConditions.getValue();
            this.drugStorageLocation = drugStorageLocation.getValue();
            this.buyer = buyer.getValue();
            this.buyTime = buyTime.getValue();
        }
    }
}
