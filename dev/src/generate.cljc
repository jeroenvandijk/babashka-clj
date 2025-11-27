;#!/usr/bin/env bb

;; Usage:
;; bb -f dev/src/generate.cljc --stdout

;; Other ways to test, but with slightly different output..
;; echo $(cd dev; bb -m generate --stdout)
;; echo $(cd dev; clj -M -m generate --stdout)
(ns generate
  (:require
   [babashka.cli :as cli]
   [clojure.java.shell :refer [sh]]
   [clojure.string :as str]
   [rewrite-clj.zip :as z]))


;; Optionally pin Clojure version in order to supported alpha features
(def required-clojure-version nil #_"1.12.3")


(defn print-deps-edn []
  (let [bb-deps-str (:out (sh "bb" "print-deps"))]
    ;; Respect the order output of `bb print-deps`
    (-> bb-deps-str
        (z/of-string)
        (cond->
          required-clojure-version
          (z/postwalk (fn select [zloc] (= 'org.clojure/clojure (z/sexpr zloc)))
                      (fn visit [zloc] (-> zloc
                                          (z/right)
                                          (z/edit assoc :mvn/version required-clojure-version)
                                          (z/left)))))
        (z/string)
        (str/replace (str/re-quote-replacement "{:deps")
                     (str
                      ";; --- DO NOT EDIT - FILE GENERATED FROM dev/generate.clj ---\n"
                      ";;\n"
                      (str ";; Deps of Babashka version " (System/getProperty "babashka.version") "\n")
                      ";;\n\n"

                      "{:paths [\"src\"]\n :deps"))
        (println))))


(defn -main [& args]
  (let [{:keys [stdout] :as opts} (cli/parse-opts args)]
    (if stdout
      (print-deps-edn)
      (spit "deps.edn" (with-out-str (print-deps-edn))))))


#?(:bb
   (apply -main *command-line-args*))
