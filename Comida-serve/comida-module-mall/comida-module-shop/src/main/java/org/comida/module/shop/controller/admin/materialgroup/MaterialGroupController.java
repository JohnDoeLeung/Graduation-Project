package org.comida.module.shop.controller.admin.materialgroup;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.*;
import java.util.*;

import org.comida.framework.common.pojo.PageResult;
import org.comida.framework.common.pojo.CommonResult;
import static org.comida.framework.common.pojo.CommonResult.success;

import org.comida.module.shop.controller.admin.materialgroup.vo.*;
import org.comida.module.shop.dal.dataobject.materialgroup.MaterialGroupDO;
import org.comida.module.shop.convert.materialgroup.MaterialGroupConvert;
import org.comida.module.shop.service.materialgroup.MaterialGroupService;

@Tag(name = "管理后台 - 素材分组")
@RestController
@RequestMapping("/shop/material-group")
@Validated
public class MaterialGroupController {

    @Resource
    private MaterialGroupService materialGroupService;

    @PostMapping("/create")
    @Operation(summary = "创建素材分组")
    public CommonResult<Long> createMaterialGroup(@Valid @RequestBody MaterialGroupCreateReqVO createReqVO) {
        return success(materialGroupService.createMaterialGroup(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新素材分组")
    public CommonResult<Boolean> updateMaterialGroup(@Valid @RequestBody MaterialGroupUpdateReqVO updateReqVO) {
        materialGroupService.updateMaterialGroup(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除素材分组")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteMaterialGroup(@RequestParam("id") Long id) {
        materialGroupService.deleteMaterialGroup(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得素材分组")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<MaterialGroupRespVO> getMaterialGroup(@RequestParam("id") Long id) {
        MaterialGroupDO materialGroup = materialGroupService.getMaterialGroup(id);
        return success(MaterialGroupConvert.INSTANCE.convert(materialGroup));
    }

    @GetMapping("/list")
    @Operation(summary = "获得素材分组列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<MaterialGroupRespVO>> getMaterialGroupList() {
        MaterialGroupExportReqVO exportReqVO = new MaterialGroupExportReqVO();
        List<MaterialGroupDO> list = materialGroupService.getMaterialGroupList(exportReqVO);
        return success(MaterialGroupConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得素材分组分页")
    public CommonResult<PageResult<MaterialGroupRespVO>> getMaterialGroupPage(@Valid MaterialGroupPageReqVO pageVO) {
        PageResult<MaterialGroupDO> pageResult = materialGroupService.getMaterialGroupPage(pageVO);
        return success(MaterialGroupConvert.INSTANCE.convertPage(pageResult));
    }
}
