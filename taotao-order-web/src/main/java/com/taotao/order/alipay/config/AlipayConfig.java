package com.taotao.order.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102100732602";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQClj8/CZfqlw+pwzkzWSU/RkwMhlYJ2vwwH3ZliWtUXj3tizXoXItrMHc1ZlfODsyhkCxMg9i9gw7a2FNmWnpXfVvXb1XXShDfiK2qwSpm9hKlGYcD0HUCVh3zBt0gDdPlmGo8TFWuQ8SnSd2Thgiv7xSL1hjI9HqqJ9NgDDZq+8L7riMMo1HM76A3XvULD9brjoVC4NbDpxl31PqnTmXrTX9GDuNWC88YB9Fhy1nCbiZdGBVLLgl38jgZviLoYzyTlyJm5WZM4S+e2p6dh6QDgkHmHcil2EtOwkwuevn907v/1JgcLdiZ2dpbR4Or59IHfFVfgTDuNEOu9XsVOP1AbAgMBAAECggEBAJXTa0SVXRqeDY9AYrCZb65MyEygLIyhn5WOqLwlTyofIp3rLU26sR5+6dLkrJbGjwraKtltcLq04qgsfiycHy0aWSw0JIf6N0hp2gbnu/GSGE4lMKS5xDSNhA0afAUE2p3X8xpmDA4C8MISYizhzEPvxw9B/jzpf5ORC9rXeO7JGwFnUHHByB58S3pmTgxiFh1K2pS8G72ICV4bMAkgJEX7zQTq/lQyNq9w67zoSqX4ldwOjDJ6Nal+6xdu6SwcZ91z5/Ip2VkF/vT7q79xQ2liKt9RXScDM105kVlVNB0d8Uo/FcKW4+qdD1fBBRI1dWiz2UMXtEcZHOE+2gRIJwECgYEA++ySORy5HSBLjIRg1VmQ5aCN9+hja7F+v/pMDICehMqsmw4EeCFG+pImW+QHFgerQa/xFFLRbQ/Eo13VnvU8b9YRmMEinWcze20Vo/3FnwoByu3OOWfvQkqbwXXjubOgqW/CsKWwMuFV1XG/ODkZDkU6cryc052J3oD1F6s6y7sCgYEAqD2KoyWq9mKW2c0cnG+f9SeVkO1rrMpQmZ66ixKlreNn1aW4fpq8cTup4ybnfkpwu1SxFeAIqwRaGzu3SB7eth+0N8v9PiXoaCpO1DC3X05OXabUlziYETuBXtTc2vtlg/soC/UccTyM2RzLH70H04mngOp/JptnDur4oAAj1yECgYEAmOwD3tNGsx11frikpx2VTS5mv0O7hhXaYOKo4zX4A5ebqwuZPurEUToCOulyO7cwB9GrP7D+G3vk214MXE90/lYtnFIwluecYqHW7xMOsPm8GNi9xC8JF25+mbLcJ0+YiQGkZuJM0LXkCorfjltqkDUJw7gmYnZQyFXzcQipyh0CgYEAp8NOZqdeFnhzIlaVDC2HHQhO7YjCkTfhLX6JV7rNeVtFJFcvZRfZhzROkRMvTdDfHv4x47VWD2q0i2l9ty4JOSTeKbGBIqLcYFqn5pF5mVDVdi+w1+Z4RN7If47VeIutS/F7mojdxtDxRmKe7lQp5LfkTvoGtrHKD6chKzNYieECgYEA6DIyyoXuU8RhFriNp8xHkqGZLmnUMYPU6sV8dNNa6Ea3uQfo9bkP4AlFYbSI9HnatZW4PjWpBEUwYnxweH8kBwdu6ZppeH1UBykpll1cFpjHahK4vnMRks0XvdFyArpzlXzGqg5VJ8Z4NgwIsv0uwP14BcKBRP2bn2Tz7NnHaGc=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuDPLr1O8ALPQE2Y8OzUiPLwZnlCKXrqpkf5up3A75TZzSCMMg1vPqxnog424txmyZW8BG/6/3MIyFNuR0hUuGfsT/AInuVYKM0zrbqNGJ8SAAPZbjOTrXGOHr9BNCb7ktoW6uUoOGlX21S0VeG1li7dFtSdjcAILpXiCbzZVwQyx6I5bG6JjeHkNAD565PoENNvTU0VMbYPv7h1RtFwCwoFjqLS6P2rW/28bDq8b80VuxYcDya9IYMn/rb3fI33vQ6brFyRB02gCwmpL+qlPIfPtEM4m3UhnJqFx85XsmxPPgcpkXu6zz4KLKeyQ4dmMQcHIbKF6Bd4MR7SDGciEqQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8091/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8091/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

