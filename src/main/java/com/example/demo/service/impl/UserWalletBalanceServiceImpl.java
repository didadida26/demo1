package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.BaseResponse;
import com.example.demo.common.ErrorCode;
import com.example.demo.common.ResultUtils;
import com.example.demo.mapper.UserWalletBalanceMapper;
import com.example.demo.model.domain.BalanceLog;
import com.example.demo.model.domain.UserWalletBalance;
import com.example.demo.model.vo.UserConsumeVo;
import com.example.demo.model.vo.UserRefundVo;
import com.example.demo.service.BalanceLogService;
import com.example.demo.service.UserWalletBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author zengjineng
* @description 针对表【user_wallet_balance(用户表)】的数据库操作Service实现
* @createDate 2023-03-02 11:25:02
*/
@Service
public class UserWalletBalanceServiceImpl extends ServiceImpl<UserWalletBalanceMapper, UserWalletBalance>
    implements UserWalletBalanceService {

    @Autowired
    private BalanceLogService balanceLogService;

    /**
     * 消费
     * @param userConsumeVo
     * @return
     */
    @Override
    public BaseResponse consumer(UserConsumeVo userConsumeVo) {
        UserWalletBalance balance = getById(userConsumeVo.getId());
        if(balance.getBalance() < userConsumeVo.getAmount()) {
            return ResultUtils.error(44,"余额不足","");
        }
        QueryWrapper<UserWalletBalance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userConsumeVo.getId());
        balance.setBalance(balance.getBalance() - userConsumeVo.getAmount());
        boolean update = update(balance,queryWrapper);
        if(!update){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }

        // 记录日志
        BalanceLog log = new BalanceLog();
        log.setBalance(balance.getBalance());
        log.setCreateDate(new Date());
        log.setInfo("consume: " + userConsumeVo.getAmount() );
        balanceLogService.log(log);
        return ResultUtils.success("消费成功");
    }

    /**
     * 退款
     *
     * @param refundVo
     * @return
     */
    @Override
    public BaseResponse refund(UserRefundVo refundVo) {
        UserWalletBalance balance = getById(refundVo.getId());
        balance.setBalance(balance.getBalance() + refundVo.getAmount());
        QueryWrapper<UserWalletBalance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",refundVo.getId());

        boolean update = update(balance, queryWrapper);
        if(!update) {
            return ResultUtils.error(666,"退款失败","");
        }

        // 记录日志
        BalanceLog log = new BalanceLog();
        log.setBalance(balance.getBalance());
        log.setCreateDate(new Date());
        log.setInfo("refund: " + refundVo.getAmount());
        balanceLogService.save(log);
        return ResultUtils.success("退款成功");
    }
}




