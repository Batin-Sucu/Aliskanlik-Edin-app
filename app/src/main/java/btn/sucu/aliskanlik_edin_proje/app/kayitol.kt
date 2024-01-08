package btn.sucu.aliskanlik_edin_proje.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class kayitol : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayitol)


        val geribtn = findViewById<ImageButton>(R.id.btn_geri)
        geribtn.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)


        }

        var eposta = findViewById<TextView>(R.id.epostaalani)
        var sifregir = findViewById<TextView>(R.id.sifregir)
        var sifretekrar = findViewById<TextView>(R.id.sifretekrar)

        val kayitbtn = findViewById<Button>(R.id.btn_kayitol)
        kayitbtn.setOnClickListener{

            val Intent = Intent(this, aliskanliklar::class.java)
            if(sifregir.text.toString() == sifretekrar.text.toString() && sifregir.text.toString().length > 5)
            {
                Firebase.auth.createUserWithEmailAndPassword(eposta.text.toString(), sifregir.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = Firebase.auth.currentUser
                            Intent.putExtra("user",user)
                            startActivity(Intent)
                        } else {
                            Toast.makeText(
                                baseContext,
                                "Kayit basarisiz",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }
        }
    }
}
