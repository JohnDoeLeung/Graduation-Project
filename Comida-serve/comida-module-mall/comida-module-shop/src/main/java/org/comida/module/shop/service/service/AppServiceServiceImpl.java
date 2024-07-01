package org.comida.module.shop.service.service;

import org.comida.module.shop.dal.dataobject.service.ServiceDO;
import org.comida.module.shop.dal.mysql.service.ServiceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 我的服务 Service 实现类
 *
  */
@Service
@Validated
public class AppServiceServiceImpl extends ServiceImpl<ServiceMapper, ServiceDO> implements AppServiceService {

    @Resource
    private ServiceMapper serviceMapper;



}
