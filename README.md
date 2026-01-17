**Java Satranç Oyunu**

Bu proje, herhangi bir hazır oyun motoru veya üçüncü parti oyun kütüphanesi kullanılmadan, tamamen Java programlama dili ve JPanel sınıfı temel alınarak geliştirilmiş nesne yönelimli (OOP) bir satranç motorudur. Projenin temel amacı, satranç kurallarının matematiksel algoritmalarla işlendiği, sağlam bir mimariye sahip bir oyun geliştirmektir.

Proje, görsel arayüz tasarımı için Java'nın JPanel yapısını kullanır. Satranç tahtası, her biri birer JPanel olan 64 karelik bir ızgara sistemi (grid) üzerine kurulmuştur ve taşların hareketi bu paneller arasındaki koordinat sistemine göre yönetilir.

**Projenin Teknik Özellikleri ve Mimari**

Proje, Sorumlulukların Ayrılığı (Separation of Concerns) ilkesine dayanarak tasarlanmıştır. Görsel arayüz ve oyun mantığı iç içe geçmiş gibi görünse de, arka planda her taş kendi hareket kurallarını bilen bağımsız birer nesne olarak modellenmiştir.

**1. JPanel Üzerine Kurulu Izgara Sistemi**

Oyun tahtası, 8x8 boyutunda, iki boyutlu bir JPanel dizisi (JPanel[][] loc) kullanılarak oluşturulmuştur. Her bir kare, bağımsız bir panel olarak tanımlanmış ve MouseListener olayları ile dinlenmektedir. Bu yapı sayesinde, tıklanan karenin koordinatları algılanır ve üzerindeki taş (Component) ile etkileşime geçilir. Taşlar, bu panellerin üzerine eklenen ve Pieces sınıfından türetilen grafik nesneleridir.

**2. Nesne Yönelimli Taş Mantığı (OOP)**

Oyundaki her taş (Pawn, Rook, Bishop, Knight, Queen, King), soyut bir Pieces sınıfından türetilmiştir. Bu yapı sayesinde Polimorfizm (Çok Biçimlilik) kullanılmıştır. Oyun motoru, hareket ettirilmek istenen taşın türünü bilmek zorunda kalmadan, sadece isCanMove metodunu çağırarak hareketin o taşın doğasına uygun olup olmadığını denetler.

**3. Hamle Simülasyonu ve Geri Alma Algoritması**

Projenin en güçlü yanı, "Move Simulation & Rollback" (Hamle Simülasyonu ve Geri Alma) tekniğini kullanan güvenlik algoritmasıdır. Bir oyuncu hamle yapmak istediğinde, motor şu adımları izler: Hamle geçici olarak, sanal bir ortamda gerçekleştirilir. Oluşan yeni tahta durumu analiz edilir ve oyuncunun kendi şahını tehdit altında bırakıp bırakmadığı (isKingUnderAttack) kontrol edilir. Eğer şah tehdit altındaysa, hamle "illegal" kabul edilir ve taşlar eski konumlarına anında geri döndürülür (Rollback). Bu sayede kurallara aykırı hamle yapılması imkansız hale getirilir.

**4. Şah Mat ve Pat Kontrolü**

Oyunun bitiş koşulları, "Brute-Force" benzeri bir tarama algoritmasıyla kontrol edilir. Sıra kendisine gelen oyuncunun şahı tehdit altında olsun veya olmasın, oyun motoru o oyuncunun tahtadaki tüm taşlarını ve bu taşların gidebileceği tüm olası kareleri simüle eder. Eğer oyuncunun şahını kurtarabileceği veya güvenli bir hamle yapabileceği tek bir senaryo bile yoksa oyun sona erer.

**Desteklenen Satranç Kuralları**

Oyun, standart FIDE satranç kurallarını tam olarak desteklemektedir:

Temel Hareketler: Tüm taşların standart hareket ve yeme kuralları matematiksel sınır kontrolleriyle (ArrayIndexOutOfBounds önlemleri dahil) kodlanmıştır.

Rok (Castling): Şah ve Kalenin belirli şartlar altında yer değiştirdiği özel hamle, koordinat takibi yapılarak oyuna entegre edilmiştir.

