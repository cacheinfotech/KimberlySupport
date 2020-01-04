package com.kimberlysupport.service;

import com.kimberlysupport.model.Complain;
import com.kimberlysupport.model.Dispenser;
import com.kimberlysupport.repository.ComplainRepository;
import com.kimberlysupport.repository.DispenserRepository;
import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class SerialNmbrService {

    @Autowired
    private DispenserRepository dispenserRepository;
    @Autowired
    private ComplainRepository complainRepository;

  public List<String> generateNmbers(HttpServletRequest request){
      String qrUrl ="https://chart.googleapis.com/chart?chs=200x200&cht=qr&choe=UTF-8&chl=";

      String hostName = "http://192.168.1.91:8080";
     // String hostName = request.getRequestURL().toString();
      //hostName = hostName.substring(0,hostName.indexOf("/admin"));
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
      Dispenser dispenser = dispenserRepository.findBySerialNumber(srnmbr);
      if(dispenser!=null) {
          mv.addObject("dispenser", dispenser);
      }else mv.addObject("msg","device not registered");

      return mv;
    }

    public ModelAndView registerDispenser(Dispenser dispenser) {
      ModelAndView mv = new ModelAndView("user/registerResp");
      try {
          dispenserRepository.save(dispenser);
          mv.addObject("msg","Device registered successfully");
      }catch (Exception e){
          mv.addObject("msg","Device already registered.");
      }

      return mv;
    }

    public ModelAndView fileComplain(Complain complain, Dispenser dispenser) {
        ModelAndView mv = new ModelAndView("complainResp");
        Dispenser dispenser1 = dispenserRepository.findBySerialNumber(dispenser.getSerialNumber());

        complain.setDispenser(dispenser1);
        try {
            complainRepository.save(complain);
            mv.addObject("msg", "complaint filed successfully");
        }
        catch (Exception e){
            mv.addObject("msg","Something went wrong");
        }
        return mv;
    }
}

