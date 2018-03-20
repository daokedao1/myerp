package com.xoa.controller.product;

import com.xoa.model.product.ProductModel;
import com.xoa.model.product.ProductUnit;
import com.xoa.service.product.ProductService;
import com.xoa.util.ToJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zhuzy on 2017/10/28.
 */
@Controller
@RequestMapping("/product")
public class productController {

    @Resource
    private ProductService productService;

    @ResponseBody
    @RequestMapping(value ="/add", produces = {"application/json;charset=UTF-8"})
    public ToJson<ProductUnit> add(ProductModel productModel, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductUnit> json = new ToJson<ProductUnit>(1, null);
        try {
            Boolean res = productService.insert(productModel);
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
    public ToJson<ProductUnit> selectByPrimaryKey(Integer pid, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductUnit> json = new ToJson<ProductUnit>(1, null);
        try {
            System.out.println(pid);
            ProductModel res = productService.selectByPrimaryKey(pid);
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
    public ToJson<ProductUnit> selectAll(Integer pagesize,Integer page,ProductModel productModel, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductUnit> json = new ToJson<ProductUnit>(1, null);
        try {
            productModel.setPagesize(pagesize);
            productModel.setPage(page);
            List<ProductModel> res = productService.selectAll(productModel);
            Integer count = productService.countByExample(productModel);
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
    public ToJson<ProductUnit> deleteById(Integer id, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductUnit> json = new ToJson<ProductUnit>(1, null);
        try {
            if(productService.deleteByPrimaryKey(id)){
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
    public ToJson<ProductUnit> updateById(ProductModel productModel, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductUnit> json = new ToJson<ProductUnit>(1, null);
        try {
            if(productService.updateByPrimaryKey(productModel)){
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

        return "app/product/index";
    }
}
