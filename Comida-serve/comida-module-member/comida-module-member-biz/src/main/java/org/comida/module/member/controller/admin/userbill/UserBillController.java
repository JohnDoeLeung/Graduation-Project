package org.comida.module.member.controller.admin.userbill;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.*;

import org.comida.framework.common.pojo.PageResult;
import org.comida.framework.common.pojo.CommonResult;
import static org.comida.framework.common.pojo.CommonResult.success;


import org.comida.module.member.controller.admin.userbill.vo.*;
import org.comida.module.member.dal.dataobject.userbill.UserBillDO;
import org.comida.module.member.convert.userbill.UserBillConvert;
import org.comida.module.member.service.userbill.UserBillService;

@Tag(name = "管理后台 - 用户账单")
@RestController
@RequestMapping("/member/user-bill")
@Validated
public class UserBillController {

    @Resource
    private UserBillService userBillService;


    @GetMapping("/page")
    @Operation(summary = "获得用户账单分页")
    public CommonResult<PageResult<UserBillRespVO>> getUserBillPage(@Valid UserBillPageReqVO pageVO) {
        PageResult<UserBillDO> pageResult = userBillService.getUserBillPage(pageVO);
        return success(UserBillConvert.INSTANCE.convertPage(pageResult));
    }
}
