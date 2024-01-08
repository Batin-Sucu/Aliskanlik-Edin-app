package btn.sucu.aliskanlik_edin_proje.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date


class aliskanlikdegistir : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aliskanlikdegistir)
        val intent = getIntent()
        val id = intent.getStringExtra("id")!!

        val sil = findViewById<ImageButton>(R.id.sil)
        sil.setOnClickListener {
            aliskanlikDao()
                .koleksiyon
                .document(id).delete().addOnSuccessListener {
                    val Intent = Intent(this, aliskanliklar::class.java)
                    startActivity(Intent)
                }
        }

        val isim = findViewById<TextView>(R.id.isimgir)
        val metin = findViewById<TextView>(R.id.metingir)
        val sayi = findViewById<TextView>(R.id.aliskanlik_degistirdekisayi)
        val tarih = findViewById<TextView>(R.id.aliskanlik_olusturma)

        val degistir = findViewById<ImageButton>(R.id.degistir)
        degistir.setOnClickListener{
            aliskanlikDao()
                .koleksiyon
                .document(id)
                .update("metin",metin.text.toString(),"isim",isim.text.toString())
                .addOnSuccessListener {
                    val Intent = Intent(this, aliskanliklar::class.java)
                    startActivity(Intent)
                }
        }

        val geri = findViewById<ImageButton>(R.id.btn_geri)

        geri.setOnClickListener {
            val Intent = Intent(this, aliskanliklar::class.java)
            startActivity(Intent)
        }

        aliskanlikDao()
            .koleksiyon
            .document(id)
            .get()
            .addOnSuccessListener {
                val olusturma = Date((it.data?.get("olusturma") as Timestamp).seconds*1000)
                isim.text = it.data?.get("isim").toString()
                metin.text = it.data?.get("metin").toString()
                sayi.text = it.data?.get("kere").toString()
                tarih.text = SimpleDateFormat("dd/MM/yyy").format(olusturma)
            }
    }
}