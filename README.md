# Android-labbar

## Lab 1
1. Androids R innehåller samtliga IDn för alla resurser i res-mappen. 
Till exempel sparas samtliga komponenter som skapas i xml-filerna här. 
Därav kan vi från MainActivity.java använda eller 
manipulera en komponent med IDt "button" genom att kalla på R.id.button.

2. Gradle är ett system för att kompilera och bygga applikationen.
Gradle Sync ser till att ladda ner och installera alla dependencies som
är specificerade i gradle.build.

3. Normalt sett byggs hela APKn när man kompilerar koden och bygger appen, men 
Instant run används för att spara tid och resurser i utvecklingen genom att 
endast uppdatera de delar som påverkas av de ändringar som genomförts i koden 
sen den senaste builden. 

## Lab 2
1. AppCompatActivity har att göra med 'app bar', eller 'action bar', högst upp i 
appen. Denna kan bete sig olikt beroende på vilken version av android som appen 
befinner sig på. Vill man att den ska bete sig konsistent över flera olika 
versioner bör man extenda AppCompatActivity.

2. En ListActivity är en aktivitet som innehåller en ListView, vilket är en lista 
av element (hämtat från en array) som man kan scrolla vertikalt i och har 
event handlers som kan användas för att hantera när en användare trycker på ett
av elementen. Ett ListFragment är liknande en ListActivity, med skillnaded att 
fragmentet kan bytas ut utan att behöva byta ut hela vyn. En vy kan dessutom 
kan dessutom innehålla flera fragment, där ett av dessa är ett ListFragment.

3. Det viktigaste (?) jag googlat under den här labben är hur man definierar 
layouts för olika skärmstorlekar.

## Lab 3
1. Externa bibliotek läggs till i ett android-projekt genom att lägga till 
bibliotekets paketnamn i build.gradle-filen i dependencies,
och sedan synka projektet.

2. JavaBean är en standard för en java-klass, som uppfyller följande konventioner: 
    * Alla funktioner och variabler är privata och har setters och getters.
    * Implementerar Serializable.
    * Har en publik konstruktor som ej tar argument.

3. Det viktigaste jag har behövt googla är exempel på requests med volley-biblioteket.