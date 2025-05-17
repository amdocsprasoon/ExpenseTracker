package org.sebprojects.expensetracker.kafka;

import org.sebprojects.expensetracker.dtos.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDtoProducer {

    @Autowired
    KafkaTemplate<String, UserInfoDto> kafkaTemplateUserInfoDto;

    public void sendUserInfoDtoEvent(String topic, UserInfoDto userInfoDto) {
        // Use KafkaTemplate to send the message to the specified topic
        kafkaTemplateUserInfoDto.send(topic, userInfoDto);
    }


}
