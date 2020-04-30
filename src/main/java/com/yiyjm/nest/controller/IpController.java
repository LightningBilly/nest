package com.yiyjm.nest.controller;

import com.yiyjm.nest.entity.Ip;
import com.yiyjm.nest.service.IpService;
import com.yiyjm.nest.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ip")
public class IpController {
    private IpService ipService;

    @Autowired
    public void setIpService(IpService ipService) {
        this.ipService = ipService;
    }

    @PostMapping("/addIp")
    @ResponseBody
    public String addIp(HttpServletRequest request) {
        String ip = IpUtil.getIp(request);
        ipService.addIp(ip);
        return "ok";
    }

    @PostMapping("/chartStatic")
    @ResponseBody
    public Map<String, Object> chartStatic() {
        return ipService.chartStatic();
    }

    @PostMapping("/listIp")
    @ResponseBody
    public List<Ip> listIp(Integer page, Integer per) {
        return ipService.listIp(page, per);
    }
}
