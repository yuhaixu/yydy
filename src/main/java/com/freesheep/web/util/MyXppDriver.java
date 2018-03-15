package com.freesheep.web.util;

import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

public class MyXppDriver extends XppDriver {

    boolean useCDATA = false;

    MyXppDriver(boolean useCDATA) {
        super(new XmlFriendlyNameCoder("__", "_"));
        this.useCDATA = useCDATA;
    }

    @Override
    public HierarchicalStreamWriter createWriter(Writer out) {
        if (!useCDATA) {
            return super.createWriter(out);
        }
        return new PrettyPrintWriter(out) {
            boolean cdata = true;

            @Override
            public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
                super.startNode(name, clazz);
            }

            @Override
            protected void writeText(QuickWriter writer, String text) {
                if (cdata) {
                    writer.write(cDATA(text));
                } else {
                    writer.write(text);
                }
            }

            private String cDATA(String text) {
                return "<![CDATA[" + text + "]]>";
            }
        };
    }
}
