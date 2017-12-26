package com.company.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class XMLUtil {
    private static String path = "F:/contact.xml";
    public static Document  getDocument(){
        try{
            Document doc = new SAXReader().read(new File(XMLUtil.path));
            return doc;
        }catch (DocumentException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static void write2xml(Document doc){
        try{
            OutputStream out = new FileOutputStream(XMLUtil.path);
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(out,format);
            writer.write(doc);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


