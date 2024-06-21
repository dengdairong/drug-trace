// SPDX-License-Identifier: MIT
pragma solidity >=0.7.0 <0.9.0;

contract DrugTrace {

    struct Drug {
        uint id;
        uint producer;
        uint productionDate;
        string productionDateStr;
        string flow;
        string transportationStr;
        uint dealer;
        uint drugAcceptanceTime;
        string drugStorageConditions;
        string drugStorageLocation;
        uint buyer;
        uint buyTime;
    }

    Drug[] public drugs;
    uint drugCount = 1;
    mapping(uint => uint[]) public userIdToDrugIdList;
    uint[] public payableDrugIdList;

    // 添加
    function addDrug(uint _producer, string memory _prouctionDataStr) public {
        drugs.push(Drug(drugCount, _producer, block.timestamp,  _prouctionDataStr, unicode"生产商",
        "", 0, 0, "", "", 0, 0));
        userIdToDrugIdList[_producer].push(drugCount);
        drugCount++;
    }

    // 分页查询, 支持可选条件编号
    function getDrugs(uint _page, uint _pageSize, uint _userId, uint _id) public view returns (Drug[] memory) {
        uint start = (_page - 1) * _pageSize;
        uint length = (_page * _pageSize) - start;
        if (_page * _pageSize > userIdToDrugIdList[_userId].length) {
            length = userIdToDrugIdList[_userId].length - start;
        }
        Drug[] memory result = new Drug[](length);
        uint index = 0;
        uint i = start;
        while (i < userIdToDrugIdList[_userId].length && index < length) {
            if (_id == 0 || drugs[userIdToDrugIdList[_userId][i] - 1].id == _id) {
                result[index] = drugs[userIdToDrugIdList[_userId][i] - 1];
                index++;
            }
            i++;
        }
        return result;
    }

    // 返回总数
    function getDrugCount(uint _userId, uint _id) public view returns (uint) {
        uint count = 0;
        for (uint i = 0; i < userIdToDrugIdList[_userId].length; i++) {
            if (_id == 0 || drugs[userIdToDrugIdList[_userId][i] - 1].id == _id) {
                count++;
            }
        }
        return count;
    }

    function sale(uint _id, uint _dealer, string memory _transportationStr) public {
        Drug storage drug = drugs[_id - 1];
        drug.dealer = _dealer;
        drug.flow = unicode"生产商->经销商";
        drug.transportationStr = _transportationStr;
        userIdToDrugIdList[_dealer].push(_id);
    }

    function accept(uint _id,  string memory _drugStorageConditions, string memory _drugStorageLocation) public {
        Drug storage drug = drugs[_id - 1];
        drug.drugAcceptanceTime = block.timestamp;
        drug.drugStorageConditions = _drugStorageConditions;
        drug.drugStorageLocation = _drugStorageLocation;
        drug.flow = unicode"经销商";
        payableDrugIdList.push(_id);
    }

    // 分页查询查询可购买的药品，支持可选条件编号
    function getPayableDrugs(uint _page, uint _pageSize, uint _id) public view returns (Drug[] memory) {
        uint start = (_page - 1) * _pageSize;
        uint length = (_page * _pageSize) - start;
        if (_page * _pageSize > payableDrugIdList.length) {
            length = payableDrugIdList.length - start;
        }
        Drug[] memory result = new Drug[](length);
        uint index = 0;
        uint i = start;
        while (i < payableDrugIdList.length && index < length) {
            if (_id == 0 || drugs[payableDrugIdList[i] - 1].id == _id) {
                result[index] = drugs[payableDrugIdList[i] - 1];
                index++;
            }
            i++;
        }
        return result;
    }

    // 返回可购买的药品总数
    function getPayableDrugCount(uint _id) public view returns (uint) {
        uint count = 0;
        for (uint i = 0; i < payableDrugIdList.length; i++) {
            if (_id == 0 || drugs[payableDrugIdList[i] - 1].id == _id) {
                count++;
            }
        }
        return count;
    }


    function buy(uint _id, uint _buyer) public {
        Drug storage drug = drugs[_id - 1];
        drug.buyer = _buyer;
        drug.buyTime = block.timestamp;
        drug.flow = unicode"已售出";
        // 删除可购买的药品
        for (uint i = 0; i < payableDrugIdList.length; i++) {
            if (payableDrugIdList[i] == _id) {
                payableDrugIdList[i] = payableDrugIdList[payableDrugIdList.length - 1];
                payableDrugIdList.pop();
                break;
            }
        }
    }
}
