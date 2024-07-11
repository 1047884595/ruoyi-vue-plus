package org.dromara.telegram.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.apache.poi.hssf.record.DVALRecord;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.telegram.bo.GroupInfoBo;
import org.dromara.telegram.domain.TelegramGroupInfo;
import org.dromara.telegram.domain.UserTelegramBotToken;
import org.dromara.telegram.domain.UserTelegramGroup;
import org.dromara.telegram.mapper.TelegramGroupInfoMapper;
import org.dromara.telegram.mapper.UserTelegramBotTokenMapper;
import org.dromara.telegram.mapper.UserTelegramGroupMapper;
import org.dromara.telegram.service.TelegramGroupInfoService;
import org.dromara.telegram.service.UserTelegramGroupService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TelegramGroupInfoServiceImpl implements TelegramGroupInfoService {

    @Resource
    UserTelegramBotTokenMapper userTelegramBotTokenMapper;

    @Resource
    TelegramGroupInfoMapper telegramGroupInfoMapper;

    @Resource
    UserTelegramGroupMapper userTelegramGroupMapper;




    @Override
    @DSTransactional
    public R addGroupInfo(GroupInfoBo groupInfoBo) {
        //1.判断用户是否绑定机器人token
        LoginUser user = LoginHelper.getLoginUser();
        UserTelegramBotToken byUserId = userTelegramBotTokenMapper.findByUserId(user.getUserId());
        if (ObjectUtil.isNull(byUserId) || StringUtils.isEmpty(byUserId.getBotToken())) {
            return R.fail("请先绑定机器人令牌");
        }

        //2.添加用户群聊信息
        TelegramGroupInfo telegramGroupInfo = new TelegramGroupInfo();
        telegramGroupInfo.setUserId(user.getUserId());
        telegramGroupInfo.setGroupId(groupInfoBo.getGroupId());
        telegramGroupInfo.setGroupName(groupInfoBo.getGroupName());
        telegramGroupInfo.setCreateTime(new Date());
        telegramGroupInfo.setUpdateTime(new Date());
        telegramGroupInfoMapper.insert(telegramGroupInfo);

        //3.添加用户群聊中间表信息
        UserTelegramGroup userTelegramGroup = new UserTelegramGroup();
        userTelegramGroup.setUserId(user.getUserId());
        userTelegramGroup.setTelegramGroupInfoId(telegramGroupInfo.getId());
        userTelegramGroupMapper.insert(userTelegramGroup);

        return R.ok();

    }


    @Override
    @DSTransactional
    public R deleteGroup(String groupId){

        TelegramGroupInfo telegramGroupInfo = telegramGroupInfoMapper.selectTelegramGroupInfoByGroupId(groupId);
        if (ObjectUtil.isNotNull(telegramGroupInfo)){
            //删除群信息
            QueryWrapper deleteGroupInfo = new QueryWrapper<>();
            deleteGroupInfo.eq("group_id",groupId);
            telegramGroupInfoMapper.delete(deleteGroupInfo);


            //删除用户群消息中间表
            QueryWrapper deleteUserGroup = new QueryWrapper();
            deleteUserGroup.eq("telegram_group_info_id",telegramGroupInfo.getId());
            userTelegramGroupMapper.delete(deleteUserGroup);
        }

        return R.ok();
    }


    @Override
    public R updateGroupInfo(GroupInfoBo groupInfoBo){
        TelegramGroupInfo telegramGroupInfo = telegramGroupInfoMapper.selectTelegramGroupInfoByGroupId(groupInfoBo.getGroupId());

        if (ObjectUtil.isNotNull(telegramGroupInfo)){
            TelegramGroupInfo telegramGroupInfoUpdate = new TelegramGroupInfo();
            telegramGroupInfoUpdate.setId(telegramGroupInfo.getId());
            telegramGroupInfoUpdate.setGroupName(groupInfoBo.getGroupName());
            telegramGroupInfoUpdate.setGroupId(groupInfoBo.getGroupId());
            telegramGroupInfoUpdate.setUpdateTime(new Date());
            telegramGroupInfoMapper.updateById(telegramGroupInfoUpdate);
        }
        return R.ok();
    }


    @Override
    public R queryGroupInfo(){
        LoginUser user = LoginHelper.getLoginUser();

        //查询群聊信息
        QueryWrapper groupInfos = new QueryWrapper();
        groupInfos.eq("user_id",user.getUserId());
        List<TelegramGroupInfo> list = telegramGroupInfoMapper.selectList(groupInfos);

        return R.ok(list);
    }
}
