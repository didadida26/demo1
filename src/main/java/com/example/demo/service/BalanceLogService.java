package com.example.demo.service;

import com.example.demo.common.BaseResponse;
import com.example.demo.model.domain.BalanceLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.vo.BalanceDetailVo;

/**
* @author zengjineng
* @description 针对表【balance_log】的数据库操作Service
* @createDate 2023-03-02 12:37:24
*/
public interface BalanceLogService extends IService<BalanceLog> {

    void log(BalanceLog log);

    /**
     * 分页查询明细
     * @param balanceDetailVo
     * @return
     */
    BaseResponse getBalanceDetailByPage(BalanceDetailVo balanceDetailVo);
}
