package app.mallusolutions.myxmlparser.com;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static java.lang.System.in;

/**
 * Created by Phantom on 25-10-2016.
 */
public class XMLParser {
    private static final String ns = null;

    public String getXmlFromUrl(String url) {
        String xml = null;

        HttpURLConnection urlConnection = null;
        try {
            URL urlc = new URL(url);
            urlConnection = (HttpURLConnection) urlc.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            xml = readStream(in);
            System.out.println(xml);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            urlConnection.disconnect();
        }
        return xml;
    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    public Document getDomElement(String xml) {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }
        // return DOM
        return doc;
    }

    public String getValue(Element item, String str) {
        NodeList n = item.getElementsByTagName(str);
        return this.getElementValue(n.item(0));
    }

    public final String getElementValue(Node elem) {
        Node child;
        if (elem != null) {
            if (elem.hasChildNodes()) {
                for (child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                    if (child.getNodeType() == Node.TEXT_NODE) {
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    public ArrayList<HashMap<String,String>> getEventsFromAnXML(Activity activity)
            throws XmlPullParserException, IOException {
        Resources res = activity.getResources();
        XmlResourceParser xpp = res.getXml(R.xml.list_detaildata);
        xpp.next();

        return readFeed(xpp);
    }

    private ArrayList<HashMap<String,String>> readFeed(XmlResourceParser xpp) throws IOException, XmlPullParserException {
        //List<songs> songsList = new ArrayList<songs>();
        ArrayList<HashMap<String,String>> songList = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> map = new HashMap<String,String>();
        int eventType = xpp.getEventType();
        String title = "", artist = "", URL = "", id = "";
        while (eventType != XmlPullParser.END_DOCUMENT) {

             if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("id")) {
                        id = readText(xpp);
                        map.put(MainActivity.KEY_ID,id);
                    }
                    if (xpp.getName().equals("title")) {
                        title = readText(xpp);
                        map.put(MainActivity.KEY_TITLE,title);
                    }
                    if (xpp.getName().equals("artist")) {
                        artist = readText(xpp);
                        map.put(MainActivity.KEY_ARTIST,artist);
                    }
                    if (xpp.getName().equals("thumb_url")) {
                        URL = readText(xpp);
                        map.put(MainActivity.KEY_THUMB_URL,URL);
                    }
            } else if (eventType == XmlPullParser.END_TAG) {
                 if (xpp.getName().equals("song")) {
                     songList.add(map);
                     map = new HashMap<String,String>();
                 }
             }

            eventType = xpp.next();
        }
        return songList;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}
