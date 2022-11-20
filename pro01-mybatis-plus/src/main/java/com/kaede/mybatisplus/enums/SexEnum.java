package com.kaede.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.ToString;

/**
 * @author kaede
 * @create 2022-10-03
 */

@Getter
@ToString
public enum SexEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    //将注解所标识的属性的值存储到数据库中
    @EnumValue
    private final Integer sex;

    private final String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }

}
