package org.dromara.telegram.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.telegram.domain.TelegramGroupInfo;

@Mapper
public interface TelegramGroupInfoMapper extends BaseMapperPlus<TelegramGroupInfo,TelegramGroupInfo> {

    TelegramGroupInfo selectTelegramGroupInfoByGroupId(@Param("groupId") String groupId);

}
