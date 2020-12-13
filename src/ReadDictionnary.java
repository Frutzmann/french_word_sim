import java.util.HashMap;
////ETAPES A SUIVRE POUR LECTURE DU FICHIER ET INITIALISATION DES HASHMAP
public interface ReadDictionnary {
    void read(String filename);
    void EveryLetterFilter(String s);
    void FirstLetterFilter(String s);
    void suitLetterFilter(String s);
}
