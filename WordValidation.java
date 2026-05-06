import java.io.File;
import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Store valid words and generate suggestions for misspelled words.
 */
public class WordValidation implements SpellingOperations{

  private HashSetDictionary dictionary;
  public WordValidation(String filename) {
    dictionary = new HashSetDictionary();
    Scanner file = null;

    try {
      file = new Scanner(new File(filename));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);
    }

    while (file.hasNextLine()) {
      String line = file.nextLine();
      if (isAlphaNumeric(line)) {
        dictionary.add(line);
      }
    }

    file.close();
  }



  public boolean isAlphaNumeric(String s) {
    String pattern = "^[a-zA-Z0-9']*$";
    return s.matches(pattern);
}
 

  /**
   * Check whether the dictionary contains a word.
   *
   * @param query the word to check
   * @return true if the word is in the dictionary
   */


  public boolean containsWord(String query) {
    if (this.dictionary.contains(query.toLowerCase())){
      return true;
    }
  else {
    return false;
  }
  }

  /**
   * Generate valid near misses for a query word.
   *
   * @param query the word to check
   * @return a set of valid suggestions that are one edit away
   */


  public Set<String> nearMisses(String query) {
    query = query.toLowerCase();
// catttle 
    Set<String> mySuggestions = new HashSet<String>();
    // we need to have words that are one edit away 
    //Deletion : we are going to loop through the word
    // It is going to loop thru the word and check the dictionary if it contains the word, if it does then the boolean will compare it with the word and see what letter isnt here, then remove it
    

    //Looping thru the word 
    for (int i = 0; i < query.length(); i++) {
      // i was stuck on how to get this so i used ai on this logic
      //here is my understandig : we break down the word at the index i (if it is an extra word we break down the word)
      //"helllo" --> hell + o = hello 
      String comparisonString = query.substring(0, i) + query.substring(i + 1);
      //checks if the dictionary has this word
      if (dictionary.contains(comparisonString)) {
      //if it is a real word then we will add it to our suggestions 
          mySuggestions.add(comparisonString);
      }
  }

    //Insertion
    for (int i = 0; i < query.length(); i++) {
      for(char alphabet = 'a'; alphabet <='z'; alphabet++ ){
        String comparisonString = query.substring(0,i) + String.valueOf(alphabet) + query.substring(i);
        if(dictionary.contains(comparisonString)){
          mySuggestions.add(comparisonString);
        }
        


      }
  

    }

    //substitution
    for (int i = 0; i < query.length(); i++) {
      for(char alphabet = 'a'; alphabet <='z'; alphabet++ ){
        String comparisonString = query.substring(0,i) + String.valueOf(alphabet) + query.substring(i+1);
        if(dictionary.contains(comparisonString)){
          mySuggestions.add(comparisonString);
        }
      }
    }

    //transpositions
    for (int i = 0; i < query.length() - 1; i++) { // Fix: was query.length(), caused index out of bounds
      char swapIndexOne = query.charAt(i);
      char swapIndexTwo = query.charAt(i+1);

      String swapStringOne = String.valueOf(swapIndexOne);
      String swapStringTwo = String.valueOf(swapIndexTwo);

      String comparisonString = query.substring(0,i) + swapStringTwo + swapStringOne + query.substring(i+2);
      if(dictionary.contains(comparisonString)){
        mySuggestions.add(comparisonString);
      }



    }



  // Splits**: Divide the word into two legal words, recorded as one string with a space in the middle.
  //  Example: **cattell** -> **cat tell**




    for (int i = 1; i < query.length(); i++) {
      String firstPart = query.substring(0, i);
      String lastPart = query.substring(i);       
      if (dictionary.contains(firstPart)) {
          if (dictionary.contains(lastPart)) {
              String comparisonString = firstPart + " " + lastPart;  
              mySuggestions.add(comparisonString);
          }
      }
    }


    
    return mySuggestions;
  }

  public static void main(String[] args) {
    WordValidation object = new WordValidation("words.txt");
    System.out.println(object.nearMisses("cattell"));
    

    

    
    
    
  }
}