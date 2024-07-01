package org.comida.module.member.controller.app.address;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.comida.framework.common.pojo.CommonResult;
import org.comida.framework.common.util.comida.LocationUtils;
import org.comida.framework.security.core.annotations.PreAuthenticated;
import org.comida.module.member.controller.app.address.param.AppAddressParam;
import org.comida.module.member.controller.app.address.vo.AppUserAddressLocationVo;
import org.comida.module.member.controller.app.address.vo.AppUserAddressQueryVo;
import org.comida.module.member.service.useraddress.AppUserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.comida.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.comida.framework.common.pojo.CommonResult.success;
import static org.comida.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static org.comida.module.member.enums.ErrorCodeConstants.USER_ADDRESS_PARAM_NOT_EXISTS;

/**
 * <p>
 * 用户地前端控制器
 * </p>
 *
   * @since 2023-6-28
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "用户 APP - 用户地址")
@RequestMapping("/address")
public class AppUserAddressController {

    private final AppUserAddressService appUserAddressService;

    /**
    * 添加或修改地址
    */
    @PreAuthenticated
    @PostMapping("/addAndEdit")
    @Operation(summary = "添加或修改地址")
    public CommonResult<Long> addYxUserAddress(@RequestBody @Valid AppAddressParam param){
        Long uid = getLoginUserId();
        Long id = appUserAddressService.addAndEdit(uid,param);
        return success(id);
    }

    /**
     * 设置默认地址
     */
    @PreAuthenticated
    @PostMapping("/default/set/{id}")
    @Parameter(name = "id", description = "地址id", required = true)
    @Operation(summary = "设置默认地址")
    public CommonResult<Boolean> setDefault(@PathVariable String id){
        Long uid = getLoginUserId();
        appUserAddressService.setDefault(uid,id);
        return success(true);
    }



    /**
    * 删除用户地址
    */
    @PreAuthenticated
    @PostMapping("/del/{id}")
    @Operation(summary = "删除用户地址")
    public CommonResult<Boolean> deleteYxUserAddress(@PathVariable String id){
        if(StrUtil.isBlank(id) || !NumberUtil.isNumber(id)){
            throw exception(USER_ADDRESS_PARAM_NOT_EXISTS);
        }
        appUserAddressService.removeById(id);
        return success(true);
    }


    /**
     * 用户地址列表
     */
    @PreAuthenticated
    @GetMapping("/list")
    @Operation(summary = "用户地址列表")
    public CommonResult<List<AppUserAddressQueryVo>> getYxUserAddressPageList(@RequestParam(value = "page",defaultValue = "1") int page,
                                                                           @RequestParam(value = "limit",defaultValue = "10") int limit){
        Long uid = getLoginUserId();
        return success(appUserAddressService.getList(uid,page,limit));
    }

    /**
     * 用户地址列表
     */
    @PostMapping("/getDistanceFromLocation")
    @Operation(summary = "用户地址列表")
    public CommonResult<Double> getDistanceFromLocation( @RequestBody AppUserAddressLocationVo addressLocation){
        return success(LocationUtils.getDistance(addressLocation.getLat(),addressLocation.getLng(),
                addressLocation.getLat2(),addressLocation.getLng2()));
    }





}

