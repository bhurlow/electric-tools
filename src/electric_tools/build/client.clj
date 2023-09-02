(ns electric-tools.build.client
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

  ;; client build would take input namespace as target
;; (defn build-client
;;   "Prod optimized ClojureScript client build. (Note: in dev, the client is built 
;; on startup)"
;;   [{:keys [optimize debug verbose version]
;;     :or {optimize true, debug false, verbose false, version version}}]
;;   (println "Building client. Version:" version)
;;   (shadow-server/start!)
;;   (shadow-api/release :prod {:debug debug,
;;                              :verbose verbose,
;;                              :config-merge [{:compiler-options {:optimizations (if optimize :advanced :simple)}
;;                                              :closure-defines {'hyperfiddle.electric-client/VERSION version}}]})
;;   (shadow-server/stop!))

  )