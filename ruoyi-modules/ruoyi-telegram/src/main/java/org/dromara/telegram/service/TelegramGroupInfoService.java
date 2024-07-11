package org.dromara.telegram.service;

import org.dromara.common.core.domain.R;
import org.dromara.telegram.bo.GroupInfoBo;

public interface TelegramGroupInfoService {


    R addGroupInfo(GroupInfoBo groupInfoBo);


    R deleteGroup(String groupId);

    R updateGroupInfo(GroupInfoBo groupInfoBo);

    R queryGroupInfo();
}
