ADMIN APLIKACIJA - AUTOMOBILSKI EKSPERIMENTI

1. Prvo u MySQL/phpMyAdmin pokrenuti SQL skriptu za bazu automobilski_eksperimenti.
2. Proveriti database.cfg:
   host=localhost
   port=3306
   db=automobilski_eksperimenti
   user=root
   password=root
3. Korisnicki nalozi su u fajlu users.txt.
   Pocetni nalog:
   korisnicko ime: admin
   lozinka: admin
4. U IntelliJ otvoriti ovaj folder kao Maven projekat.
5. Pokrenuti Main klasu ili komandu:
   mvn javafx:run

Aplikacija je napravljena za ulogu administratora i ima:
- login/sign up preko tekstualnog fajla users.txt,
- pregled zakazanih sesija i eksperimenata,
- promenu podataka izabrane zakazane sesije,
- pregled i brisanje laboratorije samo ako u njoj ne radi nijedan istrazivac.

Kod je podeljen po MVC principu:
- model: DTO klase, repozitorijumi i rad sa podacima,
- view: JavaFX prikaz i tabele,
- controller: reakcije na klik dugmeta i izbor reda u tabeli.

NAPOMENA ZA TEST BRISANJA LABORATORIJE:
U generisanim podacima skoro svaka laboratorija ima istrazivaca i druge povezane podatke,
zato brisanje moze namerno da bude odbijeno. To je dobar dokaz pravila.
Ako zelite da testirate uspesno brisanje, mozete u phpMyAdmin dodati posebnu laboratoriju
bez istrazivaca i bez povezanih sesija/alata:

INSERT INTO tip_laboratorije(naziv, opis)
VALUES ('Tip za test brisanja', 'Privremeni tip laboratorije za proveru aplikacije');

INSERT INTO laboratorija(tip_laboratorije_id, naziv, opis_lokacije,
maksimalno_opterecenje_kn, minimalna_temperatura_c, maksimalna_temperatura_c,
maksimalno_trajanje_testa_h, status_kalibracije)
VALUES (LAST_INSERT_ID(), 'Laboratorija za test brisanja', 'Privremena lokacija',
100.00, -10.00, 80.00, 24, 'validna');

DODATO: upravljanje istrazivacima u laboratorijama
---------------------------------------------------
U tabu "Istraživači u laboratorijama" mogu se dodati ili ukloniti veze iz tabele rad_u_laboratoriji.
To je korisno za testiranje administratorskog zahteva: laboratorija sme da se obrise samo ako u njoj ne radi nijedan istrazivac.

Za test brisanja laboratorije:
1. Otvori tab "Istraživači u laboratorijama".
2. Izaberi red za laboratoriju koju želiš da obrišeš.
3. Klikni "Ukloni istraživača iz laboratorije".
4. Vrati se na tab "Laboratorije".
5. Klikni "Ucitaj laboratorije".
6. Izaberi laboratoriju koja sada ima 0 istraživača.
7. Klikni "Obrisi izabranu laboratoriju".
