package com.techBrains.grievance.service;

import com.techBrains.grievance.exception.MessageSenderException;
import com.techBrains.grievance.service.data.MessageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WhatsAppMessageService implements MessageSenderService{

    @Autowired
    TranslateTextService translateTextService = null;

    @Override
    public void sendMessage(MessageData data) {

        try {
            String translatedMessage = translateTextService.translateMessage(data.getMessage());

            log.info("Translated Message: {}", translatedMessage);
            log.info("Message Sent to {}", data.getPhone());
        } catch (Exception e){
            throw new MessageSenderException(e.getMessage());
        }


    }
}
