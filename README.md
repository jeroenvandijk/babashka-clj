# Babashka-clj

Write and test [Babashka](https://github.com/babashka/babashka) code from a Clojure repl.

Create a directory with the following deps.edn:
```clojure
{:paths ["src"]
 :deps {}

 :aliases
 {:nREPL
  {:extra-deps
   {nrepl/nrepl {:mvn/version "1.0.0"}}}

  :babashka-clj
  {:extra-deps {jeroenvandijk/babashka-clj
                {:git/url "https://github.com/jeroenvandijk/babashka-clj"
                 :git/sha "38ca03ce13bd4c116ec0ce6e148b1b35d0484582"}}}}}
```
And use the following command to start a Clojure (n)repl with all the Babashka namespaces available in Clojure:
```
clj -A:babashka-clj -M:nREPL -m nrepl.cmdline --interactive

```

## Maintenance

Update deps and README

```
bb -f dev/src/generate.cljc > deps.edn
```

Update `:git/sha` in this file.