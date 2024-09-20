package com.tnillumination.truthordare

import android.os.Bundle
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisiere die Layouts
        val layoutLibrarySelection: LinearLayout = findViewById(R.id.layout_library_selection)
        val layoutTruthOrDare: LinearLayout = findViewById(R.id.layout_truth_or_dare)

        // Initialisiere die Buttons
        val btnGood: Button = findViewById(R.id.btn_good)
        val btnVeryGood: Button = findViewById(R.id.btn_very_good)
        val btnTruth: Button = findViewById(R.id.btn_truth)
        val btnDare: Button = findViewById(R.id.btn_dare)
        val btnBack: Button = findViewById(R.id.btn_back)
        val btnExit: Button = findViewById(R.id.btn_exit)

        // TextView für die Fragen/Antworten
        val tvQuestion: TextView = findViewById(R.id.tv_question)

        // Logik für den Beenden-Button mit Dialog
        btnExit.setOnClickListener {
            showExitConfirmationDialog()
        }

        // Logik für die Bibliotheksauswahl
        btnGood.setOnClickListener {
            layoutLibrarySelection.visibility = View.GONE // Verstecke Bibliotheksauswahl
            layoutTruthOrDare.visibility = View.VISIBLE // Zeige Wahrheit oder Pflicht Layout
            startGame(true, tvQuestion) // Starte das Spiel mit der ersten Bibliothek
        }

        btnVeryGood.setOnClickListener {
            layoutLibrarySelection.visibility = View.GONE // Verstecke Bibliotheksauswahl
            layoutTruthOrDare.visibility = View.VISIBLE // Zeige Wahrheit oder Pflicht Layout
            startGame(false, tvQuestion) // Starte das Spiel mit der zweiten Bibliothek
        }

        // Logik für den Zurück-Button
        btnBack.setOnClickListener {
            layoutTruthOrDare.visibility = View.GONE // Verstecke Wahrheit oder Pflicht Layout
            layoutLibrarySelection.visibility = View.VISIBLE // Zeige Bibliotheksauswahl
        }
    }

    // Funktion, um den Exit-Dialog anzuzeigen
    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("App beenden")
        builder.setMessage("Möchtest du die App wirklich beenden?")
        builder.setPositiveButton("Ja") { dialog: DialogInterface, which: Int ->
            finish() // Schließt die App
        }
        builder.setNegativeButton("Nein") { dialog: DialogInterface, which: Int ->
            dialog.dismiss() // Schließt den Dialog, macht nichts
        }
        builder.create().show()
    }


    // Funktion, die das Spiel startet
    private fun startGame(useFirstLibrary: Boolean, tvQuestion: TextView) {
        // Bibliothek 1: "Ihr kennt euch gut"
        val truthTasksGood = listOf(
            "Hast du jemals jemanden geküsst, den du eigentlich nicht küssen wolltest?",
            "Hast du jemals jemandem die Wahrheit verschwiegen, weil du Angst hattest, ihn zu verletzen?",
            "Hast du jemals jemanden betrogen?",
            "Was war dein peinlichstes Erlebnis in der Öffentlichkeit?",
            "Hast du jemals etwas über jemanden gesagt, das du bereut hast?",
            "Was ist die größte Lüge, die du jemals erzählt hast?",
            "Hast du jemals über deinen Beziehungsstatus gelogen?",
            "Was ist deine größte Schwäche in einer Beziehung?",
            "Welches Geheimnis hast du vor deinen Eltern versteckt?",
            "Hast du jemals etwas Wertvolles verloren und es nie zugegeben?",
            "Was ist dein peinlichstes Hobby?",
            "Hast du jemals etwas Unanständiges am Arbeitsplatz gemacht?",
            "Was ist das Dümmste, was du in einer Beziehung getan hast?",
            "Hast du jemals einem Freund seine Liebe gestanden und es bereut?",
            "Was ist das Peinlichste, das dir jemals beim Flirten passiert ist?",
            "Was ist das Unanständigste, was du je zu jemandem gesagt hast?",
            "Hast du jemals eine Beziehung geführt, nur weil du Angst hattest, allein zu sein?",
            "Hattest du jemals das Bedürfnis, jemandem die Meinung zu sagen, hast es aber nicht getan?",
            "Hast du jemals Gefühle für den Partner eines Freundes entwickelt?",
            "Was war deine schlechteste Erfahrung beim Daten?",
            "Was ist das Ungewöhnlichste, das du in einem öffentlichen Raum gemacht hast?",
            "Hast du jemals eine wichtige Entscheidung getroffen, die du später bereut hast?",
            "Hast du jemals ein Geheimnis so lange bewahrt, dass es dich belastet hat?",
            "Was ist das Unverschämteste, was du jemals zu einem Fremden gesagt hast?",
            "Hast du jemals eine Beziehung vor deinen Freunden verheimlicht?",
            "Hast du jemals jemanden abgelehnt und es später bereut?",
            "Was war dein größter Fehler in einer vergangenen Beziehung?",
            "Hast du jemals jemanden aus Eifersucht schlecht behandelt?",
            "Was ist das Verrückteste, das du in deinem Schlafzimmer getan hast?",
            "Welches Verbrechen würdest du begehen, wenn du sicher wärst, nicht erwischt zu werden?",
            "Hast du jemals einen Partner beschimpft, obwohl du im Unrecht warst?",
            "Was war die schmutzigste Nachricht, die du jemals erhalten hast?",
            "Hast du jemals mit jemandem geflirtet, um deinen Ex eifersüchtig zu machen?",
            "Was ist der ungewöhnlichste Ort, an dem du jemals jemanden geküsst hast?",
            "Hast du jemals jemanden bewusst ignoriert, obwohl du wusstest, dass es ihn verletzt?",
            "Was ist der größte Unterschied zwischen dir und deinem Partner?",
            "Hast du jemals eine Beziehung nur zum Spaß geführt?",
            "Hast du jemals vorgegeben, jemand anderen zu sein, um jemanden zu beeindrucken?",
            "Was war das Gefährlichste, was du jemals aus Liebe getan hast?",
            "Hast du jemals eine Trennung bewusst herausgezögert, weil du Angst vor dem Alleinsein hattest?",
            "Was war das Dümmste, was du jemals für Geld getan hast?",
            "Hast du jemals ein Versprechen gebrochen, das du nie brechen wolltest?",
            "Was ist deine größte Unsicherheit in einer Beziehung?",
            "Hast du jemals etwas Unangenehmes über dich online gefunden?",
            "Was ist das Seltsamste, das du jemals über eine andere Person gedacht hast?",
            "Hast du jemals etwas gestohlen, ohne erwischt zu werden?",
            "Was ist das Peinlichste, das jemand über dich herausgefunden hat?",
            "Hast du jemals etwas getrunken oder genommen, das du später bereut hast?",
            "Was ist das Verrückteste, das du jemals unter Alkoholeinfluss getan hast?",
            "Was ist das Schlimmste, was du jemals in einer Beziehung gesagt hast?",
            "Hast du jemals jemandem hinter seinem Rücken schlecht über ihn gesprochen?",
            "Was war dein schlimmster Kater und was hast du dabei gemacht?",
            "Hast du jemals jemanden betrogen und es geheim gehalten?",
            "Was war das Teuerste, das du jemals gekauft hast, nur um jemanden zu beeindrucken?",
            "Hast du jemals eine Lüge erzählt, die dein Leben verändert hat?",
            "Hast du jemals jemandem aus purer Boshaftigkeit Schaden zugefügt?",
            "Was ist das Ungewöhnlichste, was dich an jemandem anzieht?",
            "Hast du jemals eine Beziehung beendet, ohne den wahren Grund zu nennen?",
            "Was ist deine größte Angst in einer Beziehung?",
            "Hast du jemals über deine Gefühle gelogen, um jemanden zu schützen?",
            "Was war die peinlichste Nachricht, die du jemals gesendet hast?",
            "Hast du jemals jemanden geküsst, ohne Gefühle für ihn zu haben?",
            "Was ist der schlimmste Rat, den du jemals gegeben hast?",
            "Hast du jemals etwas getan, nur weil alle anderen es auch getan haben?",
            "Was war die schlimmste Lüge, die du jemals erzählt hast?",
            "Hast du jemals jemanden heimlich beobachtet?",
            "Was war das Riskanteste, das du jemals im Bett gemacht hast?",
            "Hast du jemals etwas Illegales getan und es nie zugegeben?",
            "Was ist das Romantischste, das du jemals für jemanden getan hast?",
            "Hast du jemals jemanden verlassen, ohne es ihm zu erklären?",
            "Was war das Peinlichste, das dir auf einem Date passiert ist?",
            "Hast du jemals absichtlich jemanden verletzt, um dich besser zu fühlen?",
            "Was war der schlimmste Job, den du jemals hattest?",
            "Hast du jemals mit jemandem geflirtet, obwohl du wusstest, dass er vergeben ist?",
            "Was ist das Seltsamste, das du jemals im Bett gesagt hast?",
            "Hast du jemals ein Geheimnis verraten, obwohl du es versprochen hattest, es nicht zu tun?",
            "Was ist das Gefährlichste, das du jemals für jemanden getan hast?",
            "Hast du jemals jemanden ignoriert, nur um zu sehen, wie er reagiert?",
            "Was war der größte Fehler, den du in einer Beziehung gemacht hast?",
            "Hast du jemals jemanden geküsst, nur um einen anderen eifersüchtig zu machen?",
            "Was ist das Erotischste, das du jemals getan hast?",
            "Hast du jemals eine Affäre gehabt?",
            "Was war der schlechteste Kuss, den du jemals hattest?",
            "Hast du jemals einen Partner angelogen, um einen Streit zu vermeiden?",
            "Was ist das Ungewöhnlichste, das du über Sex gelernt hast?",
            "Hast du jemals vorgetäuscht, jemanden zu lieben?",
            "Was ist das Dümmste, das du jemals für Liebe getan hast?",
            "Hast du jemals jemanden nur aus Mitleid geküsst?",
            "Was war das Schlimmste, das du jemals nach einem Streit gesagt hast?"
        )
        val dareTasksGood = listOf(
            "Zeige dein letztes Bild auf deinem Handy.",
            "Führe einen 30-sekündigen Tanz vor.",
            "Schicke einem Freund eine unheimliche Nachricht.",
            "Lass dir von einem Mitspieler einen neuen Spitznamen geben.",
            "Zeichne ein Bild von deinem Mitspieler mit geschlossenen Augen.",
            "Lies den letzten Text, den du jemandem geschickt hast, laut vor.",
            "Rufe jemanden an und gestehe ihm deine 'Liebe' auf humorvolle Weise.",
            "Mache 20 Kniebeugen.",
            "Führe eine Runde Karaoke mit einem Lied deiner Wahl durch.",
            "Verwende dein Handy, um ein albernes Selfie zu machen und zeige es.",
            "Sprich die nächsten 10 Minuten mit einem ausgedachten Akzent.",
            "Iss einen Löffel Senf oder eine andere scharfe Zutat.",
            "Imitiere eine berühmte Persönlichkeit, bis jemand errät, wer es ist.",
            "Erzähle eine peinliche Geschichte über dich.",
            "Zeichne etwas auf deinen Körper mit einem Filzstift.",
            "Trage deine Socken auf den Händen für die nächsten 5 Minuten.",
            "Mach ein Selfie mit einer albernen Grimasse und poste es.",
            "Schicke einer zufälligen Person aus deinem Telefonbuch ein Emoji.",
            "Sing ein Lied, das dir peinlich ist, vor allen.",
            "Schicke einem Freund einen verwirrenden Text und erkläre ihn nicht.",
            "Führe einen Handstand (oder versuche es) für 30 Sekunden.",
            "Imitiere einen Tierlaut für die nächsten 2 Minuten.",
            "Lass jemanden dir eine unvorhersehbare Frage stellen und beantworte ehrlich.",
            "Führe einen Zungenbrecher vor, ohne einen Fehler zu machen.",
            "Erzähle eine Geschichte, die dich wirklich verlegen gemacht hat.",
            "Lasse dir von einem Mitspieler eine Herausforderung stellen.",
            "Gehe für die nächsten 5 Minuten auf allen Vieren.",
            "Zeichne ein Herz auf dein Gesicht und lasse es für den Rest des Spiels.",
            "Mache 15 Liegestütze.",
            "Lies die letzte Nachricht in deiner Chat-App laut vor.",
            "Singe deinen Lieblingssong auf alberne Weise.",
            "Sende einem Mitspieler eine lustige Sprachnachricht.",
            "Mach 10 Sit-Ups.",
            "Tausche für die nächsten 5 Minuten Kleidung mit jemandem.",
            "Lass jemanden dein Handy für 5 Minuten übernehmen.",
            "Sitze für die nächsten 2 Minuten verkehrt herum auf deinem Stuhl.",
            "Tanze mit geschlossenen Augen, bis die Musik aufhört.",
            "Lies den letzten Text auf deinem Handy laut vor.",
            "Schicke jemandem aus deinem Telefonbuch einen verrückten Text.",
            "Imitiere für die nächsten 2 Minuten ein Tier deiner Wahl.",
            "Male dir mit einem Stift ein Tattoo auf deine Hand.",
            "Verbringe die nächsten 5 Minuten mit verbundenen Augen.",
            "Rufe einen Freund an und versuche, ihm ein imaginäres Produkt zu verkaufen.",
            "Trinke einen Schluck Wasser, ohne die Hände zu benutzen.",
            "Mache so viele Hampelmänner, wie du kannst, in 30 Sekunden.",
            "Trage einen Hut aus Papier für den Rest des Spiels.",
            "Mache ein Video von dir, wie du wie ein Tier isst und poste es.",
            "Mache für die nächsten 5 Minuten eine tierische Bewegung nach.",
            "Zeichne ein Porträt von jemandem aus dem Raum.",
            "Trage dein T-Shirt für den Rest des Spiels rückwärts.",
            "Trinke einen Becher Wasser in weniger als 10 Sekunden.",
            "Führe einen Luftgitarre-Solo vor.",
            "Mache ein Selfie mit einer lustigen Grimasse und schicke es an einen Freund.",
            "Rufe jemanden aus deinem Telefonbuch an und singe für ihn.",
            "Versuche für die nächsten 2 Minuten nicht zu lachen, egal was passiert.",
            "Erfinde eine alberne Tanzroutine und führe sie vor.",
            "Sprich die nächsten 3 Minuten in Reimen.",
            "Tue so, als wärst du ein Roboter, bis du wieder an der Reihe bist.",
            "Erfinde ein peinliches Geheimnis und erzähle es allen.",
            "Zeichne ein Gesicht auf deine Hand und sprich damit für die nächsten 5 Minuten.",
            "Spiele für die nächsten 5 Minuten einen Rapper und mache Freestyle-Rap.",
            "Erkläre den Raum mit den Augen verbunden.",
            "Sprich wie ein Pirat für die nächsten 5 Minuten.",
            "Gib vor, ein Werbespot für ein Shampoo zu sein und führe es vor.",
            "Mach ein Geräusch, das niemand erraten kann, was es ist.",
            "Mach einen Tierlaut, bis jemand errät, welches Tier du imitiert hast.",
            "Versuche, auf einem Bein für 30 Sekunden zu stehen.",
            "Singe die Nationalhymne auf lustige Weise.",
            "Schicke eine seltsame Nachricht an jemanden aus deinem Handy.",
            "Sprich wie ein Roboter, bis du wieder an der Reihe bist.",
            "Mache 10 Squats ohne Pause.",
            "Verbringe die nächsten 2 Minuten damit, wie ein Huhn zu gehen.",
            "Zeige den letzten Chatverlauf auf deinem Handy.",
            "Führe eine 30-sekündige Komödie für alle vor.",
            "Singe ein Lied, als ob du es nicht magst.",
            "Mach ein Video von dir, wie du ein zufälliges Geräusch machst, und poste es.",
            "Führe ein Drama vor, in dem du die Hauptrolle spielst.",
            "Erzähle eine erfundene Geschichte über ein Missgeschick.",
            "Versuche, ohne Hände einen Löffel Wasser zu trinken.",
            "Male dein Gesicht mit einem Stift an und lasse es für 5 Minuten.",
            "Erfinde einen Witz und erzähle ihn der Gruppe.",
            "Mache ein Bild von einem Mitspieler und poste es ohne Kontext.",
            "Versuche, dich selbst zu kitzeln.",
            "Sprich die nächsten 5 Minuten nur in Fragen.",
            "Schreibe eine lustige Nachricht an eine zufällige Nummer.",
            "Versuche, dich für 10 Sekunden auf nur einem Bein zu balancieren.",
            "Zeichne mit verbundenen Augen ein Bild.",
            "Tue so, als würdest du ein Gespräch in einer fremden Sprache führen.",
            "Sprich für die nächsten 2 Minuten wie ein Sportkommentator.",
            "Nenne deine nächsten 5 Textnachrichten, ohne sie zu öffnen.",
            "Zeige das albernste Foto auf deinem Handy."
        )

        // Bibliothek 2: "Ihr kennt euch sehr gut, inkl. Intim"
        val truthTasksVeryGood = listOf(
            "Was war deine peinlichste sexuelle Erfahrung?",
            "Hast du jemals an jemanden gedacht, während du mit jemand anderem zusammen warst?",
            "Was ist deine größte sexuelle Fantasie?",
            "Hattest du jemals einen erotischen Traum über jemanden im Raum?",
            "Was war der ungewöhnlichste Ort, an dem du Sex hattest?",
            "Hast du jemals jemanden geküsst, nur um einen anderen eifersüchtig zu machen?",
            "Hast du jemals erotische Nachrichten geschickt?",
            "Hattest du jemals Sex mit jemandem, den du eigentlich nicht attraktiv fandest?",
            "Hast du jemals Sex mit jemandem gehabt, nur um dich besser zu fühlen?",
            "Was war der beste Kuss, den du jemals hattest?",
            "Was ist das Verrückteste, das du je im Bett getan hast?",
            "Was war dein peinlichster Moment während des Vorspiels?",
            "Hast du jemals etwas Intimes ausprobiert, das völlig schiefgegangen ist?",
            "Was ist das Erotischste, das dir jemals gesagt wurde?",
            "Hast du jemals an einen Ex-Partner gedacht, während du mit jemand anderem zusammen warst?",
            "Was war der seltsamste Ort, an dem du sexuelle Gedanken hattest?",
            "Hast du jemals Sex gehabt, während jemand anderes im Raum war?",
            "Hast du jemals ein erotisches Foto von dir selbst gemacht?",
            "Hast du jemals Sex an einem öffentlichen Ort gehabt?",
            "Hast du jemals darüber nachgedacht, mit jemandem im Raum zu schlafen?",
            "Was ist das Ungewöhnlichste, das dich erregt?",
            "Was ist das längste, das du jemals ohne Sex ausgekommen bist?",
            "Hast du jemals etwas im Bett vorgetäuscht?",
            "Hast du jemals jemandem deine tiefsten sexuellen Fantasien gestanden?",
            "Hattest du jemals einen erotischen Traum, der dich am nächsten Tag beeinflusst hat?",
            "Was ist das Peinlichste, das dir jemals beim Sex passiert ist?",
            "Hast du jemals darüber nachgedacht, jemand anderen zu küssen, während du in einer Beziehung warst?",
            "Was war der unerwartetste Moment, in dem du erregt warst?",
            "Was war das Wildeste, das du jemals während des Vorspiels gemacht hast?",
            "Hast du jemals jemanden geküsst und es sofort bereut?",
            "Was ist das Ungewöhnlichste, das du im Bett ausprobiert hast?",
            "Hast du jemals Sex in einem Auto gehabt?",
            "Was ist das Schmutzigste, das du jemals zu jemandem gesagt hast?",
            "Hast du jemals jemanden nackt gesehen, ohne dass er es wusste?",
            "Hast du jemals mit jemandem geflirtet, obwohl du in einer Beziehung warst?",
            "Was ist das Fetischartigste, das du jemals in Betracht gezogen hast?",
            "Was war der erotischste Moment, den du je hattest?",
            "Hast du jemals von einem Freund sexuell geträumt?",
            "Was ist deine größte sexuelle Unsicherheit?",
            "Hast du jemals einen erotischen Film angesehen und es bereut?",
            "Was ist das Verrückteste, das du jemals während eines Kusses gemacht hast?",
            "Was ist die peinlichste sexuelle Vorliebe, die du jemals gestanden hast?",
            "Hast du jemals jemandem eine intime Frage gestellt, die ihn schockiert hat?",
            "Hattest du jemals Sex an einem Ort, an dem du erwischt wurdest?",
            "Was ist das Tabuartigste, das dich sexuell erregt?",
            "Hast du jemals eine Liebesnacht bereut?",
            "Hast du jemals heimlich jemanden im Schwimmbad beobachtet?",
            "Was war der spannendste Moment deines Liebeslebens?",
            "Hast du jemals an jemanden gedacht, während du Sex hattest?",
            "Was ist deine verrückteste erotische Fantasie?",
            "Hast du jemals eine Beziehung wegen Sex beendet?",
            "Hattest du jemals eine erotische Begegnung mit einem Fremden?",
            "Was war das Ungewöhnlichste, das du jemals mit deinem Partner im Bett ausprobiert hast?",
            "Hast du jemals Sex an einem sehr öffentlichen Ort gehabt?",
            "Hattest du jemals eine sexuelle Fantasie, die du niemandem erzählt hast?",
            "Was war der wildeste Ort, an dem du jemals Sex hattest?",
            "Hast du jemals jemanden beim Umziehen beobachtet?",
            "Was war der schlimmste Moment, den du jemals während des Vorspiels hattest?",
            "Hast du jemals etwas Intimes in einem sozialen Netzwerk gepostet und es später bereut?",
            "Hattest du jemals eine heimliche sexuelle Begegnung?",
            "Was ist das Tabuartigste, das du jemals ausprobiert hast?",
            "Was war die peinlichste sexuelle Panne, die dir jemals passiert ist?",
            "Was ist der ungewöhnlichste Körperteil, den du erotisch findest?",
            "Hast du jemals Sex an einem ungewöhnlichen Ort geplant?",
            "Was war der ungewöhnlichste Gegenstand, den du jemals im Bett verwendet hast?",
            "Hast du jemals Sex mit jemandem gehabt, der viel älter oder jünger war?",
            "Hast du jemals von einer intimen Begegnung mit einem Freund geträumt?",
            "Was ist das riskanteste Spiel, das du jemals im Bett gespielt hast?",
            "Was ist das Ungewöhnlichste, das dich in einem romantischen Moment erregt hat?",
            "Hast du jemals eine sexuelle Fantasie, die du geheim hältst?",
            "Was war die längste Zeit, in der du ohne Sex warst?",
            "Hast du jemals jemanden während des Sexes namentlich falsch genannt?",
            "Was war der erotischste Moment, den du je im Freien erlebt hast?",
            "Was ist die ungewöhnlichste Fantasie, die du jemals hattest?",
            "Hast du jemals Sex an einem Arbeitsplatz gehabt?",
            "Was war der peinlichste Moment, den du während des Sexes hattest?",
            "Was ist das schmutzigste Geheimnis, das du jemals jemandem anvertraut hast?",
            "Hast du jemals Sex in einem Raum gehabt, in dem andere Leute anwesend waren?",
            "Hast du jemals etwas während des Sexes vorgetäuscht?",
            "Was war das Unangenehmste, das dir jemals im Schlafzimmer passiert ist?",
            "Hast du jemals über jemanden in diesem Raum fantasierte?",
            "Was ist das wildeste sexuelle Abenteuer, das du jemals erlebt hast?",
            "Hattest du jemals einen erotischen Traum über jemanden, den du persönlich kennst?",
            "Was war der ungewöhnlichste Wunsch, den dir jemals jemand im Bett geäußert hat?",
            "Hast du jemals daran gedacht, mit jemandem Sex zu haben, der vergeben war?",
            "Was war die unangenehmste Situation, in die du durch Sex geraten bist?",
            "Hast du jemals daran gedacht, einen Dreier zu haben?",
            "Was ist das Verrückteste, das du jemals getan hast, um jemanden sexuell zu beeindrucken?",
            "Hattest du jemals einen sexuellen Wunsch, der dir peinlich war?",
            "Was war das ungewöhnlichste erotische Erlebnis, das du je hattest?",
            "Was war der aufregendste Moment, den du jemals während eines Kusses erlebt hast?",
            "Hattest du jemals Sex mit jemandem, ohne wirklich Gefühle für ihn zu haben?",
            "Was war das Schamloseste, das du jemals in einem intimen Moment getan hast?",
            "Was ist deine ungewöhnlichste sexuelle Vorliebe?"
        )
        val dareTasksVeryGood = listOf(
            "Küsse jemanden im Raum auf den Mund.",
            "Ziehe ein Kleidungsstück deiner Wahl aus.",
            "Gib jemandem im Raum einen sinnlichen Kuss auf den Hals.",
            "Massiere die Schultern der Person zu deiner Rechten für 2 Minuten.",
            "Flüstere jemandem im Raum etwas Anzügliches ins Ohr.",
            "Lecke über die Finger einer Person deiner Wahl.",
            "Gib jemanden im Raum einen sexy Lapdance für 30 Sekunden.",
            "Zeige der Gruppe einen intimen Tanz, den du noch nie vorgeführt hast.",
            "Lasse dir von einem Mitspieler ein erotisches Kompliment machen.",
            "Führe ein erotisches Rollenspiel für 1 Minute vor.",
            "Spiele 'Wahrheit oder Pflicht' nur mit 'Pflicht' für die nächsten 3 Runden.",
            "Ziehe ein weiteres Kleidungsstück aus.",
            "Mache ein sexy Selfie und zeige es jemandem im Raum.",
            "Lecke jemanden an der Wange.",
            "Erfinde eine intime Geschichte und erzähle sie der Gruppe.",
            "Imitiere 30 Sekunden lang, als wärst du beim Vorspiel.",
            "Flirte mit jemandem im Raum, als würdest du versuchen, ihn zu verführen.",
            "Streichele die nackte Haut einer Person im Raum für 1 Minute.",
            "Simuliere eine erotische Szene aus einem Film deiner Wahl.",
            "Gib jemandem im Raum einen sanften Kuss auf die Lippen.",
            "Erzähle der Gruppe von deinem erotischsten Traum.",
            "Lecke den Hals einer Person deiner Wahl.",
            "Lass jemanden aus dem Raum einen Kuss auf deine nackte Haut geben.",
            "Singe ein erotisches Lied oder mache eine erotische Geräuschkulisse.",
            "Ziehe deine Socken aus und gib sie jemandem im Raum.",
            "Tue so, als würdest du jemanden im Raum verführerisch ausziehen.",
            "Erzähle der Gruppe von deiner peinlichsten erotischen Begegnung.",
            "Zeige eine erotische Pose und halte sie für 10 Sekunden.",
            "Küsse den Hals einer Person im Raum.",
            "Erzähle von deinem ersten Kuss und wie es sich angefühlt hat.",
            "Tue so, als würdest du jemanden für einen erotischen Moment vorbereiten.",
            "Streichele den Bauch einer Person deiner Wahl.",
            "Lass dir von jemandem im Raum eine sexy Nachricht vorlesen.",
            "Flirte mit jemandem im Raum, bis alle zustimmen, dass es genug ist.",
            "Massiere den unteren Rücken einer Person im Raum.",
            "Tue so, als würdest du eine Person im Raum leidenschaftlich küssen.",
            "Erfinde eine erotische Geschichte über jemanden im Raum.",
            "Mache für 1 Minute erotische Geräusche.",
            "Lass eine Person deiner Wahl dir einen Kuss auf den Bauch geben.",
            "Ziehe ein weiteres Kleidungsstück aus und lege es in die Mitte des Raumes.",
            "Küsse jemanden im Raum leidenschaftlich auf die Lippen.",
            "Erfinde eine erotische Fantasie und teile sie mit der Gruppe.",
            "Lass dir von jemandem eine erotische Szene vorspielen.",
            "Tue so, als würdest du einen intimen Moment genießen.",
            "Lecke jemanden im Raum an der Hand.",
            "Mache für die nächste Runde mit verbundenen Augen weiter.",
            "Küsse die Person zu deiner Rechten auf die Lippen.",
            "Zeige der Gruppe deine beste verführerische Bewegung.",
            "Ziehe deine Schuhe aus und massiere die Füße einer Person.",
            "Tue so, als würdest du eine Person im Raum für einen erotischen Moment verführen.",
            "Flüstere der Person neben dir ein Geheimnis über deine erotischen Wünsche zu.",
            "Erfinde ein sinnliches Gedicht und trage es vor.",
            "Massiere für 2 Minuten die Füße einer Person im Raum.",
            "Lecke den Hals der Person neben dir.",
            "Gib jemanden im Raum einen Kuss auf den nackten Rücken.",
            "Führe eine erotische Szene aus einem Film deiner Wahl vor.",
            "Schicke einem Freund eine sexy Nachricht, die jemand anderes aus dem Raum auswählt.",
            "Küsse jemanden auf die nackte Schulter.",
            "Lecke über die Lippen einer Person deiner Wahl.",
            "Tue so, als würdest du die nächste Person, die du siehst, verführen.",
            "Gib einem Mitspieler einen langsamen, sinnlichen Tanz.",
            "Flüstere jemanden im Raum anzügliche Worte ins Ohr.",
            "Streichele die Beine der Person neben dir für 2 Minuten.",
            "Lecke die Hand einer Person, die neben dir sitzt.",
            "Gib jemandem einen Kuss auf den Nacken.",
            "Spiele eine verführerische Szene vor und lasse die anderen raten, was du tust.",
            "Tue so, als würdest du jemanden verführen und führe es für 1 Minute vor.",
            "Gib jemanden im Raum einen langsamen Kuss auf die Lippen.",
            "Lass eine Person deiner Wahl dich an einem intimen Ort küssen.",
            "Gib jemandem einen Kuss auf den nackten Fuß.",
            "Erzähle von deiner ungewöhnlichsten sexuellen Erfahrung.",
            "Führe einen erotischen Tanz vor und lass die anderen bewerten.",
            "Lecke die nackte Haut der Person zu deiner Linken.",
            "Erzähle eine erotische Fantasie, die du nie jemandem erzählt hast.",
            "Führe eine erotische Bewegung vor, die du im Bett machst.",
            "Küsse jemanden auf die nackte Stirn.",
            "Lecke den Bauch einer Person deiner Wahl.",
            "Spiele eine erotische Szene vor, die du schon immer ausprobieren wolltest.",
            "Lass jemanden im Raum ein Kleidungsstück von dir ausziehen.",
            "Lecke über die Ohren einer Person im Raum.",
            "Massiere die nackten Schultern einer Person im Raum.",
            "Gib jemanden im Raum einen langsamen, sinnlichen Kuss auf die Lippen.",
            "Führe eine erotische Fantasie vor, die jemand anderes wählt.",
            "Mache eine erotische Geräuschkulisse für 1 Minute.",
            "Erfinde ein erotisches Szenario und führe es vor."
        )

        // Wähle die entsprechenden Aufgaben basierend auf der gewählten Bibliothek
        val truthTasks = if (useFirstLibrary) truthTasksGood else truthTasksVeryGood
        val dareTasks = if (useFirstLibrary) dareTasksGood else dareTasksVeryGood

        // Logik für Wahrheit und Pflicht Buttons
        findViewById<Button>(R.id.btn_truth).setOnClickListener {
            val randomTruth = truthTasks.random() // Zufällige Aufgabe aus der Wahrheit Liste
            tvQuestion.text = randomTruth
        }

        findViewById<Button>(R.id.btn_dare).setOnClickListener {
            val randomDare = dareTasks.random() // Zufällige Aufgabe aus der Pflicht Liste
            tvQuestion.text = randomDare
        }
    }

    // Funktion, die den Bestätigungsdialog zum Beenden der App anzeigt
    private fun showExitConfirmation() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("App beenden")
        builder.setMessage("Möchtest du die App wirklich beenden?")

        builder.setPositiveButton("Ja") { dialog, _ ->
            finish() // Beendet die App
        }

        builder.setNegativeButton("Nein") { dialog, _ ->
            dialog.dismiss() // Schließt den Dialog, ohne die App zu beenden
        }

        val dialog: android.app.AlertDialog = builder.create()
        dialog.show() // Zeigt den Dialog an
    }
    fun onDonateClick(view: View) {
        val donateUrl = "https://paypal.me/TruthorDareFSK18" // Ersetze mit deinem Spendenlink
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(donateUrl))
        startActivity(intent)
    }
}