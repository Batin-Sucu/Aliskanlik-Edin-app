package btn.sucu.aliskanlik_edin_proje.app

import java.util.Date

data class aliskanlik (
    val metin: String = "",
    val uid: String = "",
    val isim: String = "",
    val olusturma: Date = Date(),
    val kere: Int = 0,
    val sonBasma: Date = Date()
)