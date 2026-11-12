# UNIVERSITAS BRAWIJAYA
## FAKULTAS ILMU KOMPUTER

**PROJECT ARSITEKTUR BERBASIS LAYANAN**  
**TAHUN AKADEMIK 2026/2027 GENAP**

---

# TOURNEX: SISTEM PENGELOLAAN TURNAMEN ESPORTS BERBASIS MICROSERVICES

* **Dosen Pengampu:** Agi Putra Kharisma
* **Nama Kelompok:** Kapal Lompat

### Anggota Kelompok:
1. **Faris Dian Pradipta** — `235150200111068`
2. **Alarik Muhamad Nakharry** — `235150200111071`
3. **I Gede Ikhsa Garbhacita NP** — `235150207111072`

---

## DISCLAIMER PENGGUNAAN AI

Kami menggunakan teknologi Artificial Intelligence (AI) sebagai bantuan dalam proses penyusunan laporan, pengembangan proyek *Game Tournament Management API*, termasuk dalam perencanaan, mencari ide, menulis dokumen, serta memahami dan menerapkan sistem tersebut. AI digunakan dengan bijak dan bertanggung jawab untuk membantu proses belajar serta menyelesaikan proyek. Hasil yang dihasilkan dengan bantuan AI dicek, diperiksa, dan disesuaikan oleh tim sesuai dengan kebutuhan proyek serta pemahaman terhadap materi kuliah.

Tim bertanggung jawab atas semua isi laporan, penerapan sistem, hasil uji coba, serta setiap keputusan teknis yang dibuat. Penggunaan AI tidak menghilangkan tanggung jawab tim untuk memahami dan menjelaskan hasil pekerjaan yang diperoleh. Tim juga bertekad untuk menghindari menjiplak, menyebutkan sumber-sumber yang digunakan, serta mengikuti aturan akademik dan kebijakan penggunaan AI yang ditentukan oleh dosen pengampu. Pernyataan ini dibuat untuk menunjukkan bahwa AI digunakan secara bertanggung jawab dalam mengerjakan proyek tersebut.

**Malang, 22 Oktober 2026**  
*(Tanda Tangan)*  
**Alarik Muhamad Nakharry**  
`235150200111071`

---

## LATAR BELAKANG & MOTIVASI

Perkembangan industri gim telah mendorong munculnya berbagai kegiatan kompetitif, salah satunya melalui penyelenggaraan turnamen gim. Dalam gim VALORANT, kompetisi merupakan salah satu bagian penting dari ekosistem permainan, yang ditunjukkan melalui penyelenggaraan turnamen profesional seperti *VALORANT Champions Tour (VCT)*. Sistem pengelolaan turnamen menjadi aspek penting yang mendukung penyelenggaraan kompetisi, mulai dari penyediaan informasi turnamen, pengelolaan tim peserta, penjadwalan pertandingan, hingga pencatatan hasil pertandingan.

Berdasarkan ketertarikan tim terhadap ekosistem kompetitif VALORANT, muncul motivasi untuk merancang sistem pengelolaan turnamen yang dapat menjadi alternatif dari sistem yang telah tersedia. Sistem yang dikembangkan diharapkan dapat menyediakan pengelolaan informasi turnamen, data tim, jadwal pertandingan, serta hasil pertandingan secara terstruktur dan terintegrasi. Melalui perancangan ini, tim ingin mengeksplorasi bagaimana sebuah sistem dapat dikembangkan dengan fitur dan alur pengelolaan yang sesuai dengan kebutuhan pengguna, sekaligus memiliki potensi untuk dikembangkan lebih lanjut.

Berdasarkan motivasi tersebut, dirancang sebuah proyek bernama **Game Tournament Management API**, yaitu sistem berbasis REST API yang ditujukan untuk mendukung pengelolaan data turnamen gim. Sistem ini dirancang dengan membagi tanggung jawab ke dalam beberapa layanan, yaitu:
* **Tournament Service:** Mengelola informasi turnamen.
* **Team Service:** Mengelola data tim dan anggotanya.
* **Match Service:** Mengelola jadwal dan hasil pertandingan.

Melalui pengembangan proyek ini, tim berharap dapat menghasilkan fondasi sistem pengelolaan turnamen yang terstruktur, konsisten, dan mudah diintegrasikan dengan aplikasi lain. Selain bertujuan mengeksplorasi alternatif sistem pengelolaan turnamen yang terinspirasi dari ekosistem kompetitif VALORANT, proyek ini juga menjadi sarana penerapan konsep arsitektur berbasis layanan melalui perancangan REST API, dokumentasi menggunakan OpenAPI, pengujian otomatis, serta pengelolaan versi API dengan perubahan kontrak yang terencana (*breaking changes*).

---

## DOKUMENTASI DAN SPESIFIKASI API VERSI 1.0 DALAM BENTUK OPENAPI (GET, POST)

Dokumentasi API versi 1.0 disusun menggunakan *OpenAPI Specification* (OAS) untuk menjelaskan kontrak REST API dalam sistem pengelolaan turnamen gim VALORANT. Dokumentasi mencakup endpoint, metode HTTP, format request dan response, serta kode status HTTP.

Pada tahap awal, API difokuskan pada metode **GET** untuk mengambil data dan **POST** untuk menambahkan data baru. Sistem dirancang menggunakan arsitektur microservices dengan tiga layanan utama:
* Tournament Service
* Team Service
* Match Service

### Rancangan Endpoint API Versi 1.0 (GET & POST)

| Layanan | Metode | Endpoint | Fungsi |
| :--- | :--- | :--- | :--- |
| Tournament | GET | `/api/v1/tournaments` | Melihat daftar turnamen |
| Tournament | POST | `/api/v1/tournaments` | Menambah turnamen |
| Team | GET | `/api/v1/teams` | Melihat daftar tim |
| Team | POST | `/api/v1/teams` | Menambah tim |
| Match | GET | `/api/v1/matches` | Melihat daftar pertandingan |
| Match | POST | `/api/v1/matches` | Menambah pertandingan |

OpenAPI digunakan untuk mendeskripsikan kontrak REST API secara terstruktur, termasuk endpoint, metode HTTP, skema data, dan respons. Dokumentasi dapat ditampilkan melalui Swagger UI agar spesifikasi lebih mudah dipahami dan endpoint dapat dicoba secara interaktif.

Spesifikasi API versi 1.0 disiapkan dalam format YAML atau JSON sebagai acuan implementasi dan pengujian. Spesifikasi ini juga menjadi dasar pengembangan versi berikutnya yang akan menerapkan perubahan kontrak tidak kompatibel (*breaking changes*).

---

## DOKUMENTASI DAN SPESIFIKASI API VERSI 1.0 DALAM BENTUK OPENAPI (PUT, PATCH, DELETE)

Dokumentasi API versi 1.0 dilengkapi dengan metode HTTP **PUT**, **PATCH**, dan **DELETE** untuk mendukung pengelolaan data pada sistem TOURNEX. 
* **PUT:** Used to update the entirety of a resource's data.
* **PATCH:** Used to update a portion of a resource's data.
* **DELETE:** Used to delete data based on its identity.

