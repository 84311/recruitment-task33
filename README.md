## Analiza

Otrzymany kod jest krótki i prosty, więc niezbyt jest co analizować. Występuje kompozycja między klasą ```Wall```, a
typem ```Block```. ```CompositeBlock``` z ```Block``` tworzą natomiast strukturę w stylu wzorca projektowego
*Composite*.
Zadanie jest trochę trudniejsze niż się wydaje, z uwagi na możliwość zagnieżdżania bloków.

## Założenia

- Zakładam, że ```CompositeBlock``` może zawierać inne ```CompositeBlock```, co może prowadzić do powstawania *
  *rekurencyjnych struktur**. Przewiduję również przypadki ```CompositeBlock``` z pustą listą lub ```null```, chociaż
  uważam, że taka możliwość powinna być zabroniona.
- Zakładam, że ```CompositeBlock``` sam w sobie nie ma koloru ani materiału - moim zdaniem byłoby to bez sensu.

## Rozwiązanie

- Użyłem Mavena, żeby wygodnie skorzystać z JUnit.
- Napisałem testy jednostkowe - staram się to robić zawsze.
- Usunąłem komentarze, uznałem je za zbędne, ponieważ nazwy metod w otrzymanym kodzie są samodokumentujące się.
- Przypadkowo zmieniłem na początku widoczność interfejsów bloków na publiczne, a więc pogrupowałem też klasy w pakiety.
  Mógłbym to odwrócić, wrzucając wszystko w jeden pakiet, a dla testów przyznać dostęp pakietowi z testami lub użyć
  mechanizmu *Reflection* albo gotowego narzędzia na tym opartego, np. *JailBreak*. Mógłbym także umieścić testy w tym
  samym pakiecie, ale wydaje mi się, że to niezbyt dobra praktyka.
- Nadpisałem domyślny konstruktor w klasie ```Wall```, tworzący pustą listę, aby zapobiec **NPE**. Dlatego też
  utworzyłem drugi konstruktor, aby pozostawić możliwość wykorzystania innej listy.

## Wykorzystane narzędzia

- Maven
- JUnit 5
- Intellij IDEA