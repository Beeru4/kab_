package com.beerus.kab_admin_provider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Beerus
 * @Description
 * @Date 2019-06-17
 **/
@Controller
public class PublicController {
  /**
   * 后台登录
   *
   * @return
   */
  @RequestMapping("/backgroundlogin")
  public String backgLogin() {
    return "backgroundlogin";
  }
}
