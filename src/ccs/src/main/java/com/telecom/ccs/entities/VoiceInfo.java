package com.telecom.ccs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoiceInfo {

    private Date callStartTime;
    private Date callEndTime;
    private long holdDuration;  // 通话时长

    private String customerNumber;  //客户编号
    private long seatNumber;      //坐席编号
    private String callDirection;    // 呼叫方向   0 呼入,1 呼出
    private String seatGroup;         // 坐席班组
    private String seatNo;    // 坐席工号 客服代表工号
    private String proPhoneNum;  // 受理号码
    private Date  inputTime;   //入库时间
    private String channelNum;   //省份标识，区域编号
    private String isEachRecord;    // 合路、分路
    private String onHook;   // 挂机方
    private String callerloc; // 客户归属地
    private String customerStart;  //客户星级
    private String satisfaction;   //满意度
    private String dissatisfactionMsg;   //不满意原因
    private int reCallFlag;   // 重复来电  0 默认 1 重复来电
    private int proStatus;    // 处理状态, 0 未处理 1 转写完成




}