Endpoint dirancang mengikuti pembagian tanggung jawab tiga layanan, yaitu Tournament Service, Team Service, dan Match Service. Spesifikasi ini melengkapi metode GET dan POST yang telah dirancang sebelumnya, sehingga setiap layanan memiliki rancangan operasi untuk membaca, menambahkan, memperbarui, dan menghapus data.

### Rancangan Endpoint API Versi 1.0 (PUT, PATCH, DELETE)

| Layanan | Metode | Endpoint | Fungsi |
| :--- | :--- | :--- | :--- |
| Tournament | PUT | `/api/v1/tournaments/{id}` | Memperbarui seluruh data turnamen |
| Tournament | PATCH | `/api/v1/tournaments/{id}` | Memperbarui sebagian data turnamen |
| Tournament | DELETE | `/api/v1/tournaments/{id}` | Menghapus turnamen |
| Team | PUT | `/api/v1/teams/{id}` | Memperbarui seluruh data tim |
| Team | PATCH | `/api/v1/teams/{id}` | Memperbarui sebagian data tim |
| Team | DELETE | `/api/v1/teams/{id}` | Menghapus tim |
| Match | PUT | `/api/v1/matches/{id}` | Memperbarui seluruh data pertandingan |
| Match | PATCH | `/api/v1/matches/{id}` | Memperbarui sebagian data pertandingan |
| Match | DELETE | `/api/v1/matches/{id}` | Menghapus pertandingan |

Spesifikasi OpenAPI mendeskripsikan setiap operasi melalui path, metode HTTP, parameter identitas sumber daya, format data permintaan, dan respons yang mungkin dihasilkan. Operasi pembaruan menggunakan parameter ID untuk menentukan data yang akan diubah, sedangkan operasi penghapusan menggunakan ID untuk menentukan data yang akan dihapus.

### Kode Status HTTP yang Direncanakan:
* **`200 OK` / `204 No Content`:** Operasi berhasil.
* **`400 Bad Request`:** Permintaan tidak valid.
* **`404 Not Found`:** Data tidak ditemukan.
* **`500 Internal Server Error`:** Kesalahan pada server.

Rancangan OpenAPI ini menjadi acuan implementasi dan pengujian API versi 1.0. Detail skema permintaan, respons, serta perilaku setiap endpoint akan disesuaikan dengan model data dan implementasi masing-masing layanan.

---

## BUILD PROPERTIES

