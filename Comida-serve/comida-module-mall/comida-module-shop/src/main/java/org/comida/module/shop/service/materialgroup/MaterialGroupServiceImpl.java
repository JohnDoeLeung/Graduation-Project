package org.comida.module.shop.service.materialgroup;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import org.comida.module.shop.controller.admin.materialgroup.vo.*;
import org.comida.module.shop.dal.dataobject.materialgroup.MaterialGroupDO;
import org.comida.framework.common.pojo.PageResult;

import org.comida.module.shop.convert.materialgroup.MaterialGroupConvert;
import org.comida.module.shop.dal.mysql.materialgroup.MaterialGroupMapper;

import static org.comida.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.comida.module.shop.enums.ErrorCodeConstants.*;

/**
 * 素材分组 Service 实现类
 *
  */
@Service
@Validated
public class MaterialGroupServiceImpl implements MaterialGroupService {

    @Resource
    private MaterialGroupMapper materialGroupMapper;

    @Override
    public Long createMaterialGroup(MaterialGroupCreateReqVO createReqVO) {
        // 插入
        MaterialGroupDO materialGroup = MaterialGroupConvert.INSTANCE.convert(createReqVO);
        materialGroupMapper.insert(materialGroup);
        // 返回
        return materialGroup.getId();
    }

    @Override
    public void updateMaterialGroup(MaterialGroupUpdateReqVO updateReqVO) {
        // 校验存在
        validateMaterialGroupExists(updateReqVO.getId());
        // 更新
        MaterialGroupDO updateObj = MaterialGroupConvert.INSTANCE.convert(updateReqVO);
        materialGroupMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaterialGroup(Long id) {
        // 校验存在
        validateMaterialGroupExists(id);
        // 删除
        materialGroupMapper.deleteById(id);
    }

    private void validateMaterialGroupExists(Long id) {
        if (materialGroupMapper.selectById(id) == null) {
            throw exception(MATERIAL_GROUP_NOT_EXISTS);
        }
    }

    @Override
    public MaterialGroupDO getMaterialGroup(Long id) {
        return materialGroupMapper.selectById(id);
    }

    @Override
    public List<MaterialGroupDO> getMaterialGroupList(Collection<Long> ids) {
        return materialGroupMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MaterialGroupDO> getMaterialGroupPage(MaterialGroupPageReqVO pageReqVO) {
        return materialGroupMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MaterialGroupDO> getMaterialGroupList(MaterialGroupExportReqVO exportReqVO) {
        return materialGroupMapper.selectList(exportReqVO);
    }

}
