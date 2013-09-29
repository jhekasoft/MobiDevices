package com.mobidevices.mobidevices;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
//import android.widget.LinearLayout;
import android.widget.ListView;
//import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.mobidevices.mobidevices.PostListViewAdapter;
import com.mobidevices.mobidevices.PostRowItem;
import java.util.List;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //System.out.println("dd");

        ListView listViewPosts = (ListView)findViewById(R.id.listViewPosts);
        List<PostRowItem> postRowItems;
        postRowItems = new ArrayList<PostRowItem>();

//        final ArrayList<String> posts = new ArrayList<String>();
//        final ArrayAdapter<String> adapter;
        PostListViewAdapter adapter = new PostListViewAdapter(this,
                R.layout.post_list_item, postRowItems);

        listViewPosts.setAdapter(adapter);

        try {
            URL url = new URL("http://mobidevices.ru/feed");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                Element fstElmnt = (Element) node;
                NodeList titleList = fstElmnt.getElementsByTagName("title");
                Element titleElement = (Element) titleList.item(0);
                titleList = titleElement.getChildNodes();

                PostRowItem item = new PostRowItem(0, ((Node)titleList.item(0)).getNodeValue(), "dd");
                postRowItems.add(item);

                //posts.add(((Node)titleList.item(0)).getNodeValue());

//                System.out.println("title = "
//                    + ((Node) nameList.item(0)).getNodeValue());
            }
        } catch (Exception e) {
            System.out.println("XML Pasing Excpetion = " + e);
        }

        //setContentView(layout);
        //setContentView(R.layout.main);
    }
}
