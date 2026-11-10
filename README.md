# TOURNEX — Iterasi 5: REST API v1.0 (GET, POST, PUT, PATCH, DELETE)

Implementasi awal TOURNEX menggunakan tiga aplikasi Spring Boot yang berjalan independen:

| Service | Port | Base URL | Database |
|---|---:|---|---|
| Tournament Service | 8081 | `http://localhost:8081/api/v1/tournaments` | H2 in-memory terpisah |
| Team Service | 8082 | `http://localhost:8082/api/v1/teams` | H2 in-memory terpisah |
| Match Service | 8083 | `http://localhost:8083/api/v1/matches` | H2 in-memory terpisah |

## Prasyarat
- JDK 17 atau lebih baru
- Apache Maven 3.9+ **atau** gunakan Maven Wrapper yang sudah disertakan (`mvnw` / `mvnw.cmd`)

## Menjalankan service
Jalankan setiap service dari direktori root pada terminal terpisah:

```bash
mvn -pl tournament-service spring-boot:run
mvn -pl team-service spring-boot:run
mvn -pl match-service spring-boot:run
```

Jika Maven belum terpasang di PATH, gunakan wrapper:

```bash
# Windows
.\mvnw.cmd -pl tournament-service spring-boot:run
.\mvnw.cmd -pl team-service spring-boot:run
.\mvnw.cmd -pl match-service spring-boot:run

# Linux/macOS
./mvnw -pl tournament-service spring-boot:run
./mvnw -pl team-service spring-boot:run
./mvnw -pl match-service spring-boot:run
```

Atau build seluruh modul:

```bash
mvn clean test
# atau
.\mvnw.cmd clean test
```

## Dokumentasi Swagger UI
- Tournament: http://localhost:8081/swagger-ui.html
- Team: http://localhost:8082/swagger-ui.html
- Match: http://localhost:8083/swagger-ui.html

OpenAPI JSON tersedia di `/v3/api-docs` pada masing-masing port. Spesifikasi OpenAPI statis hasil ekspor tersedia di direktori `docs/openapi/`.

## Endpoint v1.0
Setiap service menyediakan:

| Method | Endpoint | Keterangan |
|---|---|---|
| GET | `/api/v1/{resources}` | Mengambil seluruh data |
| GET | `/api/v1/{resources}/{id}` | Mengambil data berdasarkan ID |
| POST | `/api/v1/{resources}` | Membuat data baru |
| PUT | `/api/v1/{resources}/{id}` | Mengganti seluruh data |
| PATCH | `/api/v1/{resources}/{id}` | Memperbarui sebagian data |
| DELETE | `/api/v1/{resources}/{id}` | Menghapus data |

POST mengembalikan HTTP `201 Created`. PUT dan PATCH mengembalikan `200 OK`. DELETE mengembalikan `204 No Content`. Operasi by ID mengembalikan `404 Not Found` jika data tidak ditemukan, dan request tidak valid mengembalikan `400 Bad Request`.

## Contoh request

### Membuat tournament
```http
POST http://localhost:8081/api/v1/tournaments
Content-Type: application/json
```
```json
{
  "name": "TOURNEX Open Qualifier",
  "gameTitle": "VALORANT",
  "description": "Turnamen komunitas",
  "status": "REGISTRATION",
  "startDate": "2026-12-01",
  "endDate": "2026-12-05"
}
```

### Membuat team
```http
POST http://localhost:8082/api/v1/teams
Content-Type: application/json
```
```json
{
  "name": "Kapal Lompat",
  "tag": "KPL",
  "gameTitle": "VALORANT",
  "description": "Tim peserta TOURNEX"
}
```

### Membuat match
```http
POST http://localhost:8083/api/v1/matches
Content-Type: application/json
```
```json
{
  "tournamentId": 1,
  "teamAId": 1,
  "teamBId": 2,
  "scheduledAt": "2026-12-01T19:00:00",
  "status": "SCHEDULED"
}
```

### Mengganti tournament (PUT)
```http
PUT http://localhost:8081/api/v1/tournaments/1
Content-Type: application/json
```
```json
{
  "name": "TOURNEX Finals",
  "gameTitle": "VALORANT",
  "description": "Babak final",
  "status": "ONGOING",
  "startDate": "2026-12-02",
  "endDate": "2026-12-06"
}
```

### Memperbarui sebagian team (PATCH)
```http
PATCH http://localhost:8082/api/v1/teams/1
Content-Type: application/json
```
```json
{
  "description": "Deskripsi terbaru"
}
```

### Menghapus match (DELETE)
```http
DELETE http://localhost:8083/api/v1/matches/1
```

### API versi 2.0 (GET dan POST)

API v2 tersedia berdampingan dengan v1 pada base path `/api/v2`. Daftar data memakai pagination dan envelope `data`, `page`, `size`, `totalElements`, `totalPages`.

```http
GET http://localhost:8081/api/v2/tournaments?page=0&size=20&status=DRAFT
```
```json
{
  "data": [],
  "page": 0,
  "size": 20,
  "totalElements": 0,
  "totalPages": 0
}
```

```http
POST http://localhost:8081/api/v2/tournaments
Content-Type: application/json
```
```json
{
  "name": "TOURNEX Invitational",
  "game": "VALORANT",
  "description": "Turnamen komunitas",
  "status": "DRAFT",
  "startsAt": "2026-12-01T09:00:00Z",
  "endsAt": "2026-12-05T18:00:00Z"
}
```

ID pada tiap service dibuat otomatis oleh database. Pada iterasi ini `tournamentId`, `teamAId`, dan `teamBId` di Match disimpan sebagai referensi ID; validasi lintas-service belum dilakukan. H2 bersifat in-memory sehingga data akan kembali kosong ketika aplikasi dihentikan.

## Pengujian
Setiap modul memiliki pengujian integrasi berbasis JUnit 5 + Spring Boot Test + MockMvc untuk memeriksa GET, POST, dan pengambilan detail. Jalankan `mvn clean test` dari root. Struktur ini dapat dijalankan dalam pipeline CI/CD Maven.

## Batasan iterasi
Iterasi ini mengimplementasikan API v1.0 untuk GET, POST, PUT, PATCH, dan DELETE, serta API v2.0 untuk GET dan POST. Operasi PUT, PATCH, dan DELETE pada v2.0, API Gateway, autentikasi, dan komunikasi sinkron antarservice belum termasuk cakupan iterasi ini.

## Catatan model data
Dokumen Tahap 1 menetapkan tiga service, tetapi belum merinci atribut tiap entitas. Karena itu, field pada contoh di atas merupakan rancangan awal yang eksplisit dan dapat disesuaikan dengan kesepakatan tim.
