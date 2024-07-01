package org.comida.module.infra.convert.test;

import org.comida.framework.common.pojo.PageResult;
import org.comida.module.infra.controller.admin.test.vo.TestDemoCreateReqVO;
import org.comida.module.infra.controller.admin.test.vo.TestDemoRespVO;
import org.comida.module.infra.controller.admin.test.vo.TestDemoUpdateReqVO;
import org.comida.module.infra.dal.dataobject.test.TestDemoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典类型 Convert
 *
  */
@Mapper
public interface TestDemoConvert {

    TestDemoConvert INSTANCE = Mappers.getMapper(TestDemoConvert.class);

    TestDemoDO convert(TestDemoCreateReqVO bean);

    TestDemoDO convert(TestDemoUpdateReqVO bean);

    TestDemoRespVO convert(TestDemoDO bean);

    List<TestDemoRespVO> convertList(List<TestDemoDO> list);

    PageResult<TestDemoRespVO> convertPage(PageResult<TestDemoDO> page);

}
