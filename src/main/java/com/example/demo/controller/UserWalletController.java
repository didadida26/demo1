package com.example.demo.controller;

import com.example.demo.common.BaseResponse;
import com.example.demo.common.ErrorCode;
import com.example.demo.common.ResultUtils;
import com.example.demo.model.domain.UserWalletBalance;
import com.example.demo.model.request.QueryBalanceRequest;
import com.example.demo.model.vo.BalanceDetailVo;
import com.example.demo.model.vo.UserConsumeVo;
import com.example.demo.model.vo.UserRefundVo;
import com.example.demo.service.BalanceLogService;
import com.example.demo.service.UserWalletBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zjn
 * @version 1.0
 * @date 2023/3/2 11:29
 */
@RestController
@RequestMapping("/user_wallet")
public class UserWalletController {

    @Autowired
    private UserWalletBalanceService userWalletBalanceService;
    @Autowired
    private BalanceLogService balanceLogService;

    // 1.  查询用户钱包余额
    @GetMapping("/balance")
    public BaseResponse getBalanceById(QueryBalanceRequest request) {
        if (request == null) {
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }

        UserWalletBalance balance = userWalletBalanceService.getById(request.getId());
        if(balance == null) {
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(balance);
    }
    // 2. 用户消费100元的接口
    @PostMapping("/consume")
    public BaseResponse consume(UserConsumeVo consumeVo) {
       if(consumeVo == null) {
           return ResultUtils.error(ErrorCode.NULL_ERROR);
       }
       BaseResponse baseResponse = userWalletBalanceService.consumer(consumeVo);
       return baseResponse;
    }
    // 3. 用户退款20元接口
    @PostMapping("/refund")
    public BaseResponse refund(UserRefundVo refundVo) {
        if(refundVo == null) {
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        BaseResponse baseResponse = userWalletBalanceService.refund(refundVo);
        return baseResponse;
    }
    // 4. 查询用户钱包金额变动明细的接口
    @GetMapping("/wallet_details")
    public BaseResponse walletDetails(BalanceDetailVo balanceDetailVo) {
        if(balanceDetailVo == null) {
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        return balanceLogService.getBalanceDetailByPage(balanceDetailVo);
    }
}
