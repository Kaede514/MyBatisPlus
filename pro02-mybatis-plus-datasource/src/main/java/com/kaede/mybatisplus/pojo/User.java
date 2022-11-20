package com.kaede.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kaede
 * @create 2022-10-03
 */

@TableName("t_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;
    private String userName;
    private Integer age;
    private Integer sex;
    private String email;
    @TableLogic
    private Integer isDeleted;

}
