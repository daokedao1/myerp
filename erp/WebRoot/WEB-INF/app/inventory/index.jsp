<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title></title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="../lib/adminlte/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="../lib/adminlte/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="../lib/layui/css/layui.css">
</head>
<style>
  .searchBox{width: 180px;}
  .detailbox{
    margin-top: 10px;
    margin-left: 50px;
    margin-right: 50px;
  }

  .layui-table-body .layui-table,.layui-table-header .layui-table{width: 100%;}
  .layui-table-body.layui-table-main .layui-table-cell,.layui-table-header .layui-table-cell{word-wrap: break-word; word-break: break-all; height: auto;}
</style>
<body class="hold-transition skin-blue sidebar-mini">

<div class="">
  <div class="box-header">
    <h3 class="box-title">库存管理</h3>
  </div>

  <!-- /.box-header -->
  <div class="box-body">
    <div class="box-tools pull-left">
      <div class="has-feedback">
        <input type="text" id="search" class="form-control input-sm" placeholder="Search...">
        <span class="glyphicon glyphicon-search form-control-feedback text-muted"></span>
      </div>
    </div>
    <div class="box-tools pull-right"><button id="new_btn" class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon"></i>新建</button></div>

    <table id="demo" class="layui-table" width="100%" lay-filter="test"></table>
  </div>
  <!-- /.box-body -->
</div>
</body >
<script type="text/html" id="detailBox">
  <div class="layui-box">
    <form id="inventory" class="layui-form detailbox" action="../product/add" >
      <div class="layui-form-item">
        <label class="layui-form-label">产品名称</label>
        <div class="layui-input-block">
          <select name="product" id="product" lay-verify="" lay-search>

            ……
          </select>
         </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">仓库</label>
        <div class="layui-input-block">
          <select name="warehouse" id="warehouse" lay-verify="" lay-search>

          </select>
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">库存数量</label>
        <div class="layui-input-block">
          <input type="text" lay-verify="number"  name="num" value="0" id="num" required  lay-verify="required" placeholder="请输入数量" autocomplete="off" class="layui-input">
        </div>
      </div>
    </form>
  </div>

</script>
<script type="text/html" id="toolbar">
  <a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger  layui-btn-sm" lay-event="del">删除</a>
</script>
<!-- jQuery 2.2.3 -->
<script src="../lib/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="../lib/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script src="../js/base.js"></script>
<script src="../js/mustache.min.js"></script>

<script src="../lib/layui/layui.js"></script>


