package com.techBrains.grievance.service;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;

import com.google.cloud.translate.Translation;
import com.techBrains.grievance.exception.MessageTranslationException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TranslateTextService {
    public String translateMessage(String message) {

        try {
            Translate translate = TranslateOptions.newBuilder()
                    .setApiKey("AIzaSyB-z5RXVHhFC-1s2V947BHqFOE4QNree6Y").build().getService();
            Translation translation = translate.translate(message,
                    Translate.TranslateOption.sourceLanguage("en"),
                    Translate.TranslateOption.targetLanguage("te"));
            return translation.getTranslatedText();
        } catch (Exception e) {
            throw new MessageTranslationException(e.getMessage());
        }
    }
}
