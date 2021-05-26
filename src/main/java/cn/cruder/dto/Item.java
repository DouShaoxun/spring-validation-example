package cn.cruder.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author Cruder
 * @Date 2021/5/26 0026 21:59
 */
@Data
public class Item {
    @NotBlank(message = "itemId不能为空")
    private String itemId;
}
