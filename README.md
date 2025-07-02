# invoices-api
# Invoice API - Base64 XML Invoice Processing Service

## 🚀 Proje Hakkında

Bu proje, **Base64 ile encode edilmiş XML faturaların** alınarak doğrulanması, parse edilmesi ve kritik bilgilerin veritabanına kaydedilmesi işlemlerini gerçekleştiren modern, sağlam ve ölçeklenebilir bir REST API servisidir.

Spring Boot 3.x, Spring Data JPA ve H2 veritabanı kullanılarak geliştirilmiş, temiz mimari ve katmanlı yapı prensiplerine uygun bir backend uygulamasıdır. Ayrıca XML validasyonu ve veri işleme süreçleri güvenli ve hataya dayanıklı şekilde tasarlanmıştır.

---

## 📋 Özellikler

- **REST API Endpoint:**  
  `POST /api/invoices` ile Base64 XML verisi alır.

- **Base64 Decode:**  
  Gönderilen Base64 string, gerçek XML içeriğine çevrilir.

- **XML Validasyon:**  
  XML içerik, ilgili XSD (isteğe bağlı) veya yapısal kontrollerle doğrulanır. (Opsiyonel olarak XSD kullanılabilir veya atlanabilir.)

- **Veri İşleme:**  
  XML'den önemli alanlar (NIP, P1, P2) ayrıştırılarak veritabanına kaydedilir.

- **Hata Yönetimi:**  
  Gelişmiş exception handling ile hatalar detaylı ve anlaşılır şekilde client’a iletilir.

- **Modüler ve Temiz Kod:**  
  Controller, Service, Repository katmanları net ayrılmıştır.

- **Kolay Geliştirilebilirlik:**  
  Proje yapısı, yeni özelliklerin kolay eklenmesine ve test edilebilirliğe uygundur.

---

## 🛠️ Teknolojiler

- Java 11+
- Spring Boot 3.1.x
- Spring Web
- Spring Data JPA
- H2 Database (geliştirme/test ortamı)
- JAXB (isteğe bağlı, opsiyonel)
- Maven
- Postman (API testleri için)
