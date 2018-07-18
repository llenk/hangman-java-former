public class Word {
  char[] arrayWordFull;
  char[] arrayWordCurrent;

  public Word(String word){ 
    this.arrayWordFull = word.toCharArray();
    this.arrayWordCurrent = new char[word.length()];
  }

  public String getWord() {
    return arrayToWord(arrayWordCurrent);
  }

  public static String arrayToWord(char[] arrayWord) {
    String word = "";
    for (int i = 0; i < arrayWord.length; i++) {
      if (arrayWord[i] == 0) {
        word += "_";
      }
      else {
        word += arrayWord[i];
      }
    }
    return word;
  }

}