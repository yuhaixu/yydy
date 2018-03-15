package com.freesheep.web.controller.adminServer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.freesheep.biz.jiuBean.CreateOutOrderProBean;
import com.freesheep.biz.jiuBean.ItemBean;
import com.freesheep.biz.jiuBean.ResponseBean;
import com.freesheep.biz.jiuBean.SyncProductBean;
import com.freesheep.biz.model.StProductInfoBO;
import com.freesheep.biz.model.StProductsBO;
import com.freesheep.biz.model.StProductsImagesBO;
import com.freesheep.biz.model.SyncProductBO;
import com.freesheep.biz.service.StProductInfoService;
import com.freesheep.biz.service.StProductsImagesService;
import com.freesheep.biz.service.StProductsService;
import com.freesheep.biz.service.SyncProductService;
import com.freesheep.common.util.AESUtil;
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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/product")
public class ProductController extends BaseSecretController {

    private Logger log = Logger.getLogger(ProductController.class);

    @Resource
    StProductsService productsService;
    @Resource
    StProductInfoService productInfoService;
    @Resource
    SyncProductService syncProductService;
    @Resource
    StProductsImagesService productsImagesService;


    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultView list() {
        List<StProductsBO> list = productsService.getProductList();
        JSONObject json = new JSONObject();
        json.put("productList", list);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.ADMIN_REQUEST));
    }

    @ResponseBody
    @RequestMapping(value = "/sync", method = RequestMethod.POST)
    public ResultView syncProduct(){
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if (parMap == null) return result(null, "参数错误");
        String actionType = Utils.getSomeValue(parMap.get("action_type"));
        String warehouseCode = Utils.getSomeValue(parMap.get("warehouse_code"));
        String productCode = Utils.getSomeValue(parMap.get("product_code"));
        String productName = Utils.getSomeValue(parMap.get("product_name"));
        String barCode = Utils.getSomeValue(parMap.get("bar_code"));
        String productType = Utils.getSomeValue(parMap.get("product_type"));
        String productIdStr = Utils.getSomeValue(parMap.get("product_id"));

        if (StringUtils.isBlank(warehouseCode) || StringUtils.isBlank(actionType) || StringUtils.isBlank(productCode)
                || StringUtils.isBlank(productName) || StringUtils.isBlank(barCode) || StringUtils.isBlank(productType)
                || StringUtils.isBlank(productIdStr) ){
            result(null, "参数错误");
        }

        long pid = Utils.parseLong(productIdStr);
        if (pid < 1) return result(null, "参数错误");
        StProductsBO productsBO = productsService.selectByPrimaryKey((int)pid);
        if(productsBO == null) return result(null, "没有该商品信息");
        productsBO.setLogisticsGoodsCode(productCode);

        int type = Utils.parseInt(actionType);

        SyncProductBean syncProductBean = new SyncProductBean();
        syncProductBean.setActionType(type == 0?"add":"update");
        syncProductBean.setWarehouseCode(warehouseCode);
        syncProductBean.setOwnerCode(JiuConfig.OWNER_CODE);
        ItemBean itemBean = new ItemBean();
        itemBean.setItemCode(productCode);
        itemBean.setItemName(productName);
        itemBean.setBarCode(barCode);
        itemBean.setItemType(productType);
        syncProductBean.setItem(itemBean);

        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.alias("request", SyncProductBean.class);
        xStream.alias("item", ItemBean.class);
        String xml = xStream.toXML(syncProductBean);

        String url = JiuUtils.getRequestUrl("singleitem.synchronize", xml);
        if (StringUtils.isBlank(url)) return result(null, "商品信息同步失败");

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
            if ("100000".equals(responseBean.getCode())) {
                Date date = new Date();
                SyncProductBO syncProductBO = new SyncProductBO();
                syncProductBO.setActionType(type);
                syncProductBO.setWarehouseCode(warehouseCode);
                syncProductBO.setOwnerCode(JiuConfig.OWNER_CODE);
                syncProductBO.setProductCode(productCode);
                syncProductBO.setProductName(productName);
                syncProductBO.setBarCode(barCode);
                syncProductBO.setCreateTime(date);
                syncProductBO.setModifyTime(date);
                syncProductService.insertSelective(syncProductBO);
                productsBO.setModified(date);
                productsService.updateByPrimaryKeySelective(productsBO);

                return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
            }
        }

        return result(null, "商品信息同步失败");
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResultView allList() {
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = getBodyMap();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if(parMap == null) return result(null, "参数错误");

        String pageStr = Utils.getSomeValue(parMap.get("page"));
        int page = 1;
        if (!StringUtils.isEmpty(pageStr)) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (page < 1) page = 1;
        int pageSize = 20;

        Page<StProductsBO> pasturePage = productsService.getAllList(getPageRequest(page, pageSize));
        List<StProductsBO> list = pasturePage.getContent();
        long total = pasturePage.getTotalElements();
        int totalPages = pasturePage.getTotalPages();
        JSONObject json = new JSONObject();
        json.put("total", total);
        json.put("totalPages", totalPages);
        json.put("pageSize", pageSize);
        json.put("productList", list);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.ADMIN_REQUEST));
