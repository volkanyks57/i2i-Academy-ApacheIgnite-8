# i2i-Academy-ApacheIgnite-8
Apache Ignite 3 ile In-Memory Data Grid uygulaması

# Özet
  Bu projede Docker ile Apache Ignite 3 single-node cluster kuruldu ve Java Thin Client
  kullanılarak dağıtık bir veri tabanı uygulaması geliştirildi. Subscriber tablosu
  oluşturularak 5 abone kaydı eklendi, kullanım verileri güncellendi ve final durum
  konsola yazdırıldı.

# Tamamlanan Görevler
  - Docker Compose ile Apache Ignite 3 container'ı başlatıldı
  - Ignite CLI ile cluster initialize edildi
  - Java Thin Client API kullanılarak Ignite'a bağlantı kuruldu
  - Subscriber tablosu oluşturuldu (customerId, dataUsage, smsUsage, callUsage)
  - Tablo temizlenerek 5 Subscriber kaydı eklendi
  - Her Subscriber için rastgele kullanım verileri güncellendi
  - Final durum konsola yazdırıldı

# Kullanılan Teknolojiler
  - Docker
  - Apache Ignite 3
  - Java 21
  - Maven
  - Apache Ignite Thin Client API
