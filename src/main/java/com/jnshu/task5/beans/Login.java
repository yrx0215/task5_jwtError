package com.jnshu.task5.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Data
@Component
public class Login {
    private Integer id;
    private String loginName;
    private String pwd;
    private String qq;
    private String email;
    private String phone;
    private Long createAt = System.currentTimeMillis();


}
