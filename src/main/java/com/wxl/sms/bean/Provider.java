package com.wxl.sms.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wxl on 2021/4/29 0:24
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Provider {
    private Integer id;
    private String providerName;
    private String providerContact;
    private String contactPhone;
    private String providerAddress;
}
