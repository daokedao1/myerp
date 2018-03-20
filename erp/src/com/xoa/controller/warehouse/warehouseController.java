package com.xoa.controller.warehouse;

import com.xoa.model.user.UserModel;
import com.xoa.model.warehouse.WareHouseModel;
import com.xoa.service.user.UserService;
import com.xoa.service.warehouse.WareHouseService;
import com.xoa.util.ToJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zhuzy on 2017/11/19.
 */

@Controller
@RequestMapping("/warehouse")
public class warehouseController {

    @Resource
    private WareHouseService wareHouseService;

    @ResponseBody
    @RequestMapping(value ="/add", produces = {"application/json;charset=UTF-8"})
    public ToJson<WareHouseModel> add(WareHouseModel wareHouseModel, HttpServletRequest request, HttpServletResponse response) {
        ToJson<WareHouseModel> json = new ToJson<WareHouseModel>(1, null);
        try {
            UserModel user = (UserModel)request.getSession().getAttribute("user");
            wareHouseModel.setCreateUser(user.getUid().toString());
            Boolean res = wareHouseService.insert(wareHouseModel);
            if(res){
                json.setMsg("ok");
                json.setFlag(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value ="/getOneById", produces = {"application/json;charset=UTF-8"})
    public ToJson<WareHouseModel> selectByPrimaryKey(Integer uid, HttpServletRequest request, HttpServletResponse response) {
        ToJson<WareHouseModel> json = new ToJson<WareHouseModel>(1, null);
        try {
            System.out.println(uid);
            WareHouseModel res = wareHouseService.selectByPrimaryKey(uid);
            json.setObject(res);
            json.setMsg("OK ");
            json.setFlag(0);
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value ="/getAll", produces = {"application/json;charset=UTF-8"})
    public ToJson<WareHouseModel> selectAll(Integer pagesize, Integer start, Integer page, WareHouseModel wareHouseModel, HttpServletRequest request, HttpServletResponse response) {
        ToJson<WareHouseModel> json = new ToJson<WareHouseModel>(1, null);
        try {
            wareHouseModel.setPagesize(pagesize);
            wareHouseModel.setPage(page);
            List<WareHouseModel> res = wareHouseService.selectAll(wareHouseModel);
            Integer count = wareHouseService.countByExample(wareHouseModel);
            json.setCount(count);
            json.setObject(res);
            json.setMsg("OK ");
            json.setFlag(0);
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value ="/deleteById", produces = {"application/json;charset=UTF-8"})
    public ToJson<WareHouseModel> deleteById(Integer wid, HttpServletRequest request, HttpServletResponse response) {
        ToJson<WareHouseModel> json = new ToJson<WareHouseModel>(1, null);
        try {
            if(wareHouseService.deleteByPrimaryKey(wid)){
                json.setMsg("OK ");
                json.setFlag(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }
    @ResponseBody
    @RequestMapping(value ="/updateById", produces = {"application/json;charset=UTF-8"})
    public ToJson<WareHouseModel> updateById(WareHouseModel wareHouseModel, HttpServletRequest request, HttpServletResponse response) {
        ToJson<WareHouseModel> json = new ToJson<WareHouseModel>(1, null);
        try {
            if(wareHouseService.updateByPrimaryKey(wareHouseModel)){
                json.setMsg("OK ");
                json.setFlag(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }
    @RequestMapping("/index")
    public String Pageindex( HttpServletRequest request, HttpServletResponse response) {

        return "app/warehouse/index";
    }
}
