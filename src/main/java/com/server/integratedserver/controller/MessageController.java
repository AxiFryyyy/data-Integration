package com.server.integratedserver.controller;

import com.alibaba.fastjson.JSON;
import com.server.integratedserver.VO.MsgResponseVo;
import com.server.integratedserver.webSocket.WebSocketServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/Message")
public class MessageController {
    private String output;

    public MessageController(){
        output = "已启動\n";
        updateData();
    }

    @PostMapping("/updateData")
    public void updateData() {
        // 更新数据
        try{
            MsgResponseVo technicianMsgResponseVo = new MsgResponseVo();
            technicianMsgResponseVo.setRole("Technician");
            technicianMsgResponseVo.setRoleId(1);
            technicianMsgResponseVo.setMsg(output);
            WebSocketServer.sendInfo(JSON.toJSONString(technicianMsgResponseVo));
        }catch (IOException e){
            System.err.println(e);
        }
        //发送消息
    }

    public void addString(String str) throws IOException {
        output += str + "\n";
        updateData();
    }
}
