//
//import android.util.Log;
//
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserException;
//import org.xmlpull.v1.XmlPullParserFactory;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Search {
//
//    private static final String TAG = "NaverSearch";
//
//    private static final String BASE_URL = "http://openapi.naver.com/search?";
//    private static final String API_KEY = "9d62aa9c56b4ed922537ad43a5d29004";
//    private static final String[] TARGETS = new String[]{"local"};
//    private static final int START = 1;
//
//    private static final class PARAM {
//        private static final String API_KEY = "key=";   // always start with this parameter!!!
//        private static final String QUERY = "&query=";
//        private static final String TARGET = "&target=";
//        private static final String START = "&start=";
//        private static final String DISPLAY = "&display=";
//    }
//
//    private final List<String> resultDataList = new ArrayList<String>();
//
//    public /*List<String>*/String getResultDataList(final String query, final int resultSize) {
//        boolean inDesc = false;
//        String desc = null;
//        String tag = null;
//        resultDataList.clear();
//
//        for (String target : TARGETS) {
//            URL url = null;
//            try {
//                url = new URL(BASE_URL +
//                        PARAM.API_KEY + API_KEY +
//                        PARAM.QUERY + URLEncoder.encode(query, "UTF-8") +
//                        PARAM.TARGET + target +
//                        PARAM.START + START +
//                        PARAM.DISPLAY + resultSize);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                final XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
//                final XmlPullParser parser = parserCreator.newPullParser();
//
//                parser.setInput(url.openStream(), /*null*/"euc-kr");
//
//                int eventType = parser.getEventType();
//
//                Log.e(TAG, "NAVER Result START=====================================================");
//
//                while (eventType != XmlPullParser.END_DOCUMENT) {
//                    switch (eventType) {
//
//                        case XmlPullParser.START_DOCUMENT:
//
//                        case XmlPullParser.END_DOCUMENT:
//                            break;
//
//                        case XmlPullParser.START_TAG:
//                            if (parser.getName().equals("title")) {
//                                inDesc = true;
//                            }
//                            break;
//
//                        case XmlPullParser.TEXT:
//                            if (inDesc) {
//                                desc = parser.getText();
//                                inDesc = false;
//                                //analyzer.analyze(desc, mAnalyedList);
//                            }
//                            break;
//
//                        case XmlPullParser.END_TAG:
//                            break;
//                   }
//
//                    try {
//                        eventType = parser.next();
//                    } catch (XmlPullParserException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                Log.e(TAG, "NAVER Result END ==================================================================");
//            } catch (XmlPullParserException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return resultDataList;
//    }
//}
//
//   public String getResultDataList(final String query, final int resultSize){
//
//       String tag = null;
//       resultDataList.clear();
//                             //�˻� ���� ��ġ  1~1000
//
//       for (String target : TARGETS) {
//            URL url = null;
//            try {
//                url = new URL(BASE_URL +
//                        PARAM.API_KEY + API_KEY +
//                        PARAM.QUERY + URLEncoder.encode(query, "UTF-8") +
//                        PARAM.TARGET + target +
//                        PARAM.START + START +
//                        PARAM.DISPLAY + resultSize);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        try {
//            final XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
//                final XmlPullParser parser = parserCreator.newPullParser();
//
//                parser.setInput(url.openStream(), /*null*/"euc-kr");
//
//                int eventType = parser.getEventType();
//
//
//
//            while (eventType != XmlPullParser.END_DOCUMENT) {
//                    switch (eventType) {
//
//                        case XmlPullParser.START_DOCUMENT:
//                            break;
//
//
//                        case XmlPullParser.START_TAG:
//                            tag = parser.getName();
//
//                            if (tag.equals("item")) ;
//
//                                //resultDataList[0] : ���Ը�
//                            else if (tag.equals("title")) {
//                                parser.next();
//                                resultDataList.add(0, parser.getText());
//                            }
//
//                            //resultDataList[1] : ������ ����Ʈ
//                            else if (tag.equals("link")) {
//                                parser.next();
//                                resultDataList.add(1, parser.getText());
//                            }
//
//                            //resultDataList[2] : ������ �з�
//                            else if (tag.equals("category")) {
//                                parser.next();
//                                resultDataList.add(2, parser.getText());
//                            }
//
//                            //resultDataList[3] : ���� ����
//                            else if (tag.equals("description")) {
//                                parser.next();
//                                resultDataList.add(3, parser.getText());
//                            }
//
//                            //resultDataList[4] : ������ ��ȭ��ȣ
//                            else if (tag.equals("telephone")) {
//                                parser.next();
//                                resultDataList.add(4, parser.getText());
//                            }
//
//                            //resultDataList[5] : ������ �ּ�
//                            else if (tag.equals("adress")) {
//                                parser.next();
//                                resultDataList.add(5, parser.getText());
//                            }
//
//                            //resultDataList[6] : ������ ���θ��ּ�
//                            else if (tag.equals("roadadress")) {
//                                parser.next();
//                                resultDataList.add(6, parser.getText());
//                            }
//
//                            //resultDataList[7] : ������ ī����ǥ�� x��
//                            else if (tag.equals("mapx")) {
//                                parser.next();
//                                resultDataList.add(7, parser.getText());
//                            }
//
//                            //resultDataList[8] : ������ ī����ǥ�� y��
//                            else if (tag.equals("mapy")) {
//                                parser.next();
//                                resultDataList.add(8, parser.getText());
//                            }
//                            break;
//
//
//                        case XmlPullParser.TEXT:
//                            break;
//
//
//                        case XmlPullParser.END_TAG:
//                            tag = parser.getName();
//                            break;
//                    }
//                    eventType = parser.next();
//                }
//
//
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//
//        }//getXmlData method....
//
//        //return resultDataList; //StringBuffer ���ڿ� ��ü ��ȯ
//        String returnV =resultDataList.get(0)+"\n"+resultDataList.get(4)+"\n"+resultDataList.get(5)+"\n"+resultDataList.get(7)+"\n"+resultDataList.get(8);
//
//        return returnV;
//    }
//}




