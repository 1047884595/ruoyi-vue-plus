package org.dromara.telegram.service.impl;


import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.telegram.bo.TelegramBotBo;
import org.dromara.telegram.domain.UserTelegramBotToken;
import org.dromara.telegram.mapper.TelegramGroupInfoMapper;
import org.dromara.telegram.mapper.UserTelegramBotTokenMapper;
import org.dromara.telegram.mapper.UserTelegramGroupMapper;
import org.dromara.telegram.service.UserTelegramBotTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserTelegramBotTokenServiceImpl implements UserTelegramBotTokenService {


    @Resource
    UserTelegramBotTokenMapper userTelegramBotTokenMapper;
    @Resource
    UserTelegramGroupMapper userTelegramGroupMapper;
    @Resource
    TelegramGroupInfoMapper telegramGroupInfoMapper;





    @Override
    public R addBotToken(TelegramBotBo telegramBotBo) {
        LoginUser user = LoginHelper.getLoginUser();


        //查询用户是否绑定telegramBotToken
        UserTelegramBotToken byUserId = userTelegramBotTokenMapper.findByUserId(user.getUserId());
        if (byUserId != null){
            return R.fail("你已经绑定过机器人token");
        }
        UserTelegramBotToken userTelegramBotToken = new UserTelegramBotToken();
        userTelegramBotToken.setUserId(user.getUserId());
        userTelegramBotToken.setBotToken(telegramBotBo.getBotToken());
        userTelegramBotToken.setBotUsername(telegramBotBo.getBotUsername());
        userTelegramBotToken.setCreateTime(new Date());
        userTelegramBotToken.setUpdateTime(new Date());
        userTelegramBotTokenMapper.insertUserTelegramBotToken(userTelegramBotToken);
        return R.ok();
    }


    @Override
    @DSTransactional
    public R  deleteBotToken(){
        LoginUser user = LoginHelper.getLoginUser();

        //删除token
        QueryWrapper queryWrapperDeleteToken = new QueryWrapper();
        queryWrapperDeleteToken.eq("user_id",user.getUserId());
        userTelegramBotTokenMapper.delete(queryWrapperDeleteToken);

        //删除用户群聊中间表
        QueryWrapper deleteUserGroup = new QueryWrapper();
        deleteUserGroup.eq("user_id",user.getUserId());
        userTelegramGroupMapper.delete(deleteUserGroup);

        //删除群信息
        QueryWrapper deleteGroupInfo = new QueryWrapper();
        deleteGroupInfo.eq("user_id",user.getUserId());
        telegramGroupInfoMapper.delete(deleteGroupInfo);

        return R.ok();
    }


    @Override
    public R  updateBotToken(TelegramBotBo telegramBotBo){
        LoginUser user = LoginHelper.getLoginUser();


        //查询用户是否绑定telegramBotToken
        UserTelegramBotToken byUserId = userTelegramBotTokenMapper.findByUserId(user.getUserId());
        if (byUserId == null){
            return R.fail("你还未绑定过机器人token");
        }
        UserTelegramBotToken userTelegramBotToken = new UserTelegramBotToken();
        userTelegramBotToken.setId(byUserId.getId());
        userTelegramBotToken.setUserId(user.getUserId());
        userTelegramBotToken.setBotToken(telegramBotBo.getBotToken());
        userTelegramBotToken.setBotUsername(telegramBotBo.getBotUsername());
        userTelegramBotToken.setUpdateTime(new Date());
        userTelegramBotTokenMapper.updateById(userTelegramBotToken);
        return R.ok();
    }



    @Override
    public R queryBotInfo(){
        LoginUser user = LoginHelper.getLoginUser();


        //查询用户是否绑定telegramBotToken
        UserTelegramBotToken byUserId = userTelegramBotTokenMapper.findByUserId(user.getUserId());
        if (byUserId == null){
            return R.fail("你还未绑定过机器人token");
        }

        return R.ok(byUserId);
    }
}
