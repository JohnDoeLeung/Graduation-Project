package org.comida.module.shop.controller.admin.service;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;

import org.comida.framework.common.pojo.PageResult;
import org.comida.framework.common.pojo.CommonResult;
import static org.comida.framework.common.pojo.CommonResult.success;

import org.comida.module.shop.controller.admin.service.vo.*;
import org.comida.module.shop.dal.dataobject.service.ServiceDO;
import org.comida.module.shop.convert.service.ServiceConvert;
import org.comida.module.shop.service.service.ServiceService;

@Tag(name = "管理后台 - 我的服务")
@RestController
@RequestMapping("/shop/service")
@Validated
public class ServiceController {

    @Resource
    private ServiceService serviceService;

    @PostMapping("/create")
    @Operation(summary = "创建我的服务")
    public CommonResult<Long> createService(@Valid @RequestBody ServiceCreateReqVO createReqVO) {
        return success(serviceService.createService(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新我的服务")
    public CommonResult<Boolean> updateService(@Valid @RequestBody ServiceUpdateReqVO updateReqVO) {
        serviceService.updateService(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除我的服务")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteService(@RequestParam("id") Long id) {
        serviceService.deleteService(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得我的服务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<ServiceRespVO> getService(@RequestParam("id") Long id) {
        ServiceDO service = serviceService.getService(id);
        return success(ServiceConvert.INSTANCE.convert(service));
    }

    @GetMapping("/list")
    @Operation(summary = "获得我的服务列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<ServiceRespVO>> getServiceList(@RequestParam("ids") Collection<Long> ids) {
        List<ServiceDO> list = serviceService.getServiceList(ids);
        return success(ServiceConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得我的服务分页")
    public CommonResult<PageResult<ServiceRespVO>> getServicePage(@Valid ServicePageReqVO pageVO) {
        PageResult<ServiceDO> pageResult = serviceService.getServicePage(pageVO);
        return success(ServiceConvert.INSTANCE.convertPage(pageResult));
    }
}
