package com.xoa.controller.inventory;



import com.xoa.model.inventory.InventoryModel;
import com.xoa.model.user.UserModel;
import com.xoa.service.inventory.InventoryService;
import com.xoa.util.ToJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuzy on 2017/10/28.
 */
@Controller
@RequestMapping("/inventory")
public class inventoryController {

    @Resource
    private InventoryService inventoryService;

    @ResponseBody
    @RequestMapping(value ="/add", produces = {"application/json;charset=UTF-8"})
    public ToJson<InventoryModel> add(InventoryModel inventoryModel, HttpServletRequest request, HttpServletResponse response) {
        ToJson<InventoryModel> json = new ToJson<InventoryModel>(1, null);
        try {
            UserModel userModel = (UserModel)request.getSession().getAttribute("user");
            inventoryModel.setCreateuser(userModel.getUid());
            inventoryModel.setCreatetime(new Date());
            Boolean res = inventoryService.insert(inventoryModel);
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
    public ToJson<InventoryModel> selectByPrimaryKey(Integer id, HttpServletRequest request, HttpServletResponse response) {
        ToJson<InventoryModel> json = new ToJson<InventoryModel>(1, null);
        try {
            InventoryModel res = inventoryService.selectByPrimaryKey(id);
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
    public ToJson<InventoryModel> selectAll(Integer pagesize,Integer page,InventoryModel inventoryModel, HttpServletRequest request, HttpServletResponse response) {
        ToJson<InventoryModel> json = new ToJson<InventoryModel>(1, null);
        try {
            inventoryModel.setPagesize(pagesize);
            inventoryModel.setPage(page);
            List<InventoryModel> res = inventoryService.selectAll(inventoryModel);
            Integer count = inventoryService.countByExample(inventoryModel);
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
    public ToJson<InventoryModel> deleteById(Integer id, HttpServletRequest request, HttpServletResponse response) {
        ToJson<InventoryModel> json = new ToJson<InventoryModel>(1, null);
        try {
            if(inventoryService.deleteByPrimaryKey(id)){
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
    public ToJson<InventoryModel> updateById(InventoryModel inventoryModel, HttpServletRequest request, HttpServletResponse response) {
        ToJson<InventoryModel> json = new ToJson<InventoryModel>(1, null);
        try {
            if(inventoryService.updateByPrimaryKey(inventoryModel)){
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
    public String Pageindex( HttpServletResponse response) {

        return "app/inventory/index";
    }
    @RequestMapping("/record")
    public String PageRecord( HttpServletResponse response) {
        return "app/inventory/inventoryRecord";
    }
}
