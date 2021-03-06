package com.tmlk.controller;

/**
 * Created by LaiGuoqiang on 2015/5/14.
 */

import com.tmlk.framework.session.SessionUser;
import com.tmlk.framework.util.Constants;
import com.tmlk.framework.util.JsonResult;
import com.tmlk.po.PartyExt;
import com.tmlk.po.PartyUserExt;
import com.tmlk.po.SysUserExt;
import com.tmlk.service.IPartyServiceExt;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/party")
public class PartyController {

    private static final Logger logger = Logger.getLogger(PartyController.class);

    @Autowired
    private IPartyServiceExt partyService;

    @RequestMapping(value = "/create")
    public String goCreate(){
        return "/party/create";
    }

    @RequestMapping(value = "/doCreate", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult doCreate(@ModelAttribute PartyExt partyExt, HttpSession session){
        JsonResult result = new JsonResult();
        try{
            String partyCode = partyExt.getPartyCode();


            if(!partyService.existParty(partyCode)){

                SessionUser sessionUser = (SessionUser)session.getAttribute(Constants.SESSION_USER);
                SysUserExt sysUserExt = (SysUserExt)sessionUser.getUser();

                if(!partyExt.getIsGroup()){ //不分组 我们将自动创建一个小组来保持所有
                    partyExt.setBuildEndTime(null);
                    partyExt.setMemberNumMax(0);
                    partyExt.setMemberNumMin(0);
                    partyExt.setIsCustomBuild(null);
                }
                partyExt.setCreateBy(sysUserExt.getId());
                partyExt.setCreateTime(new Date());

                partyExt.setHotCount(0);
                partyExt.setMemberCount(0);
                partyExt.setPartyStatus(1);

                partyService.create(partyExt);

                result.setStatus(0);

                //测试
                result.setData(partyExt);
            }
            else{
                result.setMessage("识别码已存在，请修改重新提交");
            }
        }catch (Exception ex){
            result.setMessage("服务器异常，请重新提交");
            logger.error(ex);
        }
        return result;
    }

    @RequestMapping(value = "/setting")
    public String goEdit(){

//        List<PartyUserExt> partyUserExts = partyService.getPartyUsers("ddddd");

        return "/party/setting";
    }

}