//        return result(json);
    }

    @ResponseBody
    @RequestMapping(value = "/product_info", method = RequestMethod.POST)
    public ResultView getProductInfo() {
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if(parMap == null) return result(null, "参数错误");
        String pidStr = Utils.getSomeValue(parMap.get("pid"));
        if (StringUtils.isBlank(pidStr)) return result(null, "参数错误");

        long pid = Utils.parseLong(pidStr);
        if (pid < 1) return result(null, "参数错误");

        StProductsBO productsBO = productsService.getProductInfoById(pid);
        JSONObject json = new JSONObject();
        json.put("productInfo", productsBO);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.ADMIN_REQUEST));
//        return result(json);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultView add() {
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = getBodyMap();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if(parMap == null) return result(null, "参数错误");
        String productName = Utils.getSomeValue(parMap.get("product_name"));
        String intro = Utils.getSomeValue(parMap.get("intro"));
        String price = Utils.getSomeValue(parMap.get("price"));
        String sale = Utils.getSomeValue(parMap.get("sale"));
        String unit = Utils.getSomeValue(parMap.get("unit"));
        String origin = Utils.getSomeValue(parMap.get("origin"));
        String number = Utils.getSomeValue(parMap.get("number"));
        String isShow = Utils.getSomeValue(parMap.get("is_show"));
        String logisticsGoodsCode = Utils.getSomeValue(parMap.get("logistics_goods_code"));
        String productCode = Utils.getSomeValue(parMap.get("product_code"));
        String qrCode = Utils.getSomeValue(parMap.get("qr_code"));
        String productImgStr = Utils.getSomeValue(parMap.get("product_imgs"));

        if (StringUtils.isBlank(productName) || StringUtils.isBlank(intro) || StringUtils.isBlank(sale) ||
                StringUtils.isBlank(unit) || StringUtils.isBlank(origin) || StringUtils.isBlank(number) ||
                StringUtils.isBlank(isShow) || StringUtils.isBlank(productCode) || StringUtils.isBlank(productImgStr)) {
            return result(null, "参数错误");
        }

        List<String> list = JSONObject.parseObject(productImgStr, new TypeReference<List<String>>() {
        });
        if(list == null || list.size() < 1) return result(null, "参数错误");

        Date date = new Date();
        StProductsBO productsBO = new StProductsBO();
        productsBO.setProducts(productName);
        productsBO.setIntro(intro);
        if (StringUtils.isNotBlank(price)) productsBO.setPrice(Utils.parseFloat(price));
        productsBO.setSale(Utils.parseFloat(sale));
        productsBO.setUnit(unit);
        productsBO.setOrigin(origin);
        productsBO.setNumber(Utils.parseInt(number));
        productsBO.setIsShow(Utils.parseInt(isShow));
        if (StringUtils.isNotBlank(logisticsGoodsCode)) productsBO.setLogisticsGoodsCode(logisticsGoodsCode);
        productsBO.setCreated(date);
        productsBO.setModified(date);

        long pid = productsService.insertSelective(productsBO);
        if (pid < 1) result(null, "数据写入失败");

        StProductInfoBO productInfoBO = new StProductInfoBO();
        productInfoBO.setProductId(pid);
        productInfoBO.setProductCode(productCode);
        if (StringUtils.isNotBlank(qrCode)) productInfoBO.setProductQrCode(qrCode);
        productInfoBO.setCreateTime(date);
        productInfoBO.setModifyTime(date);
        int low = productInfoService.insertSelective(productInfoBO);
        if (low < 1) result(null, "数据写入失败");

        for (int i = 0; i < list.size(); i++) {
            StProductsImagesBO productsImagesBO = new StProductsImagesBO();
            productsImagesBO.setPrId((int) pid);
            productsImagesBO.setImage(list.get(i));
            productsImagesBO.setCreated(date);
            productsImagesBO.setModified(date);
            productsImagesService.insertSelective(productsImagesBO);
        }

        return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
