
# Dokumentacja

## Item:

Klasa reprezentująca przedmioty, zawierająca informacje o nazwie (również tej w kodzie), opisie i cenie. Posiada standardowy konstruktor oraz wykorzystywane gettery i setery.

## Equipment (Podklasa Item):

Abstrakcyjna klasa reprezentującą przedmioty posiadające wytrzymałość, zawierająca informacje o obecnej i całkowitej wytrzymałości. Posiada standardowy konstruktor i wykorzystywany getter oraz deklarację metody reduceDurability().

## Weapon (Podklasa Equipment):

Klasa reprezentująca bronie, zawierająca informacje o obrażeniach. Posiada standardowy konstruktor i wykorzystywany getter oraz implementację metody reduceDurability().

## Armor (Podklasa Equipment):

Klasa reprezentująca zbroje, zawierająca informacje o ochronie. Posiada standardowy konstruktor i wykorzystywany getter oraz implementację metody reduceDurability().

## Dungeon:

Klasa zawierająca układ wszystkich pokoi w grze. Posiada pola statyczne reprezentujące ilość pokoi danej trudności oraz indeks pokoju w których obecnie się znajduje gracz. Konstruktor generują w sposób losowy układ pokoi i zapisuje go do listy o nazwie rooms. Posiada również getter obecnego pokoju i metodę przechodzącą do następnego pokoju.

## Room:

Klasa zawierająca informacje o układzie danego pokoju, startowej pozycji pokoju oraz o liście potworów znajdujących się w nim, a także o poziomie trudności. Konstruktor poza podstawowymi czynnościami przyjmuje tablicę stringów reprezentujących układ pokoju i na tej podstawie generuje tablicę dwuwymiarową elementów klasy MapObject oraz listę potworów wraz z ich początkowymi lokalizacjami. Klasa posiada wykorzystywane gettery i setery. Z ciekawszych posiada metody: removeCollectible(int x, int y) (która podmienia element typu MapObject o współrzędnych (x, y) na podłogę lub otwartą skrzynkę), disarmTrap(int x, int y), openDoor(int x, int y), killMonster(int x, int y), a także isMonsterHere(int x, int y) (które robią to co ich nazwa wskazuje).

## MapObject:

Klasa zawierająca informacje o pojedynczym „kafelku” pokoju. Posiada nazwę, pozycję a także informację czy jest collectible. Posiada standardowy konstruktor oraz wykorzystywane gettery.

## Entity (Podklasa MapObject):

Abstrakcyjna klasa reprezentująca postacie ze zdrowiem atakiem i obroną. Posiada standardowy konstruktor, wykorzystywane gettery i setery oraz metody damage(int amount) oraz heal(int amount), które kolejną zadają obrażenia i leczą daną postać.

## Monster (Podklasa Entity):

Klasa reprezentująca potwory posiadają enum wszystkich potworów oraz informacje o tym czy dany potwór powinien podążać za graczem czy nie. Enum Type posiada te same informacje jakie powinien posiadać potwór, standardowy konstruktor, gettery oraz metodę randomType(…), która w zależności od poziomu trudności zwraca odpowiednio trudnego, losowego potwora z tego enum.  Klasa zawiera również pole NUMBER_ OF_TYPES_AT_TIME, która służy do celów wyliczeniowych w metodzie randomType(…).

## Player (Podklasa Entity):

Klasa reprezentująca gracza. Posiada bardzo dużą ilość pól i metod, które wszystkie oznaczają i robią to na co wskazuje ich nazwa.

## NPC (Podklasa MapObject):

Klasa reprezentującą NPC napotykanych w grze. Można z nimi wchodzić w interakcję, które wymagają płatności, dlatego klasa ta zawiera informacje o napiwku, który każdy NPC ustala sobie sam, na podstawie maksymalnego napiwku podawanego w konstruktorze.

## Shop (Podklasa NPC):

