(ns babashka.deps
  (:require [clojure.repl.deps]))


(defn add-deps [{:keys [deps] :as opts}]
  (if (empty? (dissoc opts :deps))
    (clojure.repl.deps/add-libs deps)
    ;; Slower option ->
    (clojure.repl.deps/sync-deps opts)))
