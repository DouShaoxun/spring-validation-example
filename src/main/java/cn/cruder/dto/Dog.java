package cn.cruder.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Author Cruder
 * @Date 2021/5/25 0025 22:23
 */
@Data
public class Dog {

    @NotBlank(message = "name不能为空")
    private String name;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "请输入正确邮箱")
    private String email;
}
