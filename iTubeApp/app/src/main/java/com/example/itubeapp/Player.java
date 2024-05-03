package com.example.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player extends AppCompatActivity {

    private String stringJavascript = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "  <body>\n" +
            "    <!-- 1. The <iframe> (and video player) will replace this <div> tag. -->\n" +
            "    <div id=\"player\"></div>\n" +
            "\n" +
            "    <script>\n" +
            "      // 2. This code loads the IFrame Player API code asynchronously.\n" +
            "      var tag = document.createElement('script');\n" +
            "\n" +
            "      tag.src = \"https://www.youtube.com/iframe_api\";\n" +
            "      var firstScriptTag = document.getElementsByTagName('script')[0];\n" +
            "      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
            "\n" +
            "      // 3. This function creates an <iframe> (and YouTube player)\n" +
            "      //    after the API code downloads.\n" +
            "      var player;\n" +
            "      function onYouTubeIframeAPIReady() {\n" +
            "        player = new YT.Player('player', {\n" +
            "          height: '200',\n" +
            "          width: '300',\n" +
            "          videoId: '4CP2-om4Y6Q',\n" +
            "          playerVars: {\n" +
            "            'playsinline': 1\n" +
            "          },\n" +
            "          events: {\n" +
            "            'onReady': onPlayerReady,\n" +
            "            'onStateChange': onPlayerStateChange\n" +
            "          }\n" +
            "        });\n" +
            "      }\n" +
            "\n" +
            "      // 4. The API will call this function when the video player is ready.\n" +
            "      function onPlayerReady(event) {\n" +
            "        event.target.playVideo();\n" +
            "      }\n" +
            "\n" +
            "      // 5. The API calls this function when the player's state changes.\n" +
            "      //    The function indicates that when playing a video (state=1),\n" +
            "      //    the player should play for six seconds and then stop.\n" +
            "      var done = false;\n" +
            "      function onPlayerStateChange(event) {\n" +
            "        if (event.data == YT.PlayerState.PLAYING && !done) {\n" +
            "          setTimeout(stopVideo, 6000);\n" +
            "          done = true;\n" +
            "        }\n" +
            "      }\n" +
            "      function stopVideo() {\n" +
            "        player.stopVideo();\n" +
            "      }\n" +
            "    </script>\n" +
            "  </body>\n" +
            "</html>";

    String extractedVideoId;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_player);

        webView = findViewById(R.id.webView);
        Intent i = getIntent();

        String videoUrl = i.getStringExtra("url");
        // Regular expression pattern to match YouTube video ID
        Pattern pattern = Pattern.compile("(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*");
        // Match the pattern against the video URL
        Matcher matcher = pattern.matcher(videoUrl);
        // Check if a match is found and print the video ID
        if (matcher.find()) {
            extractedVideoId = matcher.group();
            System.out.println("Video ID: " + extractedVideoId);
        } else {
            System.out.println("Video ID not found in the URL.");
        }

        String replacedHtml = stringJavascript.replace("'4CP2-om4Y6Q'", "'" + extractedVideoId + "'");
        webView.loadData(replacedHtml, "text/html", "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }
}