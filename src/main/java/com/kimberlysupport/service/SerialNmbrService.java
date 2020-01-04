package com.kimberlysupport.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class SerialNmbrService {

  public List<String> generateNmbers(HttpServletRequest request){
      String qrUrl ="https://chart.googleapis.com/chart?chs=200x200&cht=qr&choe=UTF-8&chl=";

      String hostName = "http://192.168.1.91:8080";
     // String hostName = request.getRequestURL().toString();
     // hostName = hostName.substring(0,hostName.indexOf("/admin"));
      String dev= hostName+"/user/addDevice?srnmbr=";
      hostName= hostName+"/support?srnmbr=";


      List<String> srs = new ArrayList<>();

      for(long i = 1 ; i<=4;i++){
        srs.add(qrUrl+dev+generatePin(i,"KMB"));
      }

     for(long i = 1 ; i<=4;i++){
        srs.add(qrUrl+hostName+generatePin(i,"KMB"));
      }

        return srs;
    }

    private static String generatePin(Long id, String identifier) {
        String zeroStr = "00000000";
        int displayZeroCount = zeroStr.length() - Long.toString(id).length();
        String displayString = zeroStr.substring(0, displayZeroCount);
        return (identifier + displayString + id);
    }


    public ModelAndView getSerialDetails(String srnmbr) {
      ModelAndView mv = new ModelAndView("support");
      mv.addObject("dispenser",srnmbr);
      return mv;
    }
}

