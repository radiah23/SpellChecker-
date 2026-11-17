# Spell Checker with Near-Miss Suggestions (Java)

**Checks words against a dictionary stored in a hash set and suggests corrections that are one edit away.**

![Java](https://img.shields.io/badge/Java-ED8B00?logo=openjdk&logoColor=white)
![Data Structures](https://img.shields.io/badge/topic-hashing-blue)

Built for the Data Structures course at Smith College.

---

## How it works

`WordValidation` loads a word list into a hash-set dictionary, so each lookup takes O(1) time on average. For a misspelled word, `nearMisses(query)` generates every candidate one edit away and keeps the ones that are real words:

| Edit type | Example |
|---|---|
| **Deletion**: remove one letter | `helllo` → `hello` |
| **Insertion**: add one letter `a`–`z` before any position | `helo` → `hello` |
| **Substitution**: replace one letter | `hallo` → `hello` |
| **Transposition**: swap two adjacent letters | `hlelo` → `hello` |
| **Split**: break into two valid words | `cattell` → `cat tell` |

For a word of length *n* this generates roughly 55*n* candidates (26 insertions and 26 substitutions per position, plus deletions, swaps and splits), each checked in constant time.

## Key methods

| Method | Purpose |
|---|---|
| `WordValidation(String filename)` | Reads a word list, one word per line, into the dictionary |
| `containsWord(String query)` | Is this a correctly spelled word? |
| `nearMisses(String query)` | Returns a `Set<String>` of suggested corrections |
| `isAlphaNumeric(String s)` | Filters out malformed dictionary entries |

## Running it

```bash
javac *.java
java WordValidation          # expects words.txt in the same folder
# [cat tell, ...]
```

> **Note:** the course provided the `SpellingOperations` interface, the `HashSetDictionary` class and the `words.txt` dictionary. They are not in this repo, so add them to the same folder before compiling.

## Concepts practiced

Hash sets and constant-time lookup · string manipulation · edit-distance candidate generation · file I/O

## Author

**Radiah Khan** · [Portfolio](https://radiah23.github.io)
