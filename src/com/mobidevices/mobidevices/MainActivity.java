package com.mobidevices.mobidevices;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /** Create a new layout to display the view */
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);

        /** Create a new textview array to display the results */
        TextView title[];

        System.out.println("dd");

        try {
            URL url = new URL("http://mobidevices.ru/feed");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");

            /** Assign textview array lenght by arraylist size */
            title = new TextView[nodeList.getLength()];

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                title[i] = new TextView(this);

                Element fstElmnt = (Element) node;
                NodeList nameList = fstElmnt.getElementsByTagName("title");
                Element nameElement = (Element) nameList.item(0);
                nameList = nameElement.getChildNodes();
                title[i].setText("title = "
                    + ((Node) nameList.item(0)).getNodeValue());

                layout.addView(title[i]);
            }
        } catch (Exception e) {
            System.out.println("XML Pasing Excpetion = " + e);
        }

        setContentView(layout);
        //setContentView(R.layout.main);
    }
}
