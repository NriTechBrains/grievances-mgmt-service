package com.techBrains.grievance.service;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;

import com.google.cloud.translate.Translation;
import com.techBrains.grievance.exception.MessageTranslationException;
import com.techBrains.grievance.service.data.MessageData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TranslateTextService {

    @Value("${googleApiKey}")
    private String googleApiKey;

    public String translateMessage(MessageData messageData) {

        try {
            Translate translate = TranslateOptions.newBuilder()
                    .setApiKey(googleApiKey).build().getService();
            Translation translation = translate.translate(messageData.getMessage(),
                    Translate.TranslateOption.sourceLanguage(messageData.getSourceLanguage()),
                    Translate.TranslateOption.targetLanguage(messageData.getTargetLanguage()));
            return translation.getTranslatedText();
        } catch (Exception e) {
            throw new MessageTranslationException(e.getMessage());
        }
    }
}
