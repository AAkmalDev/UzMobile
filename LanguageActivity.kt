package uz.pdp.uzmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_language.*
import uz.pdp.uzmobile.utils.MySharedpreference

class LanguageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

       MySharedpreference.getInstance(this)


        ozbek_lang.setOnClickListener {
            MySharedpreference.setLanguage("uz")
            val intent1 = Intent(this,MainActivity::class.java)
            startActivity(intent1)
            finish()
        }

        rus_lang.setOnClickListener {
            MySharedpreference.setLanguage("ru")
            val intent2 = Intent(this,MainActivity::class.java)
            startActivity(intent2)
            finish()
        }

        uzbek_lang.setOnClickListener {
            MySharedpreference.setLanguage("be")
            val intent3 = Intent(this,MainActivity::class.java)
            startActivity(intent3)
            finish()
        }

    }
}