Geçerken Alma (En Passant): Piyonların özel hareketi olan "Geçerken Alma", oyun döngüsü içinde geçici bir hafıza değişkeni kullanılarak takip edilir ve sadece hamle hakkı doğduğunda aktif olur.

Piyon Terfisi (Promotion): Karşı kıyıya ulaşan piyonlar için dinamik bir seçim penceresi açılır ve piyon; Vezir, Kale, Fil veya At taşlarından birine dönüştürülür.

**Kurulum ve Çalıştırma**

Proje standart bir Java projesi yapısındadır. Kaynak kodları indirdikten sonra herhangi bir Java IDE'si (IntelliJ IDEA, Eclipse vb.) ile derleyip çalıştırabilirsiniz.

Projenin çalışması için Main sınıfının çalıştırılması yeterlidir. Görsellerin yüklenebilmesi için res klasörünün proje ana dizininde bulunduğundan emin olunmalıdır.

Geliştirici Notları
Bu proje, Java'da algoritma kurma, nesne yönelimli tasarım ve JPanel ile özel arayüz geliştirme yeteneklerini göstermek amacıyla hazırlanmıştır. Kod yapısı, ileride eklenebilecek Yapay Zeka (AI) modülleri için uygun bir altyapıya sahiptir.



EN **Java Chess Game**

This project is an object-oriented (OOP) chess engine developed entirely based on the Java programming language and the JPanel class, without using any ready-made game engine or third-party game library. The main goal of the project is to disseminate a game with a robust architecture where taunts are handled through expansion.

The project can use Java's JPanel for visual design. The chessboard is built on a grid of 64 squares, each a JPanel, and the movement of the pieces is managed according to the coordinate system between these panels.

**Technical Specifications and Architecture of the Project**

The project was designed based on the principle of Separation of Responsibilities. Although visual changes and game logic appear intertwined, each piece in the background is modeled as an independent object that can move on its own.

**1. Grid System Based on JPanel**

The game board contains a two-dimensional array of 8x8 looped JPanels (JPanel[][] loc). Each square is developed as an independent panel and listened to via MouseListener events. This structure allows the coordinates of the clicked square to be detected, and interaction with the piece (component) on it is initiated. Pieces are the weight of these panels and graphic elements derived from the Pieces class.

**2. Object-Oriented Piece Logic (OOP)**

Each piece in the game (Pawn, Rook, Bishop, Knight, Queen, King) is derived from an abstract Pieces class. This structure utilizes Polymorphism. The game engine checks whether the move is appropriate for the object to be moved by simply calling the isCanMove method in the package that needs to be known.

**3. Move Simulation and Undo Algorithm**

The project's strongest feature is its security enhancement using "Move Simulation and Undo" technologies. When a player wants to make a move, the engine follows these steps: The move is temporarily held for a virtual period of time. The new board situation is analyzed, and it is checked whether the player has put their king under threat (isKingUnderAttack). If the king is under threat, the move is considered "illegal," and the pieces are immediately reverted to their original positions (Undo). This makes it impossible to make an illegal move.

**4. Checkmate and Stalemate Control**

The game's end conditions are checked using a "Brute-Force"-like scan. Whether the player whose turn it is has their king under threat or not, the game engine simulates all of that player's pieces on the board and all possible squares they could move to. If there is not a single scenario where the player can save their king or design a safe village, the game ends.

**Supported Chess Rules**

The game ensures full support for standard FIDE taunts:

Basic Moves: All pieces have standard move and capture rules coded with storage bounds controls (including ArrayIndexOutOfBounds measures).

Castling: A special move where the King and Rook are swapped under specific conditions; coordinate management is integrated into the game.

En Passant: The "En Passant" special move for pawns is tracked using a temporary memory variable within the game description and only becomes active when a move is due.

Pawn Promotion: A dynamic selection window opens for pawns that reach the opposite side, and the pawn is transformed into one of the following pieces: Queen, Rook, Bishop, or Knight.

**Installation and Operation**

This project is a standard Java project. After downloading the source code, you can compile and run it with any Java IDE (IntelliJ IDEA, Eclipse, etc.).

To run the project, it is sufficient to run the main class. Ensure that the images are located in the project's main directory in the `res` folder for loading.

Developer Notes

This project was created to demonstrate the development of object-oriented design and custom development with JPanel in Java. The code structure provides a suitable infrastructure for Artificial Intelligence (AI) modules that may be added in the future.
