package com.example.demo.service;

import com.example.demo.common.BaseResponse;
import com.example.demo.model.domain.UserWalletBalance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.vo.UserConsumeVo;
import com.example.demo.model.vo.UserRefundVo;
import org.springframework.transaction.annotation.Transactional;

/**
* @author zengjineng
* @description 针对表【user_wallet_balance(用户表)】的数据库操作Service
* @createDate 2023-03-02 11:25:02
*/
public interface UserWalletBalanceService extends IService<UserWalletBalance> {

    /**
     * 消费
     * @param userConsumeVo
     * @return
     */
    @Transactional
    BaseResponse consumer(UserConsumeVo userConsumeVo);

    /**
     * 退款
     * @param refundVo
     * @return
     */
    BaseResponse refund(UserRefundVo refundVo);
}
