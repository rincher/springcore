package com.sparta.springcore.controller;

import com.sparta.springcore.dto.UserTimeDto;
import com.sparta.springcore.model.UserTime;
import com.sparta.springcore.repository.UserTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserTimeController {
    private final UserTimeRepository userTimeRepository;

    @Secured("ROLE_ADMIN")
    @GetMapping("/user/time")
    public List<UserTimeDto> getUserTime(){
        List<UserTime> allUserTime = userTimeRepository.findAll();

        List<UserTimeDto> allUserTimeDto = new ArrayList<>();
        for (UserTime userTime : allUserTime){
            String username = userTime.getUser().getUsername();
            long totalTime = userTime.getTotalTime();
            long totalCall = userTime.getTotalCall();
            UserTimeDto userTimeDto = new UserTimeDto(username, totalTime,totalCall);
            allUserTimeDto.add(userTimeDto);
        }
        return allUserTimeDto;
    }
}
