package com.komponente.Korisnicki.listener;

import com.komponente.Korisnicki.listener.helper.MessageHelper;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class EmailListener {
    private MessageHelper messageHelper;

    public EmailListener(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    @JmsListener(destination = "${destination.sendEmails}", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        //MatchesDto matchesDto = messageHelper.getMessage(message, MatchesDto.class);
        //emailService.sendSimpleMessage("mradovic01011111@gmail.com", "subject", matchesDto.toString());
    }

}
