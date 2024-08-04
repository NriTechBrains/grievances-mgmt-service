package com.techBrains.grievance.service;

import com.techBrains.grievance.exception.MessageSenderException;
import com.techBrains.grievance.service.data.MessageData;
import com.techBrains.grievance.service.data.WhatsappResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class WhatsAppMessageService implements MessageSenderService{

    TranslateTextService translateTextService;
    private String whatsappApiKey;
    private final WebClient webClient;

    @Autowired
    public WhatsAppMessageService(TranslateTextService translateTextService,
                                  @Value("${whatsappApiKey}") String whatsappApiKey,
                                  @Value("${whatsappUrl}") String whatsappUrl,
                                  WebClient.Builder webClientBuilder) {
        this.translateTextService = translateTextService;
        this.whatsappApiKey = whatsappApiKey;
        this.webClient = webClientBuilder.baseUrl(whatsappUrl).build();
    }

    @Override
    public void sendMessage(MessageData data) {

        try {
            String translatedMessage = translateTextService.translateMessage(data);
            log.info("Translated Message: {}", translatedMessage);
            //Sample URL
            // http://148.251.129.118/whatsapp/api/send?mobile=919652852530&msg=test
            // &apikey=a2080745146846ceba2870a02d9b2200

            data.getPhones().forEach(phoneNumber -> {
                WhatsappResponse response = this.webClient.get().uri(uriBuilder ->
                                uriBuilder
                                        .queryParam("mobile", phoneNumber)
                                        .queryParam("apikey", this.whatsappApiKey)
                                        .queryParam("msg", translatedMessage)
                                        .build())
                        .retrieve()
                        .bodyToMono(WhatsappResponse.class)
                        .onErrorResume(e -> Mono.empty())
                        .block();
                log.info("Message Sent to {}", phoneNumber);
                log.info("WhatsApp api response: {}", response);
            });
        } catch (Exception e){
            throw new MessageSenderException(e.getMessage());
        }


    }
}
