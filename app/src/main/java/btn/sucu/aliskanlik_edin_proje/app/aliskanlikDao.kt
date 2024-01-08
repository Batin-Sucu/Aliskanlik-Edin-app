package btn.sucu.aliskanlik_edin_proje.app
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class aliskanlikDao {

    private val db = FirebaseFirestore.getInstance()
    val koleksiyon = db.collection("Aliskanliklar")

    private val auth = Firebase.auth

    fun Aliskanlikekle(aliskanlik: String,metin: String) {
        val kullaniciid = Firebase.auth.currentUser?.uid!!
        val sonBasma = Date()
        sonBasma.time = sonBasma.time - (86400 * 1000)
        val yazi = aliskanlik(metin,kullaniciid,aliskanlik,Date(),0,sonBasma)

        koleksiyon.document().set(yazi)
    }

    fun aliskanlikArtir(id:String){
        koleksiyon.document(id).update("kere", FieldValue.increment(1),"sonBasma",Date())
    }
}