<script>
    $(function(){
        var columns = ['id','name','brandId','productUnit','profile','image','createuser','createtime']
        layui.use(['layer', 'table','form'], function(){
            var table = layui.table;
            var layer = layui.layer;
            var form = layui.form;
            $('#search').on('input propertychange',function(){
                console.log($(this).val());
                table.reload('demo', {
                    where: { //设定异步数据接口的额外参数，任意设
                        search:$(this).val()
                    }
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            });
            $('#new_btn').click(function(){
                layer.open({
                    type: 1,
                    area: ['700px', '350px'],
                    fixed: false, //不固定
                    maxmin: true,
                    content: $('#detailBox').html(),
                    btn:['保存', '取消'],
                    success:function () {
                        buikdselect(function(){
                            form.render();
                        });
                    },
                    yes:function () {
                        savedate(function(){
                            layer.closeAll();
                            table.reload('demo', {
                                where: { //设定异步数据接口的额外参数，任意设
                                    search:$('#search').val()
                                }
                                ,page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                        })
                    }

                });
            })
            table.render({
                elem: '#demo'
                ,skin:'row'
                ,even:true
                //,height: "full-100"
                ,limit:10
                ,url: '../inventory/getAll' //数据接口
                ,method: 'get'
                ,request: {
                    pageName: 'page' //页码的参数名称，默认：page
                    ,limitName: 'pagesize' //每页数据量的参数名，默认：limit
                }

                ,cellMinWidth:120
                ,response:{
                    msgName: 'msg' //状态信息的字段名称，默认：msg
                    ,countName: 'count' //数据总数的字段名称，默认：count
                    ,dataName: 'object' //数据列表的字段名称，默认：data
                }
                ,page: true //开启分页
                ,cols: [[
                    {field: 'pid', title: '产品编号', width:'100', sort: true}
                    ,{field: 'pname', title: '产品名称' }
                    ,{field: 'productbrandname',title: '品牌', sort: true,width:80}
                    ,{field: 'num', title: '库存数量', sort: true,width:100}
                    ,{field: 'sellingprice', title: '销售价格',width:100 ,sort: true}
                    ,{field: 'warehousename', title: '仓库', sort: true,width:100}
                    ,{field: 'createtime', title: '创建时间', sort: true,width:165}
                    ,{field: 'bak', title: '操作',toolbar:'#toolbar',width:130}

                ]]
                ,done:function(res, curr, count){

                    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                        var data = obj.data; //获得当前行数据
                        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                        var tr = obj.tr; //获得当前行 tr 的DOM对象
                        console.log(data)
                        if(layEvent === 'detail'){ //查看
                            //do somehing
                        } else if(layEvent === 'del'){ //删除
                            layer.confirm('真的删除行么', function(index){
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                layer.close(index);
                                deleteProductByid(data.id,function (data) {
                                    table.reload('demo', {
                                        where: { //设定异步数据接口的额外参数，任意设
                                            search:$('#search').val()
                                        }
                                        ,page: {
                                            curr: 1 //重新从第 1 页开始
                                        }
                                    });
                                })
                                //向服务端发送删除指令
                            });
                        } else if(layEvent === 'edit'){ //编辑
                            $.ajax({
                                type: "get",
                                url: "../inventory/getOneById",
                                dataType: 'JSON',
                                data: {
                                    id:data.id
                                },
                                success: function (obj) {
                                    if(obj.flag){
                                        var resDate = obj.object;
                                        layer.open({
                                            type: 1,
                                            area: ['700px', '500px'],
                                            fixed: false, //不固定
                                            maxmin: true,
                                            content: $('#detailBox').html(),
                                            btn:['保存', '取消'],
                                            success:function () {
                                                buikdselect(function () {
                                                    $("#product").val(resDate.pid);
                                                    $("#num").val(resDate.num);
                                                    $("#warehouse").val(resDate.warehouseid);
                                                    form.render();
                                                })

                                            },
                                            yes:function () {
                                                updatadate(data,function(){
                                                    layer.closeAll();
                                                    table.reload('demo', {
                                                        where: { //设定异步数据接口的额外参数，任意设
                                                            search:$('#search').val()
                                                        }
                                                        ,page: {
                                                            curr: 1 //重新从第 1 页开始
                                                        }
                                                    });
                                                })
                                            }
                                        });
                                    }
                                }
                            });

                            //同步更新缓存对应的值
                            obj.update({
                                username: '123'
                                ,title: 'xxx'
                            });
                        }
                    });
                }
            });
        });
        function savedate(cb) {
            var data = {
                pid:$("#product").val(),
                num:$("#num").val(),
                warehouseid:$("#warehouse").val()
            };
            console.log(data)
            if(data.warehouseid!='' && data.pid!='' && data.num!=''){
                $.ajax({
                    type: "post",
                    url: "../inventory/add",
                    dataType: 'JSON',
                    data: data,
                    success: function (obj) {
                        cb(obj)
                    }
                });
            }else{
                alert('参数不全')
            }


        }
        function updatadate(data,cb) {
            var data = {
                id:data.id,
                pid:$("#product").val(),
                num:$("#num").val(),
                warehouseid:$("#warehouse").val()
            }
            $.ajax({
                type: "post",
                url: "../inventory/updateById",
                dataType: 'JSON',
                data: data,
                success: function (obj) {
                    cb(obj)
                }
            });
        }
        function deleteProductByid(id,cb){
            $.ajax({
                type: "post",
                url: "../inventory/deleteById",
                dataType: 'JSON',
                data: {
                    id:id
                },
                success: function (obj) {
                    cb(obj)
                }
            });
        }
        function buikdselect(cb){
            $.ajax({
                type: "get",
                url: "../common/getSelectObj",
                dataType: 'JSON',
                data: {
                    moudule:"product,warehouse"
                },
                success: function (obj) {
                    if(obj.flag){
                        var productUnit = obj.productUnit;
                        $('#product').html(Mustache.render('{{#product}}<option value="{{id}}">{{name}}</option>{{/product}}',obj.object))
                        $('#warehouse').html(Mustache.render('{{#warehouse}}<option value="{{wid}}">{{wname}}</option>{{/warehouse}}',obj.object))
                    }
                    cb(obj)
                }
            });
        }
    })
</script>
</html>
