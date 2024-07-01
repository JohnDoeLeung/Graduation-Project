package org.comida.module.member.controller.admin.useraddress;

import org.comida.framework.common.pojo.CommonResult;
import org.comida.framework.common.pojo.PageResult;
import org.comida.module.member.controller.admin.useraddress.vo.UserAddressCreateReqVO;
import org.comida.module.member.controller.admin.useraddress.vo.UserAddressPageReqVO;
import org.comida.module.member.controller.admin.useraddress.vo.UserAddressRespVO;
import org.comida.module.member.controller.admin.useraddress.vo.UserAddressUpdateReqVO;
import org.comida.module.member.convert.useraddress.UserAddressConvert;
import org.comida.module.member.dal.dataobject.useraddress.UserAddressDO;
import org.comida.module.member.service.useraddress.UserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static org.comida.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 用户地址")
@RestController
@RequestMapping("/member/user-address")
@Validated
public class UserAddressController {

    @Resource
    private UserAddressService userAddressService;

    @PostMapping("/create")
    @Operation(summary = "创建用户地址")
    public CommonResult<Long> createUserAddress(@Valid @RequestBody UserAddressCreateReqVO createReqVO) {
        return success(userAddressService.createUserAddress(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户地址")
    public CommonResult<Boolean> updateUserAddress(@Valid @RequestBody UserAddressUpdateReqVO updateReqVO) {
        userAddressService.updateUserAddress(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户地址")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteUserAddress(@RequestParam("id") Long id) {
        userAddressService.deleteUserAddress(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户地址")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<UserAddressRespVO> getUserAddress(@RequestParam("id") Long id) {
        UserAddressDO userAddress = userAddressService.getUserAddress(id);
        return success(UserAddressConvert.INSTANCE.convert(userAddress));
    }

    @GetMapping("/list")
    @Operation(summary = "获得用户地址列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<UserAddressRespVO>> getUserAddressList(@RequestParam("ids") Collection<Long> ids) {
        List<UserAddressDO> list = userAddressService.getUserAddressList(ids);
        return success(UserAddressConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户地址分页")
    public CommonResult<PageResult<UserAddressRespVO>> getUserAddressPage(@Valid UserAddressPageReqVO pageVO) {
        PageResult<UserAddressDO> pageResult = userAddressService.getUserAddressPage(pageVO);
        return success(UserAddressConvert.INSTANCE.convertPage(pageResult));
    }



}