package com.example.songjiwon.navermap2;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Intent;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;


public class Search {

    private Context mContext;

    private static final String TAG = "NaverSearch";
    private static final String BASE_URL = "http://openapi.naver.com/search?";
    private static final String API_KEY = "9d62aa9c56b4ed922537ad43a5d29004";
    private static final String TARGET = "local";
    private static final int START = 1;
    private static final int DISPLAY = 10;

    private static final class PARAM {
        private static final String API_KEY = "key=";   // always start with this parameter!!!
        private static final String QUERY = "&query=";
        private static final String TARGET = "&target=";
        private static final String START = "&start=";
        private static final String DISPLAY = "&display=";
    }

    private Restaurant restaurant = new Restaurant();


    public Restaurant getRestaurantData(final String query) {
        boolean inDesc = false;
        String desc = null;

        URL url = null;
        try {
            url = new URL(BASE_URL +
                    PARAM.API_KEY + API_KEY +
                    PARAM.QUERY + URLEncoder.encode(query, "utf-8") +
                    PARAM.TARGET + TARGET  +
                    PARAM.START + START +
                    PARAM.DISPLAY + DISPLAY);

/******/   // url= new URL("http://openapi.naver.com/search?key=c1b406b32dbbbbeee5f2a36ddc14067f&query=������&target=local&start=1&display=10");
/***/            Log.i("���ټ���\t\turl : ",url.toString());
        } catch (MalformedURLException e) {
            /***/            Log.i("���ټ���\t\t �������? : ", "area0");
            e.printStackTrace();
            /***/            Log.i("���ټ���\t\t �������? : ", "area1");
        } catch (UnsupportedEncodingException e) {
            /***/            Log.i("���ټ���\t\t �������? : ", "area2");
            e.printStackTrace();
            /***/            Log.i("���ټ���\t\t �������? : ", "area3");
        }


        try {
            /***/            Log.i("���ټ���\t\t �������? : ", "area4");
            final XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            /***/            Log.i("���ټ���\t\t �������? : ", "area5");
            final XmlPullParser parser = parserCreator.newPullParser();
            /***/            Log.i("���ټ���\t\t �������? : ", "area6");
            InputStream A = url.openStream();
/***/            Log.i("���ټ���\t\t �������? : ", "area18");
            parser.setInput(/*url.openStream()*/A, "utf-8");

            /***/            Log.i("���ټ���\t\t �������? : ", "area7");

            int parserEvent = parser.getEventType();
            /***/            Log.i("���ټ���\t\t �������? : ", "area8");
            int tagIdentifier = -1;
            /***/            Log.i("���ټ���\t\t �������? : ", "area9");
            String tag;
            /***/            Log.i("���ټ���\t\t �������? : ", "area10");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                /***/            Log.i("���ټ���\t\t �������? : ", "area11");
                switch (parserEvent) {
                    case XmlPullParser.START_DOCUMENT:
                        //break;

                    case XmlPullParser.END_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        /***/            Log.i("���ټ���\t\t tag : ",parser.getName()+"tagIdentifier == "+ tagIdentifier);
                        /***/            Log.i("���ټ���\t\t tag : ",parser.getName()+"tag == "+ tag);




                        if (tag.equals("tittle")) {
                            tagIdentifier = 0;/***/            Log.i("���ټ���\t\ttitle : ","tagIdentifier = 0�� �ڸ�");
                        } else if (tag.equals("link")) {
                            tagIdentifier = 1;/***/            Log.i("���ټ���\t\tlink : ","tagIdentifier = 1�� �ڸ�");
                        } else if (tag.equals("category")) {
                            tagIdentifier = 2;/***/            Log.i("���ټ���\t\tcategory : ","tagIdentifier = 2�� �ڸ�");
                        } else if (tag.equals("description")) {
                            tagIdentifier = 3;/***/            Log.i("���ټ���\t\tdescription : ","tagIdentifier = 3�� �ڸ�");
                        } else if (tag.equals("telephone")) {
                            tagIdentifier = 4;/***/            Log.i("���ټ���\t\ttelephone : ","tagIdentifier = 4�� �ڸ�");
                        } else if (tag.equals("adress")) {
                            tagIdentifier = 5;/***/            Log.i("���ټ���\t\tadress : ","tagIdentifier = 5�� �ڸ�");
                        } else if (tag.equals("roadadress")) {
                            tagIdentifier = 6;/***/            Log.i("���ټ���\t\troadadress : ","tagIdentifier = 6�� �ڸ�");
                        } else if (tag.equals("mapx")) {
                            tagIdentifier = 7;/***/            Log.i("���ټ���\t\tmapx : ","tagIdentifier = 7�� �ڸ�");
                        } else if (tag.equals("mapy")) {
                            tagIdentifier = 8;/***/            Log.i("���ټ���\t\tmapy : ","tagIdentifier = 8�� �ڸ�");
                        }
                        /***/            Log.i("���ټ���\t\t������� �����̴�... : ","tagIdentifier = ?�� �ڸ�");
                        break;

                    case XmlPullParser.END_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        if (tagIdentifier == 0) {
                            restaurant.setTitle(parser.getText().trim());
                        } else if (tagIdentifier == 1) {
                            restaurant.setLink(parser.getText().trim());
/***/            Log.i("���ټ��� ��ũ\t\t : ", parser.getText().trim());
                        } else if (tagIdentifier == 2) {
                            restaurant.setCategory(parser.getText().trim());
                        } else if (tagIdentifier == 3) {
                            restaurant.setDescription(parser.getText().trim());
                        } else if (tagIdentifier == 4) {
                            restaurant.setTelephone(parser.getText().trim());
/***/            Log.i("���ټ��� ������ȣ\t\t : ", parser.getText().trim());
                        } else if (tagIdentifier == 5) {
                            restaurant.setAdress(parser.getText().trim());
                        } else if (tagIdentifier == 6) {
                            restaurant.setRoadadress(parser.getText().trim());
                        } else if (tagIdentifier == 7) {
                            restaurant.setMapx(Integer.parseInt(parser.getText()));
                        } else if (tagIdentifier == 8) {
                            restaurant.setMapy(Integer.parseInt(parser.getText()));
                        }


                        tagIdentifier = -1;
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            //throw new RuntimeException(e);
            //Context context = getApplicationContext();
            //Toast.makeText(, e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.print(e.getMessage());
        }

        return restaurant;
    }
}


