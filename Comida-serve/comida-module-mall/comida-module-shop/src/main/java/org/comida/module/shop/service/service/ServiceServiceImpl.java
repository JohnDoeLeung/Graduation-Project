package org.comida.module.shop.service.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import org.comida.module.shop.controller.admin.service.vo.*;
import org.comida.module.shop.dal.dataobject.service.ServiceDO;
import org.comida.framework.common.pojo.PageResult;

import org.comida.module.shop.convert.service.ServiceConvert;
import org.comida.module.shop.dal.mysql.service.ServiceMapper;

import static org.comida.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.comida.module.shop.enums.ErrorCodeConstants.*;

/**
 * 我的服务 Service 实现类
 *
  */
@Service
@Validated
public class ServiceServiceImpl implements ServiceService {

    @Resource
    private ServiceMapper serviceMapper;

    @Override
    public Long createService(ServiceCreateReqVO createReqVO) {
        // 插入
        ServiceDO service = ServiceConvert.INSTANCE.convert(createReqVO);
        serviceMapper.insert(service);
        // 返回
        return service.getId();
    }

    @Override
    public void updateService(ServiceUpdateReqVO updateReqVO) {
        // 校验存在
        validateServiceExists(updateReqVO.getId());
        // 更新
        ServiceDO updateObj = ServiceConvert.INSTANCE.convert(updateReqVO);
        serviceMapper.updateById(updateObj);
    }

    @Override
    public void deleteService(Long id) {
        // 校验存在
        validateServiceExists(id);
        // 删除
        serviceMapper.deleteById(id);
    }

    private void validateServiceExists(Long id) {
        if (serviceMapper.selectById(id) == null) {
            throw exception(SERVICE_NOT_EXISTS);
        }
    }

    @Override
    public ServiceDO getService(Long id) {
        return serviceMapper.selectById(id);
    }

    @Override
    public List<ServiceDO> getServiceList(Collection<Long> ids) {
        return serviceMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ServiceDO> getServicePage(ServicePageReqVO pageReqVO) {
        return serviceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ServiceDO> getServiceList(ServiceExportReqVO exportReqVO) {
        return serviceMapper.selectList(exportReqVO);
    }

}
