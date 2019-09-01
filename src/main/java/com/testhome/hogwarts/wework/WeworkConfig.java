package com.testhome.hogwarts.wework;

/**
 * 配置类
 *
 * @author ywsmart
 * @date 2019-07-26
 */
public class WeworkConfig {
    // 企业测试应用的id(AgentId)
    public String agentId = "1000002";
    // 测试企业微信的企业id(corpid)
    public String corpid = "wwbae292101b56dc3b";
    // 企业测试应用的钥匙(secret)
    public String secret = "dlkzeMM0qR4XCTmi88bZPtwiJVDqxcZcAwq8UabNucw";
    // 通讯录secret
    public String contactSecret = "5-1cZrhKYGTH0eGWtvLak0pbN77jJwOAVuKlI8GVz7w";


    // 最简单单例，还可改成更安全的，不能new的单例
    private static WeworkConfig weworkConfig;

    public static WeworkConfig getInstance() {
        if (weworkConfig == null) {
            weworkConfig = new WeworkConfig();
        }
        return weworkConfig;
    }

    /**
     * 从配置文件读取配置
     */
    public static void load(String path){
        // TODO read from yaml or json

    }
}
