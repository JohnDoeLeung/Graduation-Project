package org.comida.module.store.controller.app.storeshop;

import org.comida.framework.common.pojo.CommonResult;
import org.comida.module.store.controller.app.storeshop.vo.AppStoreShopVO;
import org.comida.module.store.service.storeshop.AppStoreShopService;
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
 * 门店控制器
 * </p>
 *
   * @since 2023-8-14
 */
@Slf4j
@RestController
@Tag(name = "用户 APP - 门店")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/store")
public class AppStoreController {

    private final AppStoreShopService appStoreShopService;


    @GetMapping("/nearby")
    @Operation(summary = "获取最近的店铺")
    public CommonResult<AppStoreShopVO> getNearby(@RequestParam("lng") double lon,
                                                      @RequestParam("lat") double lat,
                                                      @RequestParam("kw") String name,
                                                      @RequestParam("shop_id") Integer shopId) {
        List<AppStoreShopVO> list = appStoreShopService.getStoreList(lon,lat,name,shopId);
        if(list != null ){
            return success(list.get(0));
        }
        return success(new AppStoreShopVO());
    }

    @GetMapping("/list")
    @Operation(summary = "服务菜单列表")
    public CommonResult<List<AppStoreShopVO>> getList(@RequestParam("lng") double lon,
                                                      @RequestParam("lat") double lat,
                                                      @RequestParam("kw") String name,
                                                      @RequestParam("shop_id") Integer shopId) {

        return success(appStoreShopService.getStoreList(lon,lat,name,shopId));
    }



}

