package com.czxy.service;

import com.alibaba.fastjson.JSON;
import com.czxy.dao.*;
import com.czxy.pojo.group.Sku;
import com.czxy.pojo.group.SkuPhoto;
import com.czxy.pojo.group.Specification;
import com.czxy.pojo.group.Spu;
import com.czxy.pojo.vo.ESData;
import com.czxy.pojo.vo.OneSkuResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SkuService {
    @Autowired
    private SkuMapper skuMapper;
    
    @Autowired
    private SkuCommentMapper skuCommentMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SkuPhotoMapper skuPhotoMapper;

    @Autowired
    private SpecificationMapper specificationMapper;


    /**
     * 通过skuid查找sku （商品详情的实现）
     * @param skuId
     * @return
     */
    public OneSkuResult findSkuByskuId(Integer skuId){

        //1.创建返回的数据
        OneSkuResult result = new OneSkuResult();
        //2.查询sku信息
        Sku sku = skuMapper.selectByPrimaryKey(skuId);
        //3.查询spu信息
        Spu spu = spuMapper.selectByPrimaryKey(sku.getSpu_id());
        //4.skuid
        result.setSkuid(sku.getId());
        //5.spuid
        result.setSpuid(sku.getSpu_id());
        //6.goods_name
        result.setGoods_name(sku.getSku_name());
        //7.price
        result.setPrice(sku.getPrice());
        //8.one_sale_data
        result.setOn_sale_date(spu.getOn_sale_time());

        //9.评论数量
        Integer commentcount = skuCommentMapper.findNumBySkuId(spu.getId());
        result.setComment_count(commentcount);

        //10.评论级别
        Double start = skuCommentMapper.findAvgStarlBySpuId(spu.getId());
        result.setComment_level(start);

        //11.分类信息 （面包屑效果）
        result.setCat1_info(categoryMapper.selectByPrimaryKey(spu.getCat1_id()));
        result.setCat2_info(categoryMapper.selectByPrimaryKey(spu.getCat2_id()));
        result.setCat3_info(categoryMapper.selectByPrimaryKey(spu.getCat3_id()));

        //12.首图
        HashMap<String, String> logo = new HashMap<>();
        System.out.println("----"+sku.getImages());
        logo.put("smlogo",sku.getImages());
        logo.put("biglogo",sku.getImages());
        logo.put("xbiglogo",sku.getImages());
         result.setLogo(logo);

        //13.商品图片
        List<SkuPhoto> photos = skuPhotoMapper.findSkuPhotoByskuId(sku.getId());
        ArrayList<Map> photoList = new ArrayList<>();
        for (SkuPhoto sp:photos) {
            HashMap<String, String> map = new HashMap<>();
            map.put("smimg",sp.getUrl());
            map.put("bigimg",sp.getUrl());
            map.put("xbigimg",sp.getUrl());
            photoList.add(map);
        }
        //将photoList 插入result
        result.setPhotos(photoList);

        //14.商品描述
        result.setDescription(spu.getDescription());
        //15.售后
        result.setAftersale(spu.getAftersale());
        //16.库存量
        result.setStock(sku.getStock());
        //17.规格和规格选项---通过什么来查找规格和规格选项
        //17.1计算出所有的规格id 1:1|2:6|6:22
        String[] spec_list_array = sku.getSpec_list().split("\\|");
        //17.2 获取规格1 2 6
        ArrayList<Integer> idList = new ArrayList<>();
        for (String s:spec_list_array) {
            String[] ids = s.split(":");
            idList.add(Integer.parseInt(ids[0]));
        }
        //17.3通过id获取规格选项
        ArrayList<Specification> list = new ArrayList<>();
        for (Integer id:idList) {
            Specification specification = specificationMapper.findSpecificationBySpecid(id);
            list.add(specification);
        }
        //设置result
        result.setSpec_list(list);

        //18.当前sku的排雷组合
        HashMap<String, String> spec_info= new HashMap<>();
        spec_info.put("id_list",sku.getSpec_list());
        spec_info.put("id__text",sku.getSpec_list_code().replace("{","").replace("}",""));
        result.setSpec_info(spec_info);

        //19.所有的skua--通过skuid--spu--spu下的所有skus
        //根据skuid查找出所有的skus
        List<Sku> skuList = skuMapper.findSkuListBySpuId(spu.getId());
        //准备集合
        ArrayList<Map> sku_id_map = new ArrayList<>();
        for (Sku s:skuList) {
            HashMap<Object, Object> map = new HashMap<>();
            map.put("skuid",s.getId());
            map.put("id_list",s.getSpec_list().replace("{","").replace("}",""));
            sku_id_map.add(map);
        }
        result.setSku_list(sku_id_map);


        //返回结果
        return  result;
    }



    /**
     * 准备ESData的数据
     * @return
     */
    public List<ESData> findESData(){
        List<Sku> skulist = skuMapper.findAllSkus();
        List<ESData> esDataList = new ArrayList<>();
        for (Sku sku:skulist){
            ESData esData = new ESData();
            // id
            esData.setId(sku.getId());
            // logo
            esData.setLogo(sku.getImages());
            // sku_name
            esData.setSku_name(sku.getSku_name());
            // all
            esData.setAll(sku.getSku_name()+"   " + sku.getSpec_list_code() + "   " +sku.getSpu().getBrand().getBrand_name());
            // on_sale_time
            esData.setOn_sale_time(sku.getSpu().getOn_sale_time());
            // brand_id
            esData.setBrand_id(sku.getSpu().getBrand().getId());
            // cat_id
            esData.setCatid(sku.getSpu().getCat3_id());
            //  Map<String, Object> specs;// 可搜索的规格参数，key是参数名，值是参数值
            Map specs = JSON.parseObject(sku.getSpec_list_code(), Map.class);
            esData.setSpecs(specs);
            // price 价格
            esData.setPrice(sku.getPrice());
            // spu_name
            esData.setSpu_name(sku.getSpu().getSpu_name());
            // stock 库存
            esData.setStock(sku.getStock());
            // description
            esData.setDescription(sku.getSpu().getDescription());
            // packages;//规格与包装
            esData.setPackages(sku.getSpu().getPackages());
            // aftersale;//售后保障
            esData.setAftersale(sku.getSpu().getAftersale());
            // midlogo;
            //esData.setMidlogo(sku.getSpu().getLogo());
            // comment_count; 评价数
            Integer comment_count = skuCommentMapper.findNumBySkuId(sku.getId());
            esData.setComment_count(comment_count);
            Integer count = orderMapper.findCountBySkuid(sku.getId());
            esData.setSeller_count(count);
            esDataList.add(esData);
        }
        return esDataList;
    }
}