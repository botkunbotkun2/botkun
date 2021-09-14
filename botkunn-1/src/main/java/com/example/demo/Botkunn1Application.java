package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.maps.GeoApiContext;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.message.VideoMessageContent;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.json.JSONArray;
import org.json.JSONObject;

@SpringBootApplication
@LineMessageHandler
public class Botkunn1Application {
    public static void main(String[] args) {
        SpringApplication.run(Botkunn1Application.class, args);
    }
  
    
   
    @EventMapping
    public Message handleLocationMessageEvent(MessageEvent< TextMessageContent> event){
    	if (event.getMessage().getText().contains("東京")||
    			event.getMessage().getText().equals("渋谷")||event.getMessage().getText().equals("秋葉原")||
    			event.getMessage().getText().equals("鎌倉")||event.getMessage().getText().equals("上野")||
    			event.getMessage().getText().equals("新宿")||event.getMessage().getText().equals("池袋")||
    			event.getMessage().getText().equals("目黒")||event.getMessage().getText().equals("大阪")||
    			event.getMessage().getText().equals("名古屋")||event.getMessage().getText().equals("六本木")
    			||event.getMessage().getText().equals("札幌")||event.getMessage().getText().equals("福岡")
    			||event.getMessage().getText().equals("京都")||event.getMessage().getText().equals("那覇")
    			||event.getMessage().getText().equals("横浜")||event.getMessage().getText().equals("神戸")
    			||event.getMessage().getText().equals("広島")||event.getMessage().getText().equals("仙台")
    			||event.getMessage().getText().equals("千葉")||event.getMessage().getText().equals("さいたま")
    			||event.getMessage().getText().equals("浜松")||event.getMessage().getText().equals("熊本")
    					
    			
    	){
             GeoApiContext context = new GeoApiContext.Builder()
           .apiKey("AIzaSyD3_BThExaXsXlEeGmtxMWHH4Iq0n3Pq1M").build();
             String result = "";
             String tmp = "";

             try{
            	 URL url = new URL("https://map.yahooapis.jp/geocode/V1/geoCoder?appid=dj00aiZpPWU1aWNHT2ZNWFRsYiZzPWNvbnN1bWVyc2VjcmV0Jng9YjE-&query="+event.getMessage().getText()+"&results=1&output=json");
             
             HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
             con.connect();
             BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
              while((tmp = in.readLine())!=null) {
                             result += tmp;
                     }
                     in.close();
                     con.disconnect();
             } catch (IOException e) {
                     e.printStackTrace();
             }
          
             JSONObject json1 = new JSONObject(result);
             if(json1.getJSONArray("Feature")==null) {
            	 return new TextMessage("存在する市の名前でお願いしますっ!!");
             			
             } else {
             JSONArray jsonList = json1.getJSONArray("Feature"); 
             JSONObject jsonNo = jsonList.getJSONObject(0);
        
      String title = jsonNo.getString("Name");
      String adress = jsonNo.getJSONObject("Property").getString("Address");
    String[]  coods = jsonNo.getJSONObject("Geometry").getString("Coordinates").split(",");
    double latitude = Double.valueOf(coods[1]);
    double longitude = Double.valueOf(coods[0]);
      
        	
        	return new LocationMessage(
        				title,
        				 adress,
        				  latitude,
        			   longitude	  
     );
         }}

        if(event.getMessage().getText().contains("カフェ")) {
     	  
        	 GeoApiContext context = new GeoApiContext.Builder()
        	           .apiKey("AIzaSyD3_BThExaXsXlEeGmtxMWHH4Iq0n3Pq1M").build();
        	             String result = "";
        	             String tmp = "";

String placename = event.getMessage().getText().substring(3);
        	             try{
        	            	 URL url = new URL("https://map.yahooapis.jp/geocode/V1/geoCoder?appid=dj00aiZpPWU1aWNHT2ZNWFRsYiZzPWNvbnN1bWVyc2VjcmV0Jng9YjE-&query="+placename+"&results=1&output=json");
        	             
        	             HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
        	             con.connect();
        	             BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        	              while((tmp = in.readLine())!=null) {
        	                             result += tmp;
        	                     }
        	                     in.close();
        	                     con.disconnect();
        	             } catch (IOException e) {
        	                     e.printStackTrace();
        	             }
        	             JSONObject json1 = new JSONObject(result);
        	             if(json1.getJSONArray("Feature")==null) {
        	            	 return new TextMessage("存在する市の名前でお願いしますっ!!");
        	             			
        	             } else {
        	             JSONArray jsonList = json1.getJSONArray("Feature"); 
        	             JSONObject jsonNo = jsonList.getJSONObject(0);
        	    String[]  coods = jsonNo.getJSONObject("Geometry").getString("Coordinates").split(",");
        	    double latitude = Double.valueOf(coods[1]);
        	    double longitude = Double.valueOf(coods[0]);
        	    String result2 = "";
	             String tmp2 = "";            
        	    try{
        	   	 URL url2 = new URL("https://map.yahooapis.jp/search/local/V1/localSearch?appid=dj00aiZpPWU1aWNHT2ZNWFRsYiZzPWNvbnN1bWVyc2VjcmV0Jng9YjE-&dist=1&gc=0115001&results=5&lat="+ latitude+ "&lon="+ longitude+ "&dist=3&sort=hybrid&output=json");
        	    
        	    HttpURLConnection con2 = (HttpURLConnection) url2.openConnection(); 
        	    con2.connect();
        	    

        	    BufferedReader in2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
        	     while((tmp2 = in2.readLine())!=null) {
        	                    result2 += tmp2;
        	            }
        	            in2.close();
        	            con2.disconnect();
        	    } catch (IOException e) {
        	            e.printStackTrace();
        	    }
        	    
        	    JSONObject json2 = new JSONObject(result2);
        	    JSONArray jsonList2 = json2.getJSONArray("Feature"); 
        	    
        	    
        	   JSONObject jsonNo2 = jsonList2.getJSONObject(0);
        	   String name = jsonNo2.getString("Name");
        	   String address = jsonNo2.getJSONObject("Property").getString("Address");
        	
        	   JSONObject jsonNo3 = jsonList2.getJSONObject(1);
        	   String name2 = jsonNo3.getString("Name");
        	   String address2 = jsonNo3.getJSONObject("Property").getString("Address");
        	
        	   JSONObject jsonNo4 = jsonList2.getJSONObject(2);
        	   String name3 = jsonNo4.getString("Name");
        	   String address3 = jsonNo4.getJSONObject("Property").getString("Address");
        	

        	   JSONObject jsonNo5 = jsonList2.getJSONObject(3);
        	   String name4 = jsonNo5.getString("Name");
        	   String address4 = jsonNo5.getJSONObject("Property").getString("Address");
        	 
        	   JSONObject jsonNo6 = jsonList2.getJSONObject(4);
        	   String name5 = jsonNo6.getString("Name");
        	   String address5 = jsonNo6.getJSONObject("Property").getString("Address");
        	 
        	    return new TextMessage(placename+"の人気なCafe☕️"+"\r\n"
        	    		+ "（ここだよん😙）"+"\r\n"
        	    +"【1位】"+"\r\n"+"店名: "+name+"\r\n"+
        	    	"住所: " +address+"\r\n"
        	   		+"\r\n"
        	   		
        	   		+"【2位】"+"\r\n"+"店名: "+name2+"\r\n"
        	   		
        	   		+"住所: " +address2+"\r\n"+"\r\n"
        	   		+"【3位】"+"\r\n"+"店名: "+name3+"\r\n"
        	   		
        	   		+"住所: " +address3+"\r\n"
        	   		+"\r\n"
        	   		+"【4位】"+"\r\n"+"店名: "+name4+"\r\n"
        	   		
        	   		+"住所: " +address4+"\r\n"
        	   		+"\r\n"
        	   		+"【5位】"+"\r\n"+"店名: "+name5+"\r\n"
        	   		
        	   		+"住所: " +address5+"\r\n"
        	   		
        	   		 );
        	    
        	      }
        }
        if(event.getMessage().getText().contains("ラーメン")) {
       	  
       	 GeoApiContext contextra = new GeoApiContext.Builder()
       	           .apiKey("AIzaSyD3_BThExaXsXlEeGmtxMWHH4Iq0n3Pq1M").build();
       	             String resultra = "";
       	             String tmpra = "";

String placenamera = event.getMessage().getText().substring(4);
       	             try{
       	            	 URL urlra = new URL("https://map.yahooapis.jp/geocode/V1/geoCoder?appid=dj00aiZpPWU1aWNHT2ZNWFRsYiZzPWNvbnN1bWVyc2VjcmV0Jng9YjE-&query="+placenamera+"&results=1&output=json");
       	             
       	             HttpURLConnection conra = (HttpURLConnection) urlra.openConnection(); 
       	             conra.connect();
       	             BufferedReader inra = new BufferedReader(new InputStreamReader(conra.getInputStream()));
       	              while((tmpra = inra.readLine())!=null) {
       	                             resultra += tmpra;
       	                     }
       	                     inra.close();
       	                     conra.disconnect();
       	             } catch (IOException e) {
       	                     e.printStackTrace();
       	             }
       	             JSONObject json1ra = new JSONObject(resultra);
       	         
       	             JSONArray jsonListra = json1ra.getJSONArray("Feature"); 
       	             JSONObject jsonNora = jsonListra.getJSONObject(0);
       	        
       	     // String title = jsonNo.getString("Name");
       	    //  String adress = jsonNo.getJSONObject("Property").getString("Address");
       	    String[]  coods = jsonNora.getJSONObject("Geometry").getString("Coordinates").split(",");
       	    double latitudera = Double.valueOf(coods[1]);
       	    double longitudera = Double.valueOf(coods[0]);
       	    String result2ra = "";
	             String tmp2ra = "";            
       	    try{
       	   	 URL url2ra = new URL("https://map.yahooapis.jp/search/local/V1/localSearch?appid=dj00aiZpPWU1aWNHT2ZNWFRsYiZzPWNvbnN1bWVyc2VjcmV0Jng9YjE-&dist=1&gc=0106001&results=5&lat="+ latitudera+ "&lon="+ longitudera+ "&dist=3&sort=hybrid&output=json");
       	    
       	    HttpURLConnection con2ra = (HttpURLConnection) url2ra.openConnection(); 
       	    con2ra.connect();
       	    

       	    BufferedReader in2ra = new BufferedReader(new InputStreamReader(con2ra.getInputStream()));
       	     while((tmp2ra = in2ra.readLine())!=null) {
       	                    result2ra += tmp2ra;
       	            }
       	            in2ra.close();
       	            con2ra.disconnect();
       	    } catch (IOException e) {
       	            e.printStackTrace();
       	    }
       	    
       	    JSONObject json2ra = new JSONObject(result2ra);
       	    JSONArray jsonList2ra = json2ra.getJSONArray("Feature"); 
       	    
       	    
       	   JSONObject jsonNo2ra = jsonList2ra.getJSONObject(0);
       	   String namera = jsonNo2ra.getString("Name");
       	   String addressra = jsonNo2ra.getJSONObject("Property").getString("Address");
       	//  String url3 = "https://www.google.co.jp/search?q=" + name + "&ie=UTF-8";
       		 
       	 //  String Genre = jsonNo2.getJSONObject("Property").getJSONArray("Genre").getJSONObject(0).getString("Name");

       	   JSONObject jsonNo3ra = jsonList2ra.getJSONObject(1);
       	   String name2ra = jsonNo3ra.getString("Name");
       	   String address2ra = jsonNo3ra.getJSONObject("Property").getString("Address");
       	 //  String Genre2 = jsonNo3.getJSONObject("Property").getJSONArray("Genre").getJSONObject(0).getString("Name");

       	   JSONObject jsonNo4ra = jsonList2ra.getJSONObject(2);
       	   String name3ra = jsonNo4ra.getString("Name");
       	   String address3ra = jsonNo4ra.getJSONObject("Property").getString("Address");
       	//   String Genre3 = jsonNo4.getJSONObject("Property").getJSONArray("Genre").getJSONObject(0).getString("Name");

       	   JSONObject jsonNo5ra = jsonList2ra.getJSONObject(3);
       	   String name4ra = jsonNo5ra.getString("Name");
       	   String address4ra = jsonNo5ra.getJSONObject("Property").getString("Address");
       	  // String Genre4 = jsonNo5.getJSONObject("Property").getJSONArray("Genre").getJSONObject(0).getString("Name");

       	   JSONObject jsonNo6ra = jsonList2ra.getJSONObject(4);
       	   String name5ra = jsonNo6ra.getString("Name");
       	   String address5ra = jsonNo6ra.getJSONObject("Property").getString("Address");
       	  // String Genre5 =jsonNo6.getJSONObject("Property").getJSONArray("Genre").getJSONObject(0).getString("Name");


       	    return new TextMessage(placenamera+"の人気なラーメン🍜️"+"\r\n"
       	    		+ "（ここだよん😙）"+"\r\n"
       	    +"【1位】"+"\r\n"+"店名: "+namera+"\r\n"+
       	    	"住所: " +addressra+"\r\n"
       	   		+"\r\n"
       	   		
       	   		+"【2位】"+"\r\n"+"店名: "+name2ra+"\r\n"
       	   		
       	   		+"住所: " +address2ra+"\r\n"+"\r\n"
       	   		+"【3位】"+"\r\n"+"店名: "+name3ra+"\r\n"
       	   		
       	   		+"住所: " +address3ra+"\r\n"
       	   		+"\r\n"
       	   		+"【4位】"+"\r\n"+"店名: "+name4ra+"\r\n"
       	   		
       	   		+"住所: " +address4ra+"\r\n"
       	   		+"\r\n"
       	   		+"【5位】"+"\r\n"+"店名: "+name5ra+"\r\n"
       	   		
       	   		+"住所: " +address5ra+"\r\n"
       	   		
       	   		
       	   		
       	   		 );
       	    
       	      }
         
        	
        	
        	
        	
        	
        	
        	
        	
        
       
       
       if(event.getMessage().getText().contains("おはよ")) {
    	   return new TextMessage("おはよ！"
    	   		+ "今日もいい日になるよ！");
       
      }
       if(event.getMessage().getText().contains("おやすみ")) {
    	   return new TextMessage("ゆっくり休んでねぇ😙");
       
      }
       if(event.getMessage().getText().contains("こんにちは")) {
    	   return new TextMessage("どうもどうも🤤");
       
      }if(event.getMessage().getText().contains("の？")) {
   	   return new TextMessage("う〜〜ん");
       
      } if(event.getMessage().getText().contains("い？")) {
    	   return new TextMessage("うん😆！");
           
     } if(event.getMessage().getText().contains("な？")) {
   	   return new TextMessage("どうかなあ？");
       
     }  if(event.getMessage().getText().contains("つかれ")) {
  	   return new TextMessage("おつかれさまです😭");
       
    }  if(event.getMessage().getText().contains("疲れ")) {
 	   return new TextMessage("おつかれ😩");
       
   }if(event.getMessage().getText().contains("ねむ")) {
 	   return new TextMessage("寝よう。");
       
   } if(event.getMessage().getText().contains("いいよ")) {
	   return new TextMessage("ありがとう！");
       
} if(event.getMessage().getText().contains("おめでとう")) {
	   return new TextMessage("ありがとう！");
       
} if(event.getMessage().getText().contains("すき")) {
	   return new TextMessage("ありがとう🤗");
       
}if(event.getMessage().getText().contains("笑")) {
	   return new TextMessage("笑ってるwwwwwwwwwwww");
       
} if(event.getMessage().getText().contains("ww")) {
	   return new TextMessage("楽しいねwwww");
       
} if(event.getMessage().getText().contains("大丈夫？")) {
	   return new TextMessage("うん！！");
       
}if(event.getMessage().getText().contains("ぴえん")) {
	   return new TextMessage("ぴえんぴえん！って言ってる人アホと思うな🥺");
       
} if(event.getMessage().getText().contains("それな")) {
	   return new TextMessage("それよ");
       
}if(event.getMessage().getText().contains("おけ")) {
	   return new TextMessage("👍");
       
}if(event.getMessage().getText().contains("好き")) {
	   return new TextMessage("ぼっとくん。も🤤");
       
}if(event.getMessage().getText().contains("ぼっと")) {
	   return new TextMessage("よんだぁ？🤤");
       
	   

	   }
	   
	else{
    	
    System.out.println("event: " + event);
       return new TextMessage(
    		   event.getMessage().getText());
   }
}

    
		@EventMapping
    public Message handleStickerMessage(MessageEvent<StickerMessageContent> event) {
        return new TextMessage("スタンプだ！！");
    }
    @EventMapping
    public Message handleImageMessage(MessageEvent<ImageMessageContent> event) {
        return new TextMessage("これは何の画像？");
    }
    @EventMapping
    public Message handleVideoMessage(MessageEvent<VideoMessageContent> event) {
        return new TextMessage("動画送ってくれたのね！");
    }
    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
    