Klasa reprezentująca sklep. W konstruktorze generuje 3 przedmioty i ustala ich ceny. Posiada również metodę usuwającą zakupiony przedmiot.

## Chest (Podklasa MapObject):

Klasa reprezentująca skrzynkę. Każda skrzynka zawiera jeden przedmiot generowany losowo w konstruktorze oraz pewną ilość złota. Posiada wykorzystywane gettery.

## MessageBox:

Klasa przetrzymująca kolejkę wiadomości, które zostaną wyświetlone na ekranie wraz z czasem jaki mają być wyświetlone. Posiada metody addMessage i getMessage, które pozwalają dodawać i otrzymywać wiadomości z kolejki.

## GameLoop (implementująca ActionListener):

Klasa nasłuchująca przycisków wciskanych przez gracza i odwołująca się bezpośrednio w działaniu do klasy GameLogic.

## GameLogic:

Klasa przetrzymująca całą logikę gry. Posiada po jednej instancji klas: Timer, Random, Player, Dungeon, Room (currentRoom), MessageBox. Posiada również tablicę aktywnych potworów. Posiada motedy startGame() i init() rozpoczynające grę i generujące instancje powyższych klas. Motody: movePlayer(…) sprawdza czy gracz może wejść na dany „kafelek” i jeśli tak to tam wchodzi, wywołując odpowiednie interakcje z elementami terenu. handleInteraction() uruchamiana jest po kliknięciu E, próbuje wykonać interakcje wokół gracza. pickupItem(…) sprawdza czy jakiś przedmiot znajduje się na danej pozycji i wykonuje z nim odpowiednią interakcję. buyItem(…) próbuje kupić przedmiot w danym sklepie i jeśli się uda to dodaje go do ekwipunku. usePlayerItem(…) używa przedmiotu znajdującego się w danym miejscu ekwipunku po kliknięciu w niego myszką. moveMonsters() sprawia że każdy potwór wykonuje jeden ruch (w zależności od tego czy ma gonić gracza czy nie). Sprawdza ona również „kolizje” z graczem i wykonywanie ataku. detectMonsterToFight(…) sprawdza czy gracz nie wszedł w potwora, a jeśli to zrobił to wykonuje atak. Pozostałe metody robią to na co wskazuje ich nazwa.

## Items:

Klasa zawierająca 3 enumy reprezentujące wszystkie consumable, bronie i zbroje dostępne w grze. Każdy enum zawiera podstawowe informacje o przedmiocie oraz metody toItem()/toWeapon()/toArmor(), które zwracają objekt klasy lub podklasy Item reprezentowany przez element w enumie. Każdy enum posiada również metodę zwracającą losowy element (w przypadku broni i zbroi jakość tego elementu zależy od poziomu trudności pokoju, w którym jest ona wywoływana)

## Layouts:

Klasa zawierająca enum zawierające informacje o wszystkich możliwych układach pokoi. Posiada metodę zwracającą losowy pokój na podstawie układu.

## Textures:

Klasa służaca do obsługi plików typu .png.

## Main:

Klasa, od której wszystko się zaczyna.

## Keyboard (implementująca KeyListener):

Klasa służąca do obsługi klawiatury.

## Mouse (implementująca MouseListener):

Klasa służąca do obsługi myszki.

## Screen (podklasa JPanel) i Window:

Klasy tworzące nowy obiekt reprezentujący okno gry.

## Renderer:

Klasa renderująca całe gui. Wyświetla m.in. gracza, moby i level. Posiada też dwie charakerystyczne metody centerPlayerX i centerPlayerY, które służą do przesuwania mapy względem gracza (tym samym sprawiają efekt wycentrowanej kamery). Ponadto wyświetlany jest cały interfejs użytkownika, w tym status, inventory, sklep i kowal. Renderer zajmuje się też wyświetlaniem komunikatów oraz ekranu początkowego i końcowego.
