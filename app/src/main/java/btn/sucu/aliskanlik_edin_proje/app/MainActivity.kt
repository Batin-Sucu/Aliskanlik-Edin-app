package btn.sucu.aliskanlik_edin_proje.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val girisbtn = findViewById<Button>(R.id.btn_giris)
        girisbtn.setOnClickListener {
            val Intent = Intent(this, aliskanliklar::class.java)
            startActivity(Intent)

            val kytbtn = findViewById<Button>(R.id.btn_kayitol)
            kytbtn.setOnClickListener {
                val Intent = Intent(this, kayitol::class.java)
                startActivity(Intent)
            }

        }
    }
}