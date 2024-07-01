package org.comida.module.member.service.useraddress;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import org.comida.module.member.controller.admin.useraddress.vo.*;
import org.comida.module.member.dal.dataobject.useraddress.UserAddressDO;
import org.comida.framework.common.pojo.PageResult;

import org.comida.module.member.convert.useraddress.UserAddressConvert;
import org.comida.module.member.dal.mysql.useraddress.UserAddressMapper;

import static org.comida.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.comida.module.member.enums.ErrorCodeConstants.*;

/**
 * 用户地址 Service 实现类
 *
  */
@Service
@Validated
public class UserAddressServiceImpl implements UserAddressService {

    @Resource
    private UserAddressMapper userAddressMapper;

    @Override
    public Long createUserAddress(UserAddressCreateReqVO createReqVO) {
        // 插入
        UserAddressDO userAddress = UserAddressConvert.INSTANCE.convert(createReqVO);
        userAddressMapper.insert(userAddress);
        // 返回
        return userAddress.getId();
    }

    @Override
    public void updateUserAddress(UserAddressUpdateReqVO updateReqVO) {
        // 校验存在
        validateUserAddressExists(updateReqVO.getId());
        // 更新
        UserAddressDO updateObj = UserAddressConvert.INSTANCE.convert(updateReqVO);
        userAddressMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserAddress(Long id) {
        // 校验存在
        validateUserAddressExists(id);
        // 删除
        userAddressMapper.deleteById(id);
    }

    private void validateUserAddressExists(Long id) {
        if (userAddressMapper.selectById(id) == null) {
            throw exception(USER_ADDRESS_NOT_EXISTS);
        }
    }

    @Override
    public UserAddressDO getUserAddress(Long id) {
        return userAddressMapper.selectById(id);
    }

    @Override
    public List<UserAddressDO> getUserAddressList(Collection<Long> ids) {
        return userAddressMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<UserAddressDO> getUserAddressPage(UserAddressPageReqVO pageReqVO) {
        return userAddressMapper.selectPage(pageReqVO);
    }

    @Override
    public List<UserAddressDO> getUserAddressList(UserAddressExportReqVO exportReqVO) {
        return userAddressMapper.selectList(exportReqVO);
    }

}
