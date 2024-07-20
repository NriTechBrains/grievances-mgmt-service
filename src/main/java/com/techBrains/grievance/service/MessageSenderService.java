package com.techBrains.grievance.service;

import com.techBrains.grievance.service.data.MessageData;
import org.springframework.beans.factory.annotation.Autowired;

public interface MessageSenderService {

    void sendMessage(MessageData data);

}
