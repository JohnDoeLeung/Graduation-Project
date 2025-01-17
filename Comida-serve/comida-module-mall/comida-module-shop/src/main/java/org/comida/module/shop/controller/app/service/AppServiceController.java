package org.comida.module.shop.controller.app.service;


import org.comida.framework.common.enums.ShopCommonEnum;
import org.comida.framework.common.pojo.CommonResult;
import org.comida.module.shop.controller.app.service.vo.AppServiceVO;
import org.comida.module.shop.convert.service.ServiceConvert;
import org.comida.module.shop.dal.dataobject.service.ServiceDO;
import org.comida.module.shop.service.service.AppServiceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.comida.framework.common.pojo.CommonResult.success;

/**
 * <p>
 * 首页广告控制器
 * </p>
 *
   * @since 2023-8-11
 */
@Slf4j
@RestController
@Tag(name = "用户 APP - 服务菜单")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/service")
public class AppServiceController {

    private final AppServiceService appServiceService;


    @GetMapping("/list")
    @Operation(summary = "服务菜单列表")
    public CommonResult<List<AppServiceVO>> getList() {
        List<ServiceDO> appShopAdsVOS = appServiceService.list(new LambdaQueryWrapper<ServiceDO>()
                .eq(ServiceDO::getStatus, ShopCommonEnum.IS_STATUS_1.getValue()));
        return success(ServiceConvert.INSTANCE.convertList03(appShopAdsVOS));
    }

    @GetMapping("/content")
    @Operation(summary = "服务菜单列表")
    public CommonResult<AppServiceVO> getContent(@RequestParam("id") Integer id) {
        ServiceDO appShopAdsVO = appServiceService.getById(id);
        return success(ServiceConvert.INSTANCE.convert03(appShopAdsVO));
    }



}

