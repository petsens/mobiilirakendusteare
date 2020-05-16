# Tujupäevik
  
## Autorid: Caupo Helvik, Marko Rillo, Peeter Roop, Priit Laupa, Tatjana Kuznetsova
## Demo video:  
https://www.youtube.com/watch?v=-YegtPLhLp4

### Kuidas alla laadida ja käivitada:
Hetkeseisuga ei ole saadaval installfaili, seega rakenduse kasutamiseks tuleks see repo enda arvutisse kloonida ning läbi Android Studio käivitada.

### Vajalik info käivitamiseks:
1. Android Studio versioon 3.6.1
2. Minimaalselt vajalik Android versioon 4.1
3. Testimiseks on kasutatud enamasti emulaatorit: Pixel 2 API 28
4. Testimiseks kasutatud Androidi versioonid: 7.0 ja uuemad(enamasti 9.0)

### Vaated:
1. Avaleht  
2. Mis tunne on?
3. Miks ma nii tunnen? 
4. Mis tempoga ma sõidan?
5. Palju energiat mul on? 
6. Kuidas mul läinud on? (üldine statistika)
7. Tempode statistika
8. Tujude statistika

### Esialgsed vaated
![Disaini mockupid](https://github.com/petsens/mobiilirakendusteare/blob/master/Tujupaevik_mobiilirakendus_mockups.png)

### Esialgne kavand: 
https://www.figma.com/file/XXStMrgNGjNksw7LjSuccQUn/Tujup%C3%A4evik?node-id=0%3A1

### Kujustus: 
https://www.figma.com/file/ZtzGUW2zeUcnFibimJVFhZ/AndroidDesign?node-id=0%3A1

### Dokumentatsioon:
Iga vaade, kus saab andmeid sisestada, salvestab andmed JSON faili vastava kuupäeva alla.
See JSON fail luuakse rakenduse avamisel juhul, kui seda juba olemas ei ole ning salvestatakse rakenduse failide hulka.

Funktsioon 'addDataToFile' võtab esimeseks parameetriks ühe järgneva stringi: "Mood", "Reason", "Tempo" või "Fuel".

See on vajalik, et JSON formaati salvestataks andmed järgnevalt: DATE => [MoodArray, ReasonArray, TempoArray, FuelArray], sest statistika vaade näitab salvestatud andmeid päeva kaupa. 

Sellisel juhul saab kasutaja sisestada päevas mitu kannet, mida koondatakse kokku statistika vaate diagrammides.
