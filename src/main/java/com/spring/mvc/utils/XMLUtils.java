package com.spring.mvc.utils;

import com.spring.mvc.model.area.AddressModel;
import com.spring.mvc.model.area.CityModel;
import com.spring.mvc.model.area.CountyModel;
import com.spring.mvc.model.area.ProvinceModel;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * get java bean from xml file or xmlString
 * Created by liluoqi on 15/7/31.
 */
public class XMLUtils {

    private static File file = new File("/Users/liluoqi/Documents/workspace/area.xml");
    private static XStream xStream = null;

    private static <T> XStream getSingleXStream(Class<T> clazz) {
        if (xStream == null) {
            xStream = new XStream();
            xStream.autodetectAnnotations(true);
            xStream.processAnnotations(clazz);
        }
        return xStream;
    }

    /**
     * @param file
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertFromXML(File file, Class<T> clazz) {
        try {
            return (T) getSingleXStream(clazz).fromXML(file);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertFromXML(Class<T> clazz) {
        try {
            return (T) getSingleXStream(clazz).fromXML(file);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param xmlString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertFromXML(String xmlString, Class<T> clazz) {
        try {
            return (T) getSingleXStream(clazz).fromXML(xmlString);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
//        AddressModel address = convertFromXML(file, AddressModel.class);
//        if (address != null) {
//            if (address.getProvince() != null) {
//                for (ProvinceModel province : address.getProvince()) {
//                    System.out.println("province:" + province.getName());
//                    if (province.getCity() != null) {
//                        for (CityModel city : province.getCity()) {
//                            System.out.println("city:" + city.getName());
//                            if (city.getCounty() != null) {
//                                for (CountyModel county : city.getCounty()) {
//                                    System.out.println("county:" + county.getName());
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
        List<String> list=new ArrayList<String>();
        list.add("北京辖区");
        System.out.println(list.contains("市辖区"));
    }
}
