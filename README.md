# Tujupäevik
  
Koostajad: Caupo Helvik, Marko Rillo, Peeter Roop, Priit Laupa, Tatjana Kuznetsova

Vaated:
1. Avaleht  
2. Mida tunnen praegu  
3. Miks ma nii tunnen  
4. Kui kiire mul on  
5. Palju energiat on  
6. Kuidas mul läinud on  

![Disaini mockupid](https://github.com/petsens/mobiilirakendusteare/blob/master/Tujupaevik_mobiilirakendus_mockups.png)

Esialgne kavand: https://www.figma.com/file/XXStMrgNGjNksw7LjSuccQUn/Tujup%C3%A4evik?node-id=0%3A1
Kujustus: https://www.figma.com/file/ZtzGUW2zeUcnFibimJVFhZ/AndroidDesign?node-id=0%3A1
  
Documentation:
Function 'addDataToFile' takes into 1th parameter following strings: Mood, Reason, Tempo or Fuel  
Otherwise will throw exception. We need this like this so json would be DATE => [MoodArray, ReasonArray, TempoArray, FuelArray] because the last summary page needs to be per day those things and in this way user can enter multiple entries to one day. So we can round up those values to show in the last summary page.