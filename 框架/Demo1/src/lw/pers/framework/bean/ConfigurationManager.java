package lw.pers.framework.bean;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置管理类
 * 读取mystruts.xml文件信息,然后封装到javabean中
 */
public class ConfigurationManager {
    private Map<String,ActionMapping> actionMappingMaps = new HashMap<>();
    public Map<String, ActionMapping> getActionMappingMaps() {
        return actionMappingMaps;
    }
    public ConfigurationManager(){
        actionMappingMaps = new HashMap<String,ActionMapping>();
        this.init();
    }
    public void init(){
        //使用类路径的方式去读取mystruts.xml
        try {
            InputStream in = ConfigurationManager.class.getResourceAsStream("/mystruts.xml");
            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);

            //读取所有的action标签
            List<Element> actionList = doc.selectNodes("//action");
            for(Element acElem : actionList){
                ActionMapping actionMapping = new ActionMapping();
                //封装actionMapping
                actionMapping.setName(acElem.attributeValue("name"));
                actionMapping.setClassName(acElem.attributeValue("class"));
                if(acElem.attributeValue("method")==null){
                    //默认值 execute
                    actionMapping.setMethod("execute");
                }else{
                    actionMapping.setMethod(acElem.attributeValue("method"));
                }
                //封装Result,elements获取所有子元素
                List<Element> resultList = acElem.elements("result");
                Map<String,Result> rsMap = new HashMap<>();
                for(Element rsElem : resultList){
                    Result rs = new Result();
                    rs.setName(rsElem.attributeValue("name"));
                    if(rsElem.attributeValue("type")==null){
                        rs.setType("dispatcher");
                    }else{
                        rs.setType(rsElem.attributeValue("type"));
                    }
                    rs.setPage(rsElem.getText().trim());
                    rsMap.put(rs.getName(),rs);
                }
                //把封装好的Result集合放入actionMapping中
                actionMapping.setResultMap(rsMap);

                //把封装好的actionMapping放入 actionMappingMaps中
                this.actionMappingMaps.put(actionMapping.getName(),actionMapping);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
