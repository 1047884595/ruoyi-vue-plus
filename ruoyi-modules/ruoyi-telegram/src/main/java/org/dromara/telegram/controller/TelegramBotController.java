package org.dromara.telegram.controller;


import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.telegram.bo.GroupInfoBo;
import org.dromara.telegram.bo.TelegramBotBo;
import org.dromara.telegram.service.TelegramBotService;
import org.dromara.telegram.service.TelegramGroupInfoService;
import org.dromara.telegram.service.UserTelegramBotTokenService;
import org.dromara.telegram.service.UserTelegramGroupService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/telegramBot")
public class TelegramBotController {

    @Resource
    private final TelegramBotService telegramBotService;
    @Resource
    private final TelegramGroupInfoService telegramGroupInfoService;
    @Resource
    private final UserTelegramBotTokenService userTelegramBotTokenService;




    //用户绑定机器人token
    @PostMapping("/bindingBotToken")
    public R add(@Validated @RequestBody TelegramBotBo telegramBotBo) {
        R r = userTelegramBotTokenService.addBotToken(telegramBotBo);
        return r;
    }

    //用户绑定机器人所在的群
    @PostMapping("/bindingGroups")
    public R addGroupInfo(@Validated @RequestBody GroupInfoBo groupInfoBo) {
        R r = telegramGroupInfoService.addGroupInfo(groupInfoBo);
        return r;
    }



    //用户删除机器人token
    @DeleteMapping("/deleteBotToken")
    public R deleteBotToken(){
        R r = userTelegramBotTokenService.deleteBotToken();
        return r;
    }

    //用户删除绑定的群信息
    @DeleteMapping("/{groupId}")
    public R deleteGroup(@PathVariable("groupId") String groupId){
        R r = telegramGroupInfoService.deleteGroup(groupId);
        return r;
    }


    //用户修改绑定的token
    @PostMapping("/updateBotToken")
    public R updateBotToken(@Validated @RequestBody TelegramBotBo telegramBotBo) {
        R r = userTelegramBotTokenService.updateBotToken(telegramBotBo);
        return r;
    }

    //用户修改绑定的群信息
    @PostMapping("/updateGroupInfo")
    public R updateGroupInfo(@Validated @RequestBody GroupInfoBo groupInfoBo) {
        R r = telegramGroupInfoService.updateGroupInfo(groupInfoBo);
        return r;
    }


    //用户查询绑定的机器人信息
    @GetMapping("/botInfo")
    public R queryBotInfo(){
        R r = userTelegramBotTokenService.queryBotInfo();
        return r;
    }


    //用户查询绑定的群信息
    @GetMapping("/groupInfo")
    public R queryGroupInfo(){
        R r = telegramGroupInfoService.queryGroupInfo();
        return r;
    }





    //启动机器人
    @GetMapping("/startBot")
    public R start() {
        R r = telegramBotService.start();
        return r;
    }
}
