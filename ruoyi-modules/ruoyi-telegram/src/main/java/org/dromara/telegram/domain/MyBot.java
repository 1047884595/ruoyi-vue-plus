package org.dromara.telegram.domain;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import org.dromara.common.core.utils.StringUtils;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Data
public class MyBot extends TelegramLongPollingBot {
    private final String botUsername;
    private final String botToken;

    private List<String> groupIds;

    public MyBot (DefaultBotOptions defaultBotOptions,String botUsername, String botToken,List<String> groupIds){
        super(defaultBotOptions);
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.groupIds = groupIds;
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().getChatId() != 0) {
            Message message = update.getMessage();
            System.err.println(message);
            groupIds.forEach(groupId -> {
                if (!groupId.equals(message.getChatId().toString()) ) { // 避免发送到同一个群
//                    if (message.hasText() && StringUtils.isNotEmpty(message.getText())){
//                        sendTextMessage(groupId, message.getText(),0,1);
//                    }
//                    if (message.hasPhoto() && message.getPhoto().size() != 0){
//                        String caption= null;
//                        if (StringUtils.isNotEmpty(message.getCaption())){
//                            caption = message.getCaption();
//                        }
//                        sendPhotoMessage(groupId, caption,message.getPhoto().get(0).getFileId(),0,1);
//                    }
//                    if (message.hasSticker() && ObjectUtil.isNotNull(message.getSticker())){
//                        sendStickerMessage(groupId, message.getSticker().getFileId(),0,1);
//                    }
//                    if (message.hasAudio() && ObjectUtil.isNotNull(message.getAudio())){
//                        sendAudioMessage(groupId,message.getAudio().getFileId(),0,1);
//                    }
//                    if (message.hasDocument() && ObjectUtil.isNotNull(message.getDocument())){
//                        sendDocumentMessage(groupId,message.getDocument().getFileId(),0,1);
//                    }
//                    if (message.hasVideo() && ObjectUtil.isNotNull(message.getVideo())){
//                        sendVideoMessage(groupId,message.getVideo().getFileId(),0,1);
//                    }
//                    if (message.hasVoice() && ObjectUtil.isNotNull(message.getVoice())){
//                        sendVoiceMessage(groupId,message.getVoice().getFileId(),0,1);
//                    }
//                    if (message.hasLocation() && ObjectUtil.isNotNull(message.getLocation())){
//                        sendLocationMessage(groupId,message.getLocation().getLatitude(),message.getLocation().getLongitude(),0,1);
//                    }
//                    if (ObjectUtil.isNotNull(message.getReplyToMessage())){
//                        if (message.getReplyToMessage().hasText()){
//                            String text = message.getReplyToMessage().getText();
//                            sendTextMessage(groupId, message.getText(),message.getReplyToMessage().getMessageId(),0,groupId,message.getChatId().toString());
//                        }
//                        if (message.getReplyToMessage().hasPhoto()){
//                            String filedId = "";
//                            String caption = "";
//                            if (ObjectUtil.isNotNull(message.getReplyToMessage().getPhoto())){
//                                filedId = message.getReplyToMessage().getPhoto().get(0).getFileId();
//                            }
//                            if (StringUtils.isNotEmpty(message.getReplyToMessage().getCaption())){
//                                caption = message.getReplyToMessage().getCaption();
//                            }
//                            sendPhotoMessage(groupId, caption,filedId,message.getReplyToMessage().getMessageId(),0);
//                        }
//                        if (message.getReplyToMessage().hasSticker()){
//                            String filedId = "";
//                            if (ObjectUtil.isNotNull(message.getReplyToMessage().getSticker())){
//                                filedId = message.getReplyToMessage().getSticker().getFileId();
//                            }
//                            sendStickerMessage(groupId, filedId,message.getReplyToMessage().getMessageId(),0);
//                        }
//                        if (message.getReplyToMessage().hasAudio()){
//                            String filedId = "";
//                            if (ObjectUtil.isNotNull(message.getReplyToMessage().getAudio())){
//                                filedId = message.getReplyToMessage().getAudio().getFileId();
//                            }
//                            sendAudioMessage(groupId, filedId,message.getReplyToMessage().getMessageId(),0);
//                        }
//                        if (message.getReplyToMessage().hasDocument()){
//                            String filedId = "";
//                            if (ObjectUtil.isNotNull(message.getReplyToMessage().getDocument())){
//                                filedId = message.getReplyToMessage().getDocument().getFileId();
//                            }
//                            sendDocumentMessage(groupId, filedId,message.getReplyToMessage().getMessageId(),0);
//                        }
//                        if (message.getReplyToMessage().hasVideo()){
//                            String filedId = "";
//                            if (ObjectUtil.isNotNull(message.getReplyToMessage().getVideo())){
//                                filedId = message.getReplyToMessage().getVideo().getFileId();
//                            }
//                            sendVideoMessage(groupId, filedId,message.getReplyToMessage().getMessageId(),0);
//                        }
//                        if (message.getReplyToMessage().hasVoice()){
//                            String filedId = "";
//                            if (ObjectUtil.isNotNull(message.getReplyToMessage().getVoice())){
//                                filedId = message.getReplyToMessage().getVoice().getFileId();
//                            }
//                            sendVoiceMessage(groupId, filedId,message.getReplyToMessage().getMessageId(),0);
//                        }
//                        if (message.getReplyToMessage().hasLocation()){
//                            Double latitude = null;
//                            Double longitude = null;
//                            if (ObjectUtil.isNotNull(message.getReplyToMessage().getLocation())){
//                                latitude = message.getReplyToMessage().getLocation().getLatitude();
//                                longitude = message.getReplyToMessage().getLocation().getLongitude();
//                            }
//                            sendLocationMessage(groupId,latitude,longitude,message.getReplyToMessage().getMessageId(),0);
//                        }
//
//                    }
                    botForwardMessage(message,groupId,message.getChatId().toString());
                }
            });
        }
    }



