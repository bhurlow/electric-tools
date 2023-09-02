(ns electric-cli.build.client
  (:require [shadow.cljs.devtools.api :as shadow-api]))

(defn build-config [main-ns]
  {:target :browser
   :output-dir "resources/public/js"
   :asset-path "/js"
   :module-hash-names true
   :modules {:main {:entries [main-ns]
                    :init-fn (symbol (str main-ns "/start!"))}}})

(defn build-client []
  ;; (shadow-api)
  )