package com.bank.dao;

import com.bank.BankMaintain;

public interface BankMaintainMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BankMaintain record);

    int insertSelective(BankMaintain record);

    BankMaintain selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankMaintain record);

    int updateByPrimaryKey(BankMaintain record);
}