package com.chen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author 陈志浩
 * @since 2022-04-29
 */
@ApiModel(value = "Qinshi对象", description = "")
public class Qinshi implements Serializable {

    private static final long serialVersionUID = 1L;
      @JsonIgnore
      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("用户名")
      private String name;

      @ApiModelProperty("年龄")
      private Integer age;

      @ApiModelProperty("电话")
      private String tel;

      @ApiModelProperty("qq号	")
      private String qq;

      @ApiModelProperty("家庭地址")
      private String adr;

    @ApiModelProperty("昵称")
    private String nickname;


    @ApiModelProperty("头像")
    private String avatarurl;

    private LocalDateTime createTime;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarurl;
    }

    public void setAvatarUrl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public Integer getAge() {
        return age;
    }

      public void setAge(Integer age) {
          this.age = age;
      }
    
    public String getTel() {
        return tel;
    }

      public void setTel(String tel) {
          this.tel = tel;
      }
    
    public String getQq() {
        return qq;
    }

      public void setQq(String qq) {
          this.qq = qq;
      }
    
    public String getAdr() {
        return adr;
    }

      public void setAdr(String adr) {
          this.adr = adr;
      }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }

      public void setCreateTime(LocalDateTime createTime) {
          this.createTime = createTime;
      }
    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }

    @Override
    public String toString() {
        return "Qinshi{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", tel='" + tel + '\'' +
                ", qq='" + qq + '\'' +
                ", adr='" + adr + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatarUrl='" + avatarurl + '\'' +
                ", createTime=" + createTime +
                ", id=" + id +
                '}';
    }
}