### Parent POM (`pom.xml`)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>id.tournex</groupId>
  <artifactId>tournex-parent</artifactId>
  <version>1.0.0</version>
  <packaging>pom</packaging>

  <modules>
    <module>tournament-service</module>
    <module>team-service</module>
    <module>match-service</module>
  </modules>

  <properties>
    <java.version>17</java.version>
    <spring-boot.version>3.5.6</spring-boot.version>
    <maven.compiler.release>17</maven.compiler.release>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>${spring-boot.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-maven-plugin</artifactId>
          <version>${spring-boot.version}</version>
          <executions>
            <execution>
              <goals>
                <goal>repackage</goal>
              </goals>
            </execution>
          </executions>
        </plugin>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.14.0</version>
          <configuration>
            <release>17</release>
            <parameters>true</parameters>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```

Penjelasan:
* **Java 17** digunakan sebagai target kompilasi (`java.version` dan `maven.compiler.release`).
* **Spring Boot 3.5.6** digunakan sebagai versi dependency melalui BOM `spring-boot-dependencies`.
* **`<parameters>true</parameters>`** wajib karena proyek tidak memakai `spring-boot-starter-parent`. Flag ini membuat nama parameter (`@PathVariable Long id`) tersedia saat runtime.
* **Goal `repackage`** pada `spring-boot-maven-plugin` membuat `mvn package` menghasilkan executable jar (fat jar).

### Module POM (`tournament-service/pom.xml`)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>id.tournex</groupId>
    <artifactId>tournex-parent</artifactId>
    <version>1.0.0</version>
  </parent>

  <artifactId>tournament-service</artifactId>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.8.13</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
</project>
```

`team-service` dan `match-service` memiliki konfigurasi yang sama; perbedaannya hanya pada `artifactId`.

---

## KONFIGURASI DEPLOYMENT

### Tournament Service (`tournament-service/src/main/resources/application.properties`)

```properties
spring.application.name=tournament-service
server.port=8081
spring.datasource.url=jdbc:h2:mem:tournamentdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.open-in-view=false
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
springdoc.swagger-ui.path=/swagger-ui.html
```

### Team Service (`team-service/src/main/resources/application.properties`)

```properties
spring.application.name=team-service
server.port=8082
spring.datasource.url=jdbc:h2:mem:teamdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.open-in-view=false
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
springdoc.swagger-ui.path=/swagger-ui.html
```

### Match Service (`match-service/src/main/resources/application.properties`)

```properties
spring.application.name=match-service
server.port=8083
spring.datasource.url=jdbc:h2:mem:matchdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.open-in-view=false
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
springdoc.swagger-ui.path=/swagger-ui.html
```

### CI/CD (`.github/workflows/maven.yml`)

```yaml
name: TOURNEX Maven CI
on:
  push:
    branches: [ "main", "master" ]
  pull_request:
    branches: [ "main", "master" ]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: maven
      - name: Run tests
        run: mvn --batch-mode clean verify
```

### Cara menjalankan

```bash
# build dan test seluruh modul
mvn clean test
# atau memakai Maven Wrapper
.\mvnw.cmd clean test

# menjalankan service
mvn -pl tournament-service spring-boot:run
mvn -pl team-service spring-boot:run
mvn -pl match-service spring-boot:run

# atau memakai Maven Wrapper (Windows)
.\mvnw.cmd -pl tournament-service spring-boot:run
.\mvnw.cmd -pl team-service spring-boot:run
.\mvnw.cmd -pl match-service spring-boot:run
```

| Service | Port | Swagger UI | OpenAPI JSON | H2 Console |
| :--- | ---: | :--- | :--- | :--- |
| Tournament | 8081 | `http://localhost:8081/swagger-ui.html` | `http://localhost:8081/v3/api-docs` | `http://localhost:8081/h2-console` |
| Team | 8082 | `http://localhost:8082/swagger-ui.html` | `http://localhost:8082/v3/api-docs` | `http://localhost:8082/h2-console` |
| Match | 8083 | `http://localhost:8083/swagger-ui.html` | `http://localhost:8083/v3/api-docs` | `http://localhost:8083/h2-console` |

Spesifikasi OpenAPI statis hasil ekspor juga tersedia di direktori `docs/openapi/`:
* `docs/openapi/tournament-service.json`
* `docs/openapi/team-service.json`
* `docs/openapi/match-service.json`

---

## KODE SUMBER UTAMA — GET & POST

### `TournamentController.java`

```java
@RestController
@RequestMapping("/api/v1/tournaments")
@Tag(name = "Tournament API", description = "Operasi API Tournament versi 1.0")
public class TournamentController {

    private final TournamentRepository repository;
    private final Validator validator;

    public TournamentController(TournamentRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @GetMapping
    @Operation(summary = "Mengambil seluruh data tournament")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Daftar tournament berhasil diambil"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public List<Tournament> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Mengambil tournament berdasarkan ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tournament ditemukan"),
        @ApiResponse(responseCode = "404", description = "Tournament tidak ditemukan"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public Tournament getById(@PathVariable Long id) {
        return findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Membuat tournament baru")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Tournament berhasil dibuat"),
        @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public Tournament create(@Valid @RequestBody Tournament request) {
        request.setId(null);
        return repository.save(request);
    }

    private Tournament findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tournament dengan ID " + id + " tidak ditemukan"));
    }
}
```

`TeamController` dan `MatchController` mengikuti pola yang sama dengan entitas dan path masing-masing:

| Service | Path | Entity | Repository |
| :--- | :--- | :--- | :--- |
| Tournament | `/api/v1/tournaments` | `Tournament` | `TournamentRepository` |
| Team | `/api/v1/teams` | `Team` | `TeamRepository` |
| Match | `/api/v1/matches` | `Match` | `MatchRepository` |

### Repository

```java
public interface TournamentRepository extends JpaRepository<Tournament, Long> {}
public interface TeamRepository extends JpaRepository<Team, Long> {}
public interface MatchRepository extends JpaRepository<Match, Long> {}
```

### Ringkasan status GET & POST

| Method | Endpoint | Status sukses | Status gagal |
| :--- | :--- | :--- | :--- |
| GET | `/api/v1/{resources}` | `200 OK` | `500` |
| GET | `/api/v1/{resources}/{id}` | `200 OK` | `404`, `500` |
| POST | `/api/v1/{resources}` | `201 Created` | `400`, `500` |

---

## KODE SUMBER UTAMA — PUT, PATCH, DELETE

### `TournamentController.java`

```java
@PutMapping("/{id}")
@Operation(summary = "Mengganti seluruh data tournament")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Tournament berhasil diperbarui"),
    @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
    @ApiResponse(responseCode = "404", description = "Tournament tidak ditemukan"),
    @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
})
public Tournament update(@PathVariable Long id, @Valid @RequestBody Tournament request) {
    Tournament existing = findById(id);
    existing.setName(request.getName());
    existing.setGameTitle(request.getGameTitle());
    existing.setDescription(request.getDescription());
    existing.setStatus(request.getStatus());
    existing.setStartDate(request.getStartDate());
    existing.setEndDate(request.getEndDate());
    return repository.save(existing);
}

@PatchMapping("/{id}")
@Operation(summary = "Memperbarui sebagian data tournament")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Tournament berhasil diperbarui sebagian"),
    @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
    @ApiResponse(responseCode = "404", description = "Tournament tidak ditemukan"),
    @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
})
public Tournament patch(@PathVariable Long id, @RequestBody Tournament request) {
    Tournament existing = findById(id);
    if (request.getName() != null) existing.setName(request.getName());
    if (request.getGameTitle() != null) existing.setGameTitle(request.getGameTitle());
    if (request.getDescription() != null) existing.setDescription(request.getDescription());
    if (request.getStatus() != null) existing.setStatus(request.getStatus());
    if (request.getStartDate() != null) existing.setStartDate(request.getStartDate());
    if (request.getEndDate() != null) existing.setEndDate(request.getEndDate());
    validate(existing);
    return repository.save(existing);
}

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
@Operation(summary = "Menghapus tournament berdasarkan ID")
@ApiResponses({
    @ApiResponse(responseCode = "204", description = "Tournament berhasil dihapus"),
    @ApiResponse(responseCode = "404", description = "Tournament tidak ditemukan"),
    @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
})
public void delete(@PathVariable Long id) {
    repository.delete(findById(id));
}

private void validate(Tournament entity) {
    validator.validate(entity).stream().findFirst().ifPresent(violation -> {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, violation.getMessage());
    });
}
```

### `TeamController.java`

```java
@PutMapping("/{id}")
public Team update(@PathVariable Long id, @Valid @RequestBody Team request) {
    Team existing = findById(id);
    existing.setName(request.getName());
    existing.setTag(request.getTag());
    existing.setGameTitle(request.getGameTitle());
    existing.setDescription(request.getDescription());
    return repository.save(existing);
}

@PatchMapping("/{id}")
public Team patch(@PathVariable Long id, @RequestBody Team request) {
    Team existing = findById(id);
    if (request.getName() != null) existing.setName(request.getName());
    if (request.getTag() != null) existing.setTag(request.getTag());
    if (request.getGameTitle() != null) existing.setGameTitle(request.getGameTitle());
    if (request.getDescription() != null) existing.setDescription(request.getDescription());
    validate(existing);
    return repository.save(existing);
}

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void delete(@PathVariable Long id) {
    repository.delete(findById(id));
}

private void validate(Team entity) {
    validator.validate(entity).stream().findFirst().ifPresent(violation -> {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, violation.getMessage());
    });
}
```

### `MatchController.java`

```java
@PutMapping("/{id}")
public Match update(@PathVariable Long id, @Valid @RequestBody Match request) {
    Match existing = findById(id);
    existing.setTournamentId(request.getTournamentId());
    existing.setTeamAId(request.getTeamAId());
    existing.setTeamBId(request.getTeamBId());
    existing.setScheduledAt(request.getScheduledAt());
    existing.setStatus(request.getStatus());
    existing.setWinnerTeamId(request.getWinnerTeamId());
    existing.setScoreTeamA(request.getScoreTeamA());
    existing.setScoreTeamB(request.getScoreTeamB());
    return repository.save(existing);
}

@PatchMapping("/{id}")
public Match patch(@PathVariable Long id, @RequestBody Match request) {
    Match existing = findById(id);
    if (request.getTournamentId() != null) existing.setTournamentId(request.getTournamentId());
    if (request.getTeamAId() != null) existing.setTeamAId(request.getTeamAId());
    if (request.getTeamBId() != null) existing.setTeamBId(request.getTeamBId());
    if (request.getScheduledAt() != null) existing.setScheduledAt(request.getScheduledAt());
    if (request.getStatus() != null) existing.setStatus(request.getStatus());
    if (request.getWinnerTeamId() != null) existing.setWinnerTeamId(request.getWinnerTeamId());
    if (request.getScoreTeamA() != null) existing.setScoreTeamA(request.getScoreTeamA());
    if (request.getScoreTeamB() != null) existing.setScoreTeamB(request.getScoreTeamB());
    validate(existing);
    return repository.save(existing);
}

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void delete(@PathVariable Long id) {
    repository.delete(findById(id));
}

private void validate(Match entity) {
    validator.validate(entity).stream().findFirst().ifPresent(violation -> {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, violation.getMessage());
    });
}
```

### Validasi model data

| Entity | Field | Constraint |
| :--- | :--- | :--- |
| `Tournament` | `name`, `gameTitle`, `status` | `@NotBlank` |
| `Tournament` | `startDate`, `endDate` | `@NotNull` |
| `Team` | `name`, `tag`, `gameTitle` | `@NotBlank` |
| `Match` | `tournamentId`, `teamAId`, `teamBId` | `@NotNull`, `@Positive` |
| `Match` | `scheduledAt` | `@NotNull` |
| `Match` | `status` | `@NotBlank` |

### Ringkasan status PUT, PATCH, DELETE

| Method | Endpoint | Status sukses | Status gagal |
| :--- | :--- | :--- | :--- |
| PUT | `/api/v1/{resources}/{id}` | `200 OK` | `400`, `404`, `500` |
| PATCH | `/api/v1/{resources}/{id}` | `200 OK` | `400`, `404`, `500` |
| DELETE | `/api/v1/{resources}/{id}` | `204 No Content` | `404`, `500` |

---

## PENGUJIAN API VERSI 1.0 (GET, POST)

Pengujian operasi GET dan POST dilakukan dengan dua pendekatan:

1. **Pengujian otomatis** menggunakan JUnit 5 dan `MockMvc` dengan anotasi `@SpringBootTest` dan `@AutoConfigureMockMvc`. Tes berjalan tanpa perlu menyalakan server dan memverifikasi kode status serta isi respons.
2. **Pengujian manual** melalui Swagger UI dengan fitur *Try it out*, atau melalui PowerShell/curl untuk memastikan perilaku endpoint secara nyata.

Setiap service memiliki 10 tes otomatis. Tiga di antaranya menguji operasi GET dan POST.

### Kode Sumber Pengujian

Berkas: `tournament-service/src/test/java/id/tournex/tournament/TournamentControllerTest.java`

```java
package id.tournex.tournament;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TournamentControllerTest {

    private static final String VALID_BODY = "{\"name\":\"TOURNEX Open\",\"gameTitle\":\"VALORANT\",\"description\":\"Community tournament\",\"status\":\"REGISTRATION\",\"startDate\":\"2026-12-01\",\"endDate\":\"2026-12-05\"}";

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllReturnsOk() throws Exception {
        mockMvc.perform(get("/api/v1/tournaments"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void postCreatesResourceAndReturns201() throws Exception {
        mockMvc.perform(post("/api/v1/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_BODY))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void getUnknownIdReturns404() throws Exception {
        mockMvc.perform(get("/api/v1/tournaments/999999"))
            .andExpect(status().isNotFound());
    }
}
```

`TeamControllerTest` dan `MatchControllerTest` menggunakan struktur yang sama. Perbedaannya hanya pada path dan isi `VALID_BODY`:

| Service | Path | `VALID_BODY` |
| :--- | :--- | :--- |
| Tournament | `/api/v1/tournaments` | `{"name":"TOURNEX Open","gameTitle":"VALORANT","description":"Community tournament","status":"REGISTRATION","startDate":"2026-12-01","endDate":"2026-12-05"}` |
| Team | `/api/v1/teams` | `{"name":"Kapal Lompat","tag":"KPL","gameTitle":"VALORANT","description":"Peserta"}` |
| Match | `/api/v1/matches` | `{"tournamentId":1,"teamAId":1,"teamBId":2,"scheduledAt":"2026-12-01T19:00:00","status":"SCHEDULED"}` |

### Skenario dan Hasil Pengujian

| No | Skenario | Endpoint | Hasil yang diharapkan | Status |
| :-: | :--- | :--- | :--- | :--- |
| 1 | Mengambil seluruh data | `GET /api/v1/{resources}` | `200 OK`, respons JSON berupa array | Lulus |
| 2 | Menambah data baru | `POST /api/v1/{resources}` | `201 Created`, respons memiliki `id` | Lulus |
| 3 | Mengambil data dengan ID tidak ada | `GET /api/v1/{resources}/999999` | `404 Not Found` | Lulus |

Total: **3 tes × 3 service = 9 tes**, seluruhnya lulus.

### Hasil Pengujian Otomatis

Perintah:

```powershell
.\mvnw.cmd -B clean test
```

Keluaran yang diharapkan:

```
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0 -- TournamentControllerTest
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0 -- TeamControllerTest
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0 -- MatchControllerTest
BUILD SUCCESS
```

![Hasil pengujian otomatis GET dan POST](docs/screenshots/get-post-test-run.png)

*Gambar 1. Hasil `mvn test` untuk pengujian GET dan POST.*

### Hasil Pengujian Manual (Swagger UI)

![POST mengembalikan 201 Created](docs/screenshots/get-post-swagger-201.png)

*Gambar 2. POST `/api/v1/tournaments` berhasil dengan status `201 Created`.*

![GET mengembalikan 200 OK](docs/screenshots/get-post-swagger-200.png)

*Gambar 3. GET `/api/v1/tournaments` berhasil dengan status `200 OK`.*

![GET ID tidak ada mengembalikan 404](docs/screenshots/get-post-swagger-404.png)

*Gambar 4. GET `/api/v1/tournaments/999999` mengembalikan status `404 Not Found`.*

### Alternatif Pengujian Manual dengan PowerShell

```powershell
# GET seluruh data
Invoke-RestMethod http://localhost:8081/api/v1/tournaments | Format-Table

# POST data baru
$body = @{
  name        = "TOURNEX Invitational"
  gameTitle   = "Mobile Legends"
  description = "Uji coba"
  status      = "UPCOMING"
  startDate   = "2026-11-10"
  endDate     = "2026-11-12"
} | ConvertTo-Json

Invoke-RestMethod -Method Post `
  -Uri http://localhost:8081/api/v1/tournaments `
  -ContentType 'application/json' `
  -Body $body
```

---

## PENGUJIAN API VERSI 1.0 (PUT, PATCH, DELETE)

Pengujian operasi PUT, PATCH, dan DELETE menggunakan pendekatan yang sama dengan GET dan POST, yaitu tes otomatis berbasis `MockMvc` dan verifikasi manual melalui Swagger UI. Setiap service memiliki 10 tes, dan tujuh di antaranya menguji ketiga operasi tersebut.

### Kode Sumber Pengujian

Tes berikut berada pada kelas yang sama, yaitu `TournamentControllerTest`.

```java
    @Test
    void putUpdatesWholeResourceAndReturns200() throws Exception {
        long id = createTournament();
        mockMvc.perform(put("/api/v1/tournaments/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"TOURNEX Finals\",\"gameTitle\":\"CS2\",\"description\":\"Update penuh\",\"status\":\"ONGOING\",\"startDate\":\"2026-12-02\",\"endDate\":\"2026-12-06\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value("TOURNEX Finals"))
            .andExpect(jsonPath("$.gameTitle").value("CS2"))
            .andExpect(jsonPath("$.status").value("ONGOING"));
    }

    @Test
    void putUnknownIdReturns404() throws Exception {
        mockMvc.perform(put("/api/v1/tournaments/999999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_BODY))
            .andExpect(status().isNotFound());
    }

    @Test
    void patchUpdatesOnlyProvidedFieldsAndReturns200() throws Exception {
        long id = createTournament();
        mockMvc.perform(patch("/api/v1/tournaments/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\":\"ONGOING\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("TOURNEX Open"))
            .andExpect(jsonPath("$.status").value("ONGOING"));
    }

    @Test
    void patchInvalidValueReturns400() throws Exception {
        long id = createTournament();
        mockMvc.perform(patch("/api/v1/tournaments/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\"}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void patchUnknownIdReturns404() throws Exception {
        mockMvc.perform(patch("/api/v1/tournaments/999999")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\":\"ONGOING\"}"))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteRemovesResourceAndReturns204() throws Exception {
        long id = createTournament();
        mockMvc.perform(delete("/api/v1/tournaments/" + id))
            .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/v1/tournaments/" + id))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteUnknownIdReturns404() throws Exception {
        mockMvc.perform(delete("/api/v1/tournaments/999999"))
            .andExpect(status().isNotFound());
    }

    private long createTournament() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_BODY))
            .andExpect(status().isCreated())
            .andReturn();
        Number id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        return id.longValue();
    }
```

Method bantu `createTournament()` membuat satu data terlebih dahulu agar PUT, PATCH, dan DELETE dapat diuji terhadap ID yang valid. Pada `TeamControllerTest` dan `MatchControllerTest`, method tersebut bernama `createTeam()` dan `createMatch()` dengan field yang disesuaikan.

### Skenario dan Hasil Pengujian

| No | Skenario | Endpoint | Hasil yang diharapkan | Status |
| :-: | :--- | :--- | :--- | :--- |
| 1 | PUT mengganti seluruh field | `PUT /api/v1/{resources}/{id}` | `200 OK`, semua field berubah | Lulus |
| 2 | PUT dengan ID tidak ada | `PUT /api/v1/{resources}/999999` | `404 Not Found` | Lulus |
| 3 | PATCH mengubah sebagian field | `PATCH /api/v1/{resources}/{id}` | `200 OK`, field lain tetap | Lulus |
| 4 | PATCH dengan nilai tidak valid | `PATCH /api/v1/{resources}/{id}` | `400 Bad Request` | Lulus |
| 5 | PATCH dengan ID tidak ada | `PATCH /api/v1/{resources}/999999` | `404 Not Found` | Lulus |
| 6 | DELETE data | `DELETE /api/v1/{resources}/{id}` | `204 No Content`, GET berikutnya `404` | Lulus |
| 7 | DELETE dengan ID tidak ada | `DELETE /api/v1/{resources}/999999` | `404 Not Found` | Lulus |

Total: **7 tes × 3 service = 21 tes**, seluruhnya lulus.

### Hasil Pengujian Otomatis

![Hasil pengujian otomatis PUT, PATCH, dan DELETE](docs/screenshots/put-patch-delete-test-run.png)

*Gambar 5. Hasil `mvn test` untuk pengujian PUT, PATCH, dan DELETE.*

### Hasil Pengujian Manual (Swagger UI)

![PUT mengembalikan 200 OK](docs/screenshots/put-patch-delete-swagger-put-200.png)

*Gambar 6. PUT `/api/v1/tournaments/{id}` berhasil dengan status `200 OK`.*

![PATCH mengembalikan 200 OK](docs/screenshots/put-patch-delete-swagger-patch-200.png)

*Gambar 7. PATCH `/api/v1/tournaments/{id}` dengan satu field berhasil dan field lain tidak berubah.*

![DELETE mengembalikan 204 No Content](docs/screenshots/put-patch-delete-swagger-204.png)

*Gambar 8. DELETE `/api/v1/tournaments/{id}` berhasil dengan status `204 No Content`.*

![PUT, PATCH, atau DELETE dengan ID tidak ada mengembalikan 404](docs/screenshots/put-patch-delete-swagger-404.png)

*Gambar 9. Operasi terhadap ID yang tidak ada mengembalikan status `404 Not Found`.*

### Alternatif Pengujian Manual dengan PowerShell

```powershell
# PUT mengganti seluruh data
$put = @{
  name        = "TOURNEX Finals"
  gameTitle   = "CS2"
  description = "Update penuh"
  status      = "ONGOING"
  startDate   = "2026-12-02"
  endDate     = "2026-12-06"
} | ConvertTo-Json

Invoke-RestMethod -Method Put `
  -Uri http://localhost:8081/api/v1/tournaments/1`
  -ContentType 'application/json' `
  -Body $put

# PATCH mengubah sebagian data
Invoke-RestMethod -Method Patch `
  -Uri http://localhost:8081/api/v1/tournaments/1`
  -ContentType 'application/json' `
  -Body '{"status":"COMPLETED"}'

# DELETE data
$r = Invoke-WebRequest -Method Delete `
  -Uri http://localhost:8081/api/v1/tournaments/1`
  -UseBasicParsing
$r.StatusCode
```

---

## IMPLEMENTASI API VERSI 2.0 (GET, POST)

Implementasi v2.0 pada iterasi ini mencakup operasi **GET** dan **POST**. Kode v2 ditempatkan pada paket terpisah (`v2`) di masing-masing service, sehingga perilaku dan kontrak v1.0 tidak berubah. Pendekatan ini memungkinkan kedua versi berjalan berdampingan pada aplikasi dan port yang sama.

### Build Properties

Tidak ada perubahan pada `pom.xml`. Seluruh kebutuhan implementasi v2 sudah tersedia dari dependency yang dipakai v1:

| Kebutuhan v2 | Penyedia |
| :--- | :--- |
| Pagination (`Page`, `Pageable`, `PageableDefault`) | `spring-boot-starter-data-jpa` |
| Validasi DTO (`@NotBlank`, `@NotNull`, `@Pattern`, `@Positive`) | `spring-boot-starter-validation` |
| Serialisasi `OffsetDateTime` | Jackson (bawaan `spring-boot-starter-web`) |
| Dokumentasi OpenAPI v2 | `springdoc-openapi-starter-webmvc-ui` |
| Java `record` untuk DTO | Java 17 |

Konfigurasi compiler tetap sama:

```xml
<release>17</release>
<parameters>true</parameters>
```

### Konfigurasi Deployment

Tidak ada perubahan pada `application.properties`. v1 dan v2 dibedakan melalui base path, bukan melalui port atau aplikasi terpisah.

| Service | Port | Base path v1 | Base path v2 |
| :--- | ---: | :--- | :--- |
| Tournament | 8081 | `/api/v1/tournaments` | `/api/v2/tournaments` |
| Team | 8082 | `/api/v1/teams` | `/api/v2/teams` |
| Match | 8083 | `/api/v1/matches` | `/api/v2/matches` |

Struktur paket v2:

```
tournament-service/src/main/java/id/tournex/tournament/v2/
├── TournamentV2Controller.java
├── TournamentV2Request.java
├── TournamentV2Response.java
├── TournamentV2Mapper.java
├── PageResponse.java
├── ErrorResponse.java
└── ApiExceptionHandler.java
```

Struktur yang sama diterapkan pada `team-service` (`id.tournex.team.v2`) dan `match-service` (`id.tournex.match.v2`).

Endpoint v2 juga otomatis muncul pada dokumentasi OpenAPI di `/v3/api-docs` dan Swagger UI, berdampingan dengan endpoint v1.

### Kode Sumber Utama GET & POST

#### `TournamentV2Controller.java`

```java
@RestController
@RequestMapping("/api/v2/tournaments")
@Tag(name = "Tournament API v2", description = "Operasi API Tournament versi 2.0")
public class TournamentV2Controller {

    private final TournamentRepository repository;

    public TournamentV2Controller(TournamentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Mengambil daftar turnamen dengan pagination")
    public PageResponse<TournamentV2Response> getAll(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String game,
            @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        Page<Tournament> page;
        if (status != null && game != null) {
            page = repository.findByStatusAndGameTitle(status, game, pageable);
        } else if (status != null) {
            page = repository.findByStatus(status, pageable);
        } else if (game != null) {
            page = repository.findByGameTitle(game, pageable);
        } else {
            page = repository.findAll(pageable);
        }
        return PageResponse.from(page.map(TournamentV2Mapper::toResponse));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Mengambil turnamen berdasarkan ID")
    public TournamentV2Response getById(@PathVariable Long id) {
        return TournamentV2Mapper.toResponse(findById(id));
    }

    @PostMapping
    @Operation(summary = "Menambah turnamen")
    public ResponseEntity<TournamentV2Response> create(@Valid @RequestBody TournamentV2Request request) {
        Tournament saved = repository.save(TournamentV2Mapper.toEntity(request));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(location)
                .body(TournamentV2Mapper.toResponse(saved));
    }

    private Tournament findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tournament dengan ID " + id + " tidak ditemukan"));
    }
}
```

#### DTO request dan response

```java
public record TournamentV2Request(
        @NotBlank String name,
        @NotBlank String game,
        String description,
        @NotBlank
        @Pattern(regexp = "DRAFT|PUBLISHED|ONGOING|COMPLETED|CANCELLED")
        String status,
        @NotNull OffsetDateTime startsAt,
        @NotNull OffsetDateTime endsAt
) {
}
```

```java
public record TournamentV2Response(
        Long id,
        String name,
        String game,
        String description,
        String status,
        OffsetDateTime startsAt,
        OffsetDateTime endsAt,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
```

#### Mapper v2

Mapper memetakan field kontrak v2 ke kolom entity v1, sehingga kedua versi memakai data yang sama.

```java
static TournamentV2Response toResponse(Tournament entity) {
    return new TournamentV2Response(
            entity.getId(),
            entity.getName(),
            entity.getGameTitle(),
            entity.getDescription(),
            entity.getStatus(),
            toOffsetDateTime(entity.getStartDate()),
            toOffsetDateTime(entity.getEndDate()),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
    );
}

static Tournament toEntity(TournamentV2Request request) {
    Tournament entity = new Tournament();
    entity.setName(request.name());
    entity.setGameTitle(request.game());
    entity.setDescription(request.description());
    entity.setStatus(request.status());
    entity.setStartDate(toLocalDate(request.startsAt()));
    entity.setEndDate(toLocalDate(request.endsAt()));
    return entity;
}
```

#### Pagination envelope

```java
public record PageResponse<T>(
        List<T> data,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
```

#### Penanganan error v2

`ApiExceptionHandler` dibatasi pada paket v2 sehingga format error v1 tidak berubah.

```java
@RestControllerAdvice(basePackages = "id.tournex.tournament.v2")
public class ApiExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatus(
            ResponseStatusException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.resolve(exception.getStatusCode().value());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(new ErrorResponse(
                codeFor(status),
                exception.getReason(),
                List.of(),
                OffsetDateTime.now(),
                request.getRequestURI()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<ErrorResponse.FieldError> details = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponse.FieldError(error.getField(), error.getDefaultMessage()))
                .toList();
        return ResponseEntity.badRequest().body(new ErrorResponse(
                "VALIDATION_ERROR",
                "Permintaan tidak valid",
                details,
                OffsetDateTime.now(),
                request.getRequestURI()
        ));
    }
}
```

#### Perbedaan implementasi per service

| Aspek | Tournament | Team | Match |
| :--- | :--- | :--- | :--- |
| Base path | `/api/v2/tournaments` | `/api/v2/teams` | `/api/v2/matches` |
| Field yang dipetakan | `game`, `startsAt`, `endsAt` | `shortName`, `game` | `startsAt` |
| Filter GET list | `status`, `game` | `game` | `tournamentId`, `status` |
| DTO | `TournamentV2Request` / `Response` | `TeamV2Request` / `Response` | `MatchV2Request` / `Response` |
| Repository query | `findByStatus`, `findByGameTitle`, `findByStatusAndGameTitle` | `findByGameTitle` | `findByTournamentId`, `findByStatus`, `findByTournamentIdAndStatus` |

#### Hasil pengujian v2

| Service | Tes v1 | Tes v2 | Total | Hasil |
| :--- | ---: | ---: | ---: | :--- |
| Tournament | 10 | 5 | 15 | Lulus |
| Team | 10 | 5 | 15 | Lulus |
| Match | 10 | 5 | 15 | Lulus |
| **Total** | **30** | **15** | **45** | **Lulus** |

Perintah pengujian:

```powershell
.\mvnw.cmd -B clean test
```

---

## IMPLEMENTASI API VERSI 2.0 (PUT, PATCH, DELETE)

Implementasi v2.0 dilengkapi dengan operasi **PUT**, **PATCH**, dan **DELETE** pada ketiga service. Seperti pada GET dan POST, kode ditempatkan di paket `v2` sehingga v1.0 tidak berubah. Ketiga operasi memakai entity dan tabel yang sama dengan v1.0 melalui mapper.

### Build Properties

Tidak ada perubahan pada `pom.xml`. Seluruh kebutuhan PUT, PATCH, dan DELETE sudah tersedia dari dependency yang dipakai sebelumnya:

| Kebutuhan v2 | Penyedia |
| :--- | :--- |
| `@PutMapping`, `@PatchMapping`, `@DeleteMapping` | `spring-boot-starter-web` |
| Parsing `Content-Type: application/merge-patch+json` | `MappingJackson2HttpMessageConverter` (mendukung `application/*+json`) |
| Validasi body PUT/PATCH (`@Valid`, `@Pattern`, `@Positive`) | `spring-boot-starter-validation` |
| `@ResponseStatus(HttpStatus.NO_CONTENT)` untuk DELETE | `spring-boot-starter-web` |

Karena `application/merge-patch+json` sudah dikenali oleh konverter Jackson bawaan, tidak diperlukan dependency atau konfigurasi tambahan.

### Konfigurasi Deployment

Tidak ada perubahan pada `application.properties` maupun port. Yang bertambah hanya route pada controller:

| Service | Port | Endpoint baru |
| :--- | ---: | :--- |
| Tournament | 8081 | `PUT`, `PATCH`, `DELETE` `/api/v2/tournaments/{id}` |
| Team | 8082 | `PUT`, `PATCH`, `DELETE` `/api/v2/teams/{id}` |
| Match | 8083 | `PUT`, `PATCH`, `DELETE` `/api/v2/matches/{id}` |

Ketiga endpoint otomatis muncul pada `/v3/api-docs` dan Swagger UI. Hasil verifikasi pada tournament-service menunjukkan method `put`, `patch`, dan `delete` terdaftar pada path `/api/v2/tournaments/{id}`, serta schema `TournamentV2PatchRequest` tersedia.

### Kode Sumber Utama PUT, PATCH, DELETE

#### Kontrak perilaku

| Operasi | Body | Sukses | Error |
| :--- | :--- | ---: | :--- |
| PUT | `*V2Request` lengkap | `200` + resource | `400`, `404`, `500` |
| PATCH | `*V2PatchRequest` sebagian, `Content-Type: application/merge-patch+json` | `200` + resource | `400`, `404`, `415`, `500` |
| DELETE | — | `204` tanpa body | `404`, `500` |

Keputusan desain:

1. **PUT bersifat replace penuh.** Seluruh field wajib dikirim; field yang tidak dikirim ditolak `400` oleh validasi DTO.
2. **PATCH bersifat merge patch sederhana.** Hanya field non-null yang diubah. `null` eksplisit diperlakukan sama dengan "tidak dikirim", karena membedakan *absent* dan *null* memerlukan dependency tambahan.
3. **ID diambil dari path**, sehingga tidak dapat diubah lewat body. `createdAt` dipertahankan, sedangkan `updatedAt` diperbarui otomatis oleh `@PreUpdate`.

#### `TournamentV2Controller.java` — method PUT, PATCH, DELETE

```java
@PutMapping("/{id}")
@Operation(summary = "Mengganti seluruh data turnamen")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Turnamen berhasil diperbarui"),
    @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
    @ApiResponse(responseCode = "404", description = "Turnamen tidak ditemukan"),
    @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
})
public TournamentV2Response update(
        @PathVariable Long id,
        @Valid @RequestBody TournamentV2Request request) {
    Tournament existing = findById(id);
    TournamentV2Mapper.applyRequest(existing, request);
    return TournamentV2Mapper.toResponse(repository.save(existing));
}

@PatchMapping(value = "/{id}", consumes = "application/merge-patch+json")
@Operation(summary = "Memperbarui sebagian data turnamen")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Turnamen berhasil diperbarui sebagian"),
    @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
    @ApiResponse(responseCode = "404", description = "Turnamen tidak ditemukan"),
    @ApiResponse(responseCode = "415", description = "Media type tidak didukung"),
    @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
})
public TournamentV2Response patch(
        @PathVariable Long id,
        @Valid @RequestBody TournamentV2PatchRequest request) {
    Tournament existing = findById(id);
    TournamentV2Mapper.applyPatch(existing, request);
    return TournamentV2Mapper.toResponse(repository.save(existing));
}

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
@Operation(summary = "Menghapus turnamen berdasarkan ID")
@ApiResponses({
    @ApiResponse(responseCode = "204", description = "Turnamen berhasil dihapus"),
    @ApiResponse(responseCode = "404", description = "Turnamen tidak ditemukan"),
    @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
})
public void delete(@PathVariable Long id) {
    repository.delete(findById(id));
}
```

#### DTO patch

DTO patch berisi field yang sama dengan DTO request, tetapi seluruhnya opsional. Anotasi `@Pattern` dan `@Positive` otomatis lolos ketika nilainya `null`, sehingga PATCH parsial tetap valid.

```java
public record TournamentV2PatchRequest(
        String name,
        String game,
        String description,
        @Pattern(regexp = "DRAFT|PUBLISHED|ONGOING|COMPLETED|CANCELLED") String status,
        OffsetDateTime startsAt,
        OffsetDateTime endsAt
) {
}
```

#### Mapper — `applyRequest` dan `applyPatch`

```java
static void applyRequest(Tournament entity, TournamentV2Request request) {
    entity.setName(request.name());
    entity.setGameTitle(request.game());
    entity.setDescription(request.description());
    entity.setStatus(request.status());
    entity.setStartDate(toLocalDate(request.startsAt()));
    entity.setEndDate(toLocalDate(request.endsAt()));
}

static void applyPatch(Tournament entity, TournamentV2PatchRequest patch) {
    if (patch.name() != null) {
        entity.setName(patch.name());
    }
    if (patch.game() != null) {
        entity.setGameTitle(patch.game());
    }
    if (patch.description() != null) {
        entity.setDescription(patch.description());
    }
    if (patch.status() != null) {
        entity.setStatus(patch.status());
    }
    if (patch.startsAt() != null) {
        entity.setStartDate(toLocalDate(patch.startsAt()));
    }
    if (patch.endsAt() != null) {
        entity.setEndDate(toLocalDate(patch.endsAt()));
    }
}
```

#### Perubahan `ApiExceptionHandler.java`

Dua handler ditambahkan agar status yang dihasilkan sesuai:

```java
@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
public ResponseEntity<ErrorResponse> handleUnsupportedMediaType(
        HttpMediaTypeNotSupportedException exception, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(new ErrorResponse(
            "UNSUPPORTED_MEDIA_TYPE",
            "Media type tidak didukung. Gunakan application/json untuk POST dan PUT, "
                    + "serta application/merge-patch+json untuk PATCH",
            List.of(),
            OffsetDateTime.now(),
            request.getRequestURI()
    ));
}

@ExceptionHandler(MethodArgumentTypeMismatchException.class)
public ResponseEntity<ErrorResponse> handleTypeMismatch(
        MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
    return ResponseEntity.badRequest().body(new ErrorResponse(
            "VALIDATION_ERROR",
            "Parameter '" + exception.getName() + "' tidak valid",
            List.of(new ErrorResponse.FieldError(
                    exception.getName(), "Nilai parameter tidak sesuai tipe data")),
            OffsetDateTime.now(),
            request.getRequestURI()
    ));
}
```

Tanpa kedua handler tersebut, kedua exception akan ditangkap `@ExceptionHandler(Exception.class)` dan dikembalikan sebagai `500`, sehingga status menjadi tidak akurat.

#### Perbedaan implementasi per service

| Aspek | Tournament | Team | Match |
| :--- | :--- | :--- | :--- |
| Base path | `/api/v2/tournaments` | `/api/v2/teams` | `/api/v2/matches` |
| Field yang diubah PUT | `name`, `game`, `description`, `status`, `startsAt`, `endsAt` | `name`, `shortName`, `game`, `description` | 8 field (termasuk 3 ID relasi dan skor) |
| Field patch | 6 field opsional | 4 field opsional | 8 field opsional |
| Validasi khusus PATCH | `@Pattern` pada `status` | — | `@Positive` pada 3 ID, `@Pattern` pada `status` |
| Konversi tanggal | `OffsetDateTime` → `LocalDate` | tidak ada | `OffsetDateTime` → `LocalDateTime` |
| Pesan 404 | "Tournament dengan ID …" | "Team dengan ID …" | "Match dengan ID …" |

Ketiga service memakai pola yang sama: `findById` → `apply*` → `save` → `toResponse`. Perbedaan hanya pada field domain, aturan validasi, dan konversi tipe tanggal.

### Hasil pengujian v2 PUT, PATCH, DELETE

Sembilan tes ditambahkan per service:

| No | Tes | Ekspektasi |
| ---: | :--- | :--- |
| 1 | PUT dengan body lengkap | `200`, seluruh field berubah |
| 2 | PUT dengan ID tidak ada | `404`, `code: NOT_FOUND` |
| 3 | PUT dengan body tidak valid | `400`, `code: VALIDATION_ERROR` |
| 4 | PATCH satu field saja | `200`, field lain tidak berubah |
| 5 | PATCH dengan ID tidak ada | `404` |
| 6 | PATCH dengan `application/json` | `415` |
| 7 | POST dengan `text/plain` | `415` + `code: UNSUPPORTED_MEDIA_TYPE` |
| 8 | DELETE resource yang ada | `204`, lalu `GET` mengembalikan `404` |
| 9 | DELETE dengan ID tidak ada | `404` |

| Service | Tes v1 | Tes v2 GET/POST | Tes v2 PUT/PATCH/DELETE | Total | Hasil |
| :--- | ---: | ---: | ---: | ---: | :--- |
| Tournament | 10 | 5 | 9 | 24 | Lulus |
| Team | 10 | 5 | 9 | 24 | Lulus |
| Match | 10 | 5 | 9 | 24 | Lulus |
| **Total** | **30** | **15** | **27** | **72** | **Lulus** |

Perintah pengujian:

```powershell
.\mvnw.cmd -B clean test
```

---

## KESESUAIAN IMPLEMENTASI DENGAN RANCANGAN

| Rancangan | Implementasi | Kesesuaian |
| :--- | :--- | :--- |
| PUT `/api/v1/tournaments/{id}` | `@PutMapping("/{id}")` pada `TournamentController` | Sesuai |
| PATCH `/api/v1/tournaments/{id}` | `@PatchMapping("/{id}")` pada `TournamentController` | Sesuai |
| DELETE `/api/v1/tournaments/{id}` | `@DeleteMapping("/{id}")` pada `TournamentController` | Sesuai |
| PUT/PATCH/DELETE `/api/v1/teams/{id}` | `TeamController` | Sesuai |
| PUT/PATCH/DELETE `/api/v1/matches/{id}` | `MatchController` | Sesuai |
| Sukses `200 OK` / `204 No Content` | PUT/PATCH `200`, DELETE `204` | Sesuai |
| `400 Bad Request` | Validasi `@Valid` (PUT) dan `Validator` (PATCH) | Sesuai |
| `404 Not Found` | `findById()` melempar `ResponseStatusException` | Sesuai |
| `500 Internal Server Error` | Default error handling Spring Boot | Sesuai |
| Dokumentasi OpenAPI | `@Operation` dan `@ApiResponses` + `/v3/api-docs` | Sesuai |
| Pengujian otomatis GET/POST | 3 tes per service menggunakan `MockMvc` | Sesuai |
| Pengujian otomatis PUT/PATCH/DELETE | 7 tes per service menggunakan `MockMvc` | Sesuai |
| GET/POST `/api/v2` | `TournamentV2Controller`, `TeamV2Controller`, `MatchV2Controller` | Sesuai |
| Pagination dan envelope v2 | `PageResponse.from(Page)` | Sesuai |
| Error response seragam v2 | `ApiExceptionHandler` pada paket v2 | Sesuai |
| Pemetaan field v2 | `TournamentV2Mapper`, `TeamV2Mapper`, `MatchV2Mapper` | Sesuai |
| PUT/PATCH/DELETE `/api/v2` | `update()`, `patch()`, `delete()` pada ketiga controller v2 | Sesuai |
| PATCH `application/merge-patch+json` | `@PatchMapping(consumes = "application/merge-patch+json")` | Sesuai |
| `204 No Content` pada DELETE v2 | `@ResponseStatus(HttpStatus.NO_CONTENT)` | Sesuai |
| `415 Unsupported Media Type` | `ApiExceptionHandler.handleUnsupportedMediaType` | Sesuai |

---

## CATATAN DAN BATASAN

1. **Dokumentasi status error di OpenAPI.** Controller telah memakai `@ApiResponses` untuk mendokumentasikan `400`, `404`, dan `500`, sehingga status tersebut muncul pada spesifikasi OpenAPI yang dihasilkan.
2. **Spesifikasi OpenAPI statis.** Spesifikasi OpenAPI hasil ekspor disimpan di `docs/openapi/` dalam format JSON. Spesifikasi juga tetap dapat diakses secara dinamis melalui `/v3/api-docs` dan Swagger UI.
3. **Perilaku PATCH.** Menerapkan *partial update*: field yang tidak dikirim (`null`) dianggap tidak berubah. Dengan demikian, PATCH belum mendukung pengosongan field secara eksplisit (`null` sebagai nilai yang disengaja).
4. **Penyimpanan data.** Setiap service menggunakan H2 in-memory dengan `ddl-auto=create-drop`, sehingga data hilang ketika aplikasi dihentikan.
5. **Cakupan deployment.** Konfigurasi saat ini ditujukan untuk pengembangan lokal dan CI. Autentikasi, API Gateway, komunikasi antar-service, serta database persisten belum termasuk dalam iterasi ini.
6. **Screenshot pengujian.** Gambar pada bagian pengujian masih berupa *placeholder* di `docs/screenshots/`. Ganti file tersebut dengan tangkapan layar asli setelah menjalankan service dan Swagger UI.
7. **Cakupan v2.0.** Implementasi v2.0 mencakup operasi GET, POST, PUT, PATCH, dan DELETE. PUT bersifat *replace* penuh, PATCH memakai `application/merge-patch+json` dengan semantik *merge patch* sederhana, dan DELETE mengembalikan `204 No Content`.
8. **Sumber data v1 dan v2.** v2 memakai tabel yang sama dengan v1 melalui mapper, sehingga data yang dibuat lewat v1 dapat dibaca lewat v2 dan sebaliknya. Status pada v2 divalidasi menggunakan pola; data lama v1 dengan status di luar daftar enum tetap dapat dibaca.
9. **Respons `415`.** Ketidakcocokan `Content-Type` pada PATCH dideteksi Spring saat pencocokan handler (atribut `consumes`) sebelum controller dipanggil, sehingga respons `415` tidak memiliki body. Respons `415` yang berasal dari konversi body tetap memakai format `ErrorResponse`.