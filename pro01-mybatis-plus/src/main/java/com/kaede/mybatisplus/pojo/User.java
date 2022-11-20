package com.kaede.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.kaede.mybatisplus.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kaede
 * @create 2022-10-02
 *
 * @TableId注解的value属性用于指定主键的字段
 * @TableId注解的type属性用于设置主键生成策略
 */

//当实体类名和表名不一致时，MyBatisPlus会找不到，需加@TableName注解
//设置实体类所对应的表名
@TableName("t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //将该属性所对应的字段指定为主键（默认指定id字段为主键，若没有则需设置）
    //若该属性名和数据库的字段名相同则只需@TableId即可，否则需要在参数value中添加所对应的字段
    //MyBatisPlus默认生成主键的算法就是雪花算法
    //数据库中设置主键为自增后，设置type = IdType.AUTO后就是数据库自增
    @TableId(value = "uid", type = IdType.AUTO)
    //@TableId(value = "uid")
    //@TableId
    private Long id;
    //@TableField注解指定属性所对应的字段名
    @TableField("user_name")
    private String name;
    private Integer age;
    private String email;

    //标识逻辑删除的字段
    @TableLogic
    private boolean isDeleted;
    private SexEnum sex;

}
