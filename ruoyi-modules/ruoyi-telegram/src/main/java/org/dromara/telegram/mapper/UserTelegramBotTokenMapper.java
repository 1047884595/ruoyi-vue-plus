package org.dromara.telegram.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.telegram.domain.UserTelegramBotToken;

@Mapper
public interface UserTelegramBotTokenMapper extends BaseMapperPlus<UserTelegramBotToken,UserTelegramBotToken> {

    // 通过用户ID查询Telegram Bot Token
    @Select("SELECT * FROM user_telegram_bot_token WHERE user_id = #{userId}")
    UserTelegramBotToken findByUserId(@Param("userId") Long userId);

    // 新增信息，通常可以使用BaseMapper的insert方法，但这里为了示例也提供一个
    @Insert("INSERT INTO user_telegram_bot_token(user_id, bot_token, bot_username, create_time, update_time) VALUES(#{userId}, #{botToken}, #{botUsername}, #{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})")
    int insertUserTelegramBotToken(UserTelegramBotToken token);


}
