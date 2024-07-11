package org.dromara.telegram.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.telegram.domain.UserTelegramGroup;

@Mapper
public interface UserTelegramGroupMapper extends BaseMapper<UserTelegramGroup> {
}
