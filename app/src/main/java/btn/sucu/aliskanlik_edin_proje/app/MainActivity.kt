package btn.sucu.aliskanlik_edin_proje.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class MainActivity : AppCompatActivity() {

    private lateinit var girisbtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        girisbtn = findViewById(R.id.btn_giris)
        var eposta = findViewById<TextView>(R.id.epostatxt)
        var sifre = findViewById<TextView>(R.id.epostaalani)

        val girisbtn = findViewById<Button>(R.id.btn_giris)
        girisbtn.setOnClickListener {
            val intent = Intent(this, aliskanliklar::class.java)
            Firebase.auth.signInWithEmailAndPassword(eposta.text.toString(), sifre.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("batin", "signInWithEmail:success")
                        val user = Firebase.auth.currentUser
                        intent.putExtra("user",user)
                        startActivity(intent)
                    } else {
                        Log.w("batin", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Giriş başarısız",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }

        val kytbtn = findViewById<Button>(R.id.btn_kayitol)
        kytbtn.setOnClickListener {
            val intent = Intent(this, kayitol::class.java)

            startActivity(intent)
        }
    }
}