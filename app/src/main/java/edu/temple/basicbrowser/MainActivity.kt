package edu.temple.basicbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var urlEditText: EditText
    private lateinit var goButton: ImageButton
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlEditText = findViewById(R.id.urlEditText)
        goButton = findViewById(R.id.goButton)
        webView = findViewById(R.id.webView)

        // Allow your browser to intercept hyperlink clicks
        webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
        // need to go to the website in the urledittext when gobutton is clicked
        goButton.setOnClickListener {
            parseUrl(urlEditText.text.toString())?.apply{
                webView.loadUrl(this)
            }
            //webView.loadUrl(urlEditText.text.toString())
            //unsure why i would need toString but ide was getting mad at me
        }

    }
}
fun parseUrl(url: String): String? {
    //returns null if invalid,
    //so can call with a question mark and
    //wont do anything if not a url
    lateinit var result: String
    if (url.startsWith("https://"))
        result= url
    else
        result= "https://$url"
    try {
        URL(result)
    } catch (e: MalformedURLException) {
        return null
    }
    return result
}