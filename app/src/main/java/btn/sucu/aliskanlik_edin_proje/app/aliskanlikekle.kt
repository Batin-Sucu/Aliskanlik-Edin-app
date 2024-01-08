package btn.sucu.aliskanlik_edin_proje.app

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class aliskanlikekle : AppCompatActivity() {

    private lateinit var isimgir: EditText
    private lateinit var metingir: EditText
    private lateinit var tamam: ImageButton
    private lateinit var aliskanlikdao: aliskanlikDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aliskanlikekle)


        isimgir = findViewById(R.id.isimgir)
        metingir = findViewById(R.id.metingir)
        tamam = findViewById(R.id.tamam)
        aliskanlikdao = aliskanlikDao()

        tamam.setOnClickListener{
            val isim = isimgir.text.toString()
            val metin = metingir.text.toString()

            if(isim.isNotEmpty()) {

                aliskanlikdao.Aliskanlikekle(isim,metin)

                val intent = Intent(this,aliskanliklar::class.java)
                startActivity(intent)
            }
        }


        val geribtn = findViewById<ImageButton>(R.id.btn_geri)
        geribtn.setOnClickListener {
            val Intent = Intent(this, aliskanliklar::class.java)
            startActivity(Intent)
        }
    }
}