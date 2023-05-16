package com.server.integratedserver.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgResponseVo {
    private String role;
    private Integer roleId;
    private String Msg;

}