    @EventMapping

    public  TextMessage handleTextMessageEvent2(MessageEvent< LocationMessageContent> event) throws IOException{
   		 
    	
    	
    	
      GeoApiContext context = new GeoApiContext.Builder()
    .apiKey("AIzaSyD3_BThExaXsXlEeGmtxMWHH4Iq0n3Pq1M").build();
   double latitude = event.getMessage().getLatitude();
 double longitude = event.getMessage().getLongitude();
 
 String result = "";
 String tmp = "";

 
 try{
	 URL url = new URL("https://map.yahooapis.jp/search/local/V1/localSearch?appid=dj00aiZpPWU1aWNHT2ZNWFRsYiZzPWNvbnN1bWVyc2VjcmV0Jng9YjE-&dist=1&gc=01&results=5&lat="+ latitude+ "&lon="+ longitude+ "&dist=3&sort=hybrid&output=json");
 
 HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
 con.connect();
 

 BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
  while((tmp = in.readLine())!=null) {
                 result += tmp;
         }
         in.close();
         con.disconnect();
 } catch (IOException e) {
         e.printStackTrace();
 }
 
 JSONObject json1 = new JSONObject(result);
 JSONArray jsonList = json1.getJSONArray("Feature"); 
 JSONObject jsonNo = jsonList.getJSONObject(0);
String name = jsonNo.getString("Name");
String address = jsonNo.getJSONObject("Property").getString("Address");
String Genre = jsonNo.getJSONObject("Property").getJSONArray("Genre").getJSONObject(0).getString("Name");

JSONObject jsonNo2 = jsonList.getJSONObject(1);
String name2 = jsonNo2.getString("Name");
String address2 = jsonNo2.getJSONObject("Property").getString("Address");
String Genre2 = jsonNo2.getJSONObject("Property").getJSONArray("Genre").getJSONObject(0).getString("Name");

JSONObject jsonNo3 = jsonList.getJSONObject(2);
String name3 = jsonNo3.getString("Name");
String address3 = jsonNo3.getJSONObject("Property").getString("Address");
String Genre3 = jsonNo3.getJSONObject("Property").getJSONArray("Genre").getJSONObject(0).getString("Name");

JSONObject jsonNo4 = jsonList.getJSONObject(3);
String name4 = jsonNo4.getString("Name");
String address4 = jsonNo4.getJSONObject("Property").getString("Address");
String Genre4 = jsonNo4.getJSONObject("Property").getJSONArray("Genre").getJSONObject(0).getString("Name");

JSONObject jsonNo5 = jsonList.getJSONObject(4);
String name5 = jsonNo5.getString("Name");
String address5 = jsonNo5.getJSONObject("Property").getString("Address");
String Genre5 = jsonNo5.getJSONObject("Property").getJSONArray("Genre").getJSONObject(0).getString("Name");


 return new TextMessage("近くの人気な飲食店🍽"+"\r\n"
 		+ "（口コミ、評価順の高い順に教えちゃうよお😙）"+"\r\n"
 +"【1位】"+"\r\n"+"店名: "+name+"\r\n"
		+"ジャンル: "+Genre+"\r\n"
		+"住所: " +address+"\r\n"+"\r\n"
		+"【2位】"+"\r\n"+"店名: "+name2+"\r\n"
		+"ジャンル: "+Genre2+"\r\n"
		+"住所: " +address2+"\r\n"+"\r\n"
		+"【3位】"+"\r\n"+"店名: "+name3+"\r\n"
		+"ジャンル: "+Genre3+"\r\n"
		+"住所: " +address3+"\r\n"
		+"\r\n"
		+"【4位】"+"\r\n"+"店名: "+name4+"\r\n"
		+"ジャンル: "+Genre4+"\r\n"
		+"住所: " +address4+"\r\n"
		+"\r\n"
		+"【5位】"+"\r\n"+"店名: "+name5+"\r\n"
		+"ジャンル: "+Genre5+"\r\n"
		+"住所: " +address5+"\r\n"
		
		
		
		 );
 
   }

 @EventMapping

   public LocationMessage handleTextMessageEvent1(MessageEvent< LocationMessageContent> event){
	 

     		 
     GeoApiContext context = new GeoApiContext.Builder()
   .apiKey("AIzaSyD3_BThExaXsXlEeGmtxMWHH4Iq0n3Pq1M").build();


  	return new LocationMessage(
     			 event.getMessage().getTitle(),
     			 event.getMessage().getAddress(),
     			 event.getMessage().getLatitude(),
     			 event.getMessage().getLongitude()
     				  
      );
  
	

 }
}
