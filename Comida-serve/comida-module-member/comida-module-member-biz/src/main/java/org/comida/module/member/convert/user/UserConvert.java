package org.comida.module.member.convert.user;

import org.comida.framework.common.pojo.PageResult;
import org.comida.module.member.api.user.dto.MemberUserRespDTO;
import org.comida.module.member.controller.admin.user.vo.UserCreateReqVO;
import org.comida.module.member.controller.admin.user.vo.UserRespVO;
import org.comida.module.member.controller.admin.user.vo.UserUpdateReqVO;
import org.comida.module.member.controller.app.auth.vo.AppAuthRegisterReqVO;
import org.comida.module.member.controller.app.user.vo.AppUserInfoRespVO;
import org.comida.module.member.controller.app.user.vo.AppUserQueryVo;
import org.comida.module.member.dal.dataobject.user.MemberUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    AppUserInfoRespVO convert(MemberUserDO bean);

    MemberUserRespDTO convert2(MemberUserDO bean);

    AppUserQueryVo convert3(MemberUserDO bean);

    UserRespVO convert4(MemberUserDO bean);

    List<MemberUserRespDTO> convertList2(List<MemberUserDO> list);

    MemberUserDO convert(UserCreateReqVO bean);

    MemberUserDO convert(UserUpdateReqVO bean);

    MemberUserDO convert5(AppAuthRegisterReqVO appAuthRegisterReqVO);


    UserRespVO convert(MemberUserDO bean,Boolean bool);

    List<UserRespVO> convertList(List<MemberUserDO> list);

    PageResult<UserRespVO> convertPage(PageResult<MemberUserDO> page);

}