//    发送文本信息
    private void sendTextMessage(String chatId,String text,Integer messageId,Integer type){

        SendMessage message = new SendMessage();
        if (type == 1){
            message.setChatId(chatId);
            message.setText(text);


        }else{
            message.setChatId(chatId);
            message.setText(text);
            message.setReplyToMessageId(messageId);
            message.setAllowSendingWithoutReply(true);
        }
        try{
            execute(message);
        }catch (TelegramApiException e){
            System.err.println(e.getMessage());
        }
    }


    //发送图片信息
    private void sendPhotoMessage(String chatId,String caption,String fileId,Integer messageId,Integer type){
        SendPhoto photo = new SendPhoto();
        if (type == 1){
            photo.setChatId(chatId);
            photo.setCaption(caption);
            photo.setPhoto(new InputFile(fileId));
        }else{
            photo.setChatId(chatId);
            photo.setReplyToMessageId(messageId);
            photo.setPhoto(new InputFile(fileId));
            photo.setAllowSendingWithoutReply(true);
        }

        try{
            execute(photo);
        }catch (TelegramApiException e){
            System.err.println(e.getMessage());
        }
    }

    //发送贴纸信息
    private void sendStickerMessage(String chatId,String fileId,Integer messageId,Integer type){
        SendSticker sticker = new SendSticker();
        if (type == 1){
            sticker.setChatId(chatId);
            sticker.setSticker(new InputFile(fileId));
        }else{
            sticker.setChatId(chatId);
            sticker.setReplyToMessageId(messageId);
            sticker.setSticker(new InputFile(fileId));
            sticker.setAllowSendingWithoutReply(true);
        }
        try{
            execute(sticker);
        }catch (TelegramApiException e){
            System.err.println(e.getMessage());
        }
    }


    //发送音频信息
    private void sendAudioMessage(String chatId,String fileId,Integer messageId,Integer type){
        SendAudio audio = new SendAudio();
        if (type == 1){
            audio.setChatId(chatId);
            audio.setAudio(new InputFile(fileId));
        }else{
            audio.setChatId(chatId);
            audio.setReplyToMessageId(messageId);
            audio.setAudio(new InputFile(fileId));
            audio.setAllowSendingWithoutReply(true);
        }
        try{
            execute(audio);
        }catch (TelegramApiException e){
            System.err.println(e.getMessage());
        }
    }

    //发送文件信息
    private void sendDocumentMessage(String chatId,String fileId,Integer messageId,Integer type){
        SendDocument document = new SendDocument();
        if (type == 1){
            document.setChatId(chatId);
            document.setDocument(new InputFile(fileId));
        }else{
            document.setChatId(chatId);
            document.setReplyToMessageId(messageId);
            document.setDocument(new InputFile(fileId));
            document.setAllowSendingWithoutReply(true);
        }
        try{
            execute(document);
        }catch (TelegramApiException e){
            System.err.println(e.getMessage());
        }
    }

    //发送视频信息
    private void sendVideoMessage(String chatId,String fileId,Integer messageId,Integer type){
        SendVideo video = new SendVideo();
        if (type == 1){
            video.setVideo(new InputFile(fileId));
            video.setChatId(chatId);
        }else {
            video.setChatId(chatId);
            video.setReplyToMessageId(messageId);
            video.setVideo(new InputFile(fileId));
            video.setAllowSendingWithoutReply(true);
        }
        try{
            execute(video);
        }catch (TelegramApiException e){
            System.err.println(e.getMessage());
        }
    }

    //发送语音信息
    private void sendVoiceMessage(String chatId,String fileId,Integer messageId,Integer type){
        SendVoice sendVoice = new SendVoice();
        if (type == 1){
            sendVoice.setChatId(chatId);
            sendVoice.setVoice(new InputFile(fileId));
        }else {
            sendVoice.setChatId(chatId);
            sendVoice.setReplyToMessageId(messageId);
            sendVoice.setVoice(new InputFile(fileId));
            sendVoice.setAllowSendingWithoutReply(true);
        }
        try{
            execute(sendVoice);
        }catch (TelegramApiException e){
            System.err.println(e.getMessage());
        }
    }

    //发送位置消息
    private void sendLocationMessage(String chatId,Double latitude, Double longitude,Integer messageId,Integer type){

        SendLocation sendLocation = new SendLocation();
        if (type == 1){
            sendLocation.setChatId(chatId);
            sendLocation.setLatitude(latitude);
            sendLocation.setLongitude(longitude);
        }else{
            sendLocation.setChatId(chatId);
            sendLocation.setReplyToMessageId(messageId);
            sendLocation.setLatitude(latitude);
            sendLocation.setLongitude(longitude);
            sendLocation.setAllowSendingWithoutReply(true);
        }
        try{
            execute(sendLocation);
        }catch (TelegramApiException e){
            System.err.println(e.getMessage());
        }
    }

    //发送引用信息
//    private void sendReplyToMessage(String chatId,String text,Integer messageId){
//        SendMessage sendReplyToMessage = new SendMessage();
//        sendReplyToMessage.setChatId(chatId);
//        sendReplyToMessage.setText(text);
//        sendReplyToMessage.setReplyToMessageId(messageId);
//        sendReplyToMessage.setAllowSendingWithoutReply(true);
//        try{
//            execute(sendReplyToMessage);
//        }catch (TelegramApiException e){
//            System.out.println(e);
//            System.err.println(e.getMessage());
//        }
//    }




        private void botForwardMessage(Message message,String toChatId,String fromChatId){
        if (ObjectUtil.isNull(message.getEntities())){
            ForwardMessage forwardMessage = new ForwardMessage();
            forwardMessage.setChatId(toChatId);
            forwardMessage.setFromChatId(fromChatId);
            forwardMessage.setMessageId(message.getMessageId());
            try{
                execute(forwardMessage);
            }catch (TelegramApiException e){
                System.err.println(e.getMessage());
            }
        }
    }


}
