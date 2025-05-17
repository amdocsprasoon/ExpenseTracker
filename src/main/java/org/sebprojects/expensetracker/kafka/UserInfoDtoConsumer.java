package org.sebprojects.expensetracker.kafka;


import org.sebprojects.expensetracker.dtos.UserInfoDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDtoConsumer {

     @KafkaListener(topics = "user_info_topic", groupId = "user-info-group", containerFactory = "kafkaListenerContainerFactoryUserInfoDto")
     public void consumeUserInfoDto(UserInfoDto userInfoDto) {
         System.out.println("Received UserInfoDto: " + userInfoDto);
         // Process the UserInfoDto as needed
     }

}
