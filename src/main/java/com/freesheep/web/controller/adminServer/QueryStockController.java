package com.freesheep.web.controller.adminServer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.freesheep.biz.jiuBean.*;
import com.freesheep.web.config.jiuConfig.JiuConfig;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.net.HttpRequest;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.JiuUtils;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/query")
public class QueryStockController extends BaseSecretController {

    private Logger log = Logger.getLogger(QueryStockController.class);

    @ResponseBody
    @RequestMapping(value = "/order_process", method = RequestMethod.POST)
    public ResultView orderProcess(){
        Map<String, Object> parMap = null;
        try {
//            parMap = getBodyMap(Constant.ADMIN_REQUEST);
            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if (parMap == null) return result(null, "参数错误");
        String orderCode = Utils.getSomeValue(parMap.get("order_code"));
        String orderType = Utils.getSomeValue(parMap.get("order_type"));
        String warehouseCode = Utils.getSomeValue(parMap.get("warehouse_code"));
        if(StringUtils.isBlank(orderCode)) return result(null, "参数错误");

        OrderProcessBean orderProcessBean = new OrderProcessBean();
        orderProcessBean.setOrderCode(orderCode);
        if(StringUtils.isNotBlank(orderType)) orderProcessBean.setOrderType(orderType);
        if(StringUtils.isNotBlank(warehouseCode)) orderProcessBean.setWarehouseCode(warehouseCode);

        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.alias("request", OrderProcessBean.class);
        String xml = xStream.toXML(orderProcessBean);
        String url = JiuUtils.getRequestUrl("orderprocess.query", xml);
        if (StringUtils.isBlank(url)) return result(null, "数据查询失败");
        log.debug("===================return order request url===========================");
        log.debug(url);
        log.debug("====================return order request url==========================");

        log.debug("===================return order body===========================");
        log.debug(xml);
        log.debug("====================return order body==========================");

        String jiuResp = new HttpRequest().xmlPost(url, xml);
        log.debug("===================return order response===========================");
        log.debug(jiuResp);
        log.debug("====================return order response==========================");

        ResponseBean responseBean = JiuUtils.getResponse(jiuResp);
        if (responseBean != null) {
            if ("success".equals(responseBean.getFlag())) {
                return result(jiuResp);
            }
        }

        return result(null, "数据查询失败");
    }


    @ResponseBody
    @RequestMapping(value = "/stock", method = RequestMethod.POST)
    public ResultView stock(){
        // 九曳不支持了
        Map<String, Object> parMap = null;
        try {
//            parMap = getBodyMap(Constant.ADMIN_REQUEST);
            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if (parMap == null) return result(null, "参数错误");

        String warehouseCode = Utils.getSomeValue(parMap.get("warehouse_code"));
        String itemCode = Utils.getSomeValue(parMap.get("item_code"));
        String itemId = Utils.getSomeValue(parMap.get("item_id"));
        String inventoryType = Utils.getSomeValue(parMap.get("inventory_type"));
        String batchCode = Utils.getSomeValue(parMap.get("batch_code"));
        String productDate = Utils.getSomeValue(parMap.get("product_date"));
        String page = Utils.getSomeValue(parMap.get("page"));
        String pageSize = Utils.getSomeValue(parMap.get("page_size"));
        if(StringUtils.isBlank(page) || StringUtils.isBlank(pageSize)) return result(null, "参数错误");

        QueryStockBean queryStockBean = new QueryStockBean();
        if(StringUtils.isNotBlank(warehouseCode)) queryStockBean.setWarehouseCode(warehouseCode);
        if(StringUtils.isNotBlank(itemCode)) queryStockBean.setItemCode(itemCode);
        if(StringUtils.isNotBlank(itemId)) queryStockBean.setItemId(itemId);
        if(StringUtils.isNotBlank(inventoryType)) queryStockBean.setInventoryType(inventoryType);
        if(StringUtils.isNotBlank(batchCode)) queryStockBean.setBatchCode(batchCode);
        if(StringUtils.isNotBlank(productDate)) queryStockBean.setProductDate(productDate);
        queryStockBean.setOwnerCode(JiuConfig.OWNER_CODE);
        queryStockBean.setPage(page);
        queryStockBean.setPageSize(pageSize);

        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.alias("request", QueryStockBean.class);
        String xml = xStream.toXML(queryStockBean);

        String url = JiuUtils.getRequestUrl("stock.query", xml);
        if (StringUtils.isBlank(url)) return result(null, "数据查询失败");

        log.debug("===================return order request url===========================");
        log.debug(url);
        log.debug("====================return order request url==========================");

        log.debug("===================return order body===========================");
        log.debug(xml);
        log.debug("====================return order body==========================");

        String jiuResp = new HttpRequest().xmlPost(url, xml);
        log.debug("===================return order response===========================");
        log.debug(jiuResp);
        log.debug("====================return order response==========================");

        ResponseBean responseBean = JiuUtils.getResponse(jiuResp);
        if (responseBean != null) {
            if ("success".equals(responseBean.getFlag())) {
                return result(responseBean.getItems());
            }
        }

        return result(null, "数据查询失败");
    }


    @ResponseBody
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResultView cancel(){
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if (parMap == null) return result(null, "参数错误");
        String productArrStr = Utils.getSomeValue(parMap.get("product_arr"));
        if (StringUtils.isBlank(productArrStr)) return result(null, "获取参数为空 参数错误");
        List<CriteriaBean> list = JSONObject.parseObject(productArrStr, new TypeReference<List<CriteriaBean>>() {
        });
        if (list == null || list.size() <= 0) return result(null, "参数错误");

        QueryStockProductBean stockProductBean = new QueryStockProductBean();
        List<CriteriaBean> criteriaList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            CriteriaBean criteriaBean = list.get(i);
            String warehouseCode = criteriaBean.getWarehouseCode();
            if(StringUtils.isBlank(warehouseCode)) return result(null, "参数错误");
            criteriaBean.setOwnerCode(JiuConfig.OWNER_CODE);
            String itemCode = criteriaBean.getItemCode();
            if(StringUtils.isBlank(itemCode)) return result(null, "参数错误");
            criteriaList.add(criteriaBean);
        }
        stockProductBean.setCriteriaList(criteriaList);

        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.alias("request", QueryStockProductBean.class);
        xStream.alias("criteria", CriteriaBean.class);
        String xml = xStream.toXML(stockProductBean);

        String url = JiuUtils.getRequestUrl("inventory.query", xml);
        if (StringUtils.isBlank(url)) return result(null, "数据查询失败");

        log.debug("===================return order request url===========================");
        log.debug(url);
        log.debug("====================return order request url==========================");

        log.debug("===================return order body===========================");
        log.debug(xml);
        log.debug("====================return order body==========================");

        String jiuResp = new HttpRequest().xmlPost(url, xml);
        log.debug("===================return order response===========================");
        log.debug(jiuResp);
        log.debug("====================return order response==========================");

        ResponseBean responseBean = JiuUtils.getResponse(jiuResp);
        if (responseBean != null) {
            if ("success".equals(responseBean.getFlag())) {
                return result(responseBean.getItems());
            }
        }

        return result(null, "数据查询失败");
    }

}
