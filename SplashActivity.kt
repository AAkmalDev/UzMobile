package uz.pdp.uzmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import uz.pdp.uzmobile.utils.MySharedpreference

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        MySharedpreference.getInstance(this)

        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                if (MySharedpreference.getLanguage().equals("")){
                    val intent1 = Intent(this@SplashActivity,LanguageActivity::class.java)
                    startActivity(intent1)
                    finish()
                }else {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        },2000)

    }
}