//        return result(Utils.getSuccessJson());
    }

    @ResponseBody
    @RequestMapping(value = "/add_code", method = RequestMethod.POST)
    public ResultView addCode() {
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = getBodyMap();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if(parMap == null) return result(null, "参数错误");
        String pidStr = Utils.getSomeValue(parMap.get("pid"));
        String productCode = Utils.getSomeValue(parMap.get("product_code"));
        String qrCode = Utils.getSomeValue(parMap.get("qr_code"));
        if (StringUtils.isBlank(pidStr) || StringUtils.isBlank(productCode)) {
            return result(null, "参数错误");
        }

        long pid = Utils.parseLong(pidStr);
        if (pid < 1) return result(null, "参数错误");
        StProductInfoBO productInfoBO = new StProductInfoBO();
        Date date = new Date();
        productInfoBO.setProductId(pid);
        productInfoBO.setProductCode(productCode);
        if(StringUtils.isNotBlank(qrCode)) productInfoBO.setProductQrCode(qrCode);
        productInfoBO.setCreateTime(date);
        productInfoBO.setModifyTime(date);
        int low = productInfoService.insertSelective(productInfoBO);
        if(low < 1) result(null, "数据写入失败");

        return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
//        return result(Utils.getSuccessJson());
    }

    @ResponseBody
    @RequestMapping(value = "/modify_code", method = RequestMethod.POST)
    public ResultView modifyCode() {
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = getBodyMap();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if(parMap == null) return result(null, "参数错误");
        String infoIdStr = Utils.getSomeValue(parMap.get("info_id"));
        String productCode = Utils.getSomeValue(parMap.get("product_code"));
        String qrCode = Utils.getSomeValue(parMap.get("qr_code"));

        if (StringUtils.isBlank(infoIdStr)) {
            return result(null, "参数错误");
        }

        long infoId = Utils.parseLong(infoIdStr);
        if (infoId < 1) return result(null, "参数错误");

        StProductInfoBO productInfoBO = new StProductInfoBO();
        Date date = new Date();
        productInfoBO.setId(infoId);
        if(StringUtils.isNotBlank(productCode)) productInfoBO.setProductCode(productCode);
        if(StringUtils.isNotBlank(qrCode)) productInfoBO.setProductQrCode(qrCode);
        productInfoBO.setModifyTime(date);
        int low = productInfoService.updateByPrimaryKeySelective(productInfoBO);
        if(low < 1) result(null, "数据更新失败");

        return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
//        return result(Utils.getSuccessJson());
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultView deleteCode() {
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = getBodyMap();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if(parMap == null) return result(null, "参数错误");

        String infoIdStr = Utils.getSomeValue(parMap.get("info_id"));
        if (StringUtils.isBlank(infoIdStr)) {
            return result(null, "参数错误");
        }

        long infoId = Utils.parseLong(infoIdStr);
        if (infoId < 1) return result(null, "参数错误");
        int low = productInfoService.deleteByPrimaryKey(infoId);
        if(low < 1) result(null, "数据删除失败");

        return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
//        return result(Utils.getSuccessJson());
    }

    @ResponseBody
    @RequestMapping(value = "/add_img", method = RequestMethod.POST)
    public ResultView addProductImg(){
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if(parMap == null) return result(null, "参数错误");

        String pidStr = Utils.getSomeValue(parMap.get("pid"));
        String imageStr = Utils.getSomeValue(parMap.get("img_arr"));
        if (StringUtils.isBlank(pidStr) || StringUtils.isBlank(imageStr)) return result(null, "参数错误");

        long pid = Utils.parseLong(pidStr);
        if (pid < 1) return result(null, "参数错误");

        List<String> imgList = JSONObject.parseObject(imageStr, new TypeReference<List<String>>() {});
        if(imgList == null || imgList.size() < 1) return result(null, "参数错误");
        Date date = new Date();
        for (int i = 0; i < imgList.size(); i++){
            StProductsImagesBO productsImagesBO = new StProductsImagesBO();
            productsImagesBO.setPrId((int)pid);
            productsImagesBO.setImage(imgList.get(i));
            productsImagesBO.setCreated(date);
            productsImagesBO.setModified(date);
            productsImagesService.insertSelective(productsImagesBO);
        }

        return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
    }

    @ResponseBody
    @RequestMapping(value = "/delete_img", method = RequestMethod.POST)
    public ResultView deleteProductImg(){
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if(parMap == null) return result(null, "参数错误");

        String idArrStr = Utils.getSomeValue(parMap.get("id_arr"));
        if (StringUtils.isBlank(idArrStr) ) return result(null, "参数错误");
        List<String> idList = JSONObject.parseObject(idArrStr, new TypeReference<List<String>>() {});
        if(idList == null || idList.size() < 1) return result(null, "参数错误");

        for (int i = 0; i < idList.size(); i++){
            long id = Utils.parseInt(idList.get(i));
            productsImagesService.deleteByPrimaryKey((int)id);
        }

        return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
    }


    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResultView modify() {
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if(parMap == null) return result(null, "参数错误");
        String pidStr = Utils.getSomeValue(parMap.get("pid"));
        String productName = Utils.getSomeValue(parMap.get("product_name"));
        String intro = Utils.getSomeValue(parMap.get("intro"));
        String price = Utils.getSomeValue(parMap.get("price"));
        String sale = Utils.getSomeValue(parMap.get("sale"));
        String unit = Utils.getSomeValue(parMap.get("unit"));
        String origin = Utils.getSomeValue(parMap.get("origin"));
        String number = Utils.getSomeValue(parMap.get("number"));
        String isShow = Utils.getSomeValue(parMap.get("is_show"));
        String logisticsGoodsCode = Utils.getSomeValue(parMap.get("logistics_goods_code"));
        String imageStr = Utils.getSomeValue(parMap.get("img_arr"));

        if (StringUtils.isBlank(pidStr)) return result(null, "参数错误");

        Date date = new Date();
        List<StProductsImagesBO> imgList = null;
        if(StringUtils.isNotBlank(imageStr)) {
            imgList = JSONObject.parseObject(imageStr, new TypeReference<List<StProductsImagesBO>>() {
            });
            System.out.println(imgList.toString());
            if(imgList != null && imgList.size() > 0){
                for (int i = 0; i < imgList.size(); i++){
                    StProductsImagesBO productsImagesBO = imgList.get(i);
                    long id = productsImagesBO.getId();
                    if(id < 1) return result(null, "参数错误");
                    productsImagesBO.setModified(date);
                    productsImagesService.updateByPrimaryKeySelective(productsImagesBO);
                }
            }
        }

        long pid = Utils.parseLong(pidStr);
        if (pid < 1) return result(null, "参数错误");


        StProductsBO productsBO = productsService.selectByPrimaryKey((int) pid);
        if(productsBO == null) return result(null, "没有该商品信息");
        if (StringUtils.isNotBlank(productName)) productsBO.setProducts(productName);
        if (StringUtils.isNotBlank(intro)) productsBO.setIntro(intro);
        if (StringUtils.isNotBlank(price)) productsBO.setPrice(Utils.parseFloat(price));
        if (StringUtils.isNotBlank(sale)) productsBO.setSale(Utils.parseFloat(sale));
        if (StringUtils.isNotBlank(unit)) productsBO.setUnit(unit);
        if (StringUtils.isNotBlank(origin)) productsBO.setOrigin(origin);
        if (StringUtils.isNotBlank(number)) productsBO.setNumber(Utils.parseInt(number));
        if (StringUtils.isNotBlank(isShow)) productsBO.setIsShow(Utils.parseInt(isShow));
        if (StringUtils.isNotBlank(logisticsGoodsCode)) productsBO.setLogisticsGoodsCode(logisticsGoodsCode);
        productsBO.setModified(date);

        int productLow = productsService.updateByPrimaryKeySelective(productsBO);
        if (productLow < 1) result(null, "数据更新失败");

        return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
//        return result(Utils.getSuccessJson());
    }


}
