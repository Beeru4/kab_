package com.beerus.common.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Beerus
 * @Description 短信验证
 * @Date 2019-06-28
 **/
public class SmsUtils {

    private static Logger logger = LoggerFactory.getLogger(SmsUtils.class);
    // 产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    /**
     * AccessKey ID
     */
    static final String accessKeyId = "LTAIPJC7AlsHv0Ye";
    /**
     * Access Key Secret
     */
    static final String accessKeySecret = "pwOcKQNANj2E09mjX9tIGqZmUjX49g";


    /**
     * @param telephone 要发送的电话
     * @param mark      标示变量 1=注册 2=找回密码
     * @param code      随机生成六位验证码
     * @return
     * @throws ClientException
     */
    public static SendSmsResponse sendSms(String telephone, int mark, int code) throws ClientException {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(telephone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("KAB创业教育网"); // TODO 改这里
        if (1 == mark) {
            // 必填:短信模板-可在短信控制台中找到
            request.setTemplateCode("SMS_170353028");  // TODO 注册
        } else if (2 == mark) {
            request.setTemplateCode("SMS_169113059");  // TODO 找回密码
        } else if (3 == mark) {
            request.setTemplateCode("SMS_169113057");  // TODO 修改密码
        } else if (4 == mark) {
            request.setTemplateCode("SMS_169640043");  // TODO 修改手机号码
        } else if (5 == mark) {
            request.setTemplateCode("SMS_170348077");  // TODO 登入
        } else {
            throw new ClientException("mark is error");
        }
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            logger.info("sendOK" + ",code:" + sendSmsResponse.getCode());
        } else {
            logger.info("sendError" + ",code:" + sendSmsResponse.getCode());
        }
        return sendSmsResponse;
    }
}
