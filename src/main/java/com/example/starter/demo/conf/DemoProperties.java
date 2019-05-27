package com.example.starter.demo.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dd
 * @date 2019-05-27
 */
@ConfigurationProperties("starter.demo.hello")
public class DemoProperties {
    /**
     * 是否启用
     */
    private Boolean enabled;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 家乡
     */
    private String hometown;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    @Override
    public String toString() {
        return "DemoProperties{" + "enabled=" + enabled + ", name='" + name + '\'' + ", age=" + age + ", hometown='" + hometown + '\'' + '}';
    }
}
