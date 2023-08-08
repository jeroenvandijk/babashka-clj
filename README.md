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
  {:extra-deps {org.clojure/clojure {:mvn/version "1.12.0-alpha4"} ;; For babashka.deps/add-deps support
                jeroenvandijk/babashka-clj
                {:git/url "https://github.com/jeroenvandijk/babashka-clj"
                 :git/sha "5758329b4838b1c2db03d647cf7d46a9ae3d14c0"}}}}}
```
And use the following command to start a Clojure (n)repl with all the Babashka namespaces available in Clojure:
```
clj -A:babashka-clj -M:nREPL -m nrepl.cmdline --interactive

```
