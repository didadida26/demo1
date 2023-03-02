package com.example.demo.mapper;

import com.example.demo.model.domain.BalanceLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zengjineng
* @description 针对表【balance_log】的数据库操作Mapper
* @createDate 2023-03-02 12:37:24
* @Entity com.example.demo.domain.BalanceLog
*/
@Mapper
public interface BalanceLogMapper extends BaseMapper<BalanceLog> {

}




