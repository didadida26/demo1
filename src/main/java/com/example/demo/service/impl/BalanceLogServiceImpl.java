package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.BaseResponse;
import com.example.demo.common.ErrorCode;
import com.example.demo.common.ResultUtils;
import com.example.demo.model.domain.BalanceLog;
import com.example.demo.mapper.BalanceLogMapper;
import com.example.demo.model.vo.BalanceDetailVo;
import com.example.demo.service.BalanceLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author zengjineng
* @description 针对表【balance_log】的数据库操作Service实现
* @createDate 2023-03-02 12:37:24
*/
@Service
public class BalanceLogServiceImpl extends ServiceImpl<BalanceLogMapper, BalanceLog>
    implements BalanceLogService{

    @Override
    public void log(BalanceLog log) {
        save(log);
    }

    /**
     * 分页查询明细
     *
     * @param balanceDetailVo
     * @return
     */
    @Override
    public BaseResponse getBalanceDetailByPage(BalanceDetailVo balanceDetailVo) {
        if (balanceDetailVo == null) {
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        if (balanceDetailVo.getPage() < 1) {
            balanceDetailVo.setPage(1);
        }
        Page<BalanceLog> page = new Page<>(balanceDetailVo.getPage(),balanceDetailVo.getSize());
        QueryWrapper<BalanceLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",balanceDetailVo.getId());
        page(page,queryWrapper);
        List<BalanceLog> records = page.getRecords();
        return ResultUtils.success(records);
    }
}




