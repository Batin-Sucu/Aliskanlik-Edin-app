package btn.sucu.aliskanlik_edin_proje.app

import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import java.util.Date

class   aliskanliklar : AppCompatActivity() {


    private lateinit var adaptor: RVAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var eklebtn: ImageButton
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aliskanliklar)

        auth = Firebase.auth
        eklebtn = findViewById(R.id.btn_ekle)

        eklebtn.setOnClickListener {
            val intent = Intent(this,aliskanlikekle::class.java)
            startActivity(intent)
        }


        val geribtn = findViewById<ImageButton>(R.id.btn_geri)
        geribtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val eklebtn = findViewById<ImageButton>(R.id.btn_ekle)
        eklebtn.setOnClickListener {
            val intent = Intent(this, aliskanlikekle::class.java)
            startActivity(intent)
        }


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val aliskanlikCollection = aliskanlikDao().koleksiyon
        val currentUserId = auth.currentUser!!.uid
        val query = aliskanlikCollection.whereEqualTo("uid",currentUserId)
        val recyclerViewOption = FirestoreRecyclerOptions.Builder<aliskanlik>().setQuery(query,aliskanlik::class.java).build()
        adaptor = RVAdapter(recyclerViewOption,this)
        recyclerView.adapter = adaptor
    }

    override fun onStart() {
        super.onStart()
        adaptor?.startListening()
    }

    override fun onStop() {
        super.onStop()
        adaptor?.startListening()
    }

    class RVAdapter(options: FirestoreRecyclerOptions<aliskanlik>, ctx: Context) : FirestoreRecyclerAdapter<aliskanlik,RVAdapter.RVViewHolder>(options){
        private var ctx: Context = ctx

        class RVViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
            val aliskanlikIsim:TextView = itemView.findViewById(R.id.aliskanlik_isim)
            val aliskanlikAciklama:TextView = itemView.findViewById(R.id.aliskanlik_aciklama)
            val aliskanlikSayi:TextView = itemView.findViewById(R.id.aliskanlik_sayi)
            val aliskanlikDegis:ImageButton = itemView.findViewById(R.id.aliskanlikdegis)
            val aliskanlikArtir: ImageButton= itemView.findViewById(R.id.aliskanlik_artir)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
            return RVViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.aliskanlik_tasarim,parent,false))
        }

        override fun onBindViewHolder(holder: RVViewHolder, position: Int, model: aliskanlik) {
            holder.aliskanlikIsim.text = model.isim
            holder.aliskanlikAciklama.text = model.metin
            //holder.aliskanlikIsim.text = model.isim
            holder.aliskanlikSayi.text = model.kere.toString()

            holder.aliskanlikDegis.setOnClickListener {
                val intent = Intent(ctx, aliskanlikdegistir::class.java)
                val id = getSnapshots().getSnapshot(position).getId();
                intent.putExtra("id", id)
                ctx.startActivity(intent)
            }

            if(model.sonBasma.day == Date().day)
            {
                //buton rengini degis
                holder.aliskanlikArtir.setBackgroundResource(R.drawable.butongoster3)
            }
            else{
                //buton rengini orijinal hale getir
                holder.aliskanlikArtir.setBackgroundResource(R.drawable.butongoster)
                holder.aliskanlikArtir.setOnClickListener{
                    val id = getSnapshots().getSnapshot(position).getId();
                    aliskanlikDao().aliskanlikArtir(id)
                }
            }
        }
    }